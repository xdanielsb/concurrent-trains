package model;


/**
 * Représentation d'un circuit constitué d'éléments de voie ferrée : gare ou
 * section de voie
 * 
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 */
public class Railway {
	private final Element[] elements;
	private boolean isInUse;
	private Direction currentDirection;
	private volatile int numberOfTrainsMoving;
	
	public Railway(Element[] elements) {
		if(elements == null)
			throw new NullPointerException();
		
		isInUse = false;
		numberOfTrainsMoving = 0;
		this.elements = elements;
		for (Element e : elements)
			e.setRailway(this);
	}
	
	private int getIndex( Position p) {
		for( int i = 0; i < elements.length; i++) 
			if( elements[i].toString() == p.getElement().toString() ) {
				return i;
			}
		return -1;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		boolean first = true;
		for (Element e : this.elements) {
			if (first)
				first = false;
			else
				result.append("--");
			result.append(e);
		}
		return result.toString();
	}
	
	public boolean isBeginning(Element e) {
		return e==elements[0];
	}
	public boolean isEnd(Element e) {
		return e==elements[elements.length-1];
	}

	public synchronized void move( Train t) {
		Direction dir = t.getPos().getDirection();
		if( isInUse && currentDirection != dir)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		numberOfTrainsMoving++;
		currentDirection = dir;
		isInUse = true;
		if( dir == Direction.LR) {
			int newPos  =  getIndex(t.getPos()) + 1;
			if( newPos < elements.length) {
				t.setPos(new Position(elements[newPos], Direction.LR));
				elements[newPos-1].leave();
				System.out.println("The train "+ t.getNameTrain() + " leaves station"+elements[newPos-1]);
				elements[newPos].arrive();
				System.out.println("The train "+ t.getNameTrain() + " arrives station"+elements[newPos]);
			}
		}else {
			int newPos  =  getIndex(t.getPos()) - 1;
			if( newPos >= 0) {
				t.setPos(new Position(elements[newPos], Direction.RL));
				elements[newPos+1].leave();
				System.out.println("The train "+ t.getNameTrain() + " leaves station"+elements[newPos+1]);
				elements[newPos].arrive();
				System.out.println("The train "+ t.getNameTrain() + " arrives station"+elements[newPos]);
			}
		}
		notifyAll();
	}
}
