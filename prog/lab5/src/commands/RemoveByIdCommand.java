package commands;



import java.util.NoSuchElementException;

import exceptions.InvalidDataException;
import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionController;
import utils.Console;
import utils.WorkerParsers;
import utils.WorkerValidators;

/**
 * Class with realization of remove_by_id command
 * <p>This command is used to remove element with given id from collection
 * @see UserCommand
 * @see ICommand
 */
public class RemoveByIdCommand extends UserCommand {
    /**
     * Controller of collection which is used to remove element by its id
     */
    private CollectionController collectionController;

    /**
     * id of element to remove
     */
    private Integer id;

    /**
     * RemoveByIdCommand constructor
     * <p> Firstly it initializes super constructor by command name, arguments and description
     * @param collectionController
     */
    public RemoveByIdCommand(CollectionController collectionController) {
        super("remove_by_id", "id", "remove element with given id from collection");
        this.collectionController = collectionController;
    }

    /**
     * Method to complete remove_by_id command
     * <p>It removes element by its id
     * @throws NoSuchElementException is element with given id was not found
     */
    @Override
    public void execute() throws NoSuchElementException {
        if (!this.collectionController.containsId(id)) {
            throw new NoSuchElementException("No element with such id!");
        }
        this.collectionController.removeById(id);
        Console.getInstance().printLn("Element removed successfully!");
    }

    /**
     * Method checks if amount arguments is correct and validates id
     * @param commandArgs String array with different arguments
     * @throws WrongAmountOfArgumentsException If number of arguments is not equal to one
     * @throws InvalidDataException If given id is not valid
     */
    @Override
    public void initCommandArgs(String[] commandArgs) throws WrongAmountOfArgumentsException, InvalidDataException {
        if(commandArgs.length != 1){
            throw new WrongAmountOfArgumentsException("Wrong amount of arguments!", 1, commandArgs.length);
        }
        this.id = WorkerParsers.integerParser.parse(commandArgs[0]);
        WorkerValidators.idValidator.validate(id);
    }
}
