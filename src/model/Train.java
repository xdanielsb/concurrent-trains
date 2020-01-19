package model;

import logic.ControlRailway;

public class Train extends Thread {
	
	/**
	 * Name of the train
	 */
	private String name;
	/**
	 * Current pos of the train
	 */
	private ElementRail currentPos;
	/**
	 * Destiny of the current train
	 */
	private ElementRail destiny;
	/**
	 * Direction of the current train
	 */
	private Direction direction;
	/**
	 * Monitor, that let synchronize with
	 * other trains in the railway.
	 */
	private ControlRailway ctrl;
	private Coordinate cord;
	
	
	public Train(String _name, ControlRailway _ctrl) {
		name = _name;
		ctrl = _ctrl;
	}

	/**
	 * Allows a train advance in the railway.
	 * 
	 */
	public void advance() { //omg
		ElementRail lst = currentPos;
		ElementRail nxt = ctrl.getNext( currentPos, direction);
		ctrl.isTheSameDirection( direction);
		if( currentPos instanceof Station) {
			// this means there is no other train
			// using the line
			if( ctrl.getNumberOfTrainsInTraject() == 0) {
				ctrl.incrementTrainsInTraject();
				ctrl.setCurrentDirection(direction);
			}else {
				ctrl.incrementTrainsInTraject();
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
	 * @param tgt
	 */
	public void setDestiny(Station tgt) {
		destiny = tgt;
	}
	
	/**
	 * Set the direction of a train
	 * @param dir
	 */
	public void setDirection(Direction dir) {
		direction = dir;
	}

	/**
	 * Set the traject of a train
	 * @param src source of the traject
	 * @param _destiny destiny of the traject
	 */
	public void addTraject(Station src, Station _destiny) {
		if( src.addTrain() ) { // is possible add another train
							   // to that station
			currentPos = src;
			destiny = _destiny;
			//find the direction in which the train
			//have to go
			direction = ctrl.getIndex(src) < ctrl.getIndex(_destiny)?
				        Direction.LR : Direction.RL;
			
			cord = new Coordinate();
			cord.setX(src.getCord().getX() + ((direction == Direction.LR)?-10:10));
			cord.setY(src.getCord().getY() + 30 
					+ (	src.getNumCurrentTrainInStation() + 
						_destiny.getNumCurrentTrainInStation()
							)*30 );
		}else {
			throw new IllegalStateException(
					"Is not possible to add a Train to this station");
		}
		
	}
		
	public Coordinate getCord() {
		return cord;
	}

	public void setCord(Coordinate cord) {
		this.cord = cord;
	}

	@Override
	public String toString() {
		return this.name;
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
		ctrl.decrementTrainsInTraject();
		
	}
}
