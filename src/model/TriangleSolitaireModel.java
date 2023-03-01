package model;

/**
 * class for TriangleSolitaireModel Model, extends AbstractSolitaireModel class
 *          that implements with MarbleSolitaireModel interface, which
 *          represents the interface for the models of our MarbleSolitaire.
 */
public class TriangleSolitaireModel extends AbstractSolitaireModel {

  /**
   * First constructor for TriangleSolitaireModel Model that takes in no parameter.
   */
  public TriangleSolitaireModel() {
    super(5, 0, 0);
  }

  /**
   * Second constructor for TriangleSolitaireModel Model
   *                  that takes in two parameters, sRow, and sCol.
   * @param sRow the y-coordinate of empty slot
   * @param sCol the x-coordinate of empty slot
   */
  public TriangleSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(5, sRow, sCol);
  }

  /**
   * Third constructor for TriangleSolitaireModel Model that takes in one parameter, dimension.
   * @param dimensions the initial armThickness.
   * @throws IllegalArgumentException when the armThickness is even or armThickness is negative.
   */
  public TriangleSolitaireModel(int dimensions) throws IllegalArgumentException {
    super(dimensions, 0, 0);
    if (dimensions <= 0) {
      throw new IllegalArgumentException("Dimension is Invalid");
    }
  }

  /**
   * Fourth constructor for TriangleSolitaireModel Model that takes in dimension, sRow, and sCol.
   * @param dimensions the initial armThickness
   * @param sRow the y-coordinate of empty slot
   * @param sCol the x-coordinate of empty slot
   */
  public TriangleSolitaireModel(int dimensions, int sRow, int sCol)
          throws IllegalArgumentException {
    super(dimensions, sRow, sCol);
  }

  /**
   * this method checks whether the (row, col) position of the marble is a invalid space.
   * @param row the row of the position (y-position)
   * @param col the column of the position (x-position)
   * @return true if the marble is invalid, false if the marble is valid.
   */
  @Override
  protected boolean inValidMarble(int row, int col) {
    // O
    // O O
    // O O O
    // O O O O
    // O O O O O
    return ((row < 0 || col < 0 || row >= this.getBoardSize() || col >= this.getBoardSize())
            || col > row);
  }

  /**
   * it is valid move when:
   *              the from Marble is Marble.
   *              the to Marble is empty.
   *              the from Marble is valid.
   *              the to marble is valid.
   *              the move has to be vertical or horizontal or diagonal.
   *              the distance from From Marble to To Marble is 2 slots. use (Math.abs).
   *              make sure the Marble you are jumping over is Marble.
   * @param fromRow the y-coordinate for from Marble.
   * @param fromCol the x-coordinate for from Marble.
   * @param toRow the y-coordinate for to Marble.
   * @param toCol the x-coordinate for from Marble.
   * @return boolean whether the move is valid considering all the scenario.
   */
  @Override
  protected boolean validMove(int fromRow, int fromCol, int toRow, int toCol) {
    return (this.board[fromRow][fromCol].equals(SlotState.Marble)
            && (!inValidMarble(fromRow, fromCol) && !inValidMarble(toRow, toCol))
            && (this.board[toRow][toCol].equals(SlotState.Empty))
            && (((Math.abs(fromRow - toRow) == 2) && (fromCol == toCol))
            || ((Math.abs(fromCol - toCol) == 2) && (fromRow == toRow))
            || ((Math.abs(fromCol - toCol) == 2) && (Math.abs(fromRow - toRow) == 2)))
            && (this.board[fromRow + (toRow - fromRow) / 2]
            [fromCol + (toCol - fromCol) / 2].equals(SlotState.Marble)));
  }

  /**
   * Determine and return if the game is over or not. A game is over if no
   *              more moves can be made.
   * @return true if the game is over, false otherwise.
   */
  @Override
  public boolean isGameOver() {
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if ((this.getSlotAt(i, j).equals(SlotState.Marble))
                && (this.validMove(i, j, i + 2, j)  || this.validMove(i, j, i - 2, j)
                || this.validMove(i, j, i, j + 2) || this.validMove(i, j, i, j - 2)
                || this.validMove(i, j, i + 2, j + 2)
                || this.validMove(i, j, i - 2, j - 2))) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Return the size of this board. The size is roughly the longest dimension of a board.
   * @return the size as an integer
   */
  @Override
  public int getBoardSize() {
    return this.armThickness;
  }
}
