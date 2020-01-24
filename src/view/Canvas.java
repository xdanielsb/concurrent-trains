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
public class Canvas extends JPanel {
	private ControlRailway ctrl;
	private final String  urlTrainl = "../assets/trainr.png";
	private final String  urlTrainr = "../assets/train.png";
	private final String  urlTrainStation = "../assets/train_station.png";
	private final String  urlRailTrain = "../assets/rail_train.png";
	private final String  urlArrowRight = "../assets/arrow_right.gif";
	private final String  urlArrowLeft = "../assets/arrow_left.gif";
	
	private  BufferedImage trainRight;
	private  BufferedImage trainLeft;
	private  BufferedImage trainStation;
	private  BufferedImage trainRail;
	private  Image arrowRight;
	private  Image arrowLeft;
	
	public Canvas(ControlRailway _ctrl) {
		ctrl = _ctrl;
		try {
			trainLeft = ImageIO.read(getClass().getResource(urlTrainl));
			trainRight = ImageIO.read(getClass().getResource(urlTrainr));
			trainStation = ImageIO.read(getClass().getResource(urlTrainStation));
			trainRail = ImageIO.read(getClass().getResource(urlRailTrain));
			arrowRight = new ImageIcon(this.getClass().getResource(urlArrowRight)).getImage();
			arrowLeft = new ImageIcon(this.getClass().getResource(urlArrowLeft)).getImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		paintElementsRail( g );
		paintTrains( g );
	}
	
	public void paintElementsRail( Graphics g ) {
		// Stations and Sections
		for (Line l : ctrl.getLines())
		for( ElementRail el: l.getElements()) {
			if( el instanceof Station) {
				g.drawImage(trainStation, el.getCord().getX(), el.getCord().getY(),40,40,null);
				g.drawString(el.toString(),el.getCord().getX()+15, el.getCord().getY()-10);
				g.drawString(((Station) el).getNumCurrentTrainInStation()+"",el.getCord().getX()+15, el.getCord().getY()-25);
				
			}else {
				if( l.getCurrentDirection() == Direction.LR && l.getNumberOfTrainsInTraject() > 0) {					
					g.drawImage(arrowRight, el.getCord().getX()+10, el.getCord().getY()-10,15,15,this);
				}else if( l.getCurrentDirection() == Direction.RL && l.getNumberOfTrainsInTraject() > 0){
					g.drawImage(arrowLeft, el.getCord().getX()+10, el.getCord().getY()-10,15,15,this);
				}
				g.drawImage(trainRail, el.getCord().getX(), el.getCord().getY(),40,40,null);
			}
		}
	}
	
	public void paintTrains( Graphics g ) {
		for (Line l : ctrl.getLines())
		for( Train el: ctrl.getTrains()) {
			if( el.getDirection() == Direction.LR) {
				g.drawImage(trainLeft, el.getCord().getX(), el.getCord().getY(),40,40,null);
			}else {
				g.drawImage(trainRight, el.getCord().getX(), el.getCord().getY(),40,40,null);
			}
			g.drawString(el.toString(), el.getCord().getX(), el.getCord().getY()+10);
			String trajet = el.getOrigin() + " -> " + el.getDestiny();
			g.drawString(trajet, el.getCord().getX(), el.getCord().getY()+40);
		}
	}
}
