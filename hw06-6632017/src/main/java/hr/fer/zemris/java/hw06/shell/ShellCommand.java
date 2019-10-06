package hr.fer.zemris.java.hw06.shell;

import java.util.List;

/**
 * Interface that defines tree abstract methods. 
 * Each command is implemented as a class that implements ShellCommand interface

 * 
 * @author Petra Barać
 *
 */
public interface ShellCommand {
	
	/**
	 * 
	 * @param env defined environment
	 * @param arguments everything that user entered after the command name
	 * @return shell status
	 */
	ShellStatus executeCommand(Environment env, String arguments);
	
	/**
	 * 
	 * @return name of command
	 */
	String getCommandName();
	
	/**
	 * 
	 * @return decription of command that is used in help method
	 */
	List<String> getCommandDescription();

}
