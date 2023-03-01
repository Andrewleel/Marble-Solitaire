import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import model.TriangleSolitaireModel;
import view.MarbleSolitaireView;
import view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;

/**
 * Test class for TriangleSolitaireTextView class that displays the model.
 */
public class TriangleSolitaireTextViewTest {
  private TriangleSolitaireModel model1;
  private TriangleSolitaireModel model2;
  private MarbleSolitaireView view1;
  private MarbleSolitaireView view2;
  private MarbleSolitaireView view3;
  private Appendable ap1;


  @Before
  public void initialize() {
    this.model1 = new TriangleSolitaireModel();
    this.model2 = new TriangleSolitaireModel(8, 2, 2);
    this.view1 = new TriangleSolitaireTextView(model1);
    this.view2 = new TriangleSolitaireTextView(model2);
    this.ap1 = new StringBuilder();
    this.view3 = new TriangleSolitaireTextView(model1, ap1);
  }

  @Test
  public void testToString() {
    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", this.view1.toString());
    assertEquals("       O\n" +
            "      O O\n" +
            "     O O _\n" +
            "    O O O O\n" +
            "   O O O O O\n" +
            "  O O O O O O\n" +
            " O O O O O O O\n" +
            "O O O O O O O O", this.view2.toString());
  }

  @Test
  public void testToStringAfterMove() {
    this.model1.move(2,2,0,0);
    assertEquals("    O\n" +
            "   O _\n" +
            "  O O _\n" +
            " O O O O\n" +
            "O O O O O", this.view1.toString());
    this.model2.move(2, 0, 2, 2);
    assertEquals("       O\n" +
            "      O O\n" +
            "     _ _ O\n" +
            "    O O O O\n" +
            "   O O O O O\n" +
            "  O O O O O O\n" +
            " O O O O O O O\n" +
            "O O O O O O O O", this.view2.toString());
  }

  @Test
  public void testRenderMessageEmptyString() throws IOException {
    this.view3.renderMessage("");
    assertEquals(new StringBuilder("").toString(), this.ap1.toString());
  }

  @Test
  public void testRenderMessageString() throws IOException {
    this.view3.renderMessage("Hello World!");
    assertEquals(new StringBuilder("Hello World!").toString(), this.ap1.toString());
  }
}