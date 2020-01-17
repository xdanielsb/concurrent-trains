package model;

/**
 * Représentation d'un train. Un train est caractérisé par deux valeurs :
 * <ol>
 *   <li>
 *     Son nom pour l'affichage.
 *   </li>
 *   <li>
 *     La position qu'il occupe dans le circuit (un élément avec une direction) : classe {@link Position}.
 *   </li>
 * </ol>
 * 
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Mayte segarra <mt.segarra@imt-atlantique.fr>
 * Test if the first element of a train is a station
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 * @version 0.3
 */
public class Train extends Thread{
	private final String name;
	private Position currentPos;
	private Station destiny;
	
	private boolean isRunning;


	public Train(String name, Position p,Station destiny) throws BadPositionForTrainException {
		if (name == null || p == null)
			throw new NullPointerException();

		// A train should be first to be in a station
		if (!(p.getElement() instanceof Station))
			throw new BadPositionForTrainException(name);

	
		this.name = name;
		this.isRunning  = true;
		this.currentPos = p.clone();
		this.destiny=destiny;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("Train[");
		result.append(this.name);
		result.append("]");
		result.append(" is on ");
		result.append(this.currentPos);
		return result.toString();
	}
	
	public void move() {
		this.getPos().getElement().railway.move(this);
	}

	public Position getPos() {
		return currentPos;
	}

	public void setPos(Position pos) {
		this.currentPos = pos;
	}
	
	public void stopTrain() {
		isRunning = false;
	}
	
	public void changeTarget(Station newTarget) {
		destiny = newTarget;
	}
	
	public String getNameTrain() {
		return this.name;
	}
	
	@Override
	public void run() {
		while( currentPos.getElement() != destiny ) {	
			move();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
