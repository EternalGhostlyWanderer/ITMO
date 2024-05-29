package commands;


import java.util.LinkedList;

import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionController;
import managers.CommandsController;
import utils.Console;

/**
 * Class with realization of history command
 * <p>This command is used to print all elements of collection
 * @see UserCommand
 * @see ICommand
 */
public class HistoryCommand extends UserCommand{
    /**
     * Controller of commands which is used to get list 
     */
	private final CommandsController commandsController;

    /**
     * historyCommand constructor
     * <p> Firstly it initializes super constructor by command name, arguments and description
     * @param commandsController
     */
    public HistoryCommand(CommandsController commandsController) {
        super("history", "print history of collection commands");
        this.commandsController = commandsController;
    }

    /**
     * Method to complete history command
     * <p>It gets list from command controller and then prints it
     */
    @Override
    public void execute() {
        Console.getInstance().printLn(this.commandsController.get14CommandHistory());
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

