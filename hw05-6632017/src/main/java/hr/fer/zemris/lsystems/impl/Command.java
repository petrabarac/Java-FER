package hr.fer.zemris.lsystems.impl;

import hr.fer.zemris.lsystems.Painter;

/**
 * Sučelje sa jednom metodom execute.
 * 
 * Implementacije komandi koje kornjača mora napaviti
 * nalaze se u razredu hr.fer.zemris.lsystems.impl.commands
 * (što je kornjača objašnjeno je u TurtleState razredu)
 * 
 * @author Petra Barać
 *
 */

public interface Command {

	public void execute(Context ctx, Painter painter);
	
	
}
