package controller;

import model.Game;
import model.NewCardObserver;
import view.View;
import view.View.Input;


/**
 * Scenario controller for playing the game.
 */
public class Player implements NewCardObserver {
  private View view;
  private Game game;

  public Player(Game g, View v) {
    game = g;
    view = v;
    game.addNewCardSubscriber(this);
  }

  public Boolean runGame() {
    displayHands();
    return play();
  }

  private void displayHands() {
    view.displayWelcomeMessage();
    view.displayDealerHand(game.getDealerHand(), game.getDealerScore());
    view.displayPlayerHand(game.getPlayerHand(), game.getPlayerScore());
  }

  /**
   * Runs the play use case.

   * @param game The game state.
   * @param view The view to use.
   * @return True as long as the game should continue.
   */
  public boolean play() {
    // view.displayWelcomeMessage();

    if (game.isGameOver()) {
      view.displayGameOver(game.isDealerWinner());
    }

    Input input = view.getInput();

    if (input == Input.Play) {
      game.newGame();
    } else if (input == Input.Hit) {
      game.hit();
    } else if (input == Input.Stand) {
      game.stand();
    }

    return input != Input.Quit;
  }

  public void newCard() {
    displayHands();
    try{
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      // Handle error
    }
  }
}