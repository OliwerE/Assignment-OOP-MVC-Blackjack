package model.rules;

/**
 * Creates concrete rules.
 */
public class RulesFactory {

  /**
   * Creates the rule to use for the dealer's hit behavior.

   * @return The rule to use
   */
  public HitStrategy getHitRule() {
    return new Soft17HitStrategy(); // new BasicHitStrategy();
  }

  /**
   * Creates the winner rule used to determine the winner.

   * @return The winner rule to use
   */
  public WinStrategy getWinStrategy() {
    return new PlayerOnEqualWinStrategy();
  }

  /**
   * Crates the rule to use when starting a new game.

   * @return The rule to use.
   */
  public NewGameStrategy getNewGameRule() {
    return new AmericanNewGameStrategy();
  }
}