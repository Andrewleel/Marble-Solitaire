import org.junit.Before;
import org.junit.Test;

import model.MarbleSolitaireModelState.SlotState;
import model.TriangleSolitaireModel;

import static org.junit.Assert.assertEquals;

/**
 * Test class for TriangleSolitaireModel class that is the model of the game.
 */
public class TriangleSolitaireModelTest {
  private TriangleSolitaireModel model1;
  private TriangleSolitaireModel model2;
  private TriangleSolitaireModel model3;
  private TriangleSolitaireModel model4;

  @Before
  public void initialize() {
    this.model1 = new TriangleSolitaireModel();
    this.model2 = new TriangleSolitaireModel(4, 0);
    this.model3 = new TriangleSolitaireModel(4);
    this.model4 = new TriangleSolitaireModel(8, 4,2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSecondConstructorException() {
    new TriangleSolitaireModel(-3, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testThirdConstructorException() {
    new TriangleSolitaireModel(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFourthConstructorExceptionFirst() {
    new TriangleSolitaireModel(-3, 0,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFourthConstructorExceptionSecond() {
    new TriangleSolitaireModel(3, -1,0);
  }

  @Test
  public void testBoardInitialize() {
    assertEquals(5, this.model1.getBoardSize());
    assertEquals(SlotState.Empty, this.model1.getSlotAt(0, 0));
    assertEquals(5, this.model2.getBoardSize());
    assertEquals(SlotState.Empty, this.model2.getSlotAt(4, 0));
    assertEquals(4, this.model3.getBoardSize());
    assertEquals(SlotState.Empty, this.model3.getSlotAt(0, 0));
    assertEquals(8, this.model4.getBoardSize());
    assertEquals(SlotState.Empty, this.model4.getSlotAt(4, 2));
  }

  @Test
  public void testMoveDown() {
    this.model4.move(2,2,4,2);
    assertEquals(SlotState.Empty, this.model4.getSlotAt(2,2));
    assertEquals(SlotState.Empty, this.model4.getSlotAt(3,2));
    assertEquals(SlotState.Marble, this.model4.getSlotAt(4,2));
  }

  @Test
  public void testMoveUp() {
    this.model4.move(6, 2,4, 2);
    assertEquals(SlotState.Empty, this.model4.getSlotAt(6,2));
    assertEquals(SlotState.Empty, this.model4.getSlotAt(5,2));
    assertEquals(SlotState.Marble, this.model4.getSlotAt(4,2));
  }

  @Test
  public void testMoveLeft() {
    this.model4.move(4, 4,4, 2);
    assertEquals(SlotState.Empty, this.model4.getSlotAt(4,4));
    assertEquals(SlotState.Empty, this.model4.getSlotAt(4,3));
    assertEquals(SlotState.Marble, this.model4.getSlotAt(4,2));
  }

  @Test
  public void testMoveRight() {
    this.model4.move(4,0,4,2);
    assertEquals(SlotState.Empty, this.model4.getSlotAt(4,0));
    assertEquals(SlotState.Empty, this.model4.getSlotAt(4,1));
    assertEquals(SlotState.Marble, this.model4.getSlotAt(4,2));
  }

  @Test
  public void testMoveDiagonalTopLeft() {
    this.model4.move(6,4,4,2);
    assertEquals(SlotState.Empty, this.model4.getSlotAt(6,4));
    assertEquals(SlotState.Empty, this.model4.getSlotAt(5,3));
    assertEquals(SlotState.Marble, this.model4.getSlotAt(4,2));
  }

  @Test
  public void testMoveDiagonalTopRight() {
    this.model4.move(6,0,4,2);
    assertEquals(SlotState.Empty, this.model4.getSlotAt(6,0));
    assertEquals(SlotState.Empty, this.model4.getSlotAt(5,1));
    assertEquals(SlotState.Marble, this.model4.getSlotAt(4,2));
  }

  @Test
  public void testMoveDiagonalBottomRight() {
    this.model4.move(2,0,4,2);
    assertEquals(SlotState.Empty, this.model4.getSlotAt(2,0));
    assertEquals(SlotState.Empty, this.model4.getSlotAt(3,1));
    assertEquals(SlotState.Marble, this.model4.getSlotAt(4,2));
  }

  @Test
  public void testMoveDiagonalBottomLeft() {
    this.model2.move(2,2,4,0);
    assertEquals(SlotState.Empty, this.model2.getSlotAt(2,2));
    assertEquals(SlotState.Empty, this.model2.getSlotAt(3,1));
    assertEquals(SlotState.Marble, this.model2.getSlotAt(4,2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromSlotOutOfBoard() {
    this.model1.move(-1,0, 1,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveToSlotOutOfBoard() {
    this.model1.move(2,0, -1,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromEmptySlot() {
    this.model1.move(0,0, 2,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveToMarbleSlot() {
    this.model1.move(2,0, 4,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveMoreThanTwoSlots() {
    this.model4.move(2,2, 5,2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveOneSlot() {
    this.model2.move(1,0, 0,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveOverEmpty() {
    this.model4.move(3,2, 5,2);
  }

  @Test
  public void testGetSlotAt() {
    assertEquals(this.model1.getSlotAt(0,0), SlotState.Empty);
    assertEquals(this.model1.getSlotAt(0,2), SlotState.Invalid);
    assertEquals(this.model1.getSlotAt(1,1), SlotState.Marble);

    assertEquals(this.model2.getSlotAt(4,0), SlotState.Empty);
    assertEquals(this.model2.getSlotAt(0,2), SlotState.Invalid);
    assertEquals(this.model2.getSlotAt(1,1), SlotState.Marble);

    assertEquals(this.model3.getSlotAt(0,0), SlotState.Empty);
    assertEquals(this.model3.getSlotAt(0,2), SlotState.Invalid);
    assertEquals(this.model3.getSlotAt(1,1), SlotState.Marble);

    assertEquals(this.model4.getSlotAt(4,2), SlotState.Empty);
    assertEquals(this.model4.getSlotAt(0,2), SlotState.Invalid);
    assertEquals(this.model4.getSlotAt(1,1), SlotState.Marble);
  }

  @Test
  public void testGameOver() {
    assertEquals(false, this.model2.isGameOver());
    this.model2.move(2, 0, 4, 0);
    this.model2.move(3, 2,3,0);
    this.model2.move(1,0,3,2);
    this.model2.move(3,3,3,1);
    assertEquals(false, this.model2.isGameOver());
    this.model2.move(3,0,3,2);
    this.model2.move(1,1,3,3);
    assertEquals(false, this.model2.isGameOver());
    this.model2.move(4,4,2,2);
    this.model2.move(4,3,2,1);
    assertEquals(false, this.model2.isGameOver());
    this.model2.move(2,2,2,0);
    this.model2.move(4,1,4,3);
    assertEquals(true, this.model2.isGameOver());
  }

  @Test
  public void testGetScore() {
    assertEquals(14, this.model2.getScore());
    this.model2.move(2, 0, 4, 0);
    this.model2.move(3, 2,3,0);
    this.model2.move(1,0,3,2);
    this.model2.move(3,3,3,1);
    assertEquals(10, this.model2.getScore());
    this.model2.move(3,0,3,2);
    this.model2.move(1,1,3,3);
    assertEquals(8, this.model2.getScore());
    this.model2.move(4,4,2,2);
    this.model2.move(4,3,2,1);
    assertEquals(6, this.model2.getScore());
    this.model2.move(2,2,2,0);
    this.model2.move(4,1,4,3);
    assertEquals(4, this.model2.getScore());

    // another model
    assertEquals(9, this.model3.getScore());
    this.model3.move(2, 2, 0, 0);
    assertEquals(8, this.model3.getScore());
    this.model3.move(2, 0 ,2, 2);
    assertEquals(7, this.model3.getScore());
  }

  @Test
  public void testGetBoardSize() {
    assertEquals(5, this.model1.getBoardSize());
    assertEquals(5, this.model2.getBoardSize());
    assertEquals(4, this.model3.getBoardSize());
    assertEquals(8, this.model4.getBoardSize());
  }
}

