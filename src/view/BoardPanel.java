package view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.MarbleSolitaireModelState;

/**
 * class for BoardPanel view.
 */
public class BoardPanel extends JPanel {
  private MarbleSolitaireModelState modelState;
  private final int cellDimension;
  private int originX;
  private int originY;

  /**
   * constructor for BoardPanel view class.
   * @param state MarbleSolitaire model.
   * @throws IllegalStateException if IOException is caught.
   */
  public BoardPanel(MarbleSolitaireModelState state) throws IllegalStateException {
    super();
    Image emptySlot;
    Image marbleSlot;
    Image blankSlot;
    this.modelState = state;
    this.setBackground(Color.WHITE);
    this.cellDimension = 50;
    try {
      emptySlot = ImageIO.read(new FileInputStream("res/empty.png"));
      emptySlot = emptySlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      marbleSlot = ImageIO.read(new FileInputStream("res/marble.png"));
      marbleSlot = marbleSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      blankSlot = ImageIO.read(new FileInputStream("res/blank.png"));
      blankSlot = blankSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      this.setPreferredSize(
              new Dimension((this.modelState.getBoardSize() + 4) * cellDimension
                      , (this.modelState.getBoardSize() + 4) * cellDimension));
    } catch (IOException e) {
      throw new IllegalStateException("Icons not found!");
    }
    this.originX = 0;
    this.originY = 0;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    this.originX = (int) (this.getPreferredSize().getWidth() / 2 -
            this.modelState.getBoardSize() * cellDimension / 2);
    this.originY = (int) (this.getPreferredSize().getHeight() / 2 -
            this.modelState.getBoardSize() * cellDimension / 2);


    //your code to the draw the board should go here. 
    //The originX and originY is the top-left of where the cell (0,0) should start
    //cellDimension is the width (and height) occupied by every cell
    
  }


 
}
