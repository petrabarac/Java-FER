package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
import hr.fer.zemris.lsystems.impl.TurtleState;

/**
 * Razred koji implemetira sučelje Command,
 * kroz konstruktor prima angle ; u trenutnom stanju modificira vektor
 * smjera gledanja kornjače tako što ga rotira za zadani kut
 * 
 * @author Petra Barać
 *
 */

public class RotateCommand implements Command{

	/**
	 * zadani kut
	 */
	private double angle;
	
	public RotateCommand(double angle) {
		this.angle = angle;
	}
	
	public void execute(Context ctx, Painter painter) {
		TurtleState state = ctx.getCurrentState();
		state.setDirection(state.getDirection().rotated(angle));
	}
}
