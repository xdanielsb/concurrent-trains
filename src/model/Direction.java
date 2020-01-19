package model;

public class Direction {

	private String name;
	public static final Direction LR = new Direction("LeftRight");
	public static final Direction RL = new Direction("RightLeft");
	
	/**
	 * Constructor Direction, private to avoid 
	 * invalid directions
	 * @param _name of the Sens of the direction
	 */
	private Direction(String _name) {
		name = _name;
	}
	
	@Override
	public String toString( ) {
		return this.name;
	}
}
