package hr.fer.zemris.lsystems.impl.commands;

import java.awt.Color;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;

/**
 * Razred koji implemetira sučelje Command.
 * Kroz konstruktor prima boju te 
 * u trenutno stanje kornjače zapisuje predanu boju
 *  
 * @author Petra Barać
 *
 */
public class ColorCommand implements Command {
	
	private Color color;
	
	public ColorCommand(Color color) {
		this.color = color;
	}

	public void execute(Context ctx, Painter painter) {
		ctx.getCurrentState().setColor(color);
	}

}
