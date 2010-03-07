
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
		this.token = tkn;
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
