package hr.fer.zemris.java.gui.charts;

import java.util.List;

public class BarChart {

	private List<XYValue> XYobjects;
	private String xDescription;
	private String yDescription;
	private int Ymin;
	private int Ymax;
	private int distance;
	
	public BarChart(List<XYValue> objects, String xDescription, String yDescription, int ymin, int ymax, int distance) {
		XYobjects = objects;
		
		this.xDescription = xDescription;
		this.yDescription = yDescription;
		
		Ymin = ymin;
		if(Ymin < 0) {
			
		}
		
		Ymax = setValidYmax(ymin,ymax,distance);
		
		if(Ymax > Ymin) {
			throw new IllegalArgumentException("Ymax can not be smaller than Ymin");
		}
		this.distance = distance;
		
		checkList();
	}
	
	
	private void checkList() {
		for(XYValue object : XYobjects) {
			if(object.getY() < Ymin) {
				throw new IllegalArgumentException("List contains XYVAlue object with y value smaller than Ymin");
			}
		}
	}
		
	private int setValidYmax(int Ymin, int Ymax, int distance) {
		while((Ymax - Ymin) % distance != 0) {
			Ymax++;
		}
		return Ymax;
	}


	public List<XYValue> getXYobjects() {
		return XYobjects;
	}


	public String getxDescription() {
		return xDescription;
	}


	public String getyDescription() {
		return yDescription;
	}


	public int getYmin() {
		return Ymin;
	}


	public int getYmax() {
		return Ymax;
	}


	public int getDistance() {
		return distance;
	}

	
	
	
}
