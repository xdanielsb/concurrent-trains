package view;
/**
 * Frame of the Graphic User Intarface (GUI)
 */
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import logic.ControlRailway;
import logic.Line;
import model.ElementRail;

@SuppressWarnings("serial")
public class Window extends JFrame  implements ActionListener{
	
	private ControlRailway ctrl;
	private Button startSimulation;
	private boolean wasStarted ;
	
	public Window(ControlRailway _ctrl) {
		ctrl = _ctrl;
		wasStarted = false;
		Canvas cv = new Canvas(ctrl);
		int length = 0;
		for (Line l : ctrl.getLines()) {
			length += l.getNumElementsRail();
		}
		setSize(length*42+ 190, 400 + ctrl.getNumberTrains()*30 );
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, 
				    dim.height/2-this.getSize().height/2);
		startSimulation = new Button("Start Simulation");
		startSimulation.addActionListener(this);
		cv.add( startSimulation);
		this.getContentPane().add( cv );
		setVisible( true );
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if( arg0.getSource() == startSimulation ){
			if( !wasStarted ) {
				wasStarted = true;
				startSimulation.disable();
				ctrl.startSimulation();
			}
		}
	}
}
