import org.junit.Test;
import org.junit.Before;

import model.EnglishSolitaireModel;
import model.MarbleSolitaireModelState.SlotState;
import view.MarbleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

/**
 * Test class for EnglishSolitaireModel class that is the model of the game.
 */
public class EnglishSolitaireModelTest {
  private EnglishSolitaireModel model1;
  private EnglishSolitaireModel model2;
  private EnglishSolitaireModel model3;
  private EnglishSolitaireModel model4;
  private EnglishSolitaireModel model5;

  @Before
  public void initialize() {
    this.model1 = new EnglishSolitaireModel();
    this.model2 = new EnglishSolitaireModel(4, 4);
    this.model3 = new EnglishSolitaireModel(5);
    this.model4 = new EnglishSolitaireModel(5, 7, 7);
    this.model5 = new EnglishSolitaireModel(3, 3, 3);
  }

  @Test
  public void testFirstConstructor() {
    assertEquals(3, this.model1.getBoardSize() / 3 + 1);
    assertEquals(SlotState.Empty, this.model1.getSlotAt(3, 3));
    assertEquals(SlotState.Invalid, this.model1.getSlotAt(0, 0));
    assertEquals(SlotState.Marble, this.model1.getSlotAt(4, 4));
  }

  @Test
  public void testSecondConstructor() {
    assertEquals(3, this.model2.getBoardSize() / 3 + 1);
    assertEquals(SlotState.Empty, this.model2.getSlotAt(4, 4));
    assertEquals(SlotState.Invalid, this.model2.getSlotAt(0, 0));
    assertEquals(SlotState.Marble, this.model2.getSlotAt(3, 4));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSecondConstructorException() {
    EnglishSolitaireModel local = new EnglishSolitaireModel(-4, 0);
  }

  @Test
  public void testThirdConstructor() {
    assertEquals(5, this.model3.getBoardSize() / 3 + 1);
    assertEquals(SlotState.Empty, this.model3.getSlotAt(6, 6));
    assertEquals(SlotState.Invalid, this.model3.getSlotAt(1, 1));
    assertEquals(SlotState.Marble, this.model3.getSlotAt(5, 5));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testThirdConstructorException() {
    EnglishSolitaireModel local = new EnglishSolitaireModel(-3);
  }

  @Test
  public void testFourthConstructor() {
    assertEquals(5, this.model4.getBoardSize() / 3 + 1);
    assertEquals(SlotState.Empty, this.model4.getSlotAt(7, 7));
    assertEquals(SlotState.Invalid, this.model4.getSlotAt(1, 1));
    assertEquals(SlotState.Marble, this.model4.getSlotAt(5, 5));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFourthConstructorExceptionFirst() {
    EnglishSolitaireModel local = new EnglishSolitaireModel(4, 3, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFourthConstructorExceptionSecond() {
    EnglishSolitaireModel local = new EnglishSolitaireModel(3, -1, 3);
  }

  @Test
  public void testMove() {
    model1.move(3, 1,3,3);
    assertEquals(SlotState.Empty, model1.getSlotAt(3, 2));
    assertEquals(SlotState.Marble, model1.getSlotAt(3, 3));
    assertEquals(SlotState.Empty, model1.getSlotAt(3, 1));
    model2.move(4,2,4,4);

    assertEquals(SlotState.Empty, model2.getSlotAt(4, 3));
    assertEquals(SlotState.Marble, model2.getSlotAt(4, 4));
    assertEquals(SlotState.Empty, model2.getSlotAt(4,2 ));
    System.out.println(new MarbleSolitaireTextView(model3).toString());
    model3.move(6,8,6,6);

    assertEquals(SlotState.Empty, model3.getSlotAt(6,8));
    assertEquals(SlotState.Marble, model3.getSlotAt(6, 6));
    assertEquals(SlotState.Empty, model3.getSlotAt(6, 7));
    model4.move(7,5,7,7);
    assertEquals(SlotState.Empty, model4.getSlotAt(7, 5));
    assertEquals(SlotState.Marble, model4.getSlotAt(7,7));
    assertEquals(SlotState.Empty, model4.getSlotAt(7,6 ));

    model5.move(3,1,3,3);
    assertEquals(SlotState.Empty, model5.getSlotAt(3, 2));
    assertEquals(SlotState.Marble, model5.getSlotAt(3,3));
    assertEquals(SlotState.Empty, model5.getSlotAt(3, 1));
    model5.move(3,4,3,2);
    assertEquals(SlotState.Empty, model5.getSlotAt(3, 3));
    assertEquals(SlotState.Empty, model5.getSlotAt(3, 4));
    assertEquals(SlotState.Marble, model5.getSlotAt(3, 2));
    model5.move(3,6,3,4);
    assertEquals(SlotState.Empty, model5.getSlotAt(3, 6));
    assertEquals(SlotState.Empty, model5.getSlotAt(3,5));
    assertEquals(SlotState.Marble, model5.getSlotAt(3,4));
  }

  @Test
  public void testMoveException() {
    assertThrows(IllegalArgumentException.class,
        () -> model1.move(1,3,2,3));
    assertThrows(IllegalArgumentException.class,
        () -> model1.move(1,5, 3,5));
    assertThrows(IllegalArgumentException.class,
        () -> model1.move(2,5, 0,5));
    assertThrows(IllegalArgumentException.class,
        () -> model1.move(3,2,3,3));

    assertThrows(IllegalArgumentException.class,
        () -> model2.move(1,3,2,3));
    assertThrows(IllegalArgumentException.class,
        () -> model2.move(1,5, 3,5));
    assertThrows(IllegalArgumentException.class,
        () -> model2.move(2,5, 0,5));
    assertThrows(IllegalArgumentException.class,
        () -> model2.move(3,2,3,3));

    assertThrows(IllegalArgumentException.class,
        () -> model3.move(5,5,6,5));
    assertThrows(IllegalArgumentException.class,
        () -> model3.move(3,3, 3,5));
    assertThrows(IllegalArgumentException.class,
        () -> model3.move(0,3, 0,5));
    assertThrows(IllegalArgumentException.class,
        () -> model3.move(4,4,5,5));

    assertThrows(IllegalArgumentException.class,
        () -> model4.move(5,5,6,5));
    assertThrows(IllegalArgumentException.class,
        () -> model4.move(3,3, 3,5));
    assertThrows(IllegalArgumentException.class,
        () -> model4.move(0,3, 0,5));
    assertThrows(IllegalArgumentException.class,
        () -> model4.move(4,4,5,5));

    assertThrows(IllegalArgumentException.class,
        () -> model5.move(1,3,2,3));
    assertThrows(IllegalArgumentException.class,
        () -> model5.move(1,5, 3,5));
    assertThrows(IllegalArgumentException.class,
        () -> model5.move(2,5, 0,5));
    assertThrows(IllegalArgumentException.class,
        () -> model5.move(3,2,3,3));
  }

  @Test
  public void testIsGameOver() {
    assertFalse(model5.isGameOver());
    model5.move(3,1,3,3);
    assertFalse(model5.isGameOver());
    model5.move(3,4,3,2);
    model5.move(3,6,3,4);
    assertFalse(model5.isGameOver());
    model5.move(5,3,3,3);
    model5.move(2,3,4,3);
    assertFalse(model5.isGameOver());
    model5.move(2,5,2,3);
    model5.move(1,3,3,3);
    assertFalse(model5.isGameOver());
    model5.move(4,4,2,4);
    model5.move(1,4,3,4);
    model5.move(4,6,4,4);
    model5.move(4,4,2,4);
    assertFalse(model5.isGameOver());
    model5.move(4,3,2,3);
    model5.move(2,3,2,5);
    model5.move(2,6,2,4);
    model5.move(6,4,4,4);
    model5.move(6,2,6,4);
    assertFalse(model5.isGameOver());
    model5.move(4,2,6,2);
    model5.move(4,0,4,2);
    model5.move(3,2,5,2);
    assertFalse(model5.isGameOver());
    model5.move(6,2,4,2);
    model5.move(2,0,4,0);
    assertFalse(model5.isGameOver());
    model5.move(2,2,2,0);
    model5.move(0,2,2,2);
    model5.move(0,4,0,2);
    assertTrue(model5.isGameOver());
  }

  @Test
  public void testGetBoardSize() {
    assertEquals(7, model1.getBoardSize());

    assertEquals(7, model2.getBoardSize());

    assertEquals(13, model3.getBoardSize());

    assertEquals(13, model4.getBoardSize());

    assertEquals(7, model5.getBoardSize());
  }

  @Test
  public void testGetSlotAt() {
    assertEquals(SlotState.Empty, model1.getSlotAt(3,3));
    assertEquals(SlotState.Marble, model1.getSlotAt(3,2));
    assertEquals(SlotState.Invalid, model1.getSlotAt(1,1 ));

    assertEquals(SlotState.Empty, model2.getSlotAt(4,4));
    assertEquals(SlotState.Marble, model2.getSlotAt(3,2));
    assertEquals(SlotState.Invalid, model2.getSlotAt(1,1));

    assertEquals(SlotState.Empty, model3.getSlotAt(6,6));
    assertEquals(SlotState.Marble, model3.getSlotAt(6,5));
    assertEquals(SlotState.Invalid, model3.getSlotAt(1,1));

    assertEquals(SlotState.Empty, model4.getSlotAt(7,7));
    assertEquals(SlotState.Marble, model4.getSlotAt(6,5));
    assertEquals(SlotState.Invalid, model4.getSlotAt(1,1));

    assertEquals(SlotState.Empty, model5.getSlotAt(3,3));
    assertEquals(SlotState.Marble, model5.getSlotAt(3,2));
    assertEquals(SlotState.Invalid, model5.getSlotAt(1,1));
  }

  @Test
  public void testGetSlotAtException() {
    assertThrows(IllegalArgumentException.class,
        () -> model1.getSlotAt(-1, 1));
    assertThrows(IllegalArgumentException.class,
        () -> model1.getSlotAt(1, -2));

    assertThrows(IllegalArgumentException.class,
        () -> model2.getSlotAt(7, 0));
    assertThrows(IllegalArgumentException.class,
        () -> model2.getSlotAt(1, 7));

    assertThrows(IllegalArgumentException.class,
        () -> model3.getSlotAt(-1, 7));
    assertThrows(IllegalArgumentException.class,
        () -> model3.getSlotAt(1, 13));

    assertThrows(IllegalArgumentException.class,
        () -> model4.getSlotAt(-1, 7));
    assertThrows(IllegalArgumentException.class,
        () -> model4.getSlotAt(1, 13));

    assertThrows(IllegalArgumentException.class,
        () -> model5.getSlotAt(8, 2));
    assertThrows(IllegalArgumentException.class,
        () -> model5.getSlotAt(4, 8));
  }

  @Test
  public void getScore() {
    assertEquals(32, model1.getScore());
    model1.move(3,1,3,3);
    assertEquals(31, model1.getScore());
    model1.move(3,4,3,2);
    assertEquals(30, model1.getScore());

    assertEquals(32, model2.getScore());
    model2.move(4,2,4,4);
    assertEquals(31, model2.getScore());
    model2.move(4,5,4,3);
    assertEquals(30, model2.getScore());

    assertEquals(104, model3.getScore());
    model3.move(6,8,6,6);
    assertEquals(103, model3.getScore());
    model3.move(6,5,6,7);
    assertEquals(102, model3.getScore());

    assertEquals(104, model4.getScore());
    model4.move(7,9,7,7);
    assertEquals(103, model4.getScore());
    model4.move(7,6,7,8);
    assertEquals(102, model4.getScore());

    assertEquals(32, model5.getScore());
    model5.move(3,1,3,3);
    assertEquals(31, model5.getScore());
    model5.move(3,4,3,2);
    assertEquals(30, model5.getScore());
  }
}