package test;

import java.util.ArrayList;
import java.util.List;

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
	private Train[] trains;
	private Station[] listStations;
	ControlRailway ctrl = new ControlRailway();
	
	public TestNStations(int nStations, int nTrains) {
		this.nStations = nStations;
		this.nTrains = nTrains;
		listStations = new Station[ nStations ];
		lines = new Line[ nStations -1 ];
	}

	public Train createTrain( int rnd, int id) {
		Line l = null; 
		Station start = null, end= null;
		boolean flag = true;
		for( int i= 0; i < lines.length && flag; i++) {
			l = lines[(rnd+i)%lines.length];
			if( l.getStart().isPossibleAddAnotherTrain()) {
				start = l.getStart(); flag = false;
			}else if( l.getEnd().isPossibleAddAnotherTrain()) {
				start = l.getEnd(); flag = false;
			}
		}
		
		rnd = (int) (Math.random()*(100));
		flag = true;
		for( int i= 0; i < listStations.length && flag; i++) {
			end = listStations[(i+rnd)%listStations.length]; 
			if(end.toString() != start.toString()) {
				flag = false;
			}
		}
		Train t = new Train("Train "+id, l, ctrl);
		t.setOrigin(start);
		t.setDestiny(end);
		return t;
	}
	
	public void start() {
		for( int  i = 0 ; i< nStations; i++) 
			listStations[i]= new Station("A"+(i+1));
		
		int nSections =0, nLines = nStations - 1;
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
		
		Train[] auxTrains = new Train[nTrains];
		for( int i= 0; i < nTrains; i++) {
			int rnd  = (int) (Math.random()*lines.length);
			auxTrains[i] = createTrain( rnd, i+1); 
		}
		ctrl.addTrains(auxTrains);
		ctrl.addLines( lines );
		
		for( Train t : auxTrains) 
			t.addTraject(t.getOrigin(), t.getDestiny());
		
		ctrl.createWindow();
	}
	
	public static void main( String[] args) {
		TestNStations t = new TestNStations( 6, 5);
		t.start();
	}

}
