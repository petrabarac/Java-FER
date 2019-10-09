package hr.fer.zemris.java.gui.layouts;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.standard.Copies;

public class CalcLayout implements LayoutManager2{
	
	public static final int MAX_COMPONENTS = 31;
	public static final int DEFAULT_MARGINE = 0;
	public static final int NO_ROWS = 5;
	public static final int NO_COLUMNS = 7;
	public static final int FIRST_COMPONENT_WIDTH = 5;
	public static final RCPosition FIRST = new RCPosition(1,1);
	
	
	private int widthHeigthGap;
	private Map<Component, RCPosition> components;
	
	public  CalcLayout() {
		this(DEFAULT_MARGINE);
	}
	
	public  CalcLayout(int gap) { 
		if(gap < 0) {
			throw new CalcLayoutException("Margine between copomentns can not be negativenumber");
		}
		this.widthHeigthGap = gap;
		components = new HashMap<Component, RCPosition>();
	}

	@Override
	public void addLayoutComponent(String name, Component comp) {
		throw new UnsupportedOperationException();		
	}

	@Override
	public void removeLayoutComponent(Component comp) {
		components.remove(comp);
	}
	
	
	private interface SizeGetter {
		Dimension getSize(Component comp);
		}
//////////////////////
	@Override
	public Dimension preferredLayoutSize(Container parent) {
		return calculateDimension(parent, Component::getPreferredSize);
	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		return calculateDimension(parent, Component::getMinimumSize);
	}
	@Override
	public Dimension maximumLayoutSize(Container target) {
		return calculateDimension(target, Component::getMaximumSize);
	}
///////////////////////////
	
	private Dimension calculateDimension(Container container, SizeGetter getter) {
		
		int n = container.getComponentCount();
		Dimension dim = new Dimension(0, 0);
		
		for(int i = 0; i < n; i++) {
			Component c = container.getComponent(i);
			Dimension cdim = getter.getSize(c);
			
			if(components.get(c).equals(FIRST)) {
				cdim.width = (cdim.width - (FIRST_COMPONENT_WIDTH - 1) * widthHeigthGap) / FIRST_COMPONENT_WIDTH;
			}
			
			if(c != null) {
				dim.width = Math.max(dim.width, cdim.width);
				dim.height = Math.max(dim.height, cdim.height);
			}
		}

		dim.width = NO_COLUMNS * dim.width + (NO_COLUMNS-1) * widthHeigthGap;
		dim.height = NO_ROWS * dim.height + (NO_ROWS - 1) * widthHeigthGap;
		
		Insets insets = container.getInsets();
		
		dim.width = dim.width + insets.left + insets.right;
		dim.height = dim.height + insets.bottom + insets.top;
		return dim;
	}
	
	
	
	@Override
	public void layoutContainer(Container parent) {
		
		Insets parentInsets = parent.getInsets();
				
		int componentWidth = (parent.getWidth() - (parentInsets.left + parentInsets.right + (NO_COLUMNS - 1) * widthHeigthGap)) /NO_COLUMNS;
		int componentHeigth = (parent.getHeight() - (parentInsets.top + parentInsets.bottom + (NO_ROWS- 1) * widthHeigthGap)) /NO_ROWS ;
		
		
		int firstComponentWidth = componentWidth * FIRST_COMPONENT_WIDTH  + widthHeigthGap * (FIRST_COMPONENT_WIDTH - 1);
		
		
		
		for (Map.Entry<Component, RCPosition> entry : components.entrySet()) {
			RCPosition position = entry.getValue();
			Component button = entry.getKey();
			
			int x = parentInsets.left+ (position.getColumn() -1) * componentWidth + (position.getColumn() - 1) * widthHeigthGap;
			int y = parentInsets.top + (position.getRow() - 1) * componentHeigth + (position.getRow() - 1) * widthHeigthGap;
			
			if(!position.equals(FIRST)) {
				button.setBounds(x, y, componentWidth, componentHeigth);
			} else {
				button.setBounds(x, y, firstComponentWidth, componentHeigth);
			}
		}

	}

	@Override
	public void addLayoutComponent(Component comp, Object constraints) {
		RCPosition position;
		
		if(constraints instanceof RCPosition){
			position = (RCPosition) constraints;
		} else if(constraints instanceof String) {
			position = RCPosition.extractPosition((String)constraints);
		} else {
			throw new UnsupportedOperationException("Invalid input for constraints");
		}
		
		try {
		checkPosition(position);
		}catch (UnsupportedOperationException e) {
			e.getMessage();		
		}
		
		components.put(comp, position);		
	}

	private void checkPosition(RCPosition position) {
		int noOfColumn = position.getColumn();
		int noOfRow = position.getRow();
		
		if(noOfRow > NO_ROWS || noOfColumn > NO_COLUMNS) {
			throw new CalcLayoutException("Constraints are out of range");	
		}
		
		if(noOfRow < 0 || noOfColumn < 0 ) {
			throw new CalcLayoutException("Index of row can be only between 1 and 7");	
		}
		
		if(components.containsValue(position)) {
			throw new CalcLayoutException("Component with given constraints already exists");
		}
		if(noOfRow == 1) {
			if(noOfColumn < 6 && noOfColumn > 1) {
				throw new CalcLayoutException("Colums between 1 and 6 in a first row are saved for (1, 1) component");	
			}
		}
	}


	@Override
	public float getLayoutAlignmentX(Container target) {
		return 0;
	}

	@Override
	public float getLayoutAlignmentY(Container target) {
		return 0;
	}

	@Override
	public void invalidateLayout(Container target) {
		// TODO Auto-generated method stub
		
	}

}
