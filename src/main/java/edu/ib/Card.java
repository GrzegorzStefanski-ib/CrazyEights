//Copyright (C) 2021, Grzegorz Stefański
package edu.ib;

public class Card {

  private char color;
  private byte value;

  /**
   * @param color
   * @param value
   */
  public Card(char color, byte value) {
    this.color = color;
    this.value = value;
  }

  /**
   * @param card
   * @return
   */
  public boolean compare(Card card) {
    return false;
  }
}
