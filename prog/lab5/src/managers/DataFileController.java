package managers;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import models.Worker;
import utils.Console;
import utils.DateDeserializer;
import utils.DateSerializer;
import utils.LocalDateTimeDeserializer;
import utils.LocalDateTimeSerializer;
import utils.ZonedDateTimeDeserializer;
import utils.ZonedDateTimeSerializer;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

/**
 * Class to operate with data file
 */
public class DataFileController {
    /**
     * Path to data file
     */
    private final File dataFile;
    private final String fileName;
    /**
     * Gson object to operate with JSON data file
     */
    private final Gson gson;

    /**
     * DataFileController constructor
     * <p>Validate path to dataFile and initialize Gson
     * @param dataFile file with data
     */
    public DataFileController(File dataFile, String fileName) {
        this.dataFile = dataFile;
        if (!(new File(fileName).exists())) {
            fileName = "../" + fileName;
          }
          this.fileName = fileName;

        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
        gsonBuilder.registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeSerializer());
        gsonBuilder.registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeDeserializer());
        gsonBuilder.registerTypeAdapter(Date.class, new DateSerializer());
        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());

        this.gson = gsonBuilder.serializeNulls().setPrettyPrinting().create();
    }

    /**
     * Method to write collection to dataFile
     * @param data collection to write
     * @throws IOException If any error occurred while writing
     */
    public void writeToJSON(PriorityQueue<Worker> data) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(dataFile));
        Type dataType = new TypeToken<PriorityQueue<Worker>>(){}.getType();
        String output = this.gson.toJson(data, dataType);
        outputStreamWriter.write(output);
        outputStreamWriter.flush();
        outputStreamWriter.close();
    }
    private BufferedReader reader;
    


    /**
     * Method to read collection  from data file
     * @return collection
     * @throws IOException If any error occurred while reading
     * @throws JsonParseException If it is impossible to deserialize file
     */
    public PriorityQueue<Worker> readJSON(){
    	try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(fileName));
            reader = new BufferedReader(inputStreamReader);
            var collectionType = new TypeToken<PriorityQueue<Worker>>() {}.getType();
            var jsonString = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) {
              line = line.trim();
              if (!line.equals("")) {
                jsonString.append(line);
              }
            }

            if (jsonString.length() == 0) {
              jsonString = new StringBuilder("[]");
            }

            PriorityQueue<Worker> collection = gson.fromJson(jsonString.toString(), collectionType);
            

            return collection;
            

          } catch (FileNotFoundException exception) {
        	  Console.getInstance().printLn("File was not found!");
          } catch (NoSuchElementException exception) {
        	  Console.getInstance().printLn("File is empty!");
          } catch (JsonParseException exception) {
        	  Console.getInstance().printLn("The required collection was not found in the file!");
          } catch (IllegalStateException | IOException exception) {
        	  Console.getInstance().printLn("Unexpected error!");
            System.exit(0);
          }
        return new PriorityQueue<>();
    }
}