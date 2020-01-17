package logic;

import model.Direction;
import model.ElementRail;
import model.Station;

public class ControlRailway {
	
	private ElementRail[] elements;
	private Direction currentDirection;
	private int numberOfTrainsInTraject;
	
	public ControlRailway() {
		numberOfTrainsInTraject = 0;
	}
		
	public synchronized int getNumberOfTrainsInTraject() {
		return numberOfTrainsInTraject;
	}


	public void addLine( ElementRail... els) {
		elements = els;
	}
	
	public synchronized void advance( ElementRail pos) {
	    pos.arrive();
	}
	
	public int getIndex(ElementRail el) {
		int idx = -1;
		for( int i= 0; i < elements.length && idx==-1; i++)
			if( el == elements[i]) idx = i;
		return idx;
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

	public Direction getCurrentDirection() {
		return currentDirection;
	}

	public synchronized void setCurrentDirection(Direction currentDirection) {
		System.out.println(" @@@@@@@@@ New Direction in RailWay" + currentDirection);
		this.currentDirection = currentDirection;
		notifyAll();
	}
	
	public synchronized void incrementTrainsInTraject() {
		numberOfTrainsInTraject++;
	}
	
	public synchronized void decrementTrainsInTraject() {
		numberOfTrainsInTraject--;
		notifyAll();
	}

	public synchronized void isTheSameDirection(Direction direction) {
		while( numberOfTrainsInTraject > 0  && direction != currentDirection ) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
