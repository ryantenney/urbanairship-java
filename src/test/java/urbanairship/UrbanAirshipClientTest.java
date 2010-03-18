
package urbanairship;

import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.*;

import com.google.gson.Gson;

public class UrbanAirshipClientTest
{
	private String username = "username";
	private String password = "password";
	
	@BeforeTest
	public void setUp()
	{
		String usernameProperty = (String) System.getProperties().get("urbanairship.java.username");
		if (usernameProperty != null)
		{
			username = usernameProperty;
		}
		
		String passwordProperty = (String) System.getProperties().get("urbanairship.java.password");
		if (passwordProperty != null)
		{
			password = passwordProperty;
		}
	}
	
	@Test
	public void client()
	{
		UrbanAirshipClient client = new UrbanAirshipClient(true, username, password);
		
		DeviceToken dtoken = new DeviceToken();
		dtoken.setToken("todo");
		
		client.register(dtoken);
	}
	
	@Test
	public void json()
	{
		Calendar c = Calendar.getInstance();

		c.add(Calendar.DAY_OF_YEAR, 1);
		
		Broadcast bcast = new Broadcast();
		bcast.setScheduleFor(c);

		Assert.assertNotNull(bcast.getScheduleFor());
		
		Gson gson = GsonFactory.create();
		
		String json = gson.toJson(bcast);
		
		Assert.assertNotNull(json);

		System.out.println(json);
		
		Broadcast bcast2 = gson.fromJson(json, Broadcast.class);
		
		Assert.assertNotNull(bcast2);
		
		Assert.assertNotNull(bcast2.getScheduleFor());
		
		Assert.assertEquals(bcast.getScheduleFor().getTimeInMillis(), 
							bcast2.getScheduleFor().getTimeInMillis());
		
		
	}
	
}
