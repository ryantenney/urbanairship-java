
package urbanairship;

import java.util.*;

public class Airmail
{
	private Push push;
	private List<String> tags;
	private List<String> users;
	private List<String> aliases;
	private String title;
	private String message;
	private Map<String, String> extra;
	public Push getPush()
	{
		return push;
	}
	public void setPush(Push push)
	{
		this.push = push;
	}
	public List<String> getTags()
	{
		return tags;
	}
	public void setTags(List<String> tags)
	{
		this.tags = tags;
	}
	public List<String> getUsers()
	{
		return users;
	}
	public void setUsers(List<String> users)
	{
		this.users = users;
	}
	public List<String> getAliases()
	{
		return aliases;
	}
	public void setAliases(List<String> aliases)
	{
		this.aliases = aliases;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getMessage()
	{
		return message;
	}
	public void setMessage(String message)
	{
		this.message = message;
	}
	public Map<String, String> getExtra()
	{
		return extra;
	}
	public void setExtra(Map<String, String> extra)
	{
		this.extra = extra;
	}


}
