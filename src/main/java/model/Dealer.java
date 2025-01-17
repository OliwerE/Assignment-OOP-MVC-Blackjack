package model;

import java.util.ArrayList;
import model.rules.HitStrategy;
import model.rules.NewGameStrategy;
import model.rules.RulesFactory;
import model.rules.WinStrategy;

/**
 * Represents a dealer player that handles the deck of cards and runs the game
 * using rules.
 */
public class Dealer extends Player {

  private Deck deck;
  private NewGameStrategy newGameRule;
  private HitStrategy hitRule;
  private WinStrategy winStrategy;
  private ArrayList<NewCardObserver> subscribers;

  /**
   * Initializing constructor.

   * @param rulesFactory A factory that creates the rules to use.
   */
  public Dealer(RulesFactory rulesFactory) {

    newGameRule = rulesFactory.getNewGameRule();
    hitRule = rulesFactory.getHitRule();
    winStrategy = rulesFactory.getWinStrategy();
    subscribers = new ArrayList<>();
  }

  /**
   * Starts a new game if the game is not currently under way.

   * @param player The player to play agains.
   * @return True if the game could be started.
   */
  public boolean newGame(Player player) {
    if (deck == null || isGameOver()) {
      deck = new Deck();
      clearHand();
      player.clearHand();
      return newGameRule.newGame(this, player);
    }
    return false;
  }

  /**
   * Gives a card from deck to a player (or dealer).

   * @param p Player who receives the card
   * @param isShow If the card is visible
   */
  public void dealPlayerCard(Player p, Boolean isShow) {
    Card.Mutable c = deck.getCard();
    c.show(isShow);
    p.dealCard(c);
    notifySubscribers();
  }

  /**
   * Add new subscriber to new card observer.

   * @param subscriber The subscriber to add
   */
  public void addSubscriber(NewCardObserver subscriber) {
    subscribers.add(subscriber);
  }

  /**
   * remove subscriber from new card observer.

   * @param subscriber The subscriber to remove
   */
  public void removeSubscriber(NewCardObserver subscriber) {
    subscribers.remove(subscriber);
  }

  /**
   * Notifies all new card subscribers.
   */
  private void notifySubscribers() {
    for (NewCardObserver newCardObserver : subscribers) {
      newCardObserver.newCard();
    }
  }

  /**
   * Gives the player one more card if possible. I.e. the player hits.

   * @param player The player to give a card to.
   * @return true if the player could get a new card, false otherwise.
   */
  public boolean hit(Player player) {
    if (deck != null && player.calcScore() < maxScore && !isGameOver()) {
      dealPlayerCard(player, true);

      return true;
    }
    return false;
  }

  /**
   * Checks if the dealer is the winner compared to a player.

   * @param player The player to check against.
   * @return True if the dealer is the winner, false if the player is the winner.
   */
  public boolean isDealerWinner(Player player) {
    return winStrategy.isDealerWinner(maxScore, this, player);
  }

  /**
   * Checks if the game is over, i.e. the dealer can take no more cards.

   * @return True if the game is over.
   */
  public boolean isGameOver() {
    if (deck != null && hitRule.doHit(this) != true) {
      return true;
    }
    return false;
  }

  /**
   * The player has choosen to take no more cards, it is the dealers turn.
   */
  public boolean stand() {
    if (this.deck != null) {
      showHand();
      while (hitRule.doHit(this) == true) {
        Boolean hit = hitRule.doHit(this);
        if (hit == true) {
          dealPlayerCard(this, true);
        }
      }
      return true;
    } else {
      return false;
    }
  }
}