package hr.fer.zemris.java.hw06.shell;

import java.util.SortedMap;
import java.util.TreeMap;

import hr.fer.zemris.java.hw06.shell.commands.CatShellCommand;
import hr.fer.zemris.java.hw06.shell.commands.CharsetsShellCommand;
import hr.fer.zemris.java.hw06.shell.commands.CopyShellCommand;
import hr.fer.zemris.java.hw06.shell.commands.ExitShellCommand;
import hr.fer.zemris.java.hw06.shell.commands.HelpShellCommand;
import hr.fer.zemris.java.hw06.shell.commands.LsShellCommand;
import hr.fer.zemris.java.hw06.shell.commands.MkdirShellCommand;
import hr.fer.zemris.java.hw06.shell.commands.SymbolShellCommand;
import hr.fer.zemris.java.hw06.shell.commands.TreeShellCommand;

/**
 * Command line program that implements shell.
 * Shell contains commands: help, exit, cat, charsets, copy ls, mkdir, symbol, tree. They are all 
 * implemented in hr.fer.zemris.java.hw06.shell.commands.
 * 
 * My shell write a  greeting message to user,  write a prompt symbol and wait for the user to enter a command. 
 * The command can span across multiple lines. However, each line that is not the last line of command must end
 *  with a special symbol that is used to inform the shell that more lines as expected.
 *  Shell terminates when user gives exit command. Until that, shell status is CONTINUE and user communicates with shell throw environment "Communication Enviroment"
 * 
 * @author Petra Barać
 *
 */
public class MyShell {
	

	private static CommunicationEnvironment env ;
	private static ShellStatus status;
	private static SortedMap<String, ShellCommand> commands;
	private static ShellCommand command;
	private static StringBuilder sb;
	
	public static void main(String[] args) {
		
		initialization();
		System.out.println("Welcome to MyShell v 1.0");

		
		String userInput;
		
		while(status != status.TERMINATE) {
			System.out.print(env.getPromptSymbol() + " ");
			userInput = env.readLine();
			
			if(userInput.endsWith(String.valueOf(env.getMorelinesSymbol()))) {
				while(userInput.charAt(userInput.length() - 1) == env.getMorelinesSymbol()) {
					userInput = userInput.substring(0, userInput.indexOf(env.getMorelinesSymbol()) );
					sb.append(userInput);
					System.out.print(env.getMultilineSymbol());
					userInput = env.readLine();	
				}
			
			}
			
			sb.append(userInput);
			
			String input = sb.toString();

			
			String[] arg = input.split(" ");
			
			String commandName = arg[0];

			String arguments = input.substring(commandName.length(), input.length()).trim();			
			command = commands.get(commandName);
			status = command.executeCommand(env, arguments);
			sb.setLength(0);
		}
		
	}
	
	/**
	 * Initialization method where basic shell items have been initialized.
	 * At the beginning shell status is set to CONTINUE and all commands are added
	 * to commands list end sent to environment
	 */

	private static void initialization() {
		
		status = ShellStatus.CONTINUE;
		commands = new TreeMap<String, ShellCommand>();
		sb = new StringBuilder();
		
		commands.put("symbol", new SymbolShellCommand());
		commands.put("exit", new ExitShellCommand());
		commands.put("help", new HelpShellCommand());
		commands.put("charsets", new CharsetsShellCommand());
		commands.put("mkdir", new MkdirShellCommand());
		commands.put("copy", new CopyShellCommand());
		commands.put("cat", new CatShellCommand());
		commands.put("ls", new LsShellCommand());
		commands.put("tree", new TreeShellCommand());
		
		env = new CommunicationEnvironment(commands);
	}
	
	
}
