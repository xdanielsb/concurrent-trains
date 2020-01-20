package model;

public abstract class ElementRail {
	protected String name;
	protected int numTrainsInRail;
	protected Coordinate cord;
	/**
	 *
	 * @param _name name of the element rail
	 */
	public ElementRail( String _name) {
		name = _name;
		numTrainsInRail = 0;
		cord = new Coordinate(100, 130);
	}
	/**
	 * if the method is fully executed
	 * means that a train can land in a given
	 * Element Rail
	 */
	public abstract void arrive();
	public abstract void leave();
	
	public Coordinate getCord() {
		return cord;
	}
	public void setCord(Coordinate cord) {
		this.cord = cord;
	}
	@Override
	public String toString() {
		return name;
	}
}
