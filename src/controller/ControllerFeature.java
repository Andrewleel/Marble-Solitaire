package controller;

import model.MarbleSolitaireModel;
import view.MarbleSolitaireGuiView;

/**
 * controller class for GUI view marble solitaire program.
 */
public class ControllerFeature implements Features {

  /**
   * Constructor for ControllerFeature class that takes in model and view.
   * @param modelPara model for the controller.
   * @param viewPara view for the controller.
   */
  public ControllerFeature(MarbleSolitaireModel modelPara, MarbleSolitaireGuiView viewPara) {
    MarbleSolitaireModel model;
    model = modelPara;
    MarbleSolitaireGuiView view;
    view = viewPara;
  }
}
