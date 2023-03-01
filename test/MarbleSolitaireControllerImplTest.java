import org.junit.Test;
import org.junit.Before;

import java.io.StringReader;

import controller.MarbleSolitaireControllerImpl;
import model.EnglishSolitaireModel;
import model.MarbleSolitaireModel;
import view.MarbleSolitaireTextView;
import view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;

/**
 * Test for the class that represents the controller for our MarbleSolitaire game.
 */
public class MarbleSolitaireControllerImplTest {
  private Appendable out;
  private MarbleSolitaireModel model1;
  private MarbleSolitaireModel model2;
  private MarbleSolitaireView view1;
  private MarbleSolitaireView view2;

  @Before
  public void initialize() {
    this.out = new StringBuilder();
    this.model1 = new EnglishSolitaireModel(3);
    this.model2 = new EnglishSolitaireModel(5);
    this.view1 = new MarbleSolitaireTextView(model1, out);
    this.view2 = new MarbleSolitaireTextView(model2, out);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testReadableNull() {
    Readable temp = null;
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model1, view1, temp);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testViewNull() {
    Readable temp = new StringReader("2 4 4 4 q");
    MarbleSolitaireView view = null;
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model1, view, temp);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testModelNull() {
    Readable temp = new StringReader("2 4 4 4 q");
    MarbleSolitaireModel model = null;
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, out);
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model, view, temp);
  }

  @Test(expected = IllegalStateException.class)
  public void testRanOutOfInputs() {
    Readable temp = new StringReader("4 2 4 4");
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model1, view1, temp);
    controller.playGame();
  }

  @Test(expected = IllegalStateException.class)
  public void testRanOutOfInputsAfterMultipleMoves() {
    Readable temp = new StringReader("4 2 4 4 4 5 4 3");
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model1, view1, temp);
    controller.playGame();
  }

  @Test(expected = IllegalStateException.class)
  public void testAppendableOutput() {
    CorruptAppendable corruptAp = new CorruptAppendable();
    Readable temp = new StringReader("4 2 4 4 q");
    MarbleSolitaireView view = new MarbleSolitaireTextView(model1, corruptAp);
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model1, view, temp);
    controller.playGame();
  }

  @Test
  public void testInvalidInputs() {
    Readable temp = new StringReader("2 2 4 4 q");
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model1, view1, temp);
    controller.playGame();
    assertEquals("\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "\n"
            + "Invalid move. Play again.\n"
            + "\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32\n"
            + "\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32"
            + "\n", out.toString());
  }

  @Test
  public void testInvalidInputsBigger() {
    Readable temp = new StringReader("8 8 7 7 q");
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model2, view2, temp);
    controller.playGame();
    assertEquals("\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 104\n" +
            "\n" +
            "Invalid move. Play again.\n" +
            "\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 104\n" +
            "\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 104\n", out.toString());
  }

  @Test
  public void testGameOver() {
    Readable temp = new StringReader("4 2 4 4 4 5 4 3 4 7 4 5 6 4 4 4 3 4 5 4 3 6 3 4 2 4 " +
            "4 4 5 5 3 5 2 5 4 5 5 7 5 5 5 5 3 5 5 4 3 4 3 4 3 6 3 7 3 5 7 5 5 5 7 3 7 5 5 3 7 3" +
            " 5 1 5 3 4 3 6 3 7 3 5 3 3 1 5 1 3 3 3 1 1 3 3 3 1 5 1 3");
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model1, view1, temp);
    controller.playGame();
    assertEquals("\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O _ _ O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O _ O _ _\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 29\n" +
            "\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O O _ _\n" +
            "O O O _ O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 28\n" +
            "\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O _ O O O\n" +
            "O _ O _ O _ _\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 27\n" +
            "\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O _ _ O\n" +
            "O _ O _ O _ _\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 26\n" +
            "\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ _ _ O\n" +
            "O _ O O O _ _\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 25\n" +
            "\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O _ O\n" +
            "O _ O O _ _ _\n" +
            "O O O O _ O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 24\n" +
            "\n" +
            "    O O O\n" +
            "    O _ _\n" +
            "O O O _ _ _ O\n" +
            "O _ O O O _ _\n" +
            "O O O O _ O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 23\n" +
            "\n" +
            "    O O O\n" +
            "    O _ _\n" +
            "O O O _ _ _ O\n" +
            "O _ O O O _ _\n" +
            "O O O O O _ _\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 22\n" +
            "\n" +
            "    O O O\n" +
            "    O _ _\n" +
            "O O O _ O _ O\n" +
            "O _ O O _ _ _\n" +
            "O O O O _ _ _\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 21\n" +
            "\n" +
            "    O O O\n" +
            "    O _ _\n" +
            "O O O O O _ O\n" +
            "O _ O _ _ _ _\n" +
            "O O O _ _ _ _\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 20\n" +
            "\n" +
            "    O O O\n" +
            "    O _ _\n" +
            "O O O _ _ O O\n" +
            "O _ O _ _ _ _\n" +
            "O O O _ _ _ _\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 19\n" +
            "\n" +
            "    O O O\n" +
            "    O _ _\n" +
            "O O O _ O _ _\n" +
            "O _ O _ _ _ _\n" +
            "O O O _ _ _ _\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 18\n" +
            "\n" +
            "    O O O\n" +
            "    O _ _\n" +
            "O O O _ O _ _\n" +
            "O _ O _ _ _ _\n" +
            "O O O _ O _ _\n" +
            "    O _ _\n" +
            "    O O _\n" +
            "Score: 17\n" +
            "\n" +
            "    O O O\n" +
            "    O _ _\n" +
            "O O O _ O _ _\n" +
            "O _ O _ _ _ _\n" +
            "O O O _ O _ _\n" +
            "    O _ _\n" +
            "    _ _ O\n" +
            "Score: 16\n" +
            "\n" +
            "    O O O\n" +
            "    O _ _\n" +
            "O O O _ O _ _\n" +
            "O _ O _ _ _ _\n" +
            "O O _ _ O _ _\n" +
            "    _ _ _\n" +
            "    O _ O\n" +
            "Score: 15\n" +
            "\n" +
            "    O O O\n" +
            "    O _ _\n" +
            "O O O _ O _ _\n" +
            "O _ O _ _ _ _\n" +
            "_ _ O _ O _ _\n" +
            "    _ _ _\n" +
            "    O _ O\n" +
            "Score: 14\n" +
            "\n" +
            "    O O O\n" +
            "    O _ _\n" +
            "O O O _ O _ _\n" +
            "O _ _ _ _ _ _\n" +
            "_ _ _ _ O _ _\n" +
            "    O _ _\n" +
            "    O _ O\n" +
            "Score: 13\n" +
            "\n" +
            "    O O O\n" +
            "    O _ _\n" +
            "O O O _ O _ _\n" +
            "O _ _ _ _ _ _\n" +
            "_ _ O _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 12\n" +
            "\n" +
            "    O O O\n" +
            "    O _ _\n" +
            "_ O O _ O _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "O _ O _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 11\n" +
            "\n" +
            "    O O O\n" +
            "    O _ _\n" +
            "O _ _ _ O _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "O _ O _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 10\n" +
            "\n" +
            "    _ O O\n" +
            "    _ _ _\n" +
            "O _ O _ O _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "O _ O _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 9\n" +
            "\n" +
            "Game over!\n" +
            "    O _ _\n" +
            "    _ _ _\n" +
            "O _ O _ O _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "O _ O _ O _ _\n" +
            "    _ _ _\n" +
            "    _ _ O\n" +
            "Score: 8\n", out.toString());
  }

  @Test
  public void testQuitOnFromRow() {
    Readable temp = new StringReader("q 6 4 4");
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model1, view1, temp);
    controller.playGame();
    assertEquals("\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", out.toString());
  }

  @Test
  public void testQuitOnFromRowBigger() {
    Readable temp = new StringReader("q 7 7 7");
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model2, view2, temp);
    controller.playGame();
    assertEquals("\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 104\n" +
            "\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 104\n", out.toString());
  }

  @Test
  public void testQuitOnFromCol() {
    Readable temp = new StringReader("4 q 4 4");
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model1, view1, temp);
    controller.playGame();
    assertEquals("\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", out.toString());
  }

  @Test
  public void testQuitOnFromColBigger() {
    Readable temp = new StringReader("9 q 7 7");
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model2, view2, temp);
    controller.playGame();
    assertEquals("\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 104\n" +
            "\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 104\n", out.toString());
  }

  @Test
  public void testQuitOnToRow() {
    Readable temp = new StringReader("4 6 q 4");
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model1, view1, temp);
    controller.playGame();
    assertEquals("\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", out.toString());
  }

  @Test
  public void testQuitOnToRowBigger() {
    Readable temp = new StringReader("9 7 q 7");
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model2, view2, temp);
    controller.playGame();
    assertEquals("\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 104\n" +
            "\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 104\n", out.toString());
  }

  @Test
  public void testQuitOnToCol() {
    Readable temp = new StringReader("4 6 4 q");
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model1, view1, temp);
    controller.playGame();
    assertEquals("\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", out.toString());
  }

  @Test
  public void testQuitOnToColBigger() {
    Readable temp = new StringReader("9 7 7 q");
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model2, view2, temp);
    controller.playGame();
    assertEquals("\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 104\n" +
            "\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 104\n", out.toString());
  }

  @Test
  public void testQuitAfterMoveLeft() {
    Readable temp = new StringReader("4 6 4 4 q");
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model1, view1, temp);
    controller.playGame();
    assertEquals("\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n", out.toString());
  }

  @Test
  public void testQuitAfterOneMoveBigger() {
    Readable temp = new StringReader("7 9 7 7 q");
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model2, view2, temp);
    controller.playGame();
    assertEquals("\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 104\n" +
            "\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O _ _ O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 103\n" +
            "\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O _ _ O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 103\n", out.toString());
  }

  @Test
  public void testQuitAfterMoveRight() {
    Readable temp = new StringReader("4 2 4 4 q");
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model1, view1, temp);
    controller.playGame();
    assertEquals("\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n", out.toString());
  }

  @Test
  public void testQuitAfterMoveDown() {
    Readable temp = new StringReader("2 4 4 4 q");
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model1, view1, temp);
    controller.playGame();
    assertEquals("\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n", out.toString());
  }

  @Test
  public void testQuitAfterMoveUp() {
    Readable temp = new StringReader("6 4 4 4 q");
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model1, view1, temp);
    controller.playGame();
    assertEquals("\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 31\n", out.toString());
  }

  @Test
  public void testQuitAfterMultipleMoves() {
    Readable temp = new StringReader("2 4 4 4 3 5 3 3 q");
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model1, view1, temp);
    controller.playGame();
    assertEquals("\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "\n" +
            "Invalid move. Play again.\n" +
            "\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n", out.toString());
  }

  @Test
  public void testQuitAfterMultipleMoveBigger() {
    Readable temp = new StringReader("7 9 7 7 9 8 7 8 q");
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model2, view2, temp);
    controller.playGame();
    assertEquals("\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 104\n" +
            "\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O _ _ O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 103\n" +
            "\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O _ O O O O\n" +
            "O O O O O O O _ O O O O O\n" +
            "O O O O O O O _ O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 102\n" +
            "\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O _ O O O O\n" +
            "O O O O O O O _ O O O O O\n" +
            "O O O O O O O _ O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 102\n", out.toString());
  }

  @Test
  public void testInvalidInputWithLetters() {
    Readable temp = new StringReader("4 e 2 4 f 4 q");
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model1, view1, temp);
    controller.playGame();
    assertEquals("\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "\n" +
            "Please enter valid input!\n" +
            "\n" +
            "Please enter valid input!\n" +
            "\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n", out.toString());
  }

  @Test
  public void testInvalidInputWithLettersBigger() {
    Readable temp = new StringReader("7 e f g h 9 7 i 7 q");
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model2, view2, temp);
    controller.playGame();
    assertEquals("\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 104\n" +
            "\n" +
            "Please enter valid input!\n" +
            "\n" +
            "Please enter valid input!\n" +
            "\n" +
            "Please enter valid input!\n" +
            "\n" +
            "Please enter valid input!\n" +
            "\n" +
            "Please enter valid input!\n" +
            "\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O _ _ O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 103\n" +
            "\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O _ _ O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 103\n", out.toString());
  }

  @Test
  public void testInvalidInputWithNegativeValues() {
    Readable temp = new StringReader("4 -2 2 4 -5 4 q");
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model1, view1, temp);
    controller.playGame();
    assertEquals("\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n", out.toString());
  }

  @Test
  public void testInvalidInputWithNegativeValuesBigger() {
    Readable temp = new StringReader("7 -4 9 7 -2 7 q");
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model2, view2, temp);
    controller.playGame();
    assertEquals("\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 104\n" +
            "\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O _ _ O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 103\n" +
            "\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O _ _ O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 103\n", out.toString());
  }

  @Test
  public void testMoveDiagonally() {
    Readable temp = new StringReader("3 3 4 4 q");
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model1, view1, temp);
    controller.playGame();
    assertEquals("\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "\n" +
            "Invalid move. Play again.\n" +
            "\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", out.toString());
  }

  @Test
  public void testMoveMoreThanTwoSlotsRight() {
    Readable temp = new StringReader("1 4 4 4 q");
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model1, view1, temp);
    controller.playGame();
    assertEquals("\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "\n" +
            "Invalid move. Play again.\n" +
            "\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", out.toString());
  }

  @Test
  public void testMoveMoreThanTwoSlotsLeft() {
    Readable temp = new StringReader("7 4 4 4 q");
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model1, view1, temp);
    controller.playGame();
    assertEquals("\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "\n" +
            "Invalid move. Play again.\n" +
            "\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", out.toString());
  }

  @Test
  public void testMoveMoreThanTwoSlotsUp() {
    Readable temp = new StringReader("4 7 4 4 q");
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model1, view1, temp);
    controller.playGame();
    assertEquals("\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "\n" +
            "Invalid move. Play again.\n" +
            "\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", out.toString());
  }

  @Test
  public void testMoveMoreThanTwoSlotsDown() {
    Readable temp = new StringReader("4 1 4 4 q");
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model1, view1, temp);
    controller.playGame();
    assertEquals("\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "\n" +
            "Invalid move. Play again.\n" +
            "\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", out.toString());
  }

  @Test
  public void testNegativeCoordinates() {
    Readable temp = new StringReader("-4 -1 -4 -4 q");
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model1, view1, temp);
    controller.playGame();
    assertEquals("\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", out.toString());
  }

  @Test
  public void testLessThanFourNumbers() {
    Readable temp = new StringReader("4 2 4 q");
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model1, view1, temp);
    controller.playGame();
    assertEquals("\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", out.toString());
  }

  @Test
  public void testOnlyLetters() {
    Readable temp = new StringReader("e g h b q");
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model1, view1, temp);
    controller.playGame();
    assertEquals("\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "\n" +
            "Please enter valid input!\n" +
            "\n" +
            "Please enter valid input!\n" +
            "\n" +
            "Please enter valid input!\n" +
            "\n" +
            "Please enter valid input!\n" +
            "\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", out.toString());
  }

  @Test
  public void testOnlyLettersBigger() {
    Readable temp = new StringReader("e g h b q");
    MarbleSolitaireControllerImpl controller
            = new MarbleSolitaireControllerImpl(model2, view2, temp);
    controller.playGame();
    assertEquals("\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 104\n" +
            "\n" +
            "Please enter valid input!\n" +
            "\n" +
            "Please enter valid input!\n" +
            "\n" +
            "Please enter valid input!\n" +
            "\n" +
            "Please enter valid input!\n" +
            "\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 104\n", out.toString());
  }
}