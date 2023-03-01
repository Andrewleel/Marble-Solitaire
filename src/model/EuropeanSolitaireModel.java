package model;

/**
 * class for EuropeanSolitaire Model, extends AbstractSolitaireModel class
 *          that implements with MarbleSolitaireModel interface, which
 *          represents the interface for the models of our MarbleSolitaire.
 */
public class EuropeanSolitaireModel extends AbstractSolitaireModel {
  /**
   * First constructor that takes in no parameter.
   */
  public EuropeanSolitaireModel() {
    super(3, 3, 3);
  }

  /**
   * Second constructor that takes in two parameters, sRow, and sCol.
   * @param sRow the y-coordinate of empty slot
   * @param sCol the x-coordinate of empty slot
   */
  public EuropeanSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(3, sRow, sCol);

  }

  /**
   * Third constructor that takes in one parameter, armThickness.
   * @param sideLength the initial armThickness.
   * @throws IllegalArgumentException when the armThickness is even or armThickness is negative.
   */
  public EuropeanSolitaireModel(int sideLength) throws IllegalArgumentException {
    super(sideLength, ((sideLength * 3) / 2) - 1, ((sideLength * 3) / 2) - 1);
    if (sideLength % 2 == 0 || sideLength <= 0) {
      throw new IllegalArgumentException("sideLength is negative. Not Allowed!");
    }
  }

  /**
   * Fourth constructor that takes in.
   * @param sideLength the initial armThickness
   * @param sRow the y-coordinate of empty slot
   * @param sCol the x-coordinate of empty slot
   */
  public EuropeanSolitaireModel(int sideLength, int sRow, int sCol)
          throws IllegalArgumentException {
    super(sideLength, sRow, sCol);
    if (sideLength % 2 == 0 || sideLength <= 0) {
      throw new IllegalArgumentException("sideLength is negative. Not Allowed!");
    }
  }

  /**
   * this method checks whether the (row, col) position of the marble is a invalid space.
   * @param row the row of the position (y-position)
   * @param col the column of the position (x-position)
   * @return true if the marble is invalid, false if the marble is valid.
   */
  @Override
  protected boolean inValidMarble(int row, int col) {
    return ((row < 0 || col < 0
            || row > this.getBoardSize() - 1 || col > this.getBoardSize() - 1)
            || (row + col < this.armThickness - 1) // top left
            || (row - col > (this.armThickness * 2) - 2) // bottom left
            || (row + col > (this.armThickness * 5) - 5) // bottom right
            || (col - row > (this.armThickness * 2) - 2)); // top right
  }
}
