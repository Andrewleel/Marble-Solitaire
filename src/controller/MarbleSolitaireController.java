package controller;

/**
 * the interface MarbleSolitaireController for the controller of our game that implements playGame
 *          method.
 */
public interface MarbleSolitaireController {

  /**
   * the method that mainly serves the interaction between model, view, and this controller.
   * It will probably take StringReader as the readable and read and communicates with the view to
   * render the board and maybe some messages. This method will try to process those actions and if
   * there is an IOException is being thrown during the process,
   * it will catch that and throw IllegalStateException.
   * @throws IllegalStateException will be thrown if the view is not rendering the messages.
   */
  void playGame() throws IllegalStateException;
}
