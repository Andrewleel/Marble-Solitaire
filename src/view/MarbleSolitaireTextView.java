package view;

import java.io.IOException;

import model.MarbleSolitaireModelState;
import model.MarbleSolitaireModelState.SlotState;

/**
 * View class that takes model and display it for client, implemented MarbleSolitaireView interface.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {
  protected MarbleSolitaireModelState model;
  protected Appendable appendable;

  /**
   * Constructor for MarbleSolitaireTextView class that takes in a model.
   * @param model MarbleSolitaireModelState type model imported from
   *              MarbleSolitaireModelState interface.
   * @throws IllegalArgumentException when the model is null.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model or appendable is invalid.");
    }
    this.model = model;
    this.appendable = System.out;
  }

  /**
   * the second constructor for MarbleSolitaireTextView class that takes in model and appendable as
   *             arguments and throws exceptions when any of them are null.
   * @param model MarbleSolitaireModelState object for model state
   * @param appendable appendable argument for rendering board.
   * @throws IllegalArgumentException when the model and appendable are null.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model,
                                 Appendable appendable) throws IllegalArgumentException {
    if (model == null || appendable == null) {
      throw new IllegalArgumentException("Model or appendable is invalid.");
    }
    this.model = model;
    this.appendable = appendable;
  }

  /**
   * Return a string that represents the current state of the board. The
   * string should have one line per row of the game board. Each slot on the
   * game board is a single character (O, _ or space for a marble, empty and
   * invalid position respectively). Slots in a row should be separated by a
   * space. Each row has no space before the first slot and after the last slot.
   * @return the game state as a string
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < this.model.getBoardSize(); i++) {
      sb.append(this.toStringHelper(i));
      if (i < this.model.getBoardSize() - 1) {
        sb.append("\n");
      }
    }
    String str = sb.toString();
    return str;
  }

  // helper method for toString that takes care of column renderings for each row
  protected StringBuilder toStringHelper(int i) {
    StringBuilder sb = new StringBuilder();
    for (int j = 0; j < this.model.getBoardSize(); j++) {
      if (j < this.model.getBoardSize() - 1) {
        if ((this.model.getSlotAt(i, j).equals(SlotState.Marble)
                || this.model.getSlotAt(i, j).equals(SlotState.Empty))
                && (this.model.getSlotAt(i, j + 1).equals(SlotState.Invalid))) {
          if (this.model.getSlotAt(i, j).equals(SlotState.Marble)) {
            sb.append("O");
            break;
          } else if (this.model.getSlotAt(i, j).equals(SlotState.Empty)) {
            sb.append("_");
            break;
          }
        } else {
          if (this.model.getSlotAt(i, j).equals(SlotState.Marble)) {
            sb.append("O");
            sb.append(" ");
          } else if (this.model.getSlotAt(i, j).equals(SlotState.Empty)) {
            sb.append("_");
            sb.append(" ");
          } else if (this.model.getSlotAt(i, j).equals(SlotState.Invalid)) {
            sb.append(" ");
            sb.append(" ");
          }
        }
      } else {
        if (this.model.getSlotAt(i, j).equals(SlotState.Marble)) {
          sb.append("O");
        } else if (this.model.getSlotAt(i, j).equals(SlotState.Empty)) {
          sb.append("_");
        }
      }
    }
    return sb;
  }

  /**
   * Render the board to the provided data destination. The board should be rendered exactly
   * in the format produced by the toString method above
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  public void renderBoard() throws IOException {
    this.appendable.append(this.toString());
  }

  /**
   * Render a specific message to the provided data destination.
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  public void renderMessage(String message) throws IOException {
    this.appendable.append(message);
  }
}
