//Copyright (C) 2021, Grzegorz Stefański
package edu.ib.project.crazyeights.backend;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;

public class DeckTest {

  public DeckTest() {
    System.out.println("\n" + " ".repeat(20) + "--- Deck class tests ---");
  }

  @Test
  public void getCardFromDeck() throws Exception {

    System.out.printf("%-50s", "\t* getCardFromDeck method... ");

    Game game = new Game("2 players");
    Deck deck = game.getDeck();
    Player player = game.getPlayer();

    Card card = deck.getCardFromDeck();

    assertNotNull(card);
    assertEquals(card.getClass(), Card.class);

    for (int i = 0; i < 38; i++) {
      player.drawCard(deck);
    }
    player.playCrazyEight(deck, 0, (byte) 0);

    card = deck.getCardFromDeck();

    assertNotNull(card);
    assertEquals(card.getClass(), Card.class);

    System.out.println("OK");
  }

  @Test
  public void getLastCardFromDiscardPile() throws IOException {

    System.out.printf("%-50s", "\t* getLastCardFromDiscardPile method... ");

    Game game = new Game("2 players");
    Deck deck = game.getDeck();
    Card card = deck.getLastCardFromDiscardPile();

    assertNotNull(card);
    assertEquals(card.getClass(), Card.class);

    System.out.println("OK");
  }

  @Test
  public void getLastCardFromDiscardPileForGUI() throws IOException {

    System.out.printf("%-50s", "\t* getLastCardFromDiscardPileForGUI method... ");

    Game game = new Game("2 players");
    Deck deck = game.getDeck();

    Card card = deck.getLastCardFromDiscardPileForGUI();

    assertNotNull(card);
    assertEquals(card.getClass(), Card.class);

    System.out.println("OK");
  }

  @Test
  public void addCardToDiscardPile() throws IOException {

    System.out.printf("%-50s", "\t* addCardToDiscardPile method... ");

    Game game = new Game("2 players");
    Deck deck = game.getDeck();
    Card card = new Card((byte) 0, (byte) 0);
    deck.addCardToDiscardPile(card);

    deck.addCardToDiscardPile(card, (byte) 0);
    deck.addCardToDiscardPile(card);

    System.out.println("OK");
  }

  @Test
  public void addCardToDiscardPile_newColor() throws IOException {

    System.out.printf("%-50s", "\t* addCardToDiscardPile2 method... ");

    Game game = new Game("2 players");
    Deck deck = game.getDeck();
    Card card = new Card((byte) 0, (byte) 0);
    deck.addCardToDiscardPile(card, (byte) 1);

    System.out.println("OK");
  }

  @Test
  public void getActualColor() throws IOException {

    System.out.printf("%-50s", "\t* getActualColor method... ");

    Game game = new Game("2 players");
    Deck deck = game.getDeck();

    Byte color = deck.getActualColor();

    assertNull(color);

    System.out.println("OK");
  }
}
