package controller;

import model.Game;
import model.NewCardObserver;
import view.EnglishView;
import view.View;
import view.View.Input;


/**
 * Scenario controller for playing the game.
 */
public class Player implements NewCardObserver {
  private View view;
  private Game game;

  /**
   * Contructs the Player controller.
   */
  public Player(View v) {
    this.game = new Game();
    this.view = v;
    this.game.addNewCardSubscriber(this);
  }

  /**
   * Represents one game round.

   * @return If the game should continue
   */
  public Boolean runGame() {
    updateView();
    return play();
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