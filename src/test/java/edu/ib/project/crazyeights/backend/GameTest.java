//Copyright (C) 2021, Grzegorz Stefański
package edu.ib.project.crazyeights.backend;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

public class GameTest {

  public GameTest() {
    System.out.println("\n" + " ".repeat(20) + "--- Game class tests ---");
  }

  @Test
  public void loadCardsData() throws IOException {

    System.out.printf("%-50s", "\t* loadCardsData method... ");

    String filePath = "cardsData.txt";
    List<Card> cards = Game.loadCardsData(filePath);

    assertEquals(cards.size(), 52);

    System.out.println("OK");
  }

  @Test
  public void getDeck() throws IOException {

    System.out.printf("%-50s", "\t* getDeck method... ");

    Game game = new Game("2 players");
    Deck deck = game.getDeck();

    assertNotNull(deck);
    assertEquals(deck.getClass(), Deck.class);

    System.out.println("OK");
  }

  @Test
  public void getPlayer() throws IOException {

    System.out.printf("%-50s", "\t* getPlayer method... ");

    Game game = new Game("2 players");
    Player player = game.getPlayer();

    assertNotNull(player);
    assertEquals(player.getClass(), Player.class);

    System.out.println("OK");
  }

  @Test
  public void getBotsList() throws IOException {

    System.out.println("\t* getBotsList method... modes: ");

    String[] modes = {"Wrong mode", "2 players", "3 players", "4 players"};
    int[] players_counts = {0, 1, 2, 3};

    for (int i = 0; i < modes.length; i++) {

      System.out.printf("%-47s", "\t\t- " + modes[i] + ":");

      Game game = new Game(modes[i]);
      List<Player> players = game.getBotsList();

      assertNotNull(players);
      assertEquals(players.size(), players_counts[i]);

      System.out.println("OK");
    }
  }
}
