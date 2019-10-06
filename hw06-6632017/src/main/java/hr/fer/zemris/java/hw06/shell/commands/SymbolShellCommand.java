package hr.fer.zemris.java.hw06.shell.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hr.fer.zemris.java.hw06.shell.*;

/**
 * Command symbol is used to print asked symbol.Permitted symbols are PROMP, MULTILINE, MORELINE.
 * Command can be used to change these symbols. Command check number of given attributes,
 * if only one attribute (asked symbol) is given, then command print that symbol,
 * otherwise change symbol to new given symbol (two arguments : name of symbol and new symbol)
 * 
 * 
 * @author Petra Barać
 *
 */

public class SymbolShellCommand implements ShellCommand{

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		
		
		String[] args = arguments.split(" ");
		
		
		if(args.length > 2 || args.length < 1) {
			throw new ShellIOException("command symbol need to have one or two arguments");
		}
		
		switch (args[0]) {
		case "PROMPT": 
			if(args.length == 1) {
				env.writeln("Symbol for PROMPT is '"  + env.getPromptSymbol() + "'" );
			} else {
				env.writeln("Symbol for PROMPT changed from '" + env.getPromptSymbol() + "'" + " to '" + args[1] + "'" );
				env.setPromptSymbol(args[1].charAt(0));
			  }
			break;
		case "MORELINES":
			if(args.length == 1) {
				env.writeln("Symbol for MORELINES is '"  + env.getMorelinesSymbol() + "'");
			} else {
				env.writeln("Symbol for MORELINES changed from '" + env.getMorelinesSymbol() + "'"  + " to '" + args[1] + "'" );
				env.setMorelinesSymbol(args[1].charAt(0));
			  }			
			break;
		case "MULTILINE":
			if(args.length == 1) {
				env.writeln("Symbol for MULTILINE is '"  + env.getMultilineSymbol() + "'" );
			} else {
				env.writeln("Symbol for MULTILINE changed from '" + env.getMultilineSymbol() + "'"  + " to '" + args[1] + "'" );
				env.setMultilineSymbol(args[1].charAt(0));
			  }
			
			break;
		default:
			throw new ShellIOException("Commands for symbol can be only :MULTILINE, MORELINES or PROMPT");
		}
		
	return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "symbol";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> description = new ArrayList<>();
		description.add("type \"symbol\" + \" name of symbol\" to see chosen symbol");
		description.add("\n");
		description.add("example \" symbol PROMPT \"");
		description.add("use it to change symbol");
		description = Collections.unmodifiableList(description);
		return description;
	}
	

}
