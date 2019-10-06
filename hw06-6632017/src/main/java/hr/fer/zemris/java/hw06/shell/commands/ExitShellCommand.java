package hr.fer.zemris.java.hw06.shell.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

/**
 * Exit command terminates shell
 * @author Petra Barać
 *
 */
public class ExitShellCommand implements ShellCommand{

	
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {

		return ShellStatus.TERMINATE;
	}

	@Override
	public String getCommandName() {
		return "exit";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> description = new ArrayList<>();
		description.add("exit shell");
		description = Collections.unmodifiableList(description);
		return description;
	}

}
