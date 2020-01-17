package model;

public abstract class ElementRail {
	protected String name;
	protected int numTrainsInRail;
	public ElementRail( String _name) {
		name = _name;
		numTrainsInRail = 0;
	}
	@Override
	public String toString() {
		return name;
	}
	public abstract void arrive();
	public abstract void leave();
}
