
package urbanairship;

import java.util.*;

public class Push implements java.io.Serializable
{
	private List<String> deviceTokens = new ArrayList<String>();
	private List<String> aliases = new ArrayList<String>();
	private List<String> tags = new ArrayList<String>();
	private List<Calendar> scheduleFor = new ArrayList<Calendar>();
	private List<String> excludeTokens = new ArrayList<String>();
	private APS aps;
	
	public List<String> getDeviceTokens()
	{
		return deviceTokens;
	}
	
	public void setDeviceTokens(List<String> deviceTokens)
	{
		this.deviceTokens = deviceTokens;
	}
	
	public List<String> getAliases()
	{
		return aliases;
	}
	
	public void setAliases(List<String> aliases)
	{
		this.aliases = aliases;
	}
	
	public List<String> getTags()
	{
		return tags;
	}
	
	public void setTags(List<String> tags)
	{
		this.tags = tags;
	}
	
	public List<Calendar> getScheduleFor()
	{
		return scheduleFor;
	}
	
	public void setScheduleFor(List<Calendar> scheduleFor)
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
	
	public APS getAps()
	{
		return aps;
	}
	
	public void setAps(APS aps)
	{
		this.aps = aps;
	}
	
	
	
}
