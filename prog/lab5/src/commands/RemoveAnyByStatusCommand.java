package commands;

import java.util.NoSuchElementException;

import exceptions.InvalidDataException;
import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionController;
import models.Status;
import utils.Console;
import utils.WorkerParsers;
import utils.WorkerValidators;

/**
 * Class with realization of remove_any_by_status command
 * <p>This command is used to remove element with given status from collection
 * @see UserCommand
 * @see ICommand
 */
public class RemoveAnyByStatusCommand extends UserCommand {
    /**
     * Controller of collection which is used to remove element by its status
     */
    private CollectionController collectionController;

    /**
     * status of element to remove
     */
    private Status status;

    /**
     * RemoveByIdCommand constructor
     * <p> Firstly it initializes super constructor by command name, arguments and description
     * @param collectionController
     */
    public RemoveAnyByStatusCommand(CollectionController collectionController) {
        super("remove_any_by_status", "status", "remove element with given status from collection");
        this.collectionController = collectionController;
    }

    /**
     * Method to complete remove_any_by_status command
     * <p>It removes element by its status
     * @throws NoSuchElementException is element with given status was not found
     */
    @Override
    public void execute() throws NoSuchElementException {
        if (this.collectionController.getByStatus(status) == null) {
            throw new NoSuchElementException("No element with such status!");
        }
        this.collectionController.removeAnyByStatus(status);
        Console.getInstance().printLn("Element removed successfully!");
    }

    /**
     * Method checks if amount arguments is correct and validates status
     * @param commandArgs String array with different arguments
     * @throws WrongAmountOfArgumentsException If number of arguments is not equal to one
     * @throws InvalidDataException If given status is not valid
     */
    @Override
    public void initCommandArgs(String[] commandArgs) throws WrongAmountOfArgumentsException, InvalidDataException {
        if(commandArgs.length != 1){
            throw new WrongAmountOfArgumentsException("Wrong amount of arguments!", 1, commandArgs.length);
        }
        this.status = WorkerParsers.statusParser.parse(commandArgs[0]);
        WorkerValidators.statusValidator.validate(status);
    }
}
