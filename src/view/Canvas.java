package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import logic.ControlRailway;
import model.ElementRail;
import model.Station;
import model.Train;

@SuppressWarnings("serial")
public class Canvas extends JPanel {
	private ControlRailway ctrl;
	public Canvas(ControlRailway _ctrl) {
		ctrl = _ctrl;
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		// Stations and Sections
		for( ElementRail el: ctrl.getElements()) {
			if( el instanceof Station) {
				g.setColor(Color.BLUE);
				g.fillOval(el.getCord().getX(), 
						   el.getCord().getY(), 
						   30, 
						   30);
			}else {
				g.setColor(Color.RED);
				g.fillRect(el.getCord().getX(), 
						   el.getCord().getY() + 10, 
						   30, 
						   10);
			}
		}
		
		// Trains
		
		for( Train el: ctrl.getTrains()) {
			g.setColor(Color.cyan);
			g.fillRect(el.getCord().getX(), 
					   el.getCord().getY() + 10, 
					   30, 
					   15);
		}
		
	}
}
