//Copyright (C) 2021, Grzegorz Stefański
package edu.ib.project.crazyeights.backend;

/** The class representing a playing card. */
public class Card {

  /** Card suits are written as the {@code byte} variable from 0 to 4. */
  private final byte suit;
  /** The card values are written as the {@code byte} variable from 0 to 12. */
  private final byte value;

  /** Card suit coding table from {@code byte} to {@code string}. */
  public static final String[] SUIT_ENCODING = {"H", "S", "D", "C"};

  /** Card value coding table from {@code byte} to {@code string}. */
  private final String[] valueEncoding = {
    "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"
  };

  public Card(byte suit, byte value) {
    this.suit = suit;
    this.value = value;
  }

  /**
   * The method of comparing if the cards are equal according to the rules of the game. Penalties
   * must be of the same suit or value.
   *
   * @param card Card to compare.
   * @return 1 when the cards are equal and 0 when not.
   */
  public boolean compare(Card card) {
    return suit == card.getSuit() || value == card.getValue();
  }

  /**
   * The method of comparing whether the given card is the Crazy Eight.
   *
   * @param card Card to compare.
   * @return 1 when the card is Crazy Eight and 0 when not.
   */
  public static boolean compareCrazyEight(Card card) {
    return card.getValue() == 6;
  }

  public byte getSuit() {
    return suit;
  }

  public byte getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "Card{" + "color=" + SUIT_ENCODING[suit] + ", value=" + valueEncoding[value] + '}';
  }
}
