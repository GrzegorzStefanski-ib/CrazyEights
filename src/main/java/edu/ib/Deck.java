//Copyright (C) 2021, Grzegorz Stefański
package edu.ib;

import java.util.List;

public class Deck {

  private List<Card> cards;

  /** @param cards */
  public Deck(List<Card> cards) {
    this.cards = cards;
  }

  /** @return */
  public Card getCard() {
    return cards.remove(cards.size() - 1);
  }
}
