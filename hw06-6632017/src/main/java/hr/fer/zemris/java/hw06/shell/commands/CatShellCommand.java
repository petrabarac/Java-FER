package hr.fer.zemris.java.hw06.shell.commands;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

/**
 * Command cat takes one or two arguments. The first argument is path to some file and is mandatory. The
 * second argument is charset name that should be used to interpret chars from bytes. If not provided, a default
 * platform charset will be used.
 *  This command opens given file and writes its content to console.
 *  
 * @author Petra Barać
 *
 */

public class CatShellCommand implements ShellCommand{

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {

		String[] args = arguments.split(" ");
		
		if(args.length < 1 || args.length > 2) {
			env.writeln("cat need one or two arguments, source file (mandatory) and charset");
			return ShellStatus.CONTINUE;
		}
				
		Path file = Paths.get(args[0]);
		Charset charset = Charset.defaultCharset();
		
		if(args.length == 2) {
			charset = Charset.forName(args[1]);
		}
		CharsetEncoder encoder = charset.newEncoder();
		
		if(!Files.exists(file)) {
			env.writeln("There is no such source file");
			return ShellStatus.CONTINUE;
		}
		try {		
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file.toFile()), charset));
				String line;
				while ((line = br.readLine()) != null) {
					env.writeln(line);
				}
				br.close();

						
		} catch (FileNotFoundException e) {
			env.writeln("file can not be found");
		} catch (IOException e) {
			env.writeln("IO exception");
		}
		
		
		
		
		return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "cat";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> description = new ArrayList<>();
		description.add("takes one or two arguments. The first argument is path to some file and is mandatory. The\n" + 
				"second argument is charset name that should be used to interpret chars from bytes. If not provided, a default\n" + 
				"platform charset should be used");
		description = Collections.unmodifiableList(description);
		return description;
		
	}

}
