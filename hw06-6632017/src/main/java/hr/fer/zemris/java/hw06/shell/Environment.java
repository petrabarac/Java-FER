package hr.fer.zemris.java.hw06.shell;

import java.util.SortedMap;

/**
 * This is an abstraction which will be passed to each defined command. The each implemented command 
 * communicates with user (reads user input and writes response) only through this interface.
 * 
 * @author Petra Barać
 *
 */
public interface Environment {

	String readLine() throws ShellIOException;
	void write(String text) throws ShellIOException;
	void writeln(String text) throws ShellIOException;
	SortedMap<String, ShellCommand> commands();
	Character getMultilineSymbol();
	void setMultilineSymbol(Character symbol);
	Character getPromptSymbol();
	void setPromptSymbol(Character symbol);
	Character getMorelinesSymbol();
	void setMorelinesSymbol(Character symbol);
}
