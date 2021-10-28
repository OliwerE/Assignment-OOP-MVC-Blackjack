package model.rules;

import model.Card;
import model.Player;

public class Soft17HitStrategy implements HitStrategy {
  private static final int hitLimit = 17;

  public boolean doHit(Player dealer) {
    if (dealer.calcScore() == hitLimit) {
      Iterable<Card> hand = dealer.getHand();

      int aceCount = 0;
      for (Card c: hand) {
        if (c.getValue() == Card.Value.Ace) {
          aceCount++;
        }
      }

      if (aceCount > 0) {
        return true; // Allow one more card
      }
    }
    return dealer.calcScore() < hitLimit;
  }
}
