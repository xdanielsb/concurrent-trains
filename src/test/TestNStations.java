package test;

import logic.ControlRailway;
import logic.Line;
import model.ElementRail;
import model.Section;
import model.Station;
import model.Train;

public class TestNStations {
	
	private int nStations;
	private int nTrains;
	private Line[] lines;
	private Station[] listStations;
	ControlRailway ctrl = new ControlRailway();
	
	public TestNStations(int nStations, int nTrains) {
		this.nStations = nStations;
		this.nTrains = nTrains;
		listStations = new Station[ nStations ];
		lines = new Line[ nStations -1 ];
	}
	
	/**
	 * Create a train an set its origin
	 * @param id the id of the train
	 * @return the train
	 */
	public Train createTrain( int id) {
		Line l = null; 
		Station start = null;
		boolean flag = true;
		int rnd = (int) (Math.random()*(100));
		for( int i= 0; i < nStations && flag; i++) {
			l = lines[(rnd+i)%lines.length];
			if( l.getStart().isPossibleAddAnotherTrain()) {
				start = l.getStart(); flag = false;
			}else if( l.getEnd().isPossibleAddAnotherTrain()) {
				start = l.getEnd(); flag = false;
			}
		}
		Train t = new Train("Train "+id, l, ctrl);
		t.setOrigin(start);
		return t;
	}
	
	/**
	 * Set a destiny of the train, the 
	 * origin should be set to call this operation
	 * @param t train
	 */
	public void setPRandomDestiny(Train t) {
		Station end=null;
		int rnd = (int) (Math.random()*(100));
		boolean flag = true;
		for( int i= 0; i < nStations && flag; i++) {
			end = listStations[(i+rnd)%nStations]; 
			if(end != t.getOrigin()) {
				flag = false;
			}
		}
		t.setDestiny(end);
	}
	
	/**
	 * Base Logic to create a pseudo-random
	 * configuration for n-trains and m-
	 * stations
	 */
	public void start() {
		for( int  i = 0 ; i< nStations; i++) {
			char let = (char) ('A'+i);
			listStations[i]= new Station(let+"");
		}
		
		int nSections = 0;
		int nLines = nStations - 1;
		for( int i= 0; i< nLines; i++) {
			int nRails = (int)(Math.random()*3 + 1);
			ElementRail[] elements = new ElementRail[nRails+2];
			elements[0] = listStations[i];
			for( int j = 0; j < nRails; j++, nSections++) 
				elements[j+1] = new Section("S"+(nSections));
			elements[nRails+1] = listStations[i+1];
			Line l1 = new Line(i+1);
			l1.addLine(elements);
			lines[i] = l1;
			
		}
		
		Train[] trains = new Train[nTrains];
		for( int i= 0; i < nTrains; i++) {
			trains[i] = createTrain(i+1); 
		}
		ctrl.addTrains(trains);
		ctrl.addLines( lines );
		
		for( Train t: trains)
			setPRandomDestiny( t);
		
		ctrl.createWindow();
	}
	

}
