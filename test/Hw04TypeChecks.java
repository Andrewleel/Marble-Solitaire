import controller.MarbleSolitaireControllerImpl;
import model.EnglishSolitaireModel;
import model.TriangleSolitaireModel;
import model.MarbleSolitaireModel;
import view.MarbleSolitaireTextView;

/**
 * Do not modify this file. This file should compile correctly with your code!
 */
public class Hw04TypeChecks {

  /**
   * main method for hw04TypeChecks to ensure our hw4 has minimal requirements.
   * @param args parameter for our main method which is string array
   */
  public static void main(String[] args) {
    Readable rd = null;
    Appendable ap = null;
    helperMarble(new EnglishSolitaireModel(),
            rd, ap);

    helperTriangle(new TriangleSolitaireModel(3, 3),
            rd, ap);
  }

  private static void helperMarble(
          MarbleSolitaireModel model,
          Readable rd,Appendable ap) {
    new MarbleSolitaireControllerImpl(model,
            new MarbleSolitaireTextView(model,ap),rd);
  }

  private static void helperTriangle(
          MarbleSolitaireModel model,
          Readable rd,Appendable ap) {
    new MarbleSolitaireControllerImpl(model,
            new MarbleSolitaireTextView(model,ap),rd);
  }

}
