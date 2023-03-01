import model.MarbleSolitaireModel;

/**
 * Class for MockModel for testing purposes to check if the controller is sending information to
 *             the model correctly.
 */
public class MockModel implements MarbleSolitaireModel {
  protected final StringBuilder log;
  protected int count;

  /**
   * Constructor for MockModel class that takes in a StringBuilder argument and
   *              throws exception if the argument is null.
   * @param log StringBuilder object
   * @throws IllegalArgumentException if the log argument is null.
   */
  public MockModel(StringBuilder log) throws IllegalArgumentException {
    if (log == null) {
      throw new IllegalArgumentException("Invalid Argument");
    }
    this.log = log;
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws
          IllegalArgumentException {
    this.log.append((fromRow + 1) + " " + (fromCol + 1)
            + " " + (toRow + 1) + " " + (toCol + 1));
  }

  @Override
  public boolean isGameOver() {
    count += 1;
    return count == 2;
  }

  @Override
  public int getBoardSize() {
    return 0;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    return null;
  }

  @Override
  public int getScore() {
    return 0;
  }
}
