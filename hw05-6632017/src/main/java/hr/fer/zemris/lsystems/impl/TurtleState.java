package hr.fer.zemris.lsystems.impl;

import java.awt.Color;

import hr.fer.zemris.math.*;


/**
 * (kornjača - sinonim za objekt koji kako bi lakše implementirali crtanje po ekranu.
 * Nalazi se na ekranu te se može kretati po njemu i ostavljati trag)
 * 
 * Razred pamti trenutnu poziciju na kojoj se kornjača nalazi. 
 * Vector2D je radij vektor.
 * @author Petra Barać
 *
 */
public class TurtleState {
	
	/**
	 * trenutna pozicija na kojoj se kornjača nalazi
	 */
	private Vector2D position;
	/**
	 * smijer u kojem kornjača gleda
	 * za 0° kornjača gleda u desno a za 90° put gore
	 */
	private Vector2D direction;
	/**
	 * boja kojom kornjača crta
	 */
	private Color color;
	/**
	 * trenutna efektivna duljina pomaka
	 */
	private double step;
	
	public TurtleState(Vector2D position, Vector2D direction, Color color, double step) {
		this.position = position;
		this.direction = direction;
		this.color = color;
		this.step = step;
	}
	
	/**
	 * Javna metoda koja vraća novi objekt s 
	 * kopijom trenutnog stanja
	 * 
	 * @return novi objekt s kopijom trenutnog stanja
	 */
	public TurtleState copy() {
		return new TurtleState(position, direction, color, step);
	}

	public Vector2D getPosition() {
		return position;
	}

	public void setPosition(Vector2D position) {
		this.position = position;
	}

	public Vector2D getDirection() {
		return direction;
	}

	public void setDirection(Vector2D direction) {
		this.direction = direction;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public double getStep() {
		return step;
	}

	public void setStep(double step) {
		this.step = step;
	}

	
}
