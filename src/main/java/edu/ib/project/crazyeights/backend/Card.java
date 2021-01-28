//Copyright (C) 2021, Grzegorz Stefański
package edu.ib.project.crazyeights.backend;

public class Card {

  private final byte color;
  private final byte value;

  public static final String[] COLOR_ENCODING = {"H", "S", "D", "C"};
  private final String[] valueEncoding = {
    "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"
  };

  public Card(byte color, byte value) {
    this.color = color;
    this.value = value;
  }

  public boolean compare(Card card) {
    return color == card.getColor() || value == card.getValue();
  }

  public static boolean compareCrazyEight(Card card) {
    return card.getValue() == 6; // 6 is secretly 8 but shhh...
  }

  public byte getColor() {
    return color;
  }

  public byte getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "Card{" + "color=" + COLOR_ENCODING[color] + ", value=" + valueEncoding[value] + '}';
  }
}
