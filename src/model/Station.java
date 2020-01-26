package model;

/**
 * Class representing a station in the railway.
 * It can store a given number of trains.
 * Trains usually can leave in two directions, except if the
 * station is at the start or end of the railway.
 * 
 * @author Daniel Santos
 * 		   Guillem Sanyas
 *
 */
public class Station extends ElementRail {

	public static final int MAX_NUMBER_TRAIN_IN_STATION  = 3;

	/**
	 * The number of trains that are fully stopped
	 * at the station.
	 */
	private int numCurrentTrainInStation;
	
	/**
	 * The number of trains in the lines containing the station
	 * and which are coming directly to this station, thus cannot change.
	 * It is used to prevent deadlocks.
	 */
	private int numComingTrain;
	
	
	
	/**
	 * Basic constructor for the Station.
	 * 
	 * @param _name : the name of this element.
	 */
	public Station(String _name) {
		super(_name);
		numCurrentTrainInStation = 0;
		numComingTrain=0;
	}
	
	/**
	 * If the train want to arrive to the station and 
	 * there is no platform to park it, it waits.
	 * Since we control the departure of trains from neighbourg
	 * stations this is not expected to happen.
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
	 * If a train leaves the station this keeps tracks of it and
	 * notifies the trains waiting to get to this station.
	 */
	public synchronized void leave() {
		numCurrentTrainInStation--;
		notifyAll();
	}

	/**
	 * This method is used only for testing purposes.
	 * Increments the number of trains in the station.
	 * @return false if Station is full, true otherwise
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
	
	/**
	 * Helper method that check if is possible to add another train
	 * this is used in the initialization part.
	 * @return boolean
	 */
	public boolean isPossibleAddAnotherTrain() {
		return numCurrentTrainInStation < MAX_NUMBER_TRAIN_IN_STATION;
	}

	/**
	 * Getter for the current number of trains
	 * that are stopped in the station.
	 * 
	 * @return this number.
	 */
	public int getNumCurrentTrainInStation() {
		return numCurrentTrainInStation;
	}

	/**
	 * Method used by trains before going to this station.
	 * It avoids deadlocks by ensuring that trains
	 * in the neighbourgs stations do not leave their stations
	 * until we ensure there is enough room to store them in this
	 * station, counting the trains that are already coming and
	 * the ones that are currently stationning here.
	 */
	public synchronized void isPossibleGo() {
		System.out.println(this + " Coming " + numComingTrain);
		while (numComingTrain + numCurrentTrainInStation >= MAX_NUMBER_TRAIN_IN_STATION)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * Method that adds one train to the count of coming trains.
	 * Used by trains when they decide to come after ensuring
	 * that it is possible.
	 */
	public synchronized void incrementNumberComingTrain() {
		numComingTrain++;
	}
	
	/**
	 * Method used to incrementNumberCurrentTrains in Station
	 * this is used just in the initialization part, that is 
	 * the reason that is not synchronized.
	 */
	public void incrementNumberCurrentTrain() {
		numCurrentTrainInStation++;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
