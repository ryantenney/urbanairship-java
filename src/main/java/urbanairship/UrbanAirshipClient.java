
package urbanairship;

import urbanairship.notifications.*;

import java.util.*;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

public class UrbanAirshipClient
{
	private static final String PUSH_BATCH_URI = "/api/push/batch/";
	private static final String CHARSET = "UTF-8";
	private boolean production = false;

	public UrbanAirshipClient(boolean isProduction)
	{
		this.setProduction(isProduction);
	}
	
	public void create(DeviceToken dt)
	{
		String token = dt.getToken();
		put("/api/device_tokens/" + token, dt);
	}
	
	protected void put(String path, Object requestBodyObject)
	{
		HttpPut put = createHttpPut(path);
		
		put.setEntity(toJsonEntity(requestBodyObject));
		
		execute(put);
	}
	
	
	protected HttpPut createHttpPut(String path)
	{
		HttpPut put = new HttpPut(getUrlForPath(path));
		return put;
	}

	public DeviceToken getDeviceToken(String deviceToken)
	{
		return null; // todo
	}
	
	public void createPushNotifications(Push... p)
	{
		// todo
		if (p.length == 0)
		{
			throw new IllegalArgumentException("todo");
		}
		else if (p.length == 1)
		{
			// single push notification
		}
		else
		{
			// batch of push notifications
			
		}
	}
	
	public void cancelScheduledNotifications(String... scheduledNotifications)
	{
		// todo 
	}
	
	public void broadcast(Broadcast b)
	{
		post("/api/push/broadcast",
				b, 
				null); 
	}
	
	public void setProduction(boolean b)
	{
		this.production  = b;
	}
	
	protected <T> T post(String path, Object obj, Class<T> responseType)
	{
		
		HttpPost post = createHttpPost(path);
		
		post.setEntity(toJsonEntity(obj));
		
		HttpResponse response = execute(post);

		checkResponse(response);
		
		if (responseType == null)
		{
			return null;
		}
		else
		{
			return fromJson(response, responseType);
		}
	}

	protected HttpEntity toJsonEntity(Object obj)
	{
		String json = toJson(obj);
		
		HttpEntity e = null;
		
		try
		{
			e = new StringEntity(json, CHARSET)
			{
				@Override
				public Header getContentType()
				{
					Header h = new BasicHeader("Content-Type", "application/json");
					return h;
				}
			};
			return e;
		}
		catch (Exception ex)
		{
			throw new RuntimeException(ex);
		}
		
	}

	protected void checkResponse(HttpResponse response)
	{
		// TODO Auto-generated method stub
	}

	protected <T> T fromJson(HttpResponse rsp, Class<T> clazz)
	{
		try
		{
			String responseBody = EntityUtils.toString(rsp.getEntity());
			return fromJson(responseBody, clazz);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	protected HttpResponse execute(HttpRequestBase method)
	{
		try
		{
			return getHttpClient().execute(method);
		}
		catch (Exception ex)
		{
			throw new RuntimeException(ex);
		}
	}

	protected HttpClient getHttpClient()
	{
		HttpClient client = new DefaultHttpClient();
		return client;
	}

	private HttpPost createHttpPost(String path)
	{
		String url = getUrlForPath(path);
		
		HttpPost post = new HttpPost(url);
		
		return post;
	}

	protected String getUrlForPath(String path)
	{
		return "https://" 
				+ getHostname()
				+ path;
	}

	protected String getHostname()
	{
		return "go.urbanairship.com";
	}
	
	protected boolean isProduction()
	{
		return this.production;
	}

	public List<Feedback> getFeedback(long since)
	{
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(since);
		return getFeedback(c);
	}
	
	public FeedbackList getFeedback(Calendar since)
	{
		FeedbackList feedbackList = get(
						FeedbackList.class,
						"/api/device_tokens/feedback/",
						"since", 
						ISO8601.toString(since));
		
		return feedbackList;
	}

	protected <T> T get(Class<T> clazz, String path, String... parameters)
	{
		return null;
	}
	
	protected <T> String toJson(T object)
	{
		Gson gson = GsonFactory.create();
		return gson.toJson(object);
	}
	
	protected <T> T fromJson(String json, Class<T> clazz)
	{
		Gson gson = GsonFactory.create();
		return gson.fromJson(json, clazz);
	}

	protected void delete(String path)
	{
		HttpDelete delete = createHttpDelete(path);
		execute(delete);
	}

	private HttpDelete createHttpDelete(String path)
	{
		HttpDelete delete = new HttpDelete(getUrlForPath(path));
		return delete;
	}
}
