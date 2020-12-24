package model;

/**
 * Class that represents a section in the railway. It can only store one train at a time. The train
 * cannot change its direction while on this element.
 *
 * @author Daniel Santos Guillem Sanyas
 */
public class Section extends ElementRail {

  /**
   * Basic constructor for the section
   *
   * @param _name : the name we give to this element.
   */
  public Section(String _name) {
    super(_name);
  }

  /**
   * The section just can handle one train in t(s) if there is a train, and another want to go there
   * he has to wait.
   */
  @Override
  public synchronized void arrive() {
    while (numTrainsInRail == 1)
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    System.out.println(this + " is blocked");
    numTrainsInRail = 1;
  }

  /** When a train leaves the section this notify the other ones that were waiting. */
  @Override
  public synchronized void leave() {
    numTrainsInRail = 0;
    System.out.println(this + " is unblocked");
    notifyAll();
  }
}
