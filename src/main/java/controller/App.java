package controller;

import model.Game;
import view.EnglishView;
import view.View;

/**
 * Starts the application using the console.
 */
public class App {
  /**
   * Starts the game.

  * @param args Not used.
  */
  public static void main(String[] args) {
    View v = new EnglishView();
    Player ctrl = new Player(v);

    while (ctrl.runGame()) {

    }
  }
}