package logic;
import model.Direction;
import model.ElementRail;
import model.Station;
import model.Train;
import view.Window;

public class ControlRailway {
	private Line[] lines;
	private Window window;
	private Train[] trains;
	
	public ControlRailway() {
		
	}
	public void addTrains( Train..._trains) {
		trains = _trains;
	}
	
	public void addLines( Line..._lines ) {
		lines = _lines;
		for( int i= 0; i< trains.length; i++) {	
			trains[i].getCord().setY(180+i*30);
		}
	}
	
	
	public Window getWindow() {
		return window;
	}
	
	public Line[] getLines() {
		return lines;
	}
	
	public Line getNextLine( Line l, Direction s) {
		Line res = null;
		for( int i= 0; i< lines.length; i++) {
			if( lines[i] == l) {
				res = s == Direction.LR? lines[i+1] : lines[i-1];
			}
		}
		return res ;
	}
	public int getIndex(Station src) {
		int ans = -1;
		for( int i = 0; i < lines.length && ans == -1; i++) {
			ElementRail[] el = lines[i].getElements();
			if( i == 0 && el[0] == src) ans =i;
			if( el[ el.length - 1] == src) ans =i+1 ;
		}
		return ans;
	}
	public int getNumberTrains() {
		return trains.length;
	}
	public Train[] getTrains() {
		return trains;
	}
	public void startSimulation() {
		window = new Window( this );
		for( Train t: trains)
			t.start();
	}
}
