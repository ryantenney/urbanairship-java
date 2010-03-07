
import org.testng.annotations.*;

import urbanairship.*;

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
	
}
