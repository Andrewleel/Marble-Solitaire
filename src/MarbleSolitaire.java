import java.io.InputStreamReader;

import controller.MarbleSolitaireController;
import controller.MarbleSolitaireControllerImpl;
import model.EnglishSolitaireModel;
import model.MarbleSolitaireModel;
import model.EuropeanSolitaireModel;
import model.TriangleSolitaireModel;
import view.MarbleSolitaireTextView;
import view.MarbleSolitaireView;
import view.TriangleSolitaireTextView;

/**
 * class for main method that actually plays the game with inputs from client.
 */
public final class MarbleSolitaire {

  /**
   * Our main method that plays the game with inputs from the user.
   * @param args string array that includes model name, the armThickness (dimension or sideLength)
   *             , and the coordinates for starting empty slot.
   * @throws IllegalArgumentException when the model name is not valid.
   */
  public static void main(String[] args) throws IllegalArgumentException {
    String model_name = args[0].toLowerCase();
    MarbleSolitaireModel model;
    MarbleSolitaireView view;
    MarbleSolitaireController controller;
    Readable rd;
    int armThickness = 3;
    int sRow = 3;
    int sCol = 3;

    if (model_name.equalsIgnoreCase("triangular")) {
      armThickness = 5;
      sRow = 0;
      sCol = 0;
    }

    for (int i = 1; i < args.length; i ++) {
      if (args[i].equals("-size")) {
        armThickness = Integer.parseInt(args[i + 1]);
      }
      if (args[i].equals("-hole")) {
        sRow = Integer.parseInt(args[i + 1]);
        sCol = Integer.parseInt(args[i + 2]);
      }
    }

    switch (model_name) {
      case "english":
        model = new EnglishSolitaireModel(armThickness, sRow, sCol);
        view = new MarbleSolitaireTextView(model);
        rd = new InputStreamReader(System.in);
        controller = new MarbleSolitaireControllerImpl(model, view, rd);
        controller.playGame();
        break;
      case "european":
        model = new EuropeanSolitaireModel(armThickness, sRow, sCol);
        view = new MarbleSolitaireTextView(model);
        rd = new InputStreamReader(System.in);
        controller = new MarbleSolitaireControllerImpl(model, view, rd);
        controller.playGame();
        break;
      case "triangular":
        model = new TriangleSolitaireModel(armThickness, sRow, sCol);
        view = new TriangleSolitaireTextView(model);
        rd = new InputStreamReader(System.in);
        controller = new MarbleSolitaireControllerImpl(model, view, rd);
        controller.playGame();
        break;
      default:
        break;
    }
  }
}
