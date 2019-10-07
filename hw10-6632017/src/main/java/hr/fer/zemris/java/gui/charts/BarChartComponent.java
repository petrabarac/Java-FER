package hr.fer.zemris.java.gui.charts;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JComponent;

public class BarChartComponent extends JComponent{

	/**
	 * default serialversionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int TITLE_GAP = 15;
	private static final int NUMBERS_GAP = 10; 
	
	private BarChart barChart;
	private Graphics2D g2d;
	private AffineTransform defaultAt;
	 

	public BarChartComponent(BarChart barChart) {
		this.barChart = barChart;
	}
	
	public void drawYDescritpion() {
		String description = barChart.getyDescription();
		
		AffineTransform at = new AffineTransform();
        at.rotate(- Math.PI / 2);
        g2d.setTransform(at);
        g2d.drawString("description", -200, 50);
        
        g2d.setTransform(defaultAt);
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	      g2d = (Graphics2D) g;
	        defaultAt = g2d.getTransform();
	         
	        drawYDescritpion();   
	}

		

}
