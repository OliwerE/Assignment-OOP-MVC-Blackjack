package model.rules;

import model.Player;

public class dealerOnEqualWinStrategy implements WinStrategy {

  public boolean isDealerWinner(int maxScore, Player dealer, Player player) {
    if (player.calcScore() > maxScore) {
      return true;
    } else if (dealer.calcScore() > maxScore) {
      return false;
    }
    return dealer.calcScore() >= player.calcScore();
  }
  
}
