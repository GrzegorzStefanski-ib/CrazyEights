//Copyright (C) 2021, Grzegorz Stefański
package edu.ib;

import java.util.ArrayList;
import java.util.List;

public class DiscardPile {

  private List<Card> cards = new ArrayList<>();

  public Card getLastCard() {
    return cards.get(cards.size() - 1);
  }

  public void addCard(Card card) {
    cards.add(card);
  }
}
