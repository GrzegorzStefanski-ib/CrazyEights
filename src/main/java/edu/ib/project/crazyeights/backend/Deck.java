//Copyright (C) 2021, Grzegorz Stefański
package edu.ib.project.crazyeights.backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** The class representing a collection of playing cards. */
public class Deck {

  private List<Card> deckCards;
  private final List<Card> discardPileCards;
  /** The variable that is set as the new valid suit in the game after playing the Crazy Eight. */
  private Byte actualSuit;

  public Deck(List<Card> deckCards, List<Card> discardPileCards) {
    this.deckCards = deckCards;
    this.discardPileCards = discardPileCards;
    this.actualSuit = null;
  }

  /**
   * The method that allows to get last card from the deck. When the deck runs out of cards, it will
   * automatically draw cards from the discard pile.
   *
   * @return Last card from deck.
   * @throws Exception If the player only draws cards without playing them, it destroys the ability
   *     to move bots.
   */
  public Card getCardFromDeck() throws Exception {
    if (deckCards.size() == 0) {

      if (discardPileCards.size() == 1)
        throw new Exception("You took all the cards and ruined the game :(");

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

  /**
   * The method that allows to take the last card from the discard pile. Used to compare cards by
   * methods from the {@see Card} class. It takes into account the current suit.
   *
   * @return Last card from discard pile.
   */
  public Card getLastCardFromDiscardPile() {
    Card lastCardOnDiscardPile = discardPileCards.get(discardPileCards.size() - 1);

    if (actualSuit != null) return new Card(actualSuit, lastCardOnDiscardPile.getValue());
    else return lastCardOnDiscardPile;
  }

  /**
   * The method that allows to take the last card from the discard pile for GUI. It do not take into
   * account the current suit.
   *
   * @return Last card from discard pile.
   */
  public Card getLastCardFromDiscardPileForGUI() {
    return discardPileCards.get(discardPileCards.size() - 1);
  }

  /**
   * The method of adding regular cards to the discard pile. It do not change actual suit. When a
   * regular card is added, the suit is reset.
   *
   * @param card Card to be added to the discard pile.
   */
  public void addCardToDiscardPile(Card card) {
    discardPileCards.add(card);
    if (actualSuit != null) actualSuit = null;
  }

  /**
   * The method of adding Crazy Eights to the discard pile. It change actual suit.
   *
   * @param card Card to be added to the discard pile.
   */
  public void addCardToDiscardPile(Card card, byte newColor) {
    discardPileCards.add(card);
    actualSuit = newColor;
  }

  public Byte getActualSuit() {
    return actualSuit;
  }
}
