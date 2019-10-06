package hr.fer.zemris.math;

/**
 * Razred modelira 2D vektor čije su komponente realni brojevi x i y.
 *  Uzima se da je svaki vektor iz iskodista(0, 0) te da mu je tocka (x, y) krajnja tocka.
 * 
 * @author Petra Barać
 *
 */

public class Vector2D {
	
	private double x;
	private double y;
	
	/**
	 * Koristi se kao granica kod provjere jednakosti dva decimalna broja.
	 */
	private static final double E = 0.001;
	
	public Vector2D(double x, double y) {
		if(x == 0 && y == 0) {
			throw new NullPointerException("Vector can not have both components equals to zero.");
		}
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Getter za parametar x
	 * @return x
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Getter za parametar y
	 * @return y
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Translacija vektora obzirom na predani vektora. 
	 * Radi se zasebnim zbrajanjem x komponenti dvaju vektora,
	 * i y komponenti dvaju vektora. Metoda azurira vrijednosti vektora 
	 * translatiranim vrijednostima.
	 * @param offset parametar vektor preko kojeg se obavlja translacija
	 */
	public void translate(Vector2D offset) {
		x= x + offset.x;
		y = y + offset.y;
		
	}
	
	/**
	 * Translacija vektora obzirom na predani vektora. Translatirani vektor 
	 * je novi vektor.
	 * @param offset parametar vektor preko kojeg se obavlja translacija
	 * @return translatirani vektor
	 */
	public Vector2D translated(Vector2D offset) {
		Vector2D translated = this.copy();
		return new Vector2D(translated.x+ offset.x, translated.y + offset.y);
	}
	
	/**
	 * Rotacija vektora za odredjeni kut u radijanima.
	 * Radi se prema formuli:
	 * 						x2=cosβx1−sinβy1
	 * 						y2=sinβx1+cosβy1
	 * Metoda azurira vrijednosti vektora koji je poslan na rotaciju.
	 * @param angle kut rotacije
	 */
	public void rotate(double angle) {
		double savedX = x;
		double savedY = y;
		x = Math.cos(angle) * savedX - Math.sin(angle) * savedY;
		y = Math.sin(angle) * savedX + Math.cos(angle) * savedY;
		
	}
	
	/**
	 * Rotacija vektora za odredjeni kut u radijanima.
	 * Metoda kreira novi rotirani vektor.
	 * @param angle
	 * @return rotirani vektor
	 */
	public Vector2D rotated(double angle) {
		Vector2D rotated = this.copy();
		
		return new Vector2D(Math.cos(angle) * rotated.x - Math.sin(angle) * rotated.y,
				Math.sin(angle) * rotated.x + Math.cos(angle) * rotated.y);
		
	}
	
	/**
	 * Skaliranje vektora. Metoda azurira vrijednosti vektora
	 * @param scaler faktor skaliranja
	 */
	public void scale(double scaler) {
		x = x * scaler;
		y = y * scaler;
	}
	
	/**
	 * Skaliranje vektora. Metod kreira novi skalirani vektor.
	 * @param scaler faktor skaliranja
	 * @return skairani vektor
	 */
	public Vector2D scaled(double scaler) {
		Vector2D scaled = this.copy();
		return new Vector2D(scaled.x * scaler, scaled.y * scaler);
		
	}
	
	/**
	 * Kopiranje vektor u novi vektor.
	 * @return kopirani vektor
	 */
	public Vector2D copy() {
		return new Vector2D(x, y);
	}
	
	/**
	 * Metoda vraca da su dva broja jednaka ako je
	 * apsolutna vrijednost njihove razlike manja od E.
	 * @param other drugi vektor za provjeru jednakosti.
	 * @return true ako su dva vektora jednaka, inace false.
	 */
	
	public boolean equals(Vector2D other) {
		if(Math.abs(other.x - x) < E && Math.abs(other.y - y) < E) {
			return true;
		}
		return false;
	}
	
}
