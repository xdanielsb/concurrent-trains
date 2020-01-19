package model;

public abstract class ElementRail {
	protected String name;
	protected int numTrainsInRail;
	/**
	 *
	 * @param _name name of the element rail
	 */
	public ElementRail( String _name) {
		name = _name;
		numTrainsInRail = 0;
	}
	/**
	 * if the method is fully executed
	 * means that a train can land in a given
	 * Element Rail
	 */
	public abstract void arrive();
	public abstract void leave();
	
	@Override
	public String toString() {
		return name;
	}
}
