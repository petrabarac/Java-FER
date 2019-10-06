package hr.fer.zemris.java.hw06.shell.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellIOException;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

/**
 * Help command, if started with no arguments, it list names of all supported commands.
 * If started with single argument, it  print name and the description of selected command 
 * 
 * @author Petra Barać
 *
 */
public class HelpShellCommand implements ShellCommand{

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		
		
		ShellCommand command = env.commands().get(arguments);
		
		
		if(arguments.length() == 0) {
			 Iterator<String> iterator = env.commands().keySet().iterator(); 
			 env.writeln("supported commands are: ");
			 while(iterator.hasNext()) {
				 env.writeln(iterator.next());
			 }
			
		} else {
			try {
				List<String> commandDescription = new ArrayList<>();
				env.writeln(command.getCommandName());
				commandDescription = command.getCommandDescription();
				for(String desc : commandDescription ) {
					env.writeln(desc);
				}

			} catch(ShellIOException ex) {
				env.write(ex.getMessage());
			}
		
		}
		
		return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "help";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> description = new ArrayList<>();
		description.add("Type \"help\" if you want to list names of all supported commands.");
		description.add("type \"help\" + \"command name\" to  print name and the description of selected command");
		
		description = Collections.unmodifiableList(description);
		return description;
	}

}
