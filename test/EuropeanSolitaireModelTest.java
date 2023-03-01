import org.junit.Before;
import org.junit.Test;

import model.MarbleSolitaireModelState.SlotState;
import model.EuropeanSolitaireModel;

import static org.junit.Assert.assertEquals;

/**
 * Test class for EuropeanSolitaireModel class that is the model of the game.
 */
public class EuropeanSolitaireModelTest {

  private EuropeanSolitaireModel model1;
  private EuropeanSolitaireModel model2;
  private EuropeanSolitaireModel model3;
  private EuropeanSolitaireModel model4;
  private EuropeanSolitaireModel model5;
  private EuropeanSolitaireModel model6;

  @Before
  public void initialize() {
    this.model1 = new EuropeanSolitaireModel();
    this.model2 = new EuropeanSolitaireModel(4,4);
    this.model3 = new EuropeanSolitaireModel(5);
    this.model4 = new EuropeanSolitaireModel(5, 4, 4);
    this.model5 = new EuropeanSolitaireModel(1, 3);
    this.model6 = new EuropeanSolitaireModel(5,5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSecondConstructorException() {
    new EuropeanSolitaireModel(-3, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testThirdConstructorException() {
    new EuropeanSolitaireModel(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFourthConstructorExceptionFirst() {
    new EuropeanSolitaireModel(-3, 0,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFourthConstructorExceptionSecond() {
    new EuropeanSolitaireModel(5, -1,0);
  }

  @Test
  public void testBoardInitialize() {
    assertEquals(7, this.model1.getBoardSize());
    assertEquals(SlotState.Empty, this.model1.getSlotAt(3, 3));
    assertEquals(7, this.model2.getBoardSize());
    assertEquals(SlotState.Empty, this.model2.getSlotAt(4, 4));
    assertEquals(13, this.model3.getBoardSize());
    assertEquals(SlotState.Empty, this.model3.getSlotAt(6, 6));
    assertEquals(13, this.model4.getBoardSize());
    assertEquals(SlotState.Empty, this.model4.getSlotAt(4, 4));
  }

  @Test
  public void testMoveDown() {
    this.model1.move(1,3,3,3);
    assertEquals(SlotState.Empty, this.model1.getSlotAt(1,3));
    assertEquals(SlotState.Empty, this.model1.getSlotAt(2,3));
    assertEquals(SlotState.Marble, this.model1.getSlotAt(3,3));
  }

  @Test
  public void testMoveUp() {
    this.model1.move(5,3,3,3);
    assertEquals(SlotState.Empty, this.model1.getSlotAt(5,3));
    assertEquals(SlotState.Empty, this.model1.getSlotAt(4,3));
    assertEquals(SlotState.Marble, this.model1.getSlotAt(3,3));
  }

  @Test
  public void testMoveRight() {
    this.model1.move(3,1,3,3);
    assertEquals(SlotState.Empty, this.model1.getSlotAt(3,1));
    assertEquals(SlotState.Empty, this.model1.getSlotAt(3,2));
    assertEquals(SlotState.Marble, this.model1.getSlotAt(3,3));
  }

  @Test
  public void testMoveLeft() {
    this.model1.move(3,5,3,3);
    assertEquals(SlotState.Empty, this.model1.getSlotAt(3,5));
    assertEquals(SlotState.Empty, this.model1.getSlotAt(3,4));
    assertEquals(SlotState.Marble, this.model1.getSlotAt(3,3));
  }

  @Test
  public void testMoveFromNewCornerCell() {
    this.model5.move(1,1,1,3);
    assertEquals(SlotState.Empty, this.model5.getSlotAt(1,1));
    assertEquals(SlotState.Empty, this.model5.getSlotAt(1,2));
    assertEquals(SlotState.Marble, this.model5.getSlotAt(1,3));
  }

  @Test public void testMoveToNewCornerCell() {
    this.model6.move(3,5,5,5);
    assertEquals(SlotState.Empty, this.model6.getSlotAt(3,5));
    assertEquals(SlotState.Empty, this.model6.getSlotAt(4,5));
    assertEquals(SlotState.Marble, this.model6.getSlotAt(5,5));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromSlotOutOfBoard() {
    this.model1.move(3,-1, 3,1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveToSlotOutOfBoard() {
    this.model1.move(3,1, 3,-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromEmptySlot() {
    this.model1.move(3,3, 3,5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveToMarbleSlot() {
    this.model1.move(4,4, 4,6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveMoreThanTwoSlots() {
    this.model1.move(3,6, 3,3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveOneSlot() {
    this.model1.move(3,4, 3,3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveOverEmpty() {
    this.model1.move(3,2, 3,4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDiagonal() {
    this.model1.move(5,5, 3,3);
  }

  @Test
  public void testGetSlotAt() {
    assertEquals(this.model1.getSlotAt(3,3), SlotState.Empty);
    assertEquals(this.model1.getSlotAt(0,1), SlotState.Invalid);
    assertEquals(this.model1.getSlotAt(4,4), SlotState.Marble);

    assertEquals(this.model2.getSlotAt(4,4), SlotState.Empty);
    assertEquals(this.model2.getSlotAt(0,1), SlotState.Invalid);
    assertEquals(this.model2.getSlotAt(3,3), SlotState.Marble);

    assertEquals(this.model3.getSlotAt(6,6), SlotState.Empty);
    assertEquals(this.model3.getSlotAt(1,1), SlotState.Invalid);
    assertEquals(this.model3.getSlotAt(7,7), SlotState.Marble);

    assertEquals(this.model4.getSlotAt(4,4), SlotState.Empty);
    assertEquals(this.model4.getSlotAt(1,1), SlotState.Invalid);
    assertEquals(this.model4.getSlotAt(6,6), SlotState.Marble);
  }

  @Test
  public void testGameOver() {
    assertEquals(false, this.model1.isGameOver());
    this.model1.move(1,3, 3, 3);
    this.model1.move(4,3, 2, 3);
    this.model1.move(3, 5, 3, 3);
    this.model1.move(4,5,4,3);
    assertEquals(false, this.model1.isGameOver());
    this.model1.move(1, 5,3 , 5);
    this.model1.move(3, 6,3, 4);
    this.model1.move(6, 4, 4,4);
    this.model1.move(4, 3,4,5);
    assertEquals(false, this.model1.isGameOver());
    this.model1.move(4,6,4,4);
    this.model1.move(6,3,4,3);
    this.model1.move(3,4,5,4);
    assertEquals(false, this.model1.isGameOver());
    this.model1.move(5,5,5,3);
    this.model1.move(1,4,3,4);
    this.model1.move(4,3,6,3);
    this.model1.move(3,3,3,5);
    assertEquals(false, this.model1.isGameOver());
    this.model1.move(2,2,2,4);
    this.model1.move(0,2,2,2);
    this.model1.move(2,1,2,3);
    assertEquals(false, this.model1.isGameOver());
    this.model1.move(2,4,2,2);
    this.model1.move(0,4,0,2);
    this.model1.move(3,2,1,2);
    this.model1.move(0,2,2,2);
    assertEquals(false, this.model1.isGameOver());
    this.model1.move(3,0,3,2);
    this.model1.move(3,2,1,2);
    this.model1.move(1,1,1,3);
    this.model1.move(6,2,6,4);
    assertEquals(false, this.model1.isGameOver());
    this.model1.move(5,1,5,3);
    this.model1.move(4,1,4,3);
    this.model1.move(5,3,3,3);
    assertEquals(true, this.model1.isGameOver());
  }

  @Test
  public void testGetScore() {
    assertEquals(36, this.model1.getScore());
    this.model1.move(1,3, 3, 3);
    this.model1.move(4,3, 2, 3);
    this.model1.move(3, 5, 3, 3);
    this.model1.move(4,5,4,3);
    assertEquals(32, this.model1.getScore());
    this.model1.move(1, 5,3 , 5);
    this.model1.move(3, 6,3, 4);
    this.model1.move(6, 4, 4,4);
    this.model1.move(4, 3,4,5);
    assertEquals(28, this.model1.getScore());
    this.model1.move(4,6,4,4);
    this.model1.move(6,3,4,3);
    this.model1.move(3,4,5,4);
    assertEquals(25, this.model1.getScore());
    this.model1.move(5,5,5,3);
    this.model1.move(1,4,3,4);
    this.model1.move(4,3,6,3);
    this.model1.move(3,3,3,5);
    assertEquals(21, this.model1.getScore());
    this.model1.move(2,2,2,4);
    this.model1.move(0,2,2,2);
    this.model1.move(2,1,2,3);
    assertEquals(18, this.model1.getScore());
    this.model1.move(2,4,2,2);
    this.model1.move(0,4,0,2);
    this.model1.move(3,2,1,2);
    this.model1.move(0,2,2,2);
    assertEquals(14, this.model1.getScore());
    this.model1.move(3,0,3,2);
    this.model1.move(3,2,1,2);
    this.model1.move(1,1,1,3);
    this.model1.move(6,2,6,4);
    assertEquals(10, this.model1.getScore());
    this.model1.move(5,1,5,3);
    this.model1.move(4,1,4,3);
    this.model1.move(5,3,3,3);
    assertEquals(7, this.model1.getScore());
  }

  @Test
  public void testGetBoardSize() {
    assertEquals(7, this.model1.getBoardSize());
    assertEquals(7, this.model2.getBoardSize());
    assertEquals(13, this.model3.getBoardSize());
    assertEquals(13, this.model4.getBoardSize());
  }
}