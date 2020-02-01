package test;
import logic.ControlRailway;
import logic.Line;
import model.ElementRail;
import model.Section;
import model.Station;
import model.Train;


/**
 * 
 * @author SANYAS Guillem
 * 		   SANTOS Daniel
 * 
 * Launcher of the application, simulate the behaviour 
 * of trains over a railway, four stations
 */

public class TestFourStations {
	
	public TestFourStations() {
		
	}

	public void start() {
		
		ControlRailway ctrl = new ControlRailway();
		
		/**
		 * 
		 *  Line 1
		 *   
		 **/	
		
		Line line1 = new Line(1);
		
		// Create the RailWay;
		ElementRail a = new Station("A");
		ElementRail ab1 = new Section("AB1");
		ElementRail ab2 = new Section("AB2");
		ElementRail ab3 = new Section("AB3");
		ElementRail b = new Station("B");
		
		// Add the line to the rail
		line1.addLine( a, ab1,ab2, ab3, b);
		
		// Create the trains for line 1
		Train t1 = new Train( "Train 1", line1, ctrl );
		Train t2 = new Train( "Train 2", line1, ctrl );
		
		/**
		 * 
		 *  Line 2
		 *   
		 **/	
		
		Line line2 = new Line(2);
		
		// Create the RailWay;
		ElementRail bc1 = new Section("BC1");
		ElementRail bc2 = new Section("BC2");
		ElementRail bc3 = new Section("BC3");
		ElementRail c = new Station("C");
		
		// Add the line to the rail
		line2.addLine( b, bc1,bc2, bc3, c);
		
		// Create the trains for line 2
		Train t3 = new Train( "Train 3", line2, ctrl );
		Train t4 = new Train( "Train 4", line2, ctrl );
		
		/**
		 * 
		 *  Line 3
		 *   
		 **/	
		
		Line line3 = new Line(3);
		
		// Create the RailWay;
		ElementRail cd1 = new Section("CD1");
		ElementRail cd2 = new Section("CD2");
		ElementRail cd3 = new Section("CD3");
		ElementRail d = new Station("D");
		
		// Add the line to the rail
		line3.addLine( c, cd1,cd2, cd3, d);
		
		// Create the trains for line 1
		Train t5 = new Train( "Train 5", line3, ctrl );
		Train t6 = new Train( "Train 6", line3, ctrl );
		Train t7 = new Train( "Train 7", line3,  ctrl);
		
		ctrl.addTrains(t1, t2, t3, t4, t5,t6,t7);
		ctrl.addLines(line1, line2,line3);
		
		// Create the trajects

		//Line 1
		
		t1.setOrigin((Station) a);
		t1.setDestiny((Station) d);
		
		t2.setOrigin((Station) b);
		t2.setDestiny((Station) a);
		
		//Line 2
		
		t3.setOrigin((Station) c);
		t3.setDestiny((Station) b);
		
		t4.setOrigin((Station) c);
		t4.setDestiny((Station) b);
		
		//Line 3
		
		t5.setOrigin((Station) c);
		t5.setDestiny((Station) d);
		
		t6.setOrigin((Station) d);
		t6.setDestiny((Station) c);
		
		t7.setOrigin((Station) d);
		t7.setDestiny((Station) c);
		
		
		ctrl.createWindow();
	}

}
