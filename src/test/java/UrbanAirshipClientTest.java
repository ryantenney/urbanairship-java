
import org.testng.annotations.*;

import urbanairship.UrbanAirshipClient;
import urbanairship.notifications.DeviceToken;

public class UrbanAirshipClientTest
{
	@Test
	public void client()
	{
		UrbanAirshipClient client = new UrbanAirshipClient(true, "user1", "pass1");
		
		DeviceToken dtoken = new DeviceToken();
		dtoken.setToken("dtoken123-" + System.currentTimeMillis());
		
		client.create(dtoken);
	}
}
