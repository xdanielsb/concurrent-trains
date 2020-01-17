package model;

public class Station extends ElementRail {

	private int numMaxTrainInStation;
	private int numCurrentTrainInStation;
	public Station(String _name) {
		super(_name);
		numMaxTrainInStation  = 3;
		numCurrentTrainInStation = 0;
	}
	
	
	public synchronized void arrive() {
		if( numCurrentTrainInStation == numMaxTrainInStation) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		numCurrentTrainInStation++;
	}
	
	public synchronized void leave() {
		numCurrentTrainInStation--;
		notifyAll();
	}

	public boolean addTrain() {
		if( numCurrentTrainInStation == numMaxTrainInStation) return false;
		numCurrentTrainInStation++;
		return true;
	}
	
}
