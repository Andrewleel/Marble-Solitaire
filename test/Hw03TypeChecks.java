import controller.MarbleSolitaireControllerImpl;
import model.EnglishSolitaireModel;
import model.MarbleSolitaireModel;
import view.MarbleSolitaireTextView;

/**
 * Do not modify this file. This file should compile correctly with your code!
 */
public class Hw03TypeChecks {

  /**
   * Main method for Hw03TypeChecks that checks the basic requirements for hw3.
   * @param args the argument for main method. Not really sure what this does yet.
   */
  public static void main(String[] args) {
    Readable rd = null;
    Appendable ap = null;
    helper(new EnglishSolitaireModel(), rd, ap);
    helper(new EnglishSolitaireModel(3, 3), rd, ap);
  }

  private static void helper(
          MarbleSolitaireModel model,
          Readable rd, Appendable ap) {
    new MarbleSolitaireControllerImpl(model,
            new MarbleSolitaireTextView(model,ap),rd);
  }

}
