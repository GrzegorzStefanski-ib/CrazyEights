//Copyright (C) 2021, Grzegorz Stefański
package edu.ib.project.crazyeights.backend;

public class Card {

  private final byte color;
  private final byte value;

  public Card(byte color, byte value) {
    this.color = color;
    this.value = value;
  }

  public boolean compare(Card card) {
    return color == card.getColor() || value == card.getValue();
  }

  public static boolean compareCrazyEight(Card card) {
    return card.getValue() == 8;
  }

  public byte getColor() {
    return color;
  }

  public byte getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "Card{" + "color=" + color + ", value=" + value + '}';
  }
}
