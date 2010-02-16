
package urbanairship.notifications;

import java.util.*;

/**
 * 
 * @see DeviceToken
 *
 */
public class DeviceTokens
{
	private Integer deviceTokensCount;
	private List<DeviceToken> deviceTokens = new ArrayList<DeviceToken>();
	private Integer currentPage;
	private Integer numPages;
	private Integer activeDeviceTokensCount;
	private String nextPage;
}
