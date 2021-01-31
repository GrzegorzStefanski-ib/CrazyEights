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
    List<Integer> indexesOfPlayableCards = getIndexesOfPlayableCards(bot);
    byte mostSuit = getMostSuit(bot);
    Deck deck = game.getDeck();

    List<Integer> botCardsToPlayIndexes = new ArrayList<>();

    if (indexesOfPlayableCards.size() == 0) {
      bot.drawCard(deck);
    } else {

      for (int i = 0; i < botCards.size(); i++) {
        if (botCards.get(i).getSuit() == mostSuit) {
          for (Integer indexesOfPlayableCard : indexesOfPlayableCards) {
            if (i == indexesOfPlayableCard) {
              botCardsToPlayIndexes.add(i);
            }
          }
        }
      }

      int cardToPlayIndex;

      if (botCardsToPlayIndexes.isEmpty()) cardToPlayIndex = indexesOfPlayableCards.get(0);
      else {
        Random random = new Random();
        int i = random.nextInt(botCardsToPlayIndexes.size());
        cardToPlayIndex = botCardsToPlayIndexes.get(i);
      }

      Card cardToPlay = bot.getCard(cardToPlayIndex);

      if (Card.compareCrazyEight(cardToPlay)) {
        byte[] suits = getSuits(bot);
        List<Byte> suitsIndexes = new ArrayList<>();

        for (byte i = 0; i < suits.length; i++) {
          if (suits[i] > 0) suitsIndexes.add(i);
        }

        Random random = new Random();
        int suitToPlayIndex = random.nextInt(suitsIndexes.size());
        byte suitToPlay = suitsIndexes.get(suitToPlayIndex);

        bot.playCrazyEight(deck, cardToPlayIndex, suitToPlay);
      } else {
        bot.playCard(deck, cardToPlayIndex);
      }
    }

    return bot.getLog();
  }

  /**
   * The method returns the color whose side has the most.
   *
   * @param bot Details of the bot.
   * @return The suit the bot has the most.
   */
  private byte getMostSuit(Player bot) {
    byte[] numbersOfSuits = getSuits(bot);

    byte suit = 0;
    byte max = 0;

    for (int i = 0; i < numbersOfSuits.length; i++)
      if (numbersOfSuits[i] > max) {
        suit = (byte) i;
        max = numbersOfSuits[i];
      }

    return suit;
  }

  /**
   * The method returns all the suits in the bot's hand.
   *
   * @param bot Details of the bot.
   * @return The suits table of the cards in the bot's hand.
   */
  private byte[] getSuits(Player bot) {
    List<Card> botCards = bot.getPlayerCards();
    byte[] numbersOfSuits = new byte[4];

    for (Card botCard : botCards) numbersOfSuits[botCard.getSuit()]++;

    return numbersOfSuits;
  }

  /**
   * The method that checks what cards the bot can play and saves their indexes.
   *
   * @param bot Details of the bot.
   * @return The list of indexes of cards that the bot can play.
   */
  private List<Integer> getIndexesOfPlayableCards(Player bot) {
    List<Card> botCards = bot.getPlayerCards();
    List<Integer> indexesOfPlayableCards = new ArrayList<>();
    Card lastCardOnDiscardPile = game.getDeck().getLastCardFromDiscardPile();

    for (int i = 0; i < botCards.size(); i++) {
      Card botCardToPlay = botCards.get(i);

      if (botCardToPlay.compare(lastCardOnDiscardPile) || Card.compareCrazyEight(botCardToPlay)) {
        indexesOfPlayableCards.add(i);
      }
    }

    return indexesOfPlayableCards;
  }
}
