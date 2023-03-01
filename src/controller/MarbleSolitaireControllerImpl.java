package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.MarbleSolitaireModel;
import view.MarbleSolitaireView;

/**
 * class for MarbleSolitaireControllerImpl that implements MarbleSolitaireController interface
 *               which represents the controller for our Marble Solitaire Game that takes in
 *               model, view, and readable.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private MarbleSolitaireModel model;
  private MarbleSolitaireView view;
  private Readable readable;

  /**
   * Constructor for MarbleSolitaireControllerImpl class that takes in model, view and readable
   *              as the arguments.
   * @param model MarbleSolitaireModel that represents the model of our game.
   * @param view MarbleSolitaireView that represents the view of our gmae.
   * @param readable Readable that reads the input
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model,
                                          MarbleSolitaireView view, Readable readable) {
    if (model == null || view == null || readable == null) {
      throw new IllegalArgumentException("Parameter is null!");
    }
    this.model = model;
    this.view = view;
    this.readable = readable;
  }

  /**
   * the method that mainly serves the interaction between model, view, and this controller.
   * It will probably take StringReader as the readable and read and communicates with the view to
   * render the board and maybe some messages. This method will try to process those actions and if
   * there is an IOException is being thrown during the process,
   * it will catch that and throw IllegalStateException.
   * @throws IllegalStateException will be thrown if the view is not rendering the messages.
   */
  @Override
  public void playGame() throws IllegalStateException {
    Scanner sc = new Scanner(this.readable);
    while (!this.model.isGameOver()) {

      this.renderRegularBoardMessage();
      // Arraylist with coordinates eventually added later
      List<Integer> temp = new ArrayList<Integer>();
      while (temp.size() < 4) {

        // check if scanner has next
        if (!sc.hasNext()) {
          throw new IllegalStateException("Nothing left to read.");
        }

        // is scanner.next() quit button? if not continue
        String next = sc.next();
        if (next.equals("q") || next.equals("Q")) {
          this.renderRegularGameQuitMessage();
          return;
        }

        // check if scanner.next() is a valid integer
        try {
          int coordinate = Integer.parseInt(next);
          if (coordinate > 0) {
            temp.add(coordinate);
          }
        } catch (NumberFormatException nfe) {
          this.renderInvalidInputMessage("Please enter valid input!",
                  "Invalid input for next value is not reading.");
        }
      }

      // try moving with coordinates given, subtract one bc input starts from 1,
      // but our coordinates start from 0.
      try {
        this.model.move(temp.get(0) - 1, temp.get(1) - 1,
                temp.get(2) - 1, temp.get(3) - 1);
      } catch (IllegalArgumentException iae) {
        this.renderInvalidInputMessage("Invalid move. Play again.",
                "Invalid value for move method is not reading.");
      }
    }

    // doesn't need if statement but why not.
    if (this.model.isGameOver()) {
      this.renderGameOverMessage();
    }
  }

  // the method that tries to render the board with messages.
  private void renderRegularBoardMessage() {
    try {
      this.view.renderMessage("\n");
      this.view.renderBoard();
      this.view.renderMessage("\n" + "Score: " + this.model.getScore() + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("The board is not rendering.");
    }
  }

  // the method that tries to render the game quit scene.
  private void renderRegularGameQuitMessage() {
    try {
      this.view.renderMessage("\n" + "Game quit!" + "\n");
      this.view.renderMessage("State of game when quit:" + "\n");
      this.view.renderBoard();
      this.view.renderMessage("\n" + "Score: " + this.model.getScore() + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("the Quit key is not reading.");
    }
  }

  // the method that tries to render the invalid input messages.
  private void renderInvalidInputMessage(String text1, String text2) {
    try {
      this.view.renderMessage("\n" + text1 + "\n");
    } catch (IOException e) {
      throw new IllegalStateException(text2);
    }
  }

  // the method that tries to render the game over scene.
  private void renderGameOverMessage() {
    try {
      this.view.renderMessage("\n" + "Game over!" + "\n");
      this.view.renderBoard();
      this.view.renderMessage("\n" + "Score: " + this.model.getScore() + "\n");
    } catch (IOException e) {
      throw new IllegalStateException(" d");
    }
  }
}
