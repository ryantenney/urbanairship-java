
package urbanairship;

import java.util.*;

public class Push implements java.io.Serializable
{
	private List<String> deviceTokens;
	private List<String> aliases;
	private List<String> tags;
	private List<Calendar> scheduleFor;
	private List<String> excludeTokens;
	private APS aps;
	private Map<String, Object> payload = new HashMap<String, Object>();
	
	public void addPayloadValue(String key, Object value) 
	{
		this.payload.put(key, value);
	}
	
	public List<String> getDeviceTokens()
	{
		return deviceTokens;
	}
	
	public void setDeviceTokens(List<String> deviceTokens)
	{
		this.deviceTokens = deviceTokens;
	}
	
	public void addDeviceToken(String token)
	{
		this.getDeviceTokens().add(token);
	}
	
	public List<String> getAliases()
	{
		return aliases;
	}
	
	public void setAliases(List<String> aliases)
	{
		this.aliases = aliases;
	}
	
	public void addAlias(String alias)
	{
		this.getAliases().add(alias);
	}
	
	public List<String> getTags()
	{
		return tags;
	}
	
	public void setTags(List<String> tags)
	{
		this.tags = tags;
	}
	
	public void addTag(String tag)
	{
		this.getTags().add(tag);
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
