package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;

/**
 * Razred koji implemetira sučelje Command.
 * Kroz konstruktor prima factor ; u trenutnom stanju ažurira efektivnu 
 * duljinu pomaka skaliranjem s danim faktorom
 * 
 * @author Petra Barać
 *
 */

public class ScaleCommand implements Command {
	
	private double factor;
	
	public ScaleCommand(double factor) {
		this.factor = factor;
	}

	public void execute(Context ctx, Painter painter) {
		
		double newStep =ctx.getCurrentState().getStep() * factor;
		
		ctx.getCurrentState().setStep(newStep);
		
	}

}
