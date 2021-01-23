//Copyright (C) 2021, Grzegorz Stefański
package edu.ib;

import java.util.List;

public class Game {

  private byte mode;
  private Deck deck;
  private DiscardPile discardPile;
  private List<Player> players;

  /**
   * @param mode
   * @param deck
   * @param discardPile
   * @param players
   */
  public Game(byte mode, Deck deck, DiscardPile discardPile, List<Player> players) {
    this.mode = mode;
    this.deck = deck;
    this.discardPile = discardPile;
    this.players = players;
  }

  /** */
  public void start() {
    // TODO:
  }

  /** */
  public void stop() {
    // TODO:
  }
}
