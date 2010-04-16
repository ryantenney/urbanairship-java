
package urbanairship;

public class PushResponse implements java.io.Serializable
{
	private ScheduledNotifications scheduledNotifications;

	public ScheduledNotifications getScheduledNotifications()
	{
		return scheduledNotifications;
	}

	public void setScheduledNotifications(
			ScheduledNotifications scheduledNotifications)
	{
		this.scheduledNotifications = scheduledNotifications;
	}
	
	public boolean hasScheduledNotifications()
	{
		return (this.getScheduledNotifications() != null);
	}
}
