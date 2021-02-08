//Copyright (C) 2021, Grzegorz Stefański
package edu.ib.project.crazyeights.backend;

/**
 * Class represent card suit.
 */
public class Suit implements Comparable<Suit> {

  private final byte suit;
  private final byte amount;

  public Suit(byte suit, byte amount) {
    this.suit = suit;
    this.amount = amount;
  }

  public byte getSuit() {
    return suit;
  }

  public byte getAmount() {
    return amount;
  }

  @Override
  public int compareTo(Suit o) {
    return amount - o.getAmount();
  }

  @Override
  public String toString() {
    return "{" + Card.SUIT_ENCODING[suit] + ", " + amount + "}";
  }
}
