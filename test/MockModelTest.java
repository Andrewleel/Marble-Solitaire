import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.Reader;
import java.io.StringReader;

import controller.MarbleSolitaireController;
import controller.MarbleSolitaireControllerImpl;
import model.MarbleSolitaireModel;
import view.MarbleSolitaireTextView;
import view.MarbleSolitaireView;

/**
 * Test class for MockModel class that tests if the controller is sending input to model correctly.
 */
public class MockModelTest {
  private StringBuilder log;
  private MarbleSolitaireModel mock;
  private MarbleSolitaireView view;

  @Before
  public void initialize() {
    this.log = new StringBuilder();
    this.mock = new MockModel(log);
    this.view = new MarbleSolitaireTextView(mock);
  }

  @Test
  public void testInput1() {
    Reader rd = new StringReader("6 4 4 4");
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(mock, view, rd);
    controller.playGame();
    assertEquals("6 4 4 4", log.toString());
  }

  @Test
  public void testInput2() {
    Reader rd = new StringReader("6 4 e 4 e 4");
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(mock, view, rd);
    controller.playGame();
    assertEquals("6 4 4 4", log.toString());
  }

  @Test
  public void testInput3() {
    Reader rd = new StringReader("e 6 4 / 4 e 4");
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(mock, view, rd);
    controller.playGame();
    assertEquals("6 4 4 4", log.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testException() {
    MarbleSolitaireModel temp_mock = new MockModel(null);
  }
}