package model.rules;

import model.Player;

/**
 * Win interface used to determine winner.
 * 
 */
public interface WinStrategy {
  /**
   * Checks if the player (dealer) should take more cards.

   * @param dealer the player to check.
   * @return True if the rule decided the player should take another card.
   */
  boolean isDealerWinner(int maxScore, Player dealer, Player player);
}
