package model;

public class Section extends ElementRail {

	public Section(String _name) {
		super(_name);
	}

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

	@Override
	public synchronized void leave() {
		numTrainsInRail = 0;
		System.out.println( this + " is unblocked");
		notifyAll();
	}


}
