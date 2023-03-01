package model;

import model.MarbleSolitaireModel;

/**
 * class for AbstractSolitaireModel class that implements with MarbleSolitaireModel interface,
 *        which represents the interface for the models of our MarbleSolitaire.
 */
public abstract class AbstractSolitaireModel implements MarbleSolitaireModel {
  protected SlotState[][] board;
  protected int armThickness;

  /**
   * the constructor that takes in.
   * @param armThickness the initial armThickness
   * @param sRow the y-coordinate of empty slot
   * @param sCol the x-coordinate of empty slot
   */
  public AbstractSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    this.armThickness = armThickness;
    if (inValidMarble(sRow, sCol)) {
      throw new IllegalArgumentException("invalid coordinates");
    }
    this.board = this.finalizeBoard(sRow, sCol);
  }

  /**
   * method to abstract dirty code.
   * this combines all the methods to finalize the board.
   * @param sRow the y coordinate of empty slot
   * @param sCol the x coordinate of empty slot
   * @return finalized board.
   */
  protected SlotState[][] finalizeBoard(int sRow, int sCol) {
    makeBoard();
    board[sRow][sCol] = SlotState.Empty;
    return board;
  }

  /**
   * Make the board for the Marble Solitaire.
   * If the marble space is not invalid and not the center, make the string "O".
   * If the marble space is not invalid, but it's the center, make the string "_".
   * If the marble space is
   */
  protected void makeBoard() {
    board = new SlotState[this.getBoardSize()][this.getBoardSize()];
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (!(this.inValidMarble(i, j))) {
          board[i][j] = SlotState.Marble;
        } else {
          board[i][j] = SlotState.Invalid;
        }
      }
    }
  }

  /**
   * this method checks whether the (row, col) position of the marble is a invalid space.
   * @param row the row of the position (y-position)
   * @param col the column of the position (x-position)
   * @return true if the marble is invalid, false if the marble is valid.
   */
  protected abstract boolean inValidMarble(int row, int col);

  /**
   * it is valid move when:
   *              the from Marble is Marble.
   *              the to Marble is empty.
   *              the from Marble is valid.
   *              the to marble is valid.
   *              the move has to be vertical or horizontal.
   *              the distance from From Marble to To Marble is 2 slots. use (Math.abs).
   *              make sure the Marble you are jumping over is Marble.
   * @param fromRow the y-coordinate for from Marble.
   * @param fromCol the x-coordinate for from Marble.
   * @param toRow the y-coordinate for to Marble.
   * @param toCol the x-coordinate for from Marble.
   * @return boolean whether the move is valid considering all the scenario.
   */
  protected boolean validMove(int fromRow, int fromCol, int  toRow, int toCol) {
    return (this.board[fromRow][fromCol].equals(SlotState.Marble)
            && (!inValidMarble(fromRow, fromCol) && !inValidMarble(toRow, toCol))
            && (this.board[toRow][toCol].equals(SlotState.Empty))
            && (fromCol == toCol || fromRow == toRow)
            && ((Math.abs(fromRow - toRow) == 2) || (Math.abs(fromCol - toCol) == 2))
            && (this.board[fromRow + (toRow - fromRow) / 2]
            [fromCol + (toCol - fromCol) / 2].equals(SlotState.Marble)));
  }

  /**
   * Move a single marble from a given position to another given position.
   * A move is valid only if the from and to positions are valid. Specific
   *                  implementations may place additional constraints on the validity of a move.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0).
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0).
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0).
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0).
   * @throws IllegalArgumentException if the move is not possible.
   */
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws
          IllegalArgumentException {
    try {
      if (this.validMove(fromRow, fromCol, toRow, toCol)) {
        this.board[fromRow][fromCol] = SlotState.Empty;
        this.board[toRow][toCol] = SlotState.Marble;
        this.board[fromRow + (toRow - fromRow) / 2]
                [fromCol + (toCol - fromCol) / 2] = SlotState.Empty;
      }
      else {
        throw new IllegalArgumentException("You can't move like this!");
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new IllegalArgumentException("You can't move like this!");
    }
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
                || this.validMove(i, j, i, j + 2) || this.validMove(i, j, i, j - 2))) {
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
    return (this.armThickness * 3) - 2;
  }

  /**
   * Get the state of the slot at a given position on the board.
   *
   * @param row the row of the position sought, starting at 0.
   * @param col the column of the position sought, starting at 0.
   * @return the state of the slot at the given row and column.
   * @throws IllegalArgumentException if the row or the column are beyond
   *         the dimensions of the board.
   */
  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || col < 0 || row >= this.getBoardSize() || col >= this.getBoardSize()) {
      throw new IllegalArgumentException("This is located beyond the dimensions of the board.");
    }
    else {
      return this.board[row][col];
    }
  }

  /**
   * Calculate the score for the game.
   * @return an integer that presents the score.
   */
  @Override
  public int getScore() {
    int score = 0;
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (this.board[i][j].equals(SlotState.Marble)) {
          score += 1;
        }
      }
    }
    return score;
  }
}
