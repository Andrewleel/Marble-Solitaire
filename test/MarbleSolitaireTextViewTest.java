import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import model.EnglishSolitaireModel;
import model.EuropeanSolitaireModel;
import view.MarbleSolitaireTextView;

import static org.junit.Assert.assertEquals;

/**
 * Test class for MarbleSolitaireTextView class that is the view class of our game.
 */
public class MarbleSolitaireTextViewTest {

  private EnglishSolitaireModel ms1;
  private EnglishSolitaireModel ms2;
  private EuropeanSolitaireModel ms3;
  private EuropeanSolitaireModel ms4;
  private MarbleSolitaireTextView view1;
  private MarbleSolitaireTextView view2;
  private MarbleSolitaireTextView view3;
  private MarbleSolitaireTextView view4;
  private MarbleSolitaireTextView view5;
  private MarbleSolitaireTextView view6;
  private Appendable ap1;
  private Appendable ap2;

  @Before
  public void initialize() {
    this.ms1 = new EnglishSolitaireModel();
    this.ms2 = new EnglishSolitaireModel(5);
    this.ms3 = new EuropeanSolitaireModel();
    this.ms4 = new EuropeanSolitaireModel(5);
    this.view1 = new MarbleSolitaireTextView(ms1);
    this.view2 = new MarbleSolitaireTextView(ms2);
    this.view3 = new MarbleSolitaireTextView(ms3);
    this.view4 = new MarbleSolitaireTextView(ms4);
    this.ap1 = new StringBuilder();
    this.ap2 = new StringBuilder();
    this.view5 = new MarbleSolitaireTextView(ms1, ap1);
    this.view6 = new MarbleSolitaireTextView(ms3, ap2);
  }

  @Test
  public void testToString() {
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", this.view1.toString());
    assertEquals("        O O O O O\n" +
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
            "        O O O O O", this.view2.toString());
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", this.view3.toString());
    assertEquals("        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O", this.view4.toString());
  }

  @Test
  public void testToStringAfterAMove() {
    this.ms1.move(3, 1, 3, 3);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", this.view1.toString());
    this.ms2.move(6,8,6,6);
    assertEquals("        O O O O O\n" +
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
            "        O O O O O", this.view2.toString());
    this.ms3.move(3,1,3,3);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", this.view3.toString());
    this.ms4.move(4,6,6,6);
    assertEquals("        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O", this.view4.toString());
  }

  @Test
  public void testRenderBoard() throws IOException {
    this.view5.renderBoard();
    assertEquals(new StringBuilder("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O").toString(), this.ap1.toString());
    this.view6.renderBoard();
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", this.ap2.toString());
  }

  @Test
  public void testRenderBoardAfterMove() throws IOException {
    this.ms1.move(3, 1, 3, 3);
    this.view5.renderBoard();
    assertEquals(new StringBuilder("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O").toString(), this.ap1.toString());
    this.ms3.move(3,5,3,3);
    this.view6.renderBoard();
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", this.ap2.toString());
  }

  @Test
  public void testRenderMessageEmptyString() throws IOException {
    this.view5.renderMessage("");
    assertEquals(new StringBuilder("").toString(), this.ap1.toString());
    this.view6.renderMessage("");
    assertEquals(new StringBuilder("").toString(), this.ap2.toString());
  }

  @Test
  public void testRenderMessageString() throws IOException {
    this.view5.renderMessage("Hello World!");
    assertEquals(new StringBuilder("Hello World!").toString(), this.ap1.toString());
    this.view6.renderMessage("Hello World!");
    assertEquals(new StringBuilder("Hello World!").toString(), this.ap2.toString());
  }
}