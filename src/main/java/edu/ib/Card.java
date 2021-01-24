//Copyright (C) 2021, Grzegorz Stefański
package edu.ib;

public class Card {

  private final char color;
  private final String value;

  public Card(char color, String value) {
    this.color = color;
    this.value = value;
  }

  public boolean compare(Card card) {
    return color == card.getColor()
        || value.equals(card.getValue())
        || value.equals("8"); // TODO: eights
  }

  public char getColor() {
    return color;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "Card{" + "color=" + color + ", value=" + value + '}';
  }
}
