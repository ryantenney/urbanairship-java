
package urbanairship;

import java.lang.reflect.Type;
import java.util.Calendar;

import com.google.gson.*;

public class GsonFactory
{

	public static Gson create()
	{
		GsonBuilder builder = new GsonBuilder();
		return builder.setPrettyPrinting()
						.setDateFormat(ISO8601.PATTERN)
						.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
						.registerTypeAdapter(java.util.Calendar.class, new CalendarAdapter())
						.create();
	}
	
	public static class CalendarAdapter
		implements JsonSerializer, JsonDeserializer<java.util.Calendar>
	{

		public JsonElement serialize(Object arg0, Type arg1,
				JsonSerializationContext arg2)
		{
			Calendar c = (Calendar) arg0;
			
			JsonElement element = new JsonPrimitive(ISO8601.toString(c));
			return element;
		}

		public Calendar deserialize(JsonElement arg0, Type arg1,
				JsonDeserializationContext arg2) throws JsonParseException
		{
			String s = arg0.getAsString();
			
			try
			{
				
				Calendar c = ISO8601.toCalendar(s);
				
				return c;
			}
			catch (java.text.ParseException ex)
			{
				throw new JsonParseException(s, ex);
			}
		}
		
	}

}
