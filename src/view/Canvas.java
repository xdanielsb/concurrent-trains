package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;

import logic.ControlRailway;
import logic.Line;
import model.Direction;
import model.ElementRail;
import model.Station;
import model.Train;

@SuppressWarnings("serial")
public class Canvas extends JComponent {
	private ControlRailway ctrl;
	private final String  urlTrainl = "../assets/trainr.png";
	private final String  urlTrainr = "../assets/train.png";
	private final String  urlTrainStation = "../assets/train_station.png";
	private final String  urlRailTrain = "../assets/rail_train.png";
	
	private  BufferedImage trainRight;
	private  BufferedImage trainLeft;
	private  BufferedImage trainStation;
	private  BufferedImage trainRail;
	
	public Canvas(ControlRailway _ctrl) {
		ctrl = _ctrl;
		try {
			trainLeft = ImageIO.read(getClass().getResource(urlTrainl));
			trainRight = ImageIO.read(getClass().getResource(urlTrainr));
			trainStation = ImageIO.read(getClass().getResource(urlTrainStation));
			trainRail = ImageIO.read(getClass().getResource(urlRailTrain));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);

	    
		// Stations and Sections
		for (Line l : ctrl.getLines())
		for( ElementRail el: l.getElements()) {
			if( el instanceof Station) {
				g.drawImage(trainStation, el.getCord().getX(), el.getCord().getY(),40,40,null);
			}else {
				g.drawImage(trainRail, el.getCord().getX(), el.getCord().getY(),40,40,null);
			}
		}
		
		// Trains
		
		for (Line l : ctrl.getLines())
		for( Train el: ctrl.getTrains()) {
			g.setColor(Color.cyan);
			if( el.getDirection() == Direction.LR) {
				g.drawImage(trainLeft, el.getCord().getX(), el.getCord().getY(),40,40,null);
			}else {
				g.drawImage(trainRight, el.getCord().getX(), el.getCord().getY(),40,40,null);
			}
		}
		
		
	}
}
