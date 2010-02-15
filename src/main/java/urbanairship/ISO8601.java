
package urbanairship;

import java.util.*;
import java.text.*;

public class ISO8601
{
	public static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ";
	
	static public String toString(Calendar c)
	{
		SimpleDateFormat fmt = new SimpleDateFormat(PATTERN);
		return fmt.format(c.getTime());
	}
	
	static public Calendar toCalendar(String dateTimeString) throws ParseException
	{
		SimpleDateFormat fmt = new SimpleDateFormat(PATTERN);
		
		Date d = fmt.parse(dateTimeString);
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		
		return c;
	}
}
