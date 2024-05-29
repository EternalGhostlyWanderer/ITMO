package commands;

import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionController;
import utils.Console;

/**
 * Class with realization of max_by_salary command
 * <p>This command is used to print any element from collection which salary field is maximal
 * @see UserCommand
 * @see ICommand
 */
public class MaxBySalaryCommand extends UserCommand {
    /**
     * Controller of collection which is used to get required element
     */
    private CollectionController collectionController;

    /**
     * maxBySalaryCommand constructor
     * <p> Firstly it initializes super constructor by command name, arguments and description
     * @param collectionController
     */
    public MaxBySalaryCommand(CollectionController collectionController) {
        super("max_by_salary", "print any element from collection which salary field is maximal");
        this.collectionController = collectionController;
    }

    /**
     * Method to complete max_by_salary command
     * <p>It prints element with maximal salary
     * <p>If collection is empty user is informed
     */
    @Override
    public void execute() {
        if(this.collectionController.getCollection().isEmpty()){
            Console.getInstance().printLn("Collection is empty!");
            return;
        }
        Console.getInstance().printLn(this.collectionController.getMaxBySalary());
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
