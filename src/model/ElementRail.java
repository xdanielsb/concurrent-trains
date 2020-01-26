package model;

/**
 * 
 * Abstraction of the concrete elements of the railway :
 * {Station,Section}.
 * 
 * @author Daniel Santos
 * 		   Guillem Sanyas
 *
 */
public abstract class ElementRail {
	
	/**
	 * The name of the element, given at his creation.
	 */
	protected String name;
	
	/**
	 * The number of trains in the element. Changes depending if its a station or a section.
	 */
	protected int numTrainsInRail;
	
	/**
	 * The coordinates of the element, used for display.
	 */
	protected Coordinate cord;
	
	/**
	 * Constructor for the element, used by its children
	 *
	 * @param _name name of the element rail
	 */
	protected ElementRail( String _name) {
		name = _name;
		numTrainsInRail = 0;
		cord = new Coordinate(100, 130);
	}
	
	/**
	 * If the method is fully executed
	 * it means that a train can land in this element.
	 * Otherwise train awaits.
	 */
	public abstract void arrive();
	
	/**
	 * Activated when a train leaves the element.
	 * Releases the other trains from their sleep.
	 */
	public abstract void leave();
	
	/**
	 * Gives the coordinates of the elements for
	 * diplay purposes.
	 * 
	 * @return those coordinates.
	 */
	public Coordinate getCord() {
		return cord;
	}
	
	/**
	 * Setter for the coordinates of the element.
	 * 
	 * @param cord : the new coordinates.
	 */
	public void setCord(Coordinate cord) {
		this.cord = cord;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
