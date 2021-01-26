//Copyright (C) 2021, Grzegorz Stefański
package edu.ib.project.crazyeights.backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {

  private Deck deck;
  private List<Player> players = new ArrayList<>();

  public Game(String gameMode) throws IOException {
    String filePath = "cardsData.txt";
    List<Card> cards = loadCardsData(filePath);

    String[] playersName = {"You", "Bot Przemek", "Bot Tomek", "Bot Mirek"};
    int mode =
        switch (gameMode) {
          case "2 players" -> 2;
          case "3 players" -> 3;
          case "4 players" -> 4;
          default -> 0;
        };

    for (int i = 0; i < mode; i++) {
      List<Card> playerCards = dealCards(cards, mode);
      players.add(new Player(playersName[i], playerCards));
    }

    List<Card> discardPileCards = new ArrayList<>();
    List<Card> deckCards;

    discardPileCards.add(cards.get(0));
    deckCards = cards;

    this.deck = new Deck(deckCards, discardPileCards);
  }

  private List<Card> dealCards(List<Card> cards, int mode) {
    List<Card> dealCards = new ArrayList<>();

    Collections.shuffle(cards);

    int numberOfCardsToDeal =
        switch (mode) {
          case 2 -> 7;
          case 3, 4 -> 5;
          default -> 0;
        };

    for (int i = 0; i < numberOfCardsToDeal; i++) {
      Card card = cards.remove(i);
      dealCards.add(card);
    }

    return dealCards;
  }

  public static List<Card> loadCardsData(String filePath) throws IOException {
    List<Card> cards = new ArrayList<>();

    File f = new File(filePath);
    FileReader fr = new FileReader(f);
    BufferedReader bf = new BufferedReader(fr);

    String line;
    while ((line = bf.readLine()) != null) {
      String[] lineSplit = line.split(",");

      cards.add(new Card(Byte.parseByte(lineSplit[0]), Byte.parseByte(lineSplit[1])));
    }

    bf.close();
    fr.close();

    return cards;
  }

  public Deck getDeck() {
    return deck;
  }

  public Player getPlayer() {
    return players.get(0);
  }

  public List<Player> getBotsList() {
    List<Player> bots = new ArrayList<>();

    for (int i = 1; i < players.size(); i++) bots.add(players.get(i));

    return bots;
  }
}
