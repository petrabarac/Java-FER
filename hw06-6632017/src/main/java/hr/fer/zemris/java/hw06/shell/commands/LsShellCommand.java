package hr.fer.zemris.java.hw06.shell.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

/**
 * Command ls takes a single argument – directory – and writes a directory listing (not recursive).
 * @author Petra Barać
 *
 */
public class LsShellCommand implements ShellCommand{

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		Path directory = Paths.get(arguments);
		
		if(!Files.isDirectory(directory)) {
			env.writeln("Directory '" + directory.toString() + "' is not a directory");
			return ShellStatus.CONTINUE;
		}
		
	
		Stream<Path> list;
		try {
			list = Files.list(directory);
			list.forEach(file -> {
				env.writeln((printFileInfo(file)));
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	 }	
	return ShellStatus.CONTINUE;
	}
	
	private static String printFileInfo(Path path) {
		StringBuilder sb = new StringBuilder();
		try {
			String formattedDateTime = dateTime(path);
			sb.append(Files.isDirectory(path) ? "d" : "-");
			sb.append(Files.isReadable(path) ? "r" : "-");
			sb.append(Files.isWritable(path) ? "w" : "-");
			sb.append(Files.isExecutable(path) ? "x" : "-");
			sb.append(" ");
			sb.append(Files.size(path));
			sb.append(" ");
			sb.append(formattedDateTime);
			sb.append(" ");
			sb.append(path.getFileName());
		} catch (IOException ex) {
			
		}
		
		return sb.toString();
	}

	
	private static String dateTime(Path path) {
		String formattedDateTime = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			BasicFileAttributeView faView = Files.getFileAttributeView(
					path, BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS
					);
			BasicFileAttributes attributes = faView.readAttributes();
		
			FileTime fileTime = attributes.creationTime();
			formattedDateTime = sdf.format(new Date(fileTime.toMillis()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return formattedDateTime;
	}
		
	@Override
	public String getCommandName() {
		return "ls";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> description = new ArrayList<>();
		description.add("Command ls takes a single argument – directory – and writes a directory listing");
		description = Collections.unmodifiableList(description);
		return description;
	}

}
