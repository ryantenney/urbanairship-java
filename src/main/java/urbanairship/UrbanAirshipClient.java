
package urbanairship;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

public class UrbanAirshipClient
{
	private static final String CHARSET = "UTF-8";
	private boolean production = false;
	private String username;
	private String password;

	public UrbanAirshipClient(boolean isProduction, String username, String password)
	{
		this.production = isProduction;
		this.username = username;
		this.password = password;
	}
	
	public List<String> getTags()
	{
		TagsResponse resp = get(TagsResponse.class, "/api/tags");

		if (resp == null)
		{
			return new ArrayList<String>();
		}
		else
		{
			return resp.tags;
		}
	}
	
	public void addTagToDevice(String deviceToken, String tag)
	{
		if (tag.length() > 255)
		{
			throw new IllegalArgumentException("maximum tag length is 255. Tag: " + tag);
		}
		
		put("/api/device_tokens/" + encode(deviceToken) + "/tags/" + encode(tag));
	}
	
	public void removeTagFromDevice(String deviceToken, String tag)
	{
		delete("/api/device_tokens/" + encode(deviceToken) + "/tags/" + encode(tag));
	}

	public List<String> getTagsForDevice(String deviceToken)
	{
		TagsResponse resp = get(TagsResponse.class, "/api/device_tokens/" + encode(deviceToken) + "/tags");
		
		if (resp == null)
		{
			return new ArrayList<String>();
		}
		else
		{
			return resp.tags;
		}
	}
	
	public void register(DeviceToken dt)
	{
		String token = dt.getToken();
		put("/api/device_tokens/" + encode(token), dt);
	}
	

	protected void put(String path)
	{
		put(path, null);
	}
	
	protected void put(String path, Object requestBodyObject)
	{
		HttpPut put = createHttpPut(path);
		
		if (requestBodyObject != null)
		{
			put.setEntity(toJsonEntity(requestBodyObject));
		}
		
		execute(put);
	}
	
	
	protected void post(String path, Object requestBodyObject)
	{
		post(path, requestBodyObject, null);
	}
	
	protected HttpPut createHttpPut(String path)
	{
		HttpPut put = new HttpPut(getUrlForPath(path));
		return put;
	}

	public DeviceToken getDeviceToken(String deviceToken)
	{
		return get(DeviceToken.class, "/api/device_tokens/" + encode(deviceToken)); 
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
			post("/api/push", p[0]);
		}
		else
		{
			// batch of push notifications
			post("/api/push/batch", p);
		}
	}
	
	public void cancelScheduledNotifications(String... scheduledNotificationUrls)
	{
		Map<String, String[]> cancelMap = new HashMap<String, String[]>();
		cancelMap.put("cancel", scheduledNotificationUrls);
		
		post("https://go.urbanairship.com/api/push/scheduled", cancelMap);
		
	}
	
	public void broadcast(Broadcast b)
	{
		post("/api/push/broadcast", b);
	}
	
	protected <T> T post(final String path, final Object obj, final Class<T> responseType)
	{
		
		HttpPost post = createHttpPost(path);
		
		if (obj != null)
		{
			post.setEntity(toJsonEntity(obj));
		}
		
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
		
		StatusLine status = response.getStatusLine();
		
		int statusCode = status.getStatusCode();
		
	
		if (statusCode == 404)
		{
			throw new NotFoundException();
		}
		else if ( (statusCode < 200) || (statusCode > 299) )
		{
			
			StringBuilder msg = new StringBuilder();
			
			// todo : improve msg by including more details (URL, headers, etc)
			
			msg.append("statusCode=" + statusCode);
			
			try
			{
				String responseBody = EntityUtils.toString(response.getEntity());
				msg.append(", responseBody=" + responseBody);
			}
			catch (Exception ignored)
			{
				// ignored
			}
			
			
			throw new RuntimeException("unexpected response: " + msg);
		}
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
		DefaultHttpClient client = new DefaultHttpClient();
		
		CredentialsProvider credProvider = new BasicCredentialsProvider();
		
		credProvider.setCredentials(
		    new AuthScope(getHostname(), AuthScope.ANY_PORT), 
		    new UsernamePasswordCredentials(getUsername(), getPassword()));
		
		client.setCredentialsProvider(credProvider);
		
		return client;
	}

	protected String getUsername()
	{
		return this.username;
	}

	protected String getPassword()
	{
		return this.password;
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

	protected String encode(String s)
	{
		try
		{
			return URLEncoder.encode(s, "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			throw new RuntimeException(e);
		}
	}

}
