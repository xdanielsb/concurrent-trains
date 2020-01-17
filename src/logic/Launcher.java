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
		
		t1.setOrigin( (Station) src );
		t2.setOrigin( (Station) src );
		t3.setOrigin( (Station) src );
		
		t1.setDestiny( (Station) tgt);
		t2.setDestiny( (Station) tgt);
		t3.setDestiny( (Station) tgt);
		
		t1.setDirection( Direction.LR);
		t2.setDirection( Direction.LR);
		t3.setDirection( Direction.LR);
		
		
		t1.start();
		t2.start();
		t3.start();
		
	}
}
