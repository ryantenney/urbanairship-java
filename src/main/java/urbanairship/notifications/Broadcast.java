
package urbanairship.notifications;

import java.util.*;

public class Broadcast
{
	private APS aps;
	private Calendar scheduleFor; // schedule_for is optional
	private List<String> excludeTokens = new ArrayList<String>();
	
	public APS getAps()
	{
		return aps;
	}
	
	public void setAps(APS aps)
	{
		this.aps = aps;
	}
	
	public Calendar getScheduleFor()
	{
		return scheduleFor;
	}
	
	public void setScheduleFor(Calendar scheduleFor)
	{
		this.scheduleFor = scheduleFor;
	}
	
	public List<String> getExcludeTokens()
	{
		return excludeTokens;
	}
	
	public void setExcludeTokens(List<String> excludeTokens)
	{
		this.excludeTokens = excludeTokens;
	}
	
	
}
