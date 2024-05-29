package commands;

import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionController;
import utils.Console;

/**
 * Class with realization of print_unique_salary command
 * <p>This command is used to print values of all salary fields in collection in descending order
 * @see UserCommand
 * @see ICommand
 */
public class PrintUniqueSalaryCommand extends UserCommand {
    /**
     * Controller of collection which is used to get sorted list of all salaries
     */
    private CollectionController collectionController;

    /**
     * PrintFieldDescendingSalaryCommand constructor
     * <p> Firstly it initializes super constructor by command name, arguments and description
     * @param collectionController
     */
    public PrintUniqueSalaryCommand(CollectionController collectionController) {
        super("print_unique_salary", "print values of all salary fields in collection in descending order");
        this.collectionController = collectionController;
    }

    /**
     * Method to complete print_unique_salary command
     * <p>It prints list of all salaries in descending order
     * <p>If collection is empty user is informed
     */
    @Override
    public void execute() {
        if(this.collectionController.getCollection().isEmpty()){
            Console.getInstance().printLn("Collection is empty!");
            return;
        }
        Console.getInstance().printLn(this.collectionController.printUniqueSalary());
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
