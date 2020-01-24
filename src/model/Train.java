package model;

import logic.ControlRailway;
import logic.Line;

/**
 * Class representing a train in the railway.
 * Every train is a separate thread.
 * 
 * @author Daniel Santos
 * 		   Guillem Sanyas
 *
 */
public class Train extends Thread {
	
	/**
	 * Name of the train
	 */
	private String name;
	/**
	 * Current position of the train
	 */
	private ElementRail currentPos;
	/**
	 * Initial origin of the train
	 */
	private Station origin;
	/**
	 * Destination of the current train
	 */
	private Station destiny;
	/**
	 * Direction of the current train,
	 * calculated with origin and destiny
	 */
	private Direction direction;
	/**
	 * Monitor, that is synchronized with
	 * other trains in the same portion of the railway
	 * (called line).
	 */
	private Line currentLine;
	/**
	 * The coordinates of the train, used for display only.
	 */
	private Coordinate cord;
	
	/**
	 * The entire railway system, that is used
	 * to switch between lines at a given station.
	 */
	private ControlRailway ctrl;
	
	
	/**
	 * Constructor for the Train.
	 * 
	 * @param _name : the name of the train.
	 * @param line : the line in which the train is initially set. The
	 * origin must always be a station of this line.
	 * @param control : the entire railway system.
	 */
	public Train(String _name, Line line,ControlRailway control) {
		name = _name;
		currentLine = line;
		ctrl = control;
		cord = new Coordinate();
	}

	/**
	 * Makes the train advance in the railway.
	 * It is the brain of the simulation.
	 */
	//TODO complete commentary
	public void advance() {
		ElementRail lst = currentPos;

		if (currentPos == getOrigin() && direction == Direction.RL ||
			currentPos == getDestiny() && direction == Direction.LR) {
			System.out.println("Change of line of the Train: " + this);
			currentLine.decrementTrainsInTraject();
			currentLine = ctrl.getNextLine( currentLine, direction);
		}
		if (currentPos instanceof Station) {
			Station st;	
			st = currentLine.nextStation(currentPos);
			st.isPossibleGo();
			st.incrementNumberComingTrain();
		}
		ElementRail nxt = currentLine.getNext( currentPos, direction);
		currentLine.isTheSameDirection( direction);
		if( currentPos instanceof Station) {
			if( currentLine.getNumberOfTrainsInTraject() == 0) {
				// this means there is no other train
				// using the line
				
				currentLine.incrementTrainsInTraject();
				
				currentLine.setCurrentDirection(direction);
			}else {
				currentLine.incrementTrainsInTraject();
			}
		}
		nxt.arrive();

		currentPos = nxt;
		getCord().setX(nxt.getCord().getX());
		// just leaves when is possible arrive
		lst.leave();
	}

	/**
	 * Set the target station of a train
	 * @param tgt : the destination of the train.
	 */
	public void setDestiny(Station tgt) {
		destiny = tgt;
	}
	
	/**
	 * Getter for the origin of the train.
	 * 
	 * @return the origin.
	 */

	public void setDirection(Direction dir) {
		direction = dir;
	}
	
	public Station getOrigin() {
		return origin;
	}

	public Station getDestiny() {

		return destiny;
	}

	/**
	 * Set the traject of a train.
	 * 
	 * @param src source of the traject
	 * @param _destiny destiny of the traject
	 */
	public void addTraject(Station src, Station _destiny) {
		currentPos = src;
		origin = src;
		destiny = _destiny;
		//find the direction in which the train
		//have to go
		direction = ctrl.getIndex(src) < ctrl.getIndex(_destiny)?
			        Direction.LR : Direction.RL;
		cord.setX(src.getCord().getX());
		System.out.println(src +" to "+ _destiny + " dir= " + direction);
	}
	
	/**
	 * Getter for the coordinates of the train,
	 * used for display.
	 * 
	 * @return these coordinates.
	 */
	public Coordinate getCord() {
		return cord;
	}
	
	public void setOrigin(Station origin) {
		origin.incrementNumberCurrentTrain();
		this.origin = origin;
	}

	/**
	 * Setter for the coordinates of the train,
	 * used for display.
	 * 
	 * @param cord : the new coordinates.
	 */
	public void setCord(Coordinate cord) {
		this.cord = cord;
	}

	@Override
	public String toString() {
		return this.name;
	}
	
	/**
	 * Getter for the current direction
	 * in which the train is going.
	 * 
	 * @return this direction.
	 */
	public Direction getDirection() {
		return this.direction;
	}
	
	@Override
	public void run() {
		while( currentPos != destiny) {
			System.out.println("Current pos  "+this.name+ " = "+currentPos );
			advance();
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ctrl.getWindow().repaint();
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~"+this.name +" Arrives ");
		currentLine.decrementTrainsInTraject();
		
	}

	public Line getCurrentLine() {
		return currentLine;
	}
	
	
}
