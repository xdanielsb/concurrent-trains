package model;

/**
 * Représentation d'une section de voie ferrée. C'est une sous-classe de la
 * classe {@link Element}.
 *
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 */
public class Section extends Element {
	private boolean isFull;
	
	public Section(String name) {
		super(name);
		isFull=false;
	}
	
	@Override
	public synchronized void arrive() {
		while (isFull)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		isFull = true;
		notifyAll();
	}
	
	public synchronized void leave() {
		isFull = false;
		notifyAll();
	}
}
