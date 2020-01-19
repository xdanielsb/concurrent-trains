package model;

public class Section extends ElementRail {

	public Section(String _name) {
		super(_name);
	}

	/**
	 * The section just can handle one train in t(s)
	 * if there is a train, and another want wants to
	 * go there this is block.
	 */
	@Override
	public synchronized void arrive() {
		while( numTrainsInRail == 1 )
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		System.out.println( this + " is blocked");
		numTrainsInRail = 1;
	}

	/**
	 * When a trains leaves the section this notify
	 * the other that were waiting.
	 */
	@Override
	public synchronized void leave() {
		numTrainsInRail = 0;
		System.out.println( this + " is unblocked");
		notifyAll();
	}
}
