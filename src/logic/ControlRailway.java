package logic;
import java.util.NoSuchElementException;

import model.Direction;
import model.ElementRail;
/**
 *  Monitor of the trains and the Elements of the rail
 *  {Section, Station} let synchronize the rail of trains.
 */

public class ControlRailway {
	
	private ElementRail[] elements;
	private Direction currentDirection;
	private int numberOfTrainsInTraject;
	
	public ControlRailway() {
		numberOfTrainsInTraject = 0;
	}
	
	/**
	 * 
	 * @return number of trains that are using that railway
	 */
	public synchronized int getNumberOfTrainsInTraject() {
		return numberOfTrainsInTraject;
	}

	
	/**
	 * 
	 * @param els: List of {Station and Sections}
	 * in which the first one and last one are stations
	 * {Station1, Section1,...SectionN, Station2}
	 */
	public void addLine( ElementRail... els) {
		elements = els;
	}
	
	/**
	 * 
	 * @param pos
	 */
	public synchronized void advance( ElementRail pos) {
	    pos.arrive();
	}
	
	/**
	 * This method is used when is added a 
	 * trajet it find the right direction in
	 * which a train should go. 
	 * next index in which he needs to go
	 * @param el element to find in the line
	 * @return the index of that element
	 */
	public int getIndex(ElementRail el) {
		int idx = -1;
		for( int i= 0; 
				 i < elements.length && idx==-1;
				 i++)
			if( el == elements[i]) idx = i;
		
		if( idx == -1)
			throw new NoSuchElementException("The "+el+" was not found");
		return idx;
	}

	/**
	 * This method is used by the train to find the 
	 * next ElementRail in which it needs to go
	 * @param currentPos
	 * @param dir direction
	 * @return the index of that element
	 */
	public ElementRail getNext(ElementRail currentPos, Direction dir) {
		ElementRail res = null;
		for( int i = 0; i < elements.length && res == null; i++)
			if( elements[i] == currentPos) {
				res = dir == Direction.LR ? elements[i+1]: elements[i-1];
			}
		if( res == null)
			throw new NoSuchElementException("There was not found a valid pos");
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
