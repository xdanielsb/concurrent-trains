package model;

public class Station extends ElementRail {

	public static final int MAX_NUMBER_TRAIN_IN_STATION  = 3;

	private int numCurrentTrainInStation;
	private int numComingTrain; // the current number in station plus the trains coming
	
	public Station(String _name) {
		super(_name);
		numCurrentTrainInStation = 0;
		numComingTrain=0;
	}
	
	/**
	 * if the train want to arrive to the station and 
	 * there is not platform to park it waits
	 */
	public synchronized void arrive() {
		if( numCurrentTrainInStation == MAX_NUMBER_TRAIN_IN_STATION) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		numCurrentTrainInStation++;
		numComingTrain--;
		notifyAll();
	}
	
	/**
	 * if a train leaves the station this reduces the number of 
	 * trains in the station and notify other trains that are waiting
	 * possible, some trains that want to park in a platform of a station
	 */
	public synchronized void leave() {
		numCurrentTrainInStation--;
		notifyAll();
	}

	/**
	 * This method is used when is configured the test of the TrainSimulator
	 * due, is configure in code, it is neccesary a method different 
	 * from arrive, but, increments the number of trains in the station
	 * @return
	 */
	public boolean addTrain() {
		if( numCurrentTrainInStation == MAX_NUMBER_TRAIN_IN_STATION) { 
			System.out.println("It is not possible to add another train"+
						 	   "to the station is full");
			return false;
		}
		numCurrentTrainInStation++;
		return true;
	}

	public int getNumCurrentTrainInStation() {
		return numCurrentTrainInStation;
	}

	public synchronized void isPossibleGo() {
		System.out.println(this + " Coming " + numComingTrain);
		while (numComingTrain + numCurrentTrainInStation >= MAX_NUMBER_TRAIN_IN_STATION)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	
	
	public synchronized void incrementNumberComingTrain() {
		numComingTrain++;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	
}
