package view;

/**
 * Menu that shows the options to execute the predefined test and pseudo-random tests
 *
 * @author Santos Daniel Sanyas Guillem
 */
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
public class MenuTest extends JFrame implements ActionListener {
  /** Button that start the test with four stations */
  private Button fourStation;
  /** Button that start the test with three stations */
  private Button threeStation;
  /** Button that start the random configuration test */
  private Button randomConfig;
  /** Label with the title test for predefined test */
  private JLabel titleTest;
  /** Label with the test of pseudo-random test */
  private JLabel titleRandomTest;
  /** The container for the elements */
  private JPanel panel;

  /** Constructor with the configuration */
  public MenuTest() {
    setSize(500, 300);
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    setLocation(
        dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

    panel = new JPanel();
    panel.setLayout(new GridLayout(5, 1));

    titleTest = new JLabel("Predefined Test - Configurations were set");
    fourStation = new Button("Four Stations");
    threeStation = new Button("Three Stations");
    randomConfig = new Button("Random Configuration");
    titleRandomTest =
        new JLabel("Pseudorandom Test - {Trains, Stations and Sections vary PseudoRandom}");

    fourStation.addActionListener(this);
    threeStation.addActionListener(this);
    randomConfig.addActionListener(this);

    panel.add(titleTest);
    panel.add(threeStation);
    panel.add(fourStation);
    panel.add(titleRandomTest);
    panel.add(randomConfig);

    this.getContentPane().add(panel);
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  @Override
  public void actionPerformed(ActionEvent evt) {
    if (evt.getSource() == fourStation) {
      new TestFourStations().start();
    } else if (evt.getSource() == threeStation) {
      new TestThreeStations().start();
    } else if (evt.getSource() == randomConfig) {
      int numStations = (int) (Math.random() * 7 + 2); // [2 7]
      int numTrains = (int) (Math.random() * 10 + 1); // [1 10]
      new TestNStations(numStations, numTrains).start();
    }
  }
}
