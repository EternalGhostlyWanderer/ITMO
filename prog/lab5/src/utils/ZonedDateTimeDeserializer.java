package utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import lab5.Constants;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Custom deserializer to parse ZonedDateTime from JSON files
 */
public class ZonedDateTimeDeserializer implements JsonDeserializer<ZonedDateTime> {
    @Override
    public ZonedDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    	DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
    	//return ZonedDateTime.of(LocalDateTime.parse(jsonElement.getAsString(), Constants.formatter), ZoneId.of("Europe/Moscow"));
    	return ZonedDateTime.of(LocalDateTime.parse(jsonElement.getAsString(), formatter), ZoneId.of("Europe/Moscow"));
    }
    /*@Override
    public ZonedDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return ZonedDateTime.of(LocalDateTime.parse(jsonElement.getAsString(), Constants.formatter), ZoneId.of("Europe/Moscow"));
    }*/
}
