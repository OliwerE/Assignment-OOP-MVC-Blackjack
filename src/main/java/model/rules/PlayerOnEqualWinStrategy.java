package model.rules;

import model.Player;

public class PlayerOnEqualWinStrategy implements WinStrategy {

  /**
   * Checks if the dealer is the winner compared to a player.
   * 
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
      return false;
    }
    return dealer.calcScore() > player.calcScore();
  }
  
}
