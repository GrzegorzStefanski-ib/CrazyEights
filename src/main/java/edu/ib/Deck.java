//Copyright (C) 2021, Grzegorz Stefański
package edu.ib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

  private List<Card> cards;

  public Deck(List<Card> cards) {
    this.cards = cards;
    Collections.shuffle(cards);
  }

  public Card getCard() {
    return cards.remove(cards.size() - 1);
  }

  public List<Card> getCards(int n) {

    List<Card> cardList = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      cardList.add(getCard());
    }

    return cardList;
  }
}
