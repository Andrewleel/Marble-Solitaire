package view;

import model.MarbleSolitaireModelState;

/**
 * View class that takes a triangleSolitaire model
 *            and display it for client, extends MarbleSolitaireTextView class, that
 *            implemented MarbleSolitaireView interface.
 */
public class TriangleSolitaireTextView extends MarbleSolitaireTextView {

  /**
   * the constructor that takes in a model for TriangleSolitaireTextView that extends
   *               MarbleSolitaireTextView.
   * @param model MarbleSolitaireModelState object for model state
   * @throws IllegalArgumentException if model is null.
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model)
          throws IllegalArgumentException {
    super(model);
  }

  /**
   * the second constructor that takes in a model and appendable for TriangleSolitaireTextView
   *            that extends MarbleSolitaireTextView.
   * @param model  MarbleSolitaireModelState object for model state
   * @param appendable appendable argument for rendering board.
   * @throws IllegalArgumentException if model or appendable is null.
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model, Appendable appendable)
          throws IllegalArgumentException {
    super(model, appendable);
  }

  /**
   * Return a string that represents the current state of the board. The
   * string should have one line per row of the game board. Each slot on the
   * game board is a single character (O, _ or space for a marble, empty and
   * invalid position respectively). Slots in a row should be separated by a
   * space. Each row has no space before the first slot and after the last slot.
   * @return the game state as a string
   */

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    int counter = this.model.getBoardSize() - 1;
    for (int i = 0; i < this.model.getBoardSize(); i++) {
      for (int k = counter; k > 0; k--) {
        sb.append(" ");
      }
      sb.append(this.toStringHelper(i));
      if (i < this.model.getBoardSize() - 1) {
        sb.append("\n");
      }
      counter -= 1;
    }
    String str = sb.toString();
    return str;
  }
}
