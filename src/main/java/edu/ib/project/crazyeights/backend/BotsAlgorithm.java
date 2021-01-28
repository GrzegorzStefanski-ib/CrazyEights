//Copyright (C) 2021, Grzegorz Stefański
package edu.ib.project.crazyeights.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BotsAlgorithm {

  private Game game;

  public BotsAlgorithm(Game game) {
    this.game = game;
  }

  public String makeBotMove(Player bot) throws Exception {
    List<Integer> indexesOfPlayableCards = getIndexesOfPlayableCards(bot);
    Deck deck = game.getDeck();

    if (indexesOfPlayableCards.size() == 0) {
      bot.drawCard(deck);
    } else {
      int cardToPlayIndex = indexesOfPlayableCards.get(0);
      Card cardToPlay = bot.getCard(cardToPlayIndex);

      if (Card.compareCrazyEight(cardToPlay)) {
        Random random = new Random();

        bot.playCrazyEight(deck, cardToPlayIndex, (byte) random.nextInt(4));
      } else {
        bot.playCard(deck, cardToPlayIndex);
      }
    }

    return bot.getLog();
  }

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
