package hr.fer.zemris.java.hw06.shell.commands;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

/**
 * The tree command expects a single argument: directory name and prints a tree (each directory level shifts
 * output two charatcers to the right).
 * 
 * @author Petra Barać
 *
 */
public class TreeShellCommand implements ShellCommand {

	@Override
	public ShellStatus executeCommand(Environment env, String arguments)  {
		
		Path path = Paths.get(arguments);
		
		if(!Files.isDirectory(path)) {
			env.write("Argument must be directory");
		}
		try {
			Files.walkFileTree(path, new TreeVisitor(env));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ShellStatus.CONTINUE;
	}
	
	private static class TreeVisitor implements FileVisitor<Path> {
		
		private int level;
		private Environment env;

		TreeVisitor(Environment env) {
			this.env = env;
		}

		private void print(Path file) throws IOException {
			if (level == 0) {
				env.writeln(file.normalize().toAbsolutePath().toString());
			} else {
				env.writeln(String.format("%" + (2 * level) + "s%s", "", file.getFileName()));
			}
		}
		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
			print(dir);
			level++;
			return FileVisitResult.CONTINUE;
		}
		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			print(file);
			return FileVisitResult.CONTINUE;
		}
		@Override
		public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
			return FileVisitResult.CONTINUE;
		}
		@Override
		public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
			level--;
			return FileVisitResult.CONTINUE;
		}
	}


	@Override
	public String getCommandName() {
		return "tree";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> description = new ArrayList<>();
		description.add("The tree command expects a single argument: directory name and prints a tree (each directory level shifts\n" + 
				"output two charatcers to the right).");
		description = Collections.unmodifiableList(description);
		return description;
		
	}

}
