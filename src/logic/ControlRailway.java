package logic;
import model.Direction;
import model.ElementRail;
import model.Station;
import model.Train;
import view.Window;

/**
 * 
 * The complete railway system
 * 
 * @author Santos Daniel
 * 		   Sanyas Guillem
 *
 */
public class ControlRailway {
	
	/**
	 * The lines of the railway. A line is an abstract structure representing
	 * two stations and the rails in between, with a given fixed direction.
	 */
	private Line[] lines;
	
	/**
	 * The window in which the railway will be displayed.
	 */
	private Window window;
	
	/**
	 * The trains in the railway. Each one is a thread.
	 */
	private Train[] trains;
	
	
	
	/**
	 * Constructor for the railway
	 */
	public ControlRailway() {	
	}
	
	
	
	/**
	 * Replaces the trains in the railway with the trains given
	 * 
	 * @param _trains : the new trains in the railway
	 */
	public void addTrains( Train..._trains) {
		trains = _trains;
	}
	
	/**
	 * Replaces the lines in the railway with the lines given
	 * 
	 * @param _lines : the new lines in the railway
	 */
	public void addLines( Line..._lines ) {
		lines = _lines;
		// Information for the display
		for( int i= 0; i< trains.length; i++) {	
			trains[i].getCord().setY(180+i*55);
		}
	}
	
	/**
	 * Getter of the displaying window
	 * 
	 * @return the window in which the railway is displayed
	 */
	public Window getWindow() {
		return window;
	}
	
	/**
	 * Getter of the lines.
	 * 
	 * @return an array containing the lines
	 */
	public Line[] getLines() {
		return lines;
	}
	
	/**
	 * Method used by trains to determine the next line they will run through.
	 * 
	 * @param l : the current line in which the train is.
	 * @param s : the current direction of the train.
	 * @return the next line.
	 */
	public Line getNextLine( Line l, Direction s) {
		Line res = null;
		// search for the current line in the entire table
		for( int i= 0; i< lines.length; i++) {
			// When the current line is found : happens only once
			if( lines[i] == l) {
				// depending on direction gives the line on left or right
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
	public void createWindow() {
		window = new Window( this );
	}
	public void startSimulation() {
		for( Train t: trains)
			t.start();
	}
}
