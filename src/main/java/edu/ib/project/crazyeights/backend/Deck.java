//Copyright (C) 2021, Grzegorz Stefański
package edu.ib.project.crazyeights.backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

  private List<Card> deckCards;
  private List<Card> discardPileCards;
  private Byte actualColor;

  public Deck(List<Card> deckCards, List<Card> discardPileCards) {
    this.deckCards = deckCards;
    this.discardPileCards = discardPileCards;
    this.actualColor = null;
  }

  public Card getCardFromDeck() throws Exception {
    if (deckCards.size() == 0) {

      // TODO: Change the exception name before presentation.
      if (discardPileCards.size() == 1)
        throw new Exception("You took all the cards and ruined the game, fucking faggot!");

      List<Card> cardsFromDiscardPileToDeck = new ArrayList<>();

      for (int i = 0; i < discardPileCards.size() - 1; i++) {
        cardsFromDiscardPileToDeck.add(discardPileCards.get(i));
      }

      Collections.shuffle(cardsFromDiscardPileToDeck);
      deckCards = cardsFromDiscardPileToDeck;

      Card lastCardFromDiscardPile = getLastCardFromDiscardPile();
      discardPileCards.clear();
      discardPileCards.add(lastCardFromDiscardPile);
    }

    return deckCards.remove(deckCards.size() - 1);
  }

  public Card getLastCardFromDiscardPile() {
    Card lastCardOnDiscardPile = discardPileCards.get(discardPileCards.size() - 1);

    if (actualColor != null) return new Card(actualColor, lastCardOnDiscardPile.getValue());
    else return lastCardOnDiscardPile;
  }

  public void addCardToDiscardPile(Card card) {
    discardPileCards.add(card);
    if (actualColor != null) actualColor = null;
  }

  public void addCardToDiscardPile(Card card, byte newColor) {
    discardPileCards.add(card);
    actualColor = newColor;
  }
}
