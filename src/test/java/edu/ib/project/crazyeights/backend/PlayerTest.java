//Copyright (C) 2021, Grzegorz Stefański
package edu.ib.project.crazyeights.backend;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PlayerTest {

  public PlayerTest() {
    System.out.println("\n" + " ".repeat(19) + "--- Player class tests ---");
  }

  @Test
  public void playCard() throws IOException {

    System.out.printf("%-50s", "\t* playCard method... ");

    Game game = new Game("2 players");
    Player player = game.getPlayer();
    Deck deck = game.getDeck();
    Card lastCardOnDiscardPile = deck.getLastCardFromDiscardPile();
    List<Card> cards = player.getPlayerCards();

    for (int i = 0; i < cards.size(); i++) {

      if (cards.get(i).compare(lastCardOnDiscardPile)) {
        assertTrue(player.playCard(deck, i));
        lastCardOnDiscardPile = deck.getLastCardFromDiscardPile();
      } else {
        assertFalse(player.playCard(deck, i));
      }
    }

    System.out.println("OK");
  }

  @Test
  public void playCrazyEight() throws IOException {

    System.out.printf("%-50s", "\t* playCrazyEight method... ");

    Game game = new Game("2 players");
    Player player = game.getPlayer();
    Deck deck = game.getDeck();

    player.playCrazyEight(deck, 0, (byte) 0);

    System.out.println("OK");
  }

  @Test
  public void drawCard() throws IOException {

    System.out.printf("%-50s", "\t* drawCard method... ");

    Game game = new Game("2 players");
    Player player = game.getPlayer();
    Deck deck = game.getDeck();

    player.drawCard(deck);

    System.out.println("OK");
  }

  @Test
  public void isPlayersCardEmpty() throws IOException {

    System.out.printf("%-50s", "\t* isPlayersCardEmpty method... ");

    Game game = new Game("2 players");
    Player player = game.getPlayer();

    assertFalse(player.isPlayersCardEmpty());

    player.getPlayerCards().clear();
    assertTrue(player.isPlayersCardEmpty());

    System.out.println("OK");
  }

  @Test
  public void isCheater() throws IOException {

    System.out.printf("%-50s", "\t* isCheater method... ");

    Game game = new Game("2 players");
    Player player = game.getPlayer();
    Deck deck = game.getDeck();

    assertFalse(player.isCheater());

    for (int i = 0; i < 39; i++) {
      player.drawCard(deck);
    }

    assertTrue(player.isCheater());

    System.out.println("OK");
  }

  @Test
  public void getCard() throws IOException {

    System.out.printf("%-50s", "\t* getCard method... ");

    Game game = new Game("2 players");
    Player player = game.getPlayer();
    Card card = player.getCard(0);

    assertNotNull(card);
    assertEquals(card.getClass(), Card.class);

    System.out.println("OK");
  }

  @Test
  public void getPlayerCards() throws IOException {

    System.out.printf("%-50s", "\t* getPlayerCards method... ");

    Game game = new Game("2 players");
    Player player = game.getPlayer();
    List<Card> cards = player.getPlayerCards();

    assertNotNull(cards);
    assertEquals(cards.size(), 7);

    System.out.println("OK");
  }
}
