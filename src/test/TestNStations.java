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
	private List<Line> lines = new ArrayList<Line>();
	
	public TestNStations(int nStations, int nTrains) {
		this.nStations = nStations;
		this.nTrains = nTrains;
	}
	
	public Station getFreeStation( int rnd) {
		for( int i= rnd; i< (rnd-1+lines.size())%lines.size(); i++) {
			if( lines.get(i).getStart().getNumCurrentTrainInStation() < Station.MAX_NUMBER_TRAIN_IN_STATION )
				return lines.get(i).getStart();
			if( lines.get(i).getEnd().getNumCurrentTrainInStation() < Station.MAX_NUMBER_TRAIN_IN_STATION )
				return lines.get(i).getEnd();
		
		return null;
	}
	
	public void start() {
		ControlRailway ctrl = new ControlRailway();
		ArrayList< Station > listStations = new ArrayList();
		ArrayList< Section > listSection = new ArrayList();
		for( int  i = 1 ; i<= nStations; i++) {
			listStations.add( new Station("A"+i));
		}
		ArrayList< Line> listLines = new ArrayList();
		int numSections = 0;
		for( int i= 0; i< nStations-1; i++) {
			ArrayList< ElementRail > elements = new ArrayList();
			elements.add(listStations.get(i));
			int nRails = (int)(Math.random()*3 + 1);
			for( int j = 0; j < nRails; j++, numSections++) 
				elements.add(new Section("S"+(numSections)));
			elements.add(listStations.get(i+1));
			
			Line l1 = new Line(i);
			l1.addLine((ElementRail[]) elements.toArray());
			lines.add(l1);
		}
		for( int i = 0; i< nStations-1; i++) 
			ctrl.addLines((Line[]) listLines.toArray());
		ArrayList<Train > trains = new ArrayList();
		for( int i= 0; i < nTrains; i++) {
			int randomLine = (int) (Math.random()*listLines.size());
			int randomLine2 = (int) (Math.random()*listLines.size());
			Station start = getFreeStation( randomLine2);
			trains.add( new Train("Train"+(i+1), listLines.get(randomLine) , ctrl));
			trains.get(i).addTraject(
					listLines.get(randomLine).getStart(), 
					_destiny);
		}
		
	}

}
