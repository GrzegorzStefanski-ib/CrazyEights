//Copyright (C) 2021, Grzegorz Stefański
package edu.ib.project.crazyeights.backend;

import java.util.List;

/** The class representing the player in the game. Bots too. */
public class Player {

  private final String name;
  private final List<Card> playerCards;
  /** Variable that determines whether the player has broken the game rules. */
  private boolean cheater;

  /** Message about the player's last move. */
  private String log;

  public Player(String name, List<Card> playerCards) {
    this.name = name;
    this.playerCards = playerCards;
    this.cheater = false;
  }

  /**
   * The method that allows the player to play a card.
   *
   * @param deck The set of cards on which the discarded card is to be placed.
   * @param cardIndex The card index from a player's stock of cards that identifies which card to
   *     play.
   * @return 1 when the player played a card or 0 when he was unable to do so.
   */
  public boolean playCard(Deck deck, int cardIndex) {
    boolean ok = true;

    Card lastCardOnDiscardPile = deck.getLastCardFromDiscardPile();
    Card cardToPlay = playerCards.get(cardIndex);

    if (cardToPlay.compare(lastCardOnDiscardPile)) {
      playerCards.remove(cardToPlay);
      deck.addCardToDiscardPile(cardToPlay);
      log = "Player " + name + " played: " + cardToPlay.toString() + ".";
    } else {
      log = "You cant play this card now.";
      ok = false;
    }

    return ok;
  }

  /**
   * The method that allows the player to play Crazy Eight.
   *
   * @param deck The set of cards on which the discarded Crazy Eight is to be placed.
   * @param cardIndex The Crazy Eight index from a player's stock of cards that identifies which
   *     Crazy Eight to play.
   * @param newColor The new suit in the game that the player wants to set.
   */
  public void playCrazyEight(Deck deck, int cardIndex, byte newColor) {
    Card cardToPlay = playerCards.get(cardIndex);

    playerCards.remove(cardToPlay);
    deck.addCardToDiscardPile(cardToPlay, newColor);
    log =
        "Player "
            + name
            + " played Crazy Eight: "
            + cardToPlay.toString()
            + ".\n"
            + "Color changed to: "
            + Card.SUIT_ENCODING[newColor]
            + ".";
  }

  /**
   * The method that allows the player to draw cards.
   *
   * @param deck The set of cards from which cards are to be drawn.
   */
  public void drawCard(Deck deck) {
    try {
      playerCards.add(deck.getCardFromDeck());
      log = "Player " + name + " drew card.";
    } catch (Exception e) {
      cheater = true;
    }
  }

  /**
   * The method of checking if the player has got rid of all the cards in his hand.
   *
   * @return 1 when the player has no cards left and 0 when he still has.
   */
  public boolean isPlayersCardEmpty() {
    return playerCards.isEmpty();
  }

  /**
   * The method that returns the card with the given index.
   *
   * @param index Card index in the hand of the player to be returned.
   * @return The card with the indicated index.
   */
  public Card getCard(int index) {
    return playerCards.get(index);
  }

  public List<Card> getPlayerCards() {
    return playerCards;
  }

  public boolean isCheater() {
    return cheater;
  }

  public String getLog() {
    return log;
  }
}
