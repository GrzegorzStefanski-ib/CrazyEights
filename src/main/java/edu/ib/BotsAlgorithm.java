package edu.ib;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BotsAlgorithm {

    private Game game;

    public BotsAlgorithm(Game game) {
        this.game = game;
    }

    public void start() {
        List<Player> bots = getBots();

        for (Player bot : bots)
            makeMove(bot);
    }

    private void makeMove(Player bot) {
        List<Integer> indexesOfPlayableCards = getIndexesOfPlayableCards(bot);

        while (indexesOfPlayableCards.size() == 0) {
            bot.drawCard(game.getDeck());
            indexesOfPlayableCards = getIndexesOfPlayableCards(bot);
        }

        Random random = new Random();
        int index = random.nextInt(indexesOfPlayableCards.size());

        bot.playCard(game.getDiscardPile(), index);
    }

    private List<Integer> getIndexesOfPlayableCards(Player bot) {
        List<Card> botCards = bot.getCards();
        List<Integer> indexesOfPlayableCards = new ArrayList<>();

        for (int i = 0; i < botCards.size(); i++) {
            if (botCards.get(i).compare(game.getDiscardPile().getLastCard()))
                indexesOfPlayableCards.add(i);
        }

        return indexesOfPlayableCards;
    }

    private List<Player> getBots() {

        List<Player> players = game.getPlayers();
        List<Player> bots = new ArrayList<>();

        for (int i = 1; i < players.size(); i++)
            bots.add(players.get(i));

        return bots;
    }
}
