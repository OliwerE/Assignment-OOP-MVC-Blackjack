package model.rules;

import model.Player;

/**
 * Win strategy when dealer win on equal score.
 */
public class DealerOnEqualWinStrategy implements WinStrategy {

  /**
   * Checks if the dealer is the winner compared to a player.

   * @param maxScore The highest score.
   * @param dealer The dealer.
   * @param player The player to check against.
   */
  public boolean isDealerWinner(int maxScore, Player dealer, Player player) {
    if (player.calcScore() > maxScore) {
      return true;
    } else if (dealer.calcScore() > maxScore) {
      return false;
    } else if (dealer.calcScore() == player.calcScore()) {
      return true;
    }
    return dealer.calcScore() > player.calcScore();
  }
  
}
