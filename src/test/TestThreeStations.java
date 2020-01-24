package test;
import javax.swing.JFrame;

import logic.ControlRailway;
import logic.Line;
import model.*;

/**
 * 
 * @author SANYAS Guillem
 * 		   SANTOS Daniel
 * 
 * Launcher of the application, simulate the behaviour 
 * of trains over a railway.
 */

public class TestThreeStations {
	public TestThreeStations() {
		
	}

	public void start() {
		
		ControlRailway ctrl = new ControlRailway();
		
		/**
		 * 
		 *  Line 1
		 *   
		 **/
		Line line1 = new Line( 1 );
		
		// Create the RailWay;
		ElementRail staA = new Station("A");
		ElementRail a = new Section("AB");
		ElementRail b = new Section("BC");
		ElementRail c = new Section("CD");
		ElementRail staD = new Station("D");
		
		// Add the line to the rail
		line1.addLine( staA, a, b, c, staD);
		
		// Create the trains
		Train t1 = new Train( "Train 1", line1, ctrl );
		Train t2 = new Train( "Train 2", line1, ctrl );
		Train t3 = new Train( "Train 3", line1, ctrl );
		
		
		/**
		 * 
		 *  Line 2
		 *   
		 **/	
		
		Line line2 = new Line(2);
		
		// Create the RailWay;
		ElementRail e = new Section("DE");
		ElementRail e2 = new Section("DE2");
		ElementRail f = new Section("EF");
		ElementRail staG = new Station("G");
		
		// Add the line to the rail
		line2.addLine( staD, e,e2, f, staG);
		
		
		// Create the trains
		Train t4 = new Train( "Train 4", line2, ctrl );
		Train t5 = new Train( "Train 5", line2, ctrl );
		Train t6 = new Train( "Train 6", line2,  ctrl);
		
		ctrl.addTrains(t1, t2, t3, t4, t5, t6);
		ctrl.addLines(line1, line2);
		
		
		// Create the trajects

		//Line 1
		t1.addTraject( (Station) staA, (Station) staG);
		t1.setOrigin((Station) staA);
		t1.setDestiny((Station) staG);
		t2.addTraject( (Station) staA, (Station) staD);
		t2.setOrigin((Station) staA);
		t2.setDestiny((Station) staD);
		t3.addTraject( (Station) staA, (Station) staG);
		t3.setOrigin((Station) staA);
		t3.setDestiny((Station) staG);
		
		//Line 2
		t4.addTraject( (Station) staD, (Station) staG);
		t4.setOrigin((Station) staD);
		t4.setDestiny((Station) staG);
		t5.addTraject( (Station) staG, (Station) staA);
		t5.setOrigin((Station) staG);
		t5.setDestiny((Station) staA);
		t6.addTraject( (Station) staG, (Station) staA);
		t6.setOrigin((Station) staG);
		t6.setDestiny((Station) staA);
		
		ctrl.createWindow();

		
	}
}
