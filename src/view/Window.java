package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import logic.ControlRailway;
import logic.Line;
import model.ElementRail;

@SuppressWarnings("serial")
public class Window extends JFrame {
	
	private ControlRailway ctrl;
	public Window(ControlRailway _ctrl) {
		ctrl = _ctrl;
		Canvas cv = new Canvas(ctrl);
		int length = 0;
		for (Line l : ctrl.getLines()) {
			length += l.getNumElementsRail();
		}
		setSize(length*50+ 190, 400 + ctrl.getNumberTrains()*10 );
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, 
				    dim.height/2-this.getSize().height/2);
		add( cv );
		setVisible( true );
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
