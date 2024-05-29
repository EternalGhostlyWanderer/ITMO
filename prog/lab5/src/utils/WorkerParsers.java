package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import exceptions.InvalidDataException;
import lab5.Constants;
import models.OrganizationType;
import models.Status;

/**
 * Class with parsers which are required to read Worker objects
 */
public class WorkerParsers {
    /**
     * String parser
     */
    public static Parser<String> stringParser = s -> s;
    /**
     * Long parser
     */
    public static Parser<Long> longParser = s -> {
        try{
            return Long.parseLong(s);
        } catch (NumberFormatException e){
            throw new InvalidDataException("Value must be a long!");
        }
    };
    /**
     * Integer parser
     */
    public static Parser<Integer> integerParser = s -> {
        try{
            return Integer.parseInt(s);
        } catch (NumberFormatException e){
            throw new InvalidDataException("Value must be an integer!");
        }
    };
    /**
     * Double parser
     */
    public static Parser<Double> doubleParser = s -> {
        try{
            return Double.parseDouble(s);
        } catch (NumberFormatException e){
            throw new InvalidDataException("Value must be a double!");
        }
    };
    /**
     * Float parser
     */
    public static Parser<Float> floatParser = s -> {
        try{
            return Float.parseFloat(s);
        } catch (NumberFormatException e){
            throw new InvalidDataException("Value must be a double!");
        }
    };
    /**
     * Date parser
     */
    public static Parser<Date> dateParser = s -> {
    	
    	SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT_STRING);
        try{
            return formatter.parse(s);
        } catch (ParseException e){
            throw new InvalidDataException("Wrong date format!");
        }
    };
    /**
     * ZonedDateTime parser
     */
    public static Parser<ZonedDateTime> zonedDateTimeParser = s -> {
    	s = s + "T15:30:00+03:00[Europe/Moscow]";

    	//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.dd.yyyy - HH:mm:ss z");
    	 DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
    	    try {
    	        return ZonedDateTime.parse(s, formatter);
    	    } catch (DateTimeParseException e) {
    	        throw new InvalidDataException("Wrong zoned date format!");
    	    }
    };
    /**
     * Status parser
     */
    public static Parser<Status> statusParser = s -> {
        try{
            return Status.valueOf(s);
        } catch (IllegalArgumentException e){
            throw new InvalidDataException("Status not found! Please choose value from list!");
        }
    };
    /**
     * OrganizationType parser
     */
    public static Parser<OrganizationType> organizationTypeParser = s -> {
        try{
            return OrganizationType.valueOf(s);
        } catch (IllegalArgumentException e){
            throw new InvalidDataException("OrganizationType not found! Please choose value from list!");
        }
    };
}
