package hr.fer.zemris.java.hw06.shell.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellIOException;
import hr.fer.zemris.java.hw06.shell.ShellStatus;


/**
 * The mkdir command takes a single argument: directory name, and creates the appropriate directory structure.
 * 
 * @author Petra Barać
 *
 */
public class MkdirShellCommand implements ShellCommand {
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		Path path;
		
	
		path = Paths.get(arguments);


		try {
			Files.createDirectories(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ShellStatus.CONTINUE;
	}
	
	
	@Override
	public String getCommandName() {
		return "mkdir";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> description = new ArrayList<>();
		description.add("Make new directories");
		
		description = Collections.unmodifiableList(description);
		return description;

	}

}
