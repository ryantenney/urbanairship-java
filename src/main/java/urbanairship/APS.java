
package urbanairship;

/**
 * 
 * APS: Apple Push Service
 *
 */
public class APS implements java.io.Serializable
{
	private Object badge;
	private String alert;
	private String sound;
	
	public Integer getBadgeAsInteger()
	{
		if (this.badge instanceof Integer)
		{
			return (Integer) badge;
		}
		else
		{
			throw new IllegalStateException("badge is not an integer");
		}
	}
	
	
	public String getBadgetAsString()
	{
		if (this.badge instanceof String)
		{
			return (String) badge;
		}
		else
		{
			throw new IllegalStateException("badge is not a string");
		}
	}
	
	/**
	 * 
	 * use this method for autobadge
	 * 
	 * @param badge  "auto"   or "+1"  or "-1"
	 * 
	 * @see #setBadge(Integer)
	 * 
	 */
	public void setBadge(String strBadge)
	{
		if ( (strBadge != null) 
				&& (!strBadge.equalsIgnoreCase("auto"))
				&& (!strBadge.startsWith("+"))
				&& (!strBadge.startsWith("-")) )
		{
			throw new IllegalArgumentException("strBadge parameter: " + strBadge);
		}
		
		this.badge = strBadge;
	}
	
	public void setBadge(Integer iBadge)
	{
		this.badge = iBadge;
	}
	
	public String getAlert()
	{
		return alert;
	}
	
	public void setAlert(String alert)
	{
		this.alert = alert;
	}
	
	public String getSound()
	{
		return sound;
	}
	
	public void setSound(String sound)
	{
		this.sound = sound;
	}
	
	
}
