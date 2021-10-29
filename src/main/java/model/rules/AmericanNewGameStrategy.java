package model.rules;

import model.Dealer;
import model.Player;

class AmericanNewGameStrategy implements NewGameStrategy {

  public boolean newGame(Dealer dealer, Player player) {
    dealer.dealPlayerCard(player, true);

    dealer.dealPlayerCard(dealer, true);

    dealer.dealPlayerCard(player, true);

    dealer.dealPlayerCard(dealer, false);

    return true;
  }
}