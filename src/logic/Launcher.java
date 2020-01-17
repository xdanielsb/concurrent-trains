package logic;

import model.*;

public class Launcher {
	public static void main( String[] args) {
		ControlRailway ctrl = new ControlRailway();
		
		// Create the RailWay;
		ElementRail src = new Station("Station A");
		ElementRail a = new Section("AB");
		ElementRail b = new Section("BC");
		ElementRail tgt = new Station("Station D");
		
		// Add the line to the rail
		ctrl.addLine( src, a, b, tgt);
		
		// Create the trains
		Train t1 = new Train( "Train 1", ctrl );
		Train t2 = new Train( "Train 2", ctrl );
		Train t3 = new Train( "Train 3", ctrl );
		
		//Creates trajects
		t1.addTraject( (Station) src, (Station) tgt);
		t2.addTraject( (Station) src, (Station) tgt);
		t3.addTraject( (Station) src, (Station) tgt);
		
		//Init travels
		t1.start();
		t2.start();
		t3.start();
		
	}
}
