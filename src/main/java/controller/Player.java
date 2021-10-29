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
  private Boolean isStarting;

  /**
   * Contructs the Player controller.
   */
  public Player(View v) {
    this.view = v;
    this.game = new Game();
    isStarting = true;
    this.game.addNewCardSubscriber(this);
  }

  /**
   * Updates welcome message and hands in the view.
   */
  private void updateView() {
    view.displayWelcomeMessage();
    view.displayDealerHand(game.getDealerHand(), game.getDealerScore());
    view.displayPlayerHand(game.getPlayerHand(), game.getPlayerScore());
  }

  /**
   * Runs the play use case.

   * @return True as long as the game should continue.
   */
  public boolean play() {
    if (isStarting) {
      updateView();
      isStarting = false;
    }

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

  /**
   * When a dealer or player get a new card.
   */
  public void newCard() {
    updateView();
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      view.displayInterruptedException(e);
    }
  }
}