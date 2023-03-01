package model;

/**
 * class for EnglishSolitaireModel Model, extends AbstractSolitaireModel class
 *          that implements with MarbleSolitaireModel interface, which
 *          represents the interface for the models of our MarbleSolitaire.
 */
public class EnglishSolitaireModel extends AbstractSolitaireModel {

  /**
   * First constructor that takes in no parameter.
   */
  public EnglishSolitaireModel() {
    super(3, 3, 3);
  }

  /**
   * Second constructor that takes in two parameters, sRow, and sCol.
   * @param sRow the y-coordinate of empty slot
   * @param sCol the x-corrdinate of empty slot
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(3, sRow, sCol);
  }

  /**
   * Third constructor that takes in one parameter, armThickness.
   * @param armThickness the inital armThickness.
   * @throws IllegalArgumentException when the armThickness is even or armThickness is negative.
   */
  public EnglishSolitaireModel(int armThickness) throws IllegalArgumentException {
    super(armThickness, (armThickness * 3 / 2)  - 1 , (armThickness * 3 / 2)  - 1);
    if (armThickness % 2 == 0 || armThickness <= 0) {
      throw new IllegalArgumentException("armThickness is negative. Not Allowed!");
    }
  }

  /**
   * Fourth constructor that takes in.
   * @param armThickness the inital armThickness
   * @param sRow the y-coordinate of empty slot
   * @param sCol the x-coordinate of empty slot
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    super(armThickness, sRow, sCol);
    if (armThickness % 2 == 0 || armThickness <= 0) {
      throw new IllegalArgumentException("armThickness is negative. Not Allowed!");
    }
  }

  /**
   * this method checks whether the (row, col) position of the marble is a invalid space.
   * @param row the row of the position (y-position)
   * @param col the column of the position (x-position)
   * @return true if the marble is invalid, false if the marble is valid.
   */
  protected boolean inValidMarble(int row, int col) {
    return ((row < 0 || col < 0 || row >= this.getBoardSize() || col >= this.getBoardSize())
            || (col < armThickness - 1 && row < armThickness - 1)
            || ((col > (armThickness * 2 - 2) && row < armThickness - 1))
            || (col < armThickness - 1 && row > (armThickness * 2 - 2))
            || ((col > (armThickness * 2 - 2) && row > (armThickness * 2 - 2))));
  }
}