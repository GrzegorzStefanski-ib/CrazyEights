//Copyright (C) 2021, Grzegorz Stefański
package edu.ib.project.crazyeights.backend;

import java.util.*;

/** The class responsible for the algorithm controlling the bots. */
public class BotsAlgorithm {

  private final Game game;

  public BotsAlgorithm(Game game) {
    this.game = game;
  }

  /**
   * The method that makes a bot move depending on the cards it has.
   *
   * @param bot Details of the bot.
   * @return The message about the made move.
   */
  public String makeBotMove(Player bot) {
    List<Card> botCards = bot.getPlayerCards();
    List<Integer> indexesOfPlayableStandardCards = getIndexesOfPlayableStandardCards(bot);
    List<Integer> indexesOfCrazyEights = getIndexesOfCrazyEights(bot);
    ArrayList<Suit> suits = getSuits(bot);
    Deck deck = game.getDeck();

    List<Integer> botCardsToPlayIndexes = new ArrayList<>();

    if (indexesOfPlayableStandardCards.size() == 0) {
      if (!indexesOfCrazyEights.isEmpty()
          && botCards.size() - indexesOfCrazyEights.size() <= indexesOfCrazyEights.size()) {
        Random random = new Random();
        int cardToPlayIndex = random.nextInt(indexesOfCrazyEights.size());
        byte suitToPlay = suits.get(suits.size() - 1).getSuit();

        bot.playCrazyEight(deck, cardToPlayIndex, suitToPlay);
      } else bot.drawCard(deck);
    } else {
      int n = 1;
      while (botCardsToPlayIndexes.isEmpty()) {
        for (int i = 0; i < botCards.size(); i++) {
          if (botCards.get(i).getSuit() == suits.get(suits.size() - n).getSuit()) {
            for (Integer indexesOfPlayableCard : indexesOfPlayableStandardCards) {
              if (i == indexesOfPlayableCard) {
                botCardsToPlayIndexes.add(i);
              }
            }
          }
        }

        n++;
      }

      int cardToPlayIndex;
      Random random = new Random();
      int i = random.nextInt(botCardsToPlayIndexes.size());
      cardToPlayIndex = botCardsToPlayIndexes.get(i);

      bot.playCard(deck, cardToPlayIndex);
    }

    return bot.getLog();
  }

  /**
   * The method returns all the suits in the bot's hand.
   *
   * @param bot Details of the bot.
   * @return The set of Suit objects that match the suits of the cards in the hand of bot.
   */
  private ArrayList<Suit> getSuits(Player bot) {
    ArrayList<Suit> suits = new ArrayList<>();
    List<Card> botCards = bot.getPlayerCards();
    byte[] numbersOfSuits = new byte[4];

    for (Card botCard : botCards) {
      if (!Card.compareCrazyEight(botCard)) numbersOfSuits[botCard.getSuit()]++;
    }

    for (int i = 0; i < numbersOfSuits.length; i++)
      suits.add(new Suit((byte) i, numbersOfSuits[i]));

    Collections.sort(suits);

    return suits;
  }

  /**
   * The method that checks what cards the bot can play and saves their indexes.
   *
   * @param bot Details of the bot.
   * @return The list of indexes of standard cards that the bot can play.
   */
  private List<Integer> getIndexesOfPlayableStandardCards(Player bot) {
    List<Card> botCards = bot.getPlayerCards();
    List<Integer> indexesOfPlayableCards = new ArrayList<>();
    Card lastCardOnDiscardPile = game.getDeck().getLastCardFromDiscardPile();

    for (int i = 0; i < botCards.size(); i++) {
      Card botCardToPlay = botCards.get(i);

      if (botCardToPlay.compare(lastCardOnDiscardPile) && botCardToPlay.getValue() != 6) {
        indexesOfPlayableCards.add(i);
      }
    }

    return indexesOfPlayableCards;
  }

  /**
   * The method that saves Crazy Eights indexes from bot's cards.
   *
   * @param bot Details of the bot.
   * @return The list of indexes of Crazy Eights that the bot can play.
   */
  private List<Integer> getIndexesOfCrazyEights(Player bot) {
    List<Card> botCards = bot.getPlayerCards();
    List<Integer> indexesOfCrazyEights = new ArrayList<>();

    for (int i = 0; i < botCards.size(); i++) {
      Card botCardToPlay = botCards.get(i);

      if (Card.compareCrazyEight(botCardToPlay)) {
        indexesOfCrazyEights.add(i);
      }
    }

    return indexesOfCrazyEights;
  }
}
