package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.*;

/**
 *Razred koji implemetira sučelje Command,
 *briše jedno stanje s vrha stoga
 * 
 * @author Petra Barać
 *
 */
public class PopCommand implements Command {

	@Override
	public void execute(Context ctx, Painter painter) {
		ctx.popState();
	}
	
}
