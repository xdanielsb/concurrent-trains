package model;

/**
 * 
 * Directions in which trains can go.
 * Only two exists in our simple model : left to right and right to left.
 * 
 * @author Daniel Santos
 * 		   Guillem Sanyas
 *
 */
public class Direction {

	/**
	 * The name given to the direction.
	 */
	private String name;
	/**
	 * Left to right direction.
	 */
	public static final Direction LR = new Direction("LeftRight");
	/**
	 * Right to left direction.
	 */
	public static final Direction RL = new Direction("RightLeft");
	
	/**
	 * Constructor Direction, private to avoid 
	 * invalid directions
	 * 
	 * @param : _name of the direction
	 */
	private Direction(String _name) {
		name = _name;
	}
	
	@Override
	public String toString( ) {
		return this.name;
	}
}
