//Copyright (C) 2021, Grzegorz Stefański
package edu.ib.project.crazyeights.backend;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CardTest {

  public CardTest() {
    System.out.println("\n" + " ".repeat(20) + "--- Card class tests ---");
  }

  @Test
  public void compare() {

    System.out.printf("%-50s", "\t* compare method... ");

    Card card1 = new Card((byte) 0, (byte) 0);
    Card card2 = new Card((byte) 0, (byte) 1);
    Card card3 = new Card((byte) 1, (byte) 0);
    Card card4 = new Card((byte) 1, (byte) 1);

    assertTrue(card1.compare(card2));
    assertTrue(card1.compare(card3));
    assertFalse(card1.compare(card4));

    System.out.println("OK");
  }

  @Test
  public void compareCrazyEight() {

    System.out.printf("%-50s", "\t* compareCrazyEight method... ");

    Card card1 = new Card((byte) 0, (byte) 0);
    Card card2 = new Card((byte) 1, (byte) 6);

    assertFalse(Card.compareCrazyEight(card1));
    assertTrue(Card.compareCrazyEight(card2));

    System.out.println("OK");
  }

  @Test
  public void getColor() {

    System.out.printf("%-50s", "\t* getColor method... ");

    Card card = new Card((byte) 0, (byte) 0);
    assertEquals(card.getSuit(), (byte) 0);

    System.out.println("OK");
  }

  @Test
  public void getValue() {

    System.out.printf("%-50s", "\t* getValue method... ");

    Card card = new Card((byte) 0, (byte) 0);
    assertEquals(card.getValue(), (byte) 0);

    System.out.println("OK");
  }

  @Test
  public void testToString() {

    System.out.printf("%-50s", "\t* testToString method... ");

    Card card = new Card((byte) 0, (byte) 0);
    assertEquals("Card{suit=H, value=2}", card.toString());

    System.out.println("OK");
  }
}
