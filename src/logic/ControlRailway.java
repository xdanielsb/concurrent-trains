package logic;

import model.Direction;
import model.ElementRail;
import model.Station;

public class ControlRailway {
	
	private ElementRail[] elements;
	
	public void addLine( ElementRail... els) {
		elements = els;
	}
	
	public synchronized void advance( ElementRail pos) {
	   pos.arrive();
	}

	public ElementRail getNext(ElementRail currentPos, Direction dir) {
		ElementRail res = null;
		for( int i = 0; i < elements.length && res == null; i++)
			if( elements[i] == currentPos) {
				res = dir == Direction.LR ? elements[i+1]: elements[i-1];
			}
		if( res == null) {
			System.out.println("There was not found any element");
		}
		return res;
	}
}
