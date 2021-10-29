package model;

/**
 * Observes when new card is added to player or dealer hand.
 */
public interface NewCardObserver {
  public void newCard();
}
