//Copyright (C) 2021, Grzegorz Stefański
package edu.ib.project.crazyeights.backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** The class containing the game engine. It connects {@link Deck} and {@link Player} classes. */
public class Game {

  private final Deck deck;
  private final List<Player> players = new ArrayList<>();

  /**
   * Game constructor. Prepares the deck. Creates players. Deals the cards.
   *
   * @param gameMode The type of game determined by the number of players involved.
   * @throws IOException When it is not possible to read the file with cards in the deck.
   */
  public Game(String gameMode) throws IOException {
    String filePath = "cardsData.txt";
    List<Card> cards = loadCardsData(filePath);

    String[] playersName = {"You", "Bot Mruczek", "Bot Puszek", "Bot Kitku"};
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

  /**
   * The method of dealing cards to players.
   *
   * @param cards Collection of read (all) cards.
   * @param mode The number of players taking part in the game determines the number of starting
   *     cards.
   * @return Collections of cards to one of the players.
   */
  private List<Card> dealCards(List<Card> cards, int mode) {
    List<Card> dealCards = new ArrayList<>();

    Collections.shuffle(cards);

    int numberOfCardsToDeal;
    if (mode == 2) numberOfCardsToDeal = 7;
    else numberOfCardsToDeal = 5;

    for (int i = 0; i < numberOfCardsToDeal; i++) {
      Card card = cards.remove(i);
      dealCards.add(card);
    }

    return dealCards;
  }

  /**
   * The method that downloads data from a file about the cards in the deck.
   *
   * @param filePath Path to the file with cards to be read.
   * @return List of downloaded cards.
   * @throws IOException When it is not possible to read the file with cards in the deck.
   */
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

  /**
   * The method that makes the list of bots in the game.
   *
   * @return List of bots in the game.
   */
  public List<Player> getBotsList() {
    List<Player> bots = new ArrayList<>();

    for (int i = 1; i < players.size(); i++) bots.add(players.get(i));

    return bots;
  }

  /**
   * The method that return the player in the game.
   *
   * @return The player in the game.
   */
  public Player getPlayer() {
    return players.get(0);
  }

  public Deck getDeck() {
    return deck;
  }
}
