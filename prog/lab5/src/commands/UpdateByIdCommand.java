package commands;


import java.util.NoSuchElementException;

import exceptions.InvalidDataException;
import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionController;
import models.Worker;
import models.WorkerReader;
import utils.Console;
import utils.WorkerParsers;
import utils.WorkerValidators;

/**
 * Class with realization of update command
 * <p>This command is used to update value of collection element which id is equal to given
 * @see UserCommand
 * @see ICommand
 */
public class UpdateByIdCommand extends UserCommand {
    /**
     * Worker reader which is used to read new element from user
     */
    private WorkerReader workerReader;
    /**
     * Controller of collection which is used to update element
     */
    private CollectionController collectionController;
    /**
     * id of element to update
     */
    private Integer id;

    /**
     * UpdateByIdCommand constructor
     * <p> Firstly it initializes super constructor by command name, arguments and description
     * @param workerReader
     * @param collectionController
     */
    public UpdateByIdCommand(WorkerReader workerReader, CollectionController collectionController) {
        super("update", "id {element}", "update value of collection element which id is equal to given");
        this.workerReader = workerReader;
        this.collectionController = collectionController;
    }

    /**
     * Method to complete update command
     * <p>It reads new element from user and then updates value of element with given id inside collection
     * @throws NoSuchElementException is element with given id was not found
     */
    @Override
    public void execute() throws InvalidDataException, NoSuchElementException {
        if (!this.collectionController.containsId(id)) {
            throw new NoSuchElementException("No element with such id!");
        }
        Worker worker = this.workerReader.readWorker();
        this.collectionController.update(id, worker);
        Console.getInstance().printLn("Element updated successfully!");
    }

    /**
     * Method checks if amount arguments is correct and validates id
     * @param commandArgs String array with different arguments
     * @throws WrongAmountOfArgumentsException If number of arguments is not equal to one
     * @throws InvalidDataException If given id is not valid
     */
    @Override
    public void initCommandArgs(String[] commandArgs) throws WrongAmountOfArgumentsException, InvalidDataException {
        if (commandArgs.length != 1) throw new WrongAmountOfArgumentsException("Wrong amount of arguments!", 1, commandArgs.length);
        this.id = WorkerParsers.integerParser.parse(commandArgs[0]);
        WorkerValidators.idValidator.validate(id);
    }
}
