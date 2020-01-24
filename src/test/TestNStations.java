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
	private ArrayList<Line> lines = new ArrayList<Line>();
	private ArrayList<Train> trains = new ArrayList<Train>();
	ArrayList< Station > listStations = new ArrayList();
	ArrayList< Section > listSection = new ArrayList();
	ControlRailway ctrl = new ControlRailway();
	
	public TestNStations(int nStations, int nTrains) {
		this.nStations = nStations;
		this.nTrains = nTrains;
	}

	public Train createTrain( int rnd, int id) {
		Line l = null; 
		Station start = null, end= null;
		boolean flag = true;
		for( int i= 0; i < lines.size() && flag; i++) {
			l = lines.get((rnd+i)%lines.size());
			if( l.getStart().isPossibleAddAnotherTrain()) {
				start = l.getStart(); flag = false;
			}else if( l.getEnd().isPossibleAddAnotherTrain()) {
				start = l.getEnd(); flag = false;
			}
		}
		
		rnd = (int) (Math.random()*(listStations.size()));
		flag = true;
		for( int i= 0; i < listStations.size() && flag; i++) {
			end = listStations.get((i+rnd)%listStations.size()); 
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
		for( int  i = 1 ; i<= nStations; i++) 
			listStations.add( new Station("A"+i));
		
		int numSections = 0;
		for( int i= 0; i< nStations-1; i++) {
			ArrayList< ElementRail > elements = new ArrayList();
			elements.add(listStations.get(i));
			int nRails = (int)(Math.random()*3 + 1);
			for( int j = 0; j < nRails; j++, numSections++) 
				elements.add(new Section("S"+(numSections)));
			elements.add(listStations.get(i+1));
			
			Line l1 = new Line(i);
			ElementRail[] aux = new ElementRail[elements.size()];
			for( int k= 0; k < elements.size();k++)
				aux[k] = elements.get(k);
			l1.addLine(aux);
			lines.add(l1);
		}
		

		Train[] auxTrains = new Train[nTrains];
		for( int i= 0; i < nTrains; i++) {
			int rnd  = (int) (Math.random()*lines.size());
			auxTrains[i] = createTrain( rnd, i+1); 
		}
		ctrl.addTrains(auxTrains);
		
		
		Line[] auxLines = new  Line[lines.size()];
		for( int k= 0; k < lines.size(); k++)
			auxLines[ k ]= lines.get( k );
		ctrl.addLines( auxLines );
		
		for( Train t : auxTrains) {
			t.addTraject(t.getOrigin(), t.getDestiny());
		}
		
		ctrl.createWindow();
	}
	
	public static void main( String[] args) {
		TestNStations t = new TestNStations( 6, 7);
		t.start();
	}

}
