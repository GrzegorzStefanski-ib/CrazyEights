//Copyright (C) 2021, Grzegorz Stefański
package edu.ib.project.crazyeights.backend;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

public class GUIController {

  @FXML private ResourceBundle resources;

  @FXML private URL location;

  @FXML private Pane menuScreen;

  @FXML private ChoiceBox<String> gameModeSelector;

  @FXML private Button startGameButton;

  @FXML private Pane gameScreen;

  @FXML private ListView<String> discardPileCardList;

  @FXML private ListView<String> playerCardList;

  @FXML private Button playCardButton;

  @FXML private Button drawCardButton;

  @FXML private ChoiceBox<String> newColorSelector;

  @FXML private Button confirmNewColorChoiceButton;

  @FXML private ListView<String> bot1CardList;

  @FXML private ListView<String> bot2CardList;

  @FXML private ListView<String> bot3CardList;

  @FXML private Label winPrompt;

  @FXML private Label defeatPrompt;

  @FXML private Label cheaterPrompt;

  @FXML private Label actualColorLabel;

  private Game game;

  // TODO: Support for crazy eights.

  @FXML
  void startGameButtonOnClick(ActionEvent event) {
    String gameMode = gameModeSelector.getValue();

    try {
      game = new Game(gameMode);
    } catch (IOException e) {
      e.printStackTrace();
    }

    menuScreen.setVisible(false);
    gameScreen.setVisible(true);

    displayAllCardsInGame();
  }

  @FXML
  void playCardButtonOnClick(ActionEvent event) throws Exception {
    int cardToPlayIndex = playerCardList.getSelectionModel().getSelectedIndex();

    Deck deck = game.getDeck();
    Player player = game.getPlayer();

    try {
      player.playCard(deck, cardToPlayIndex);
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }

    showDiscardPileLastCard();
    showPlayerCards();

    if (player.isPlayersCardEmpty()) gameEnd(winPrompt);
    else {
      List<Player> bots = game.getBotsList();
      BotsAlgorithm botsAlgorithm = new BotsAlgorithm(game);

      for (Player bot : bots) {
        botsAlgorithm.makeBotMove(bot);

        showDiscardPileLastCard();
        showBotsCards();

        if (bot.isPlayersCardEmpty()) gameEnd(defeatPrompt);
      }
    }
  }

  @FXML
  void confirmNewColorChoiceButtonOnClick(ActionEvent event) {}

  @FXML
  void drawCardButtonOnClick(ActionEvent event) {
    Deck deck = game.getDeck();
    Player player = game.getPlayer();

    player.drawCard(deck);

    if (player.isCheater()) {
      gameEnd(cheaterPrompt);
    }

    showPlayerCards();
  }

  @FXML
  void initialize() {
    assert menuScreen != null
        : "fx:id=\"menuScreen\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert gameModeSelector != null
        : "fx:id=\"gameModeSelector\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert startGameButton != null
        : "fx:id=\"startGameButton\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert gameScreen != null
        : "fx:id=\"gameScreen\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert discardPileCardList != null
        : "fx:id=\"discardPileCardList\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert playerCardList != null
        : "fx:id=\"playerCardList\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert playCardButton != null
        : "fx:id=\"playCardButton\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert drawCardButton != null
        : "fx:id=\"drawCardButton\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert newColorSelector != null
        : "fx:id=\"newColorSelector\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert confirmNewColorChoiceButton != null
        : "fx:id=\"confirmNewColorChoiceButton\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert bot1CardList != null
        : "fx:id=\"bot1CardList\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert bot2CardList != null
        : "fx:id=\"bot2CardList\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert bot3CardList != null
        : "fx:id=\"bot3CardList\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert winPrompt != null
        : "fx:id=\"winPrompt\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert defeatPrompt != null
        : "fx:id=\"defeatPrompt\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert cheaterPrompt != null
        : "fx:id=\"cheaterPrompt\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert actualColorLabel != null
        : "fx:id=\"actualColorLabel\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";

    gameModeSelector.getItems().add("2 players");
    gameModeSelector.getItems().add("3 players");
    gameModeSelector.getItems().add("4 players");
    gameModeSelector.setValue("2 players");

    newColorSelector.getItems().add("Hearts");
    newColorSelector.getItems().add("Diamonds");
    newColorSelector.getItems().add("Spades");
    newColorSelector.getItems().add("Clubs");
    newColorSelector.setValue("Hearts");
  }

  private void gameEnd(Label label) {
    playCardButton.setDisable(true);
    drawCardButton.setDisable(true);

    Timer timer = new Timer();
    TimerTask timerTask =
        new TimerTask() {
          @Override
          public void run() {
            label.setVisible(false);
            gameScreen.setVisible(false);
            menuScreen.setVisible(true);
            timer.cancel();
          }
        };

    label.setVisible(true);
    timer.schedule(timerTask, 5000);
  }

  private void displayAllCardsInGame() {
    showDiscardPileLastCard();
    showPlayerCards();
    showBotsCards();
  }

  private void showDiscardPileLastCard() {
    Deck deck = game.getDeck();
    Card discardPileLastCard = deck.getLastCardFromDiscardPile();

    discardPileCardList.getItems().clear();
    discardPileCardList.getItems().add(discardPileLastCard.toString());
  }

  private void showPlayerCards() {
    Player player = game.getPlayer();
    List<Card> playerCards = player.getPlayerCards();

    playerCardList.getItems().clear();
    for (Card playerCard : playerCards) playerCardList.getItems().add(playerCard.toString());
  }

  // For dev tests
  private void showBotsCards() {
    List<Player> bots = game.getBotsList();

    for (int i = 0; i < bots.size(); i++) {
      Player bot = bots.get(i);
      List<Card> botCards = bot.getPlayerCards();

      ListView<String> botCardList =
          switch (i) {
            case 0 -> bot1CardList;
            case 1 -> bot2CardList;
            case 2 -> bot3CardList;
            default -> null;
          };

      assert botCardList != null;
      botCardList.getItems().clear();
      for (Card botCard : botCards) {
        botCardList.getItems().add(botCard.toString());
      }
    }
  }
}
