
package example;

import java.util.Calendar;

import urbanairship.*;

public class UrbanAirshipExample 
{

	public void doSomething()
	{
		
		UrbanAirshipClient client = new UrbanAirshipClient("uaAccount", "uaPass");
		
		Device device = new Device();
		
		device.setiOSDeviceToken("iosDeviceToken1");
		
		device.setAlias("giltUser58");
		
		device.addTag("oregon_resident");
		device.addTag("pacific_time_zone");
		device.addTag("rebecca_black_fan");
		
		client.register(device);
		
		Push p = new Push();
		
		p.addTag("rebecca_black_fan");
		
		p.setMessage("Yesterday was Thursday. Today it is Friday.");
		
		Calendar scheduleFor = Calendar.getInstance();
		scheduleFor.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		scheduleFor.set(Calendar.HOUR_OF_DAY, 8);
		
		p.addScheduleFor(scheduleFor);
		
		client.sendPushNotifications(p);
		
	}
}
