package model;

public class Direction {

	private String name;
	public Direction(String _name) {
		name = _name;
	}

	public static final Direction LR = new Direction("LeftRight");
	public static final Direction RL = new Direction("RightLeft");
}
