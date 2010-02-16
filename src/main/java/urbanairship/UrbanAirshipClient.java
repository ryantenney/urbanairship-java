
package urbanairship;

import urbanairship.notifications.*;

import java.util.*;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
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
	private static final String CHARSET = "UTF-8";
	private boolean production = false;

	public UrbanAirshipClient(boolean isProduction)
	{
		this.production = isProduction;
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
		return get(DeviceToken.class, "/api/device_tokens/" + deviceToken); 
	}
	
	protected HttpGet createHttpGet(String path)
	{
		HttpGet get = new HttpGet(this.getUrlForPath(path));
		return get;
	}

	public void sendPushNotifications(Push... p)
	{
		if (p.length == 0)
		{
			throw new IllegalArgumentException("parameter p");
		}
		else if (p.length == 1)
		{
			// single push notification
			// todo
		}
		else
		{
			// batch of push notifications
			// todo
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
	
	protected <T> T post(final String path, final Object obj, final Class<T> responseType)
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

	protected HttpEntity toJsonEntity(final Object obj)
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
		// TODO : code here
	}

	protected <T> T fromJson(HttpResponse rsp, Class<T> clazz)
	{
		try
		{
			String responseBody = EntityUtils.toString(rsp.getEntity());
			return fromJson(responseBody, clazz);
		}
		catch (RuntimeException rtex)
		{
			throw rtex;
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
			HttpResponse rsp = getHttpClient().execute(method);
			checkResponse(rsp);
			return rsp;
		}
		catch (RuntimeException rtex)
		{
			throw rtex;
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

	public List<Feedback> getFeedback(final long since)
	{
		if (since < 0)
		{
			throw new IllegalArgumentException("since parameter: " + since);
		}
		
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(since);
		return getFeedback(c);
	}
	
	public FeedbackList getFeedback(final Calendar since)
	{
		FeedbackList feedbackList = get(
						FeedbackList.class,
						"/api/device_tokens/feedback/",
						"since", 
						ISO8601.toString(since));
		
		return feedbackList;
	}

	protected <T> T get(final Class<T> clazz, final String path, final String... parameters)
	{
		HttpGet get = createHttpGet(path);
		
		HttpResponse rsp = this.execute(get);
		
		return fromJson(rsp, clazz);
	}
	
	protected <T> String toJson(final T object)
	{
		if (object == null)
		{
			throw new NullPointerException("object parameter is null");
		}
		
		Gson gson = GsonFactory.create();
		return gson.toJson(object);
	}
	
	protected <T> T fromJson(final String json, final Class<T> clazz)
	{
		Gson gson = GsonFactory.create();
		return gson.fromJson(json, clazz);
	}

	protected void delete(final String path)
	{
		HttpDelete delete = createHttpDelete(path);
		execute(delete);
	}

	private HttpDelete createHttpDelete(final String path)
	{
		HttpDelete delete = new HttpDelete(getUrlForPath(path));
		return delete;
	}
}
