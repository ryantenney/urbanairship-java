
package urbanairship;

import com.google.gson.*;

class GsonFactory
{

	public static Gson create()
	{
		GsonBuilder builder = new GsonBuilder();
		return builder.setPrettyPrinting()
						.setDateFormat(ISO8601.PATTERN)
						.create();
	}

}
