package logic;
import model.*;

/**
 * 
 * @author SANYAS Guillem
 * 		   SANTOS Daniel
 * 
 * Launcher of the application, simulate the behaviour 
 * of trains over a railway.
 */

public class Launcher {
	public static void main( String[] args) {
		ControlRailway ctrl = new ControlRailway();
		
		// Create the RailWay;
		ElementRail staA = new Station("Station A");
		ElementRail a = new Section("AB");
		ElementRail b = new Section("BC");
		ElementRail staD = new Station("Station D");
		
		// Add the line to the rail
		ctrl.addLine( staA, a, b, staD);
		
		// Create the trains
		Train t1 = new Train( "Train 1", ctrl );
		Train t2 = new Train( "Train 2", ctrl );
		Train t3 = new Train( "Train 3", ctrl );
		
		//Creates trajects
		t1.addTraject( (Station) staA, (Station) staD);
		t2.addTraject( (Station) staA, (Station) staD);
		t3.addTraject( (Station) staD, (Station) staA);
		
		//Init travels
		t1.start();
		t2.start();
		t3.start();
	}
}
