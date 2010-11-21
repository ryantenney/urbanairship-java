
package urbanairship;

import java.util.*;

/**
 * 
 *  TODO rename this class to 'Device'  (???)
 *  
 *  @see DeviceTokens
 *
 *
 */
public class DeviceToken implements java.io.Serializable
{
	private String alias;
	private String deviceToken;
	private List<String> tags;
	private boolean active = true;
	private Calendar lastRegistration;
	private Integer badge;
	private String tz; // timezone
	private QuietTime quiettime;
	
	public String getDeviceToken()
	{
		return deviceToken;
	}
	
	/**
	 * 
	 * @param token token should be upper case without spaces and 64 characters long
	 * 
	 * 
	 */
	public void setToken(String tkn)
	{
		if (tkn == null)
		{
			throw new NullPointerException("tkn parameter is null");
		}
		else if (tkn.length() != 64)
		{
			throw new IllegalArgumentException("token length should be 64. Actual: " + tkn.length());
		}
		
		
		this.deviceToken = tkn;
		
	}
	
	public boolean isActive()
	{
		return active;
	}
	
	public void setActive(boolean active)
	{
		this.active = active;
	}
	
	public String getAlias()
	{
		return alias;
	}
	
	public void setAlias(String alias)
	{
		this.alias = alias;
	}
	
	public Calendar getLastRegistration()
	{
		return lastRegistration;
	}
	
	public void setLastRegistration(Calendar lastRegistration)
	{
		this.lastRegistration = lastRegistration;
	}

	public List<String> getTags()
	{
		return tags;
	}

	public void setTags(List<String> tags)
	{
		this.tags = tags;
	}

	public Integer getBadge()
	{
		return badge;
	}

	public void setBadge(Integer badge)
	{
		this.badge = badge;
	}

	public String getTz()
	{
		return tz;
	}

	public void setTz(String tz)
	{
		this.tz = tz;
	}

	public QuietTime getQuiettime()
	{
		return quiettime;
	}

	public void setQuiettime(QuietTime quiettime)
	{
		this.quiettime = quiettime;
	}

	public void setDeviceToken(String deviceToken)
	{
		this.deviceToken = deviceToken;
	}


}
