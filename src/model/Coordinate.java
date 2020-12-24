package model;

/**
 * Utility class responsible to manage the coordinates (x,y) of a ElementRail or Train in the
 * canvas.
 *
 * @author Daniel Santos Guillem Sanyas
 */
public class Coordinate {
  private int x;
  private int y;

  public Coordinate() {
    this.x = 0;
    this.y = 0;
  }

  public Coordinate(int x, int y) {
    super();
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  @Override
  public String toString() {
    return "{" + this.x + ", " + this.y + "}";
  }
}
