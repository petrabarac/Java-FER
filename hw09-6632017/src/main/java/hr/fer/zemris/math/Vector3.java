package hr.fer.zemris.math;

import java.text.DecimalFormat;

public class Vector3 {
	
	private double x;
	private double y;
	private double z;
	
	
	
	public Vector3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getX() {
		return x;
	} 
	public double getY() {
		return y;
	} 
	public double getZ() {
		return z;
	}
	
	public double norm() {
		
		return Math.sqrt(x*x + y*y + z*z);
	}

	public Vector3 normalized() {
		double norm = norm();
		if(norm == 0) {
			throw new NullPointerException("Vectors norm is equal to zero; can not divide with zero");
		}
		
		return new Vector3(x/norm, y/norm, z/norm);
	}

	public Vector3 add(Vector3 other) {
		return new Vector3(x + other.x, y + other.y, z + other.z);
	} 
	
	public Vector3 sub(Vector3 other) {
		return new Vector3(x - other.x, y - other.y, z - other.z);
		
	} 
	
	public double dot(Vector3 other) {
		return x * other.x + y * other.y + z * other.z;
	}

	public Vector3 cross(Vector3 other) {
		double crossX = y * other.z - z * other.y;
		double crossY = z * other.x - x * other.z;
		double crossZ = x * other.y - y * other.x;

		return new Vector3(crossX, crossY, crossZ);
	}
	
	public Vector3 scale(double s) {
		return new Vector3(x * s, y * s, z * s);
	}

	public double cosAngle(Vector3 other) {
		double a = norm();
		double b = other.norm();
		
		if(a == 0 && b == 0) {
			throw new NullPointerException("Vectors norm is equal to zero; can not divide with zero");
		}
		
		return dot(other) / (a * b);
	}

 
	public double[] toArray() {
		
		return new double [] {x, y, z };

	}
	public String toString() {
		DecimalFormat df = new DecimalFormat( "##0.000000" );
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(df.format(x)).append(", ");
		sb.append(df.format(y)).append(", ");
		sb.append(df.format(z));
		sb.append(")");
		
		return sb.toString();
	}


}
