//Copyright (C) 2021, Grzegorz Stefański
package edu.ib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {

  private Deck deck;
  private DiscardPile discardPile;
  private List<Player> players;

  public Game(String n_players) throws IOException {

    String[] playersName = {"You", "Pszemek", "Pszemek_v2", "Pszemek_v3"};

    int n =
        switch (n_players) {
          case "2 players" -> 2;
          case "3 players" -> 3;
          case "4 players" -> 4;
          default -> 0;
        };

    String filePath = "cardsData.txt";
    List<Card> cards = loadCardsData(filePath);

    deck = new Deck(cards);
    players = new ArrayList<>();
    discardPile = new DiscardPile();

    for (int i = 0; i < n; i++) {
      List<Card> playerCards = deck.getCards(7);
      players.add(new Player(playersName[i], playerCards));
    }

    discardPile.addCard(deck.getCard());
  }

  public static List<Card> loadCardsData(String filePath) throws IOException {

    List<Card> cards = new ArrayList<>();

    File f = new File(filePath);
    FileReader fr = new FileReader(f);
    BufferedReader bf = new BufferedReader(fr);

    String line;

    while ((line = bf.readLine()) != null) {

      String[] lineSplit = line.split(",");
      System.out.println(lineSplit.length);
      cards.add(new Card(lineSplit[1].toCharArray()[0], lineSplit[0]));
    }

    bf.close();
    fr.close();

    return cards;
  }

  public Deck getDeck() {
    return deck;
  }

  public DiscardPile getDiscardPile() {
    return discardPile;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public Player getPlayer(int index) {
    return players.get(index);
  }
}
