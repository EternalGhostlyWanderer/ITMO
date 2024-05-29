package lab5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import commands.UserCommand;
import managers.CollectionController;
import managers.CommandsController;
import managers.DataFileController;
import managers.FileLoader;
import models.Worker;
import models.WorkerReader;
import utils.Console;

/**
 * Main app class
 * <p>Completes initialization of all controllers, sets default input stream for Console
 * <p>In the beginning loads data file (if it is wrong program stops), then calls interactiveMode method
 */
public class Main {
	
    /**
     * Controller of collection
     */
    private static CollectionController collectionController;
    /**
     * Reader of data elements
     */
    private static WorkerReader workerReader;
    /**
     * Controller of commands
     */
    private static CommandsController commandsController;
    /**
     * Controller of data file
     */
    private static DataFileController dataFileController;

    /**
     * Main method of program
     * <p>Calls methods to load data file, init all controllers and start handling user commands
     * @param args 
     */
    public static void main(String[] args) {
    	System.setProperty("file.encoding","UTF-8");
        utils.Console.getInstance().setScanner(new Scanner(System.in));
        if(args.length == 0){
    		Console.getInstance().printError("Usage: java Main <file_name>");
            System.exit(1);
        }
        String fileName = args[0];
        File file = new File(fileName);
        collectionController = new CollectionController(loadData(file, fileName));
        workerReader = new WorkerReader(collectionController);
        commandsController = new CommandsController(collectionController, workerReader, dataFileController);
        interactiveMode();
        
    }

    /**
     * method which is used to work with script file
     * @throws Exception if any error occurred in process of executing
     */
    public static void scriptMode() throws Exception {
        while(Console.getInstance().hasNextLine()) {
            String s = Console.getInstance().readLine();
            String[] input = (s.trim() + " ").split(" ");
            if(input.length == 0) continue;
            String commandName = input[0];
            Console.getInstance().printLn(commandName);
            String[] commandArgs = Arrays.copyOfRange(input, 1, input.length);
            UserCommand command = commandsController.launchCommand(commandName, commandArgs);
            command.execute();
        }
    }

    /**
     * Method to handle user input
     *
     * <p>Reads commands from user, gets their name and arguments, launch command and execute it
     * <p>If any error is occurred method prints error message and continues to read data
     */
    public static void interactiveMode(){
        while(Console.getInstance().hasNextLine()) {
            String s = Console.getInstance().readLine();
            String[] input = (s.trim() + " ").split(" ");
            if(input.length == 0) continue;
            String commandName = input[0];
            String[] commandArgs = Arrays.copyOfRange(input, 1, input.length);

            try {
                UserCommand command = commandsController.launchCommand(commandName, commandArgs);
                command.execute();
                commandsController.addToHistory(commandName);
            }
            catch (Exception e){
                Console.getInstance().printError(e.getMessage());
            }
        }
    }



    /**
     * Method to load collection from data file.
     * <p>Method also completes validation of filePath and collection inside dataFile
     * @return Collection of workers
     * @see DataFileController
     * @see CollectionController
     */
    private static PriorityQueue<Worker> loadData(File file, String fileName){
    
        PriorityQueue<Worker> data = null;
        File dataFile = null;

        dataFileController = new DataFileController(file, fileName);
        data = dataFileController.readJSON();
        try {
            dataFile = new FileLoader().loadFile(fileName, "json", "rw", "data file");
        } catch (FileNotFoundException e) {
            Console.getInstance().printError(e.getMessage());
            System.exit(0);
        }

        dataFileController = new DataFileController(dataFile,fileName);

        try {
            data = dataFileController.readJSON();
        } catch (Exception e) {
            Console.getInstance().printError("Data file reading error!");
            System.exit(0);
        }

        if(data == null) data = new PriorityQueue<>();
        if(!CollectionController.isValid(data)){
            Console.getInstance().printError("Data file is not valid!");
            System.exit(0);
        }
        Console.getInstance().printLn("Data loaded successfully!");
        return data;
    }
}