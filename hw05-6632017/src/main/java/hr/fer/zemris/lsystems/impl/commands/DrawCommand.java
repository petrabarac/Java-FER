package hr.fer.zemris.lsystems.impl.commands;

import java.awt.Color;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
import hr.fer.zemris.math.Vector2D;

/**
 * Razred koji implemetira sučelje Command.
 * Kroz konstruktor prima step ; računa gdje kornjača mora otići; povlači liniju
 * zadanom bojom od trenutne pozicije kornjače do izračunate i pamti u trenutnom stanju novu poziciju
 * kornjače
 * 
 * @author Petra Barać
 *
 */

public class DrawCommand implements Command{
	
	private double step;
	
	public DrawCommand(double step) {
		this.step = step;
	}
	
	public void execute(Context ctx, Painter painter) {
	
		Color color = ctx.getCurrentState().getColor();
		
				
		Vector2D startPosition = ctx.getCurrentState().getPosition();
		Vector2D direction = ctx.getCurrentState().getDirection();
		Vector2D endPosition = startPosition.translated(direction.scaled(step * ctx.getCurrentState().getStep()));
	
		
		painter.drawLine(startPosition.getX(), startPosition.getY(), endPosition.getX(), endPosition.getY(),color, 1);
		
		ctx.getCurrentState().setPosition(endPosition);
			
	}

	

}
