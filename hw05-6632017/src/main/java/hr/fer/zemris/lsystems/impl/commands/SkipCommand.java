package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
import hr.fer.zemris.math.Vector2D;

/**
 * Razred koji implemetira sučelje Command.
 * Kroz konstruktor prima step ; računa gdje kornjača mora otići; 
 * pamti u trenutnom stanju novu poziciju kornjače
 * 
 * @author Petra Barać
 *
 */

public class SkipCommand implements Command{
	
	private double step;
	
	public SkipCommand(double step) {
		this.step = step;
	}

	public void execute(Context ctx, Painter painter) {
		
		Vector2D stepVector = new Vector2D(step, step);
		
		Vector2D startPosition = ctx.getCurrentState().getPosition();
		
		Vector2D endPosition = startPosition.translated(stepVector);
			
		ctx.getCurrentState().setPosition(endPosition);
	}


}
