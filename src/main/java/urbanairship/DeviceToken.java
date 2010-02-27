
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
	private String token;
	private boolean active = true;
	private String alias;
	private Calendar lastRegistration;
	
	public String getToken()
	{
		return token;
	}
	
	public void setToken(String token)
	{
		this.token = token;
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


}
