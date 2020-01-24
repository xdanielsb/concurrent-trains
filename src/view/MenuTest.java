package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import test.TestFourStations;
import test.TestNStations;
import test.TestThreeStations;

@SuppressWarnings("serial")
public class MenuTest extends JFrame implements ActionListener{
	private Button fourStation;
	private Button threeStation;
	private Button randomConfig;
	private JLabel titleTest;
	private JPanel panel;
	
	public MenuTest() {
		setSize(300,300 );
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, 
				    dim.height/2-this.getSize().height/2);
		
		panel = new JPanel();
		panel.setLayout( new GridLayout(4, 1));
		
		titleTest = new JLabel("Predefined Test");
		fourStation = new Button("Four Stations");
		threeStation = new Button("Three Stations");
		randomConfig = new Button("Random Configuration");
		
		
		fourStation.addActionListener(this);
		threeStation.addActionListener(this);
		randomConfig.addActionListener(this);
		
		panel.add(titleTest);
		panel.add(threeStation);
		panel.add(fourStation);
		panel.add(randomConfig);
		
		
		this.getContentPane().add( panel );
		setVisible( true );
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if( evt.getSource() == fourStation) {
			new TestFourStations().start();
		}else if( evt.getSource() == threeStation) {
			new TestThreeStations().start();
		}else {
			new TestNStations( 6, 5).start();
		}
		
	}
}
