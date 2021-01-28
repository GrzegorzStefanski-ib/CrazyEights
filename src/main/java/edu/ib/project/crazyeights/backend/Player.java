//Copyright (C) 2021, Grzegorz Stefański
package edu.ib.project.crazyeights.backend;

import java.util.List;

public class Player {

  private String name;
  private List<Card> playerCards;
  private int score;
  private boolean cheater;
  private String log;

  public Player(String name, List<Card> playerCards) {
    this.name = name;
    this.playerCards = playerCards;
    this.score = 0;
    this.cheater = false;
  }

  public void playCard(Deck deck, int cardIndex) throws Exception {
    Card lastCardOnDiscardPile = deck.getLastCardFromDiscardPile();
    Card cardToPlay = playerCards.get(cardIndex);

    if (cardToPlay.compare(lastCardOnDiscardPile)) {
      playerCards.remove(cardToPlay);
      deck.addCardToDiscardPile(cardToPlay);
      log = "Player " + name + " played: " + cardToPlay.toString() + ".";
    } else log = "You cant play this card now.";
  }

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
            + Card.COLOR_ENCODING[newColor]
            + ".";
  }

  public void drawCard(Deck deck) {
    try {
      playerCards.add(deck.getCardFromDeck());
      log = "Player " + name + " drew card.";
    } catch (Exception e) {
      cheater = true;
    }
  }

  public boolean isPlayersCardEmpty() {
    return playerCards.isEmpty();
  }

  public boolean isCheater() {
    return cheater;
  }

  public Card getCard(int index) {
    return playerCards.get(index);
  }

  public List<Card> getPlayerCards() {
    return playerCards;
  }

  public String getLog() {
    return log;
  }
}
