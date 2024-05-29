package commands;

import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionController;
import utils.Console;

/**
 * Class with realization of head command
 * <p>This command is used to print all elements of collection
 * @see UserCommand
 * @see ICommand
 */
public class HeadCommand extends UserCommand {
    /**
     * Controller of collection which is used to get collection
     */
    private CollectionController collectionController;

    /**
     * headCommand constructor
     * <p> Firstly it initializes super constructor by command name, arguments and description
     * @param collectionController
     */
    public HeadCommand(CollectionController collectionController) {
        super("head", "print head element of collection");
        this.collectionController = collectionController;
    }

    /**
     * Method to complete head command
     * <p>It gets collection from collection controller and then prints it
     * <p>If collection is empty user is informed
     */
    @Override
    public void execute() {
        if(this.collectionController.getCollection().isEmpty()){
            Console.getInstance().printLn("Collection is empty");
            return;
        }
        Console.getInstance().printLn(this.collectionController.getHead());
    }

    /**
     * Method checks if amount arguments is correct
     * @param commandArgs String array with different arguments
     * @throws WrongAmountOfArgumentsException If number of arguments is not equal to zero
     */
    @Override
    public void initCommandArgs(String[] commandArgs) throws WrongAmountOfArgumentsException {
        if(commandArgs.length != 0) throw new WrongAmountOfArgumentsException("Wrong amount of arguments!", 0, commandArgs.length);
    }
}

