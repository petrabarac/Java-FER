package hr.fer.zemris.java.gui.layouts;

import java.util.Objects;

public class RCPosition {
	private int row;
	private int column;
	
	public RCPosition(int row, int column) {
		this.row = row;
		this.column = column;
	}
	

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}
	
	public static RCPosition extractPosition(String position){
		if(position.contentEquals(" ") || position.equals("") || position == null) {
			throw new CalcLayoutException("Invalid input for defining component position");
		}
		
		if(!position.contains(",")) {
			throw new CalcLayoutException("Invalid input for defining component position. Position need to be define like: column comma row");
		}
		
		String[] args = position.trim().split(",");
		
		if(args.length != 2) {
			throw new CalcLayoutException("Component position only contains number of row and number of a column");
		}
		
		return new RCPosition(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
	
	}


	@Override
	public int hashCode() {
		return Objects.hash(column, row);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof RCPosition))
			return false;
		RCPosition other = (RCPosition) obj;
		return column == other.column && row == other.row;
	}
	
	
	
}
