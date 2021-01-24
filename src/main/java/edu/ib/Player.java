//Copyright (C) 2021, Grzegorz Stefański
package edu.ib;

import java.util.List;

public class Player {

  private String name;
  private List<Card> cards;
  private int score;

  public Player(String name, List<Card> cards) {
    this.name = name;
    this.cards = cards;
    this.score = 0;
  }

  public Card playCard(DiscardPile discardPile, int index) {

    Card card = cards.get(index);

    if (discardPile.getLastCard().compare(card)) {
      return cards.remove(index);
    }

    return null;
  }

  public boolean checkIfWin() {
    return cards.isEmpty();
  }

  public void drawCard(Deck deck) {

    Card card = deck.getCard();
    cards.add(card);
  }

  public int getScore() {
    return score;
  }

  public List<Card> getCards() {
    return cards;
  }

  public void setScore(int score) {
    this.score = score;
  }
}
