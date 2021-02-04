//Copyright (C) 2021, Grzegorz Stefański
package edu.ib.project.crazyeights.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SuitTest {

  public SuitTest() {
    System.out.println("\n" + " ".repeat(20) + "--- Suit class tests ---");
  }

  @Test
  public void getSuit() {

    System.out.printf("%-50s", "\t* getSuit method... ");

    Suit suit = new Suit((byte) 0, (byte) 2);
    byte s = suit.getSuit();

    assertEquals(s, (byte) 0);

    System.out.println("OK");
  }

  @Test
  public void getAmount() {

    System.out.printf("%-50s", "\t* getAmount method... ");

    Suit suit = new Suit((byte) 0, (byte) 2);
    byte s = suit.getAmount();

    assertEquals(s, (byte) 2);

    System.out.println("OK");
  }

  @Test
  public void compareTo() {

    System.out.printf("%-50s", "\t* compareTo method... ");

    Suit suit = new Suit((byte) 0, (byte) 2);

    Suit suit_test1 = new Suit((byte) 0, (byte) 0);
    Suit suit_test2 = new Suit((byte) 2, (byte) 2);
    Suit suit_test3 = new Suit((byte) 1, (byte) 6);

    assertEquals(suit.compareTo(suit_test1), 2);
    assertEquals(suit.compareTo(suit_test2), 0);
    assertEquals(suit.compareTo(suit_test3), -4);

    System.out.println("OK");
  }

  @Test
  public void testToString() {

    System.out.printf("%-50s", "\t* testToString method... ");

    Suit suit = new Suit((byte) 0, (byte) 0);
    assertEquals("{H, 0}", suit.toString());

    System.out.println("OK");
  }
}
