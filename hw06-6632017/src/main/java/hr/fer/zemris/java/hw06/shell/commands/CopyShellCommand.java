package hr.fer.zemris.java.hw06.shell.commands;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
 * The copy command expects two arguments: source file name and destination file name (i.e. paths and names).
 * If destination file exists, method ask user is it allowed to overwrite it.
 * Method copy source file to destination file
 * 
 * @author Petra Barać
 *
 */
public class CopyShellCommand implements ShellCommand{

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
				
		String[] args = arguments.split(" ");
		if(args.length != 2) {
			env.writeln("command copy need to have source file path and destination file path");
			return ShellStatus.CONTINUE;
		}
		
		String sourceFile = args[0];
		String destinationFile = args[1];	
		Path originalFile = Paths.get(sourceFile);
		Path copyFile = Paths.get(destinationFile);
		
		if(!Files.exists(originalFile)) {
			env.writeln("There is no such source file");
			return ShellStatus.CONTINUE;
		}
		
		if(Files.exists(copyFile)) {
			env.writeln("File '" + copyFile.toString() + "' exists, do you wanna rewrite it? Y/N " );
			String answer = env.readLine();
			
			while(!(answer.toUpperCase().equals("N") || answer.toUpperCase().equals("Y"))) {
				env.writeln("File '" + copyFile.toString() + "' exists, do you wanna rewrite it? Y/N " );
				answer = env.readLine();
			}
			
			if(answer.equals("N")) {
				return ShellStatus.CONTINUE;
			}
		}
		
		try(InputStream is = new BufferedInputStream(new FileInputStream(originalFile.toString()));
				OutputStream os = new BufferedOutputStream(new FileOutputStream(copyFile.toString()))) {
			
			byte[] buffer = new byte[4096];
			while(true) {
				int r = is.read(buffer);
				
				if(r < 1) {
					break;
				}
				os.write(buffer, 0, r);
			}
			
		} catch (FileNotFoundException e) {
			env.writeln("File can not be opened!");
		} catch (IOException e) {
			env.write("IO exception");
		} catch (SecurityException e) {
			env.writeln("security exception");
		}
		
		return ShellStatus.CONTINUE;

	}
		

	@Override
	public String getCommandName() {
		return "copy";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> description = new ArrayList<>();
		description.add("copy file from sourcepath to file in a dectination path");
		description = Collections.unmodifiableList(description);
		return description;
	}

}
