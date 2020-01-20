package logic;
import java.util.NoSuchElementException;

import model.Direction;
import model.ElementRail;
import model.Station;
import model.Train;
/**
 *  Monitor of the trains and the Elements of the rail
 *  {Section, Station} let synchronize the rail of trains.
 */

public class Line {
	
	private ElementRail[] elements;
	private Direction currentDirection;
	private int numberOfTrainsInTraject;
	
	private int id;
	
	public Line( int _id) {
		id = _id;
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
		for( int i = 1; i < els.length; i++) {
			int nx = elements[i-1].getCord().getX();
			elements[i].getCord().setX(nx + 50);
		}
	}
	
	/**
	 * 
	 * @param pos
	 */
	public synchronized void advance( ElementRail pos) {
	    pos.arrive();
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
		if( res == null) {
			System.out.println( currentPos  + " " + dir + " NOT FOUND at "+this);
			throw new NoSuchElementException("There was not found a valid pos");
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

	public ElementRail[] getElements() {
		return elements;
	}
	
	public int getNumElementsRail() {
		return elements.length;
	}

	
	public Station getStart() {
		return (Station) elements[0];
	}
	
	public Station getEnd() {
		return (Station) elements[elements.length-1];
	}
	
	@Override
	public String toString() {
		return "Line "+id;
	}


	public Station nextStation(ElementRail currentPos) {
		if (currentPos == this.getStart()) {
			return this.getEnd();
		}
		else if (currentPos == this.getEnd()) {
			return this.getStart();
		}
		else {
			throw new IllegalStateException("The train is not at a station but is searching for a new one.");
		}
	}

	
}
