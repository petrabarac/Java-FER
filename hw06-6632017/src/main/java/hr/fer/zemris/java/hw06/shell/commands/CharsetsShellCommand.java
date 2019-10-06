package hr.fer.zemris.java.hw06.shell.commands;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

/**
 * 
 * Command charsets takes no arguments and lists names of supported charsets for your Java platform.
 * A single charset name is written per line
 * 
 * @author Petra Barać
 *
 */
public class CharsetsShellCommand implements ShellCommand {
	
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		
		 Map<String, Charset> charsets = Charset.availableCharsets(); 

 
		 Iterator<Charset> iterator = charsets.values().iterator(); 


		 while (iterator.hasNext()) { 
			 env.writeln(iterator.next().toString());
		 }
		
		return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "charsets";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> description = new ArrayList<>();
		description.add("list all available charsets");
		description = Collections.unmodifiableList(description);
		return description;
	}

}
