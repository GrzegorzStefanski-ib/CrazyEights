//Copyright (C) 2021, Grzegorz Stefański
package edu.ib.project.crazyeights.backend;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class BotsAlgorithmTest {

  public BotsAlgorithmTest() {
    System.out.println("\n" + " ".repeat(15) + "--- BotsAlgorithm class tests ---");
  }

  @Test
  public void makeBotMove() throws Exception {

    System.out.printf("%-50s", "\t* makeBotMove method... ");

    Game game = new Game("2 players");
    Player player1 = game.getPlayer();
    Player player2 = game.getBotsList().get(0);

    BotsAlgorithm botsAlgorithm = new BotsAlgorithm(game);
    botsAlgorithm.makeBotMove(player1);
    botsAlgorithm.makeBotMove(player2);

    List<Card> cards = new ArrayList<>();
    cards.add(new Card((byte) 0, (byte) 6));
    Player player3 = new Player("test_8", cards);
    botsAlgorithm.makeBotMove(player3);
    botsAlgorithm.makeBotMove(player3);

    System.out.println("OK");
  }
}
