package hr.fer.zemris.java.hw06.shell;

import java.util.Collections;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Environment for communication between user and shell.
 * 
 * @author Petra Barać
 *
 */

public class CommunicationEnvironment implements Environment {
	
	Scanner sc = new Scanner(System.in);
/**
 * Defined PROMPT, MORELINE and MULTILINE symbols.
 */
	private static char PROMPT = '>';
	private static char MORELINES = '\\';
	private static char MULTILINE = '|';
			
	
	private static SortedMap<String, ShellCommand> commands = new TreeMap<>();
	
	public CommunicationEnvironment(SortedMap<String, ShellCommand> commands) {
		this.commands = commands;
	}

	/**
	 * Method reeds next user input
	 */
	@Override
	public String readLine() throws ShellIOException {
		return sc.nextLine();
	}

	/**
	 * Write to user (console)
	 */
	@Override
	public void write(String text) throws ShellIOException {
		System.out.print(text);
		
	}

	/**
	 * Write users to and adding new line
	 */
	@Override
	public void writeln(String text) throws ShellIOException {
		System.out.println(text);
		
	}

	/**
	 * Return unmodifiable map, so that the client can not delete commands by clearing the map
	 */
	@Override
	public SortedMap<String, ShellCommand> commands() {

		SortedMap unmodsortmap = Collections.unmodifiableSortedMap(commands);
		return unmodsortmap;
	}
	/**
	 * Getter method for MULTILINE symbol
	 */
	@Override
	public Character getMultilineSymbol() {
		return this.MULTILINE;
	}

	/**
	 * Setter method for MULTILINE symbol 
	 */
	@Override
	public void setMultilineSymbol(Character symbol) {
		this.MULTILINE = symbol;
		
	}

	/**
	 * Getter method for PROMPT symbol
	 */
	@Override
	public Character getPromptSymbol() {
		return this.PROMPT;
	}

	/**
	 * Setter method for PROMPT symbol 
	 */
	@Override
	public void setPromptSymbol(Character symbol) {
		this.PROMPT = symbol;
		
	}

	/**
	 * Getter method for MORELINES symbol
	 */
	@Override
	public Character getMorelinesSymbol() {
		return this.MORELINES;
	}

	/**
	 * Setter method for MORELINES symbol
	 */
	@Override
	public void setMorelinesSymbol(Character symbol) {
		this.MORELINES = symbol;
		
	}
}
