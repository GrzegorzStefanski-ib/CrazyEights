//Copyright (C) 2021, Grzegorz Stefański
package edu.ib;

import java.util.List;

public class Player {

  private String name;
  private List<Card> cards;
  private int score;

  /**
   * @param name
   * @param cards
   * @param score
   */
  public Player(String name, List<Card> cards, int score) {
    this.name = name;
    this.cards = cards;
    this.score = score;
  }

  /**
   * @param discardPile
   * @param index
   * @return
   */
  public Card playCard(DiscardPile discardPile, int index) {
    // TODO:
    return null;
  }

  /** @param deck */
  public void drawCard(Deck deck) {
    // TODO:
  }

  /** @return */
  public int getScore() {
    return score;
  }

  /** @param score */
  public void setScore(int score) {
    this.score = score;
  }
}
