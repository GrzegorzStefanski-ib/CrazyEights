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

  public void makeBotMove(Player bot) throws Exception {
    List<Integer> indexesOfPlayableCards = getIndexesOfPlayableCards(bot);
    Deck deck = game.getDeck();

    if (indexesOfPlayableCards.size() == 0) bot.drawCard(deck);
    else {
      int cardToPlayIndex = indexesOfPlayableCards.get(0);
      Card cardToPlay = bot.getCard(cardToPlayIndex);

      if (Card.compareCrazyEight(cardToPlay)) {
        String[] colors = {"H","S","D","C"};
        Random random = new Random();
        int colorIndex = random.nextInt(colors.length);

        bot.playCrazyEight(deck, cardToPlayIndex, colors[colorIndex]);
      }
      else
        bot.playCard(deck, cardToPlayIndex);
    }

    //    while (indexesOfPlayableCards.size() == 0) {
    //      bot.drawCard(deck);
    //      indexesOfPlayableCards = getIndexesOfPlayableCards(bot);
    //    }
    //
    //    bot.playCard(deck, indexesOfPlayableCards.get(0));
  }

  private List<Integer> getIndexesOfPlayableCards(Player bot) {
    List<Card> botCards = bot.getPlayerCards();
    List<Integer> indexesOfPlayableCards = new ArrayList<>();
    Card lastCardOnDiscardPile = game.getDeck().getLastCardFromDiscardPile();

    for (int i = 0; i < botCards.size(); i++) {
      Card botCardToPlay = botCards.get(i);

      if (botCardToPlay.compare(lastCardOnDiscardPile)) indexesOfPlayableCards.add(i);
    }

    return indexesOfPlayableCards;
  }
}
