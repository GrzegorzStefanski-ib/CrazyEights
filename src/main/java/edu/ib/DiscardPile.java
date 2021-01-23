//Copyright (C) 2021, Grzegorz Stefański
package edu.ib;

import java.util.List;

public class DiscardPile {

  private List<Card> cards;

  /** @param cards */
  public DiscardPile(List<Card> cards) {
    this.cards = cards;
  }

  /** @return */
  public Card getLastCard() {
    return cards.get(cards.size() - 1);
  }

  /** @param card */
  public void addCard(Card card) {
    cards.add(card);
  }
}
