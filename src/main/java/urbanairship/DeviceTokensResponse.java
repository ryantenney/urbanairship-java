
package urbanairship;

import java.util.*;

public class DeviceTokensResponse implements java.io.Serializable
{
	private Integer deviceTokensCount;
	public List<DeviceToken> deviceTokens = new ArrayList<DeviceToken>();
	private Integer currentPage;
	private Integer numPages;
	private Integer activeDeviceTokensCount;
	
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
	
	
	
}
