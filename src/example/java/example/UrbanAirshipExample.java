
package example;

import java.util.Calendar;

import urbanairship.*;

public class UrbanAirshipExample 
{

	public void doSomething()
	{
		
		UrbanAirshipClient client = new UrbanAirshipClient("urbanUsername", "urbanPassword");
		
		Device device = new Device();
		
		device.setiOSDeviceToken("iosDeviceToken1");
		device.setAlias("giltUser58");
		
		device.addTag("idaho_residents");
		device.addTag("rebecca_black_fans");
		
		client.register(device);
		
		Push p = new Push();
		
		p.addTag("rebecca_black_fans");
		
		p.setMessage("Yesterday was Thursday. Today it is Friday.");
		
		Calendar scheduleFor = Calendar.getInstance();
		scheduleFor.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		
		p.addScheduleFor(scheduleFor);
		
		client.sendPushNotifications(p);
		
	}
}
