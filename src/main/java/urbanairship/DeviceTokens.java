
package urbanairship;

import java.util.*;

/**
 * 
 * @see DeviceToken
 *
 */
public class DeviceTokens implements java.io.Serializable, java.lang.Iterable<DeviceToken>
{
	private Integer deviceTokensCount;
	private List<DeviceToken> deviceTokens = new ArrayList<DeviceToken>();
	private Integer currentPage;
	private Integer numPages;
	private Integer activeDeviceTokensCount;
	private String nextPage;
	
	public Integer getDeviceTokensCount()
	{
		return deviceTokensCount;
	}
	
	public void setDeviceTokensCount(Integer deviceTokensCount)
	{
		this.deviceTokensCount = deviceTokensCount;
	}
	
	public List<DeviceToken> getDeviceTokens()
	{
		return deviceTokens;
	}
	
	public void setDeviceTokens(List<DeviceToken> deviceTokens)
	{
		this.deviceTokens = deviceTokens;
	}
	
	public Integer getCurrentPage()
	{
		return currentPage;
	}
	
	public void setCurrentPage(Integer currentPage)
	{
		this.currentPage = currentPage;
	}
	
	public Integer getNumPages()
	{
		return numPages;
	}
	
	public void setNumPages(Integer numPages)
	{
		this.numPages = numPages;
	}
	
	public Integer getActiveDeviceTokensCount()
	{
		return activeDeviceTokensCount;
	}
	
	public void setActiveDeviceTokensCount(Integer activeDeviceTokensCount)
	{
		this.activeDeviceTokensCount = activeDeviceTokensCount;
	}
	
	public String getNextPage()
	{
		return nextPage;
	}
	
	public void setNextPage(String nextPage)
	{
		this.nextPage = nextPage;
	}

	public Iterator<DeviceToken> iterator()
	{
		return this.getDeviceTokens().iterator();
	}
	
	
}
