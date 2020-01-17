package model;

import logic.ControlRailway;

public class Train extends Thread {
	
	private String name;
	private ElementRail currentPos;
	private ElementRail destiny;
	private Direction direction;
	private ControlRailway ctrl;
	
	public Train(String _name, ControlRailway _ctrl) {
		name = _name;
		ctrl = _ctrl;
	}

	public void advance() {
		ElementRail lst = currentPos;
		ElementRail nxt = ctrl.getNext( lst, direction);
		nxt.arrive();
		currentPos = nxt;
		lst.leave();
	}
	public void setOrigin(Station src) {
		if( src.addTrain() ) currentPos = src;
		else {
			System.out.println("Is not possible to add a Train to this station");
		}	
	}
	public void setDestiny(Station tgt) {
		destiny = tgt;
	}
	
	@Override
	public void run() {
		while( currentPos != destiny) {
			System.out.println("Current pos  "+this.name+ " = "+currentPos );
			advance();
		}
		System.out.println(this.name +" Arrives ");
	}

	public void setDirection(Direction dir) {
		direction = dir;
	}
}
