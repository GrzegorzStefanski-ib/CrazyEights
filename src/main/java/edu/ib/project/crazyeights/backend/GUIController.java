//Copyright (C) 2021, Grzegorz Stefański
package edu.ib.project.crazyeights.backend;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

  @FXML private ImageView bot1ImageView;

  @FXML private ImageView bot3ImageView;

  @FXML private ImageView bot2ImageView;

  @FXML private Button card1Button;

  @FXML private Button card2Button;

  @FXML private Button card3Button;

  @FXML private Button card4Button;

  @FXML private Button card5Button;

  @FXML private Button card6Button;

  @FXML private Button card7Button;

  @FXML private Button card8Button;

  @FXML private Button card9Button;

  @FXML private Button card10Button;

  private Game game;
  private List<Button> playerButtons;
  private Button[] buttons;

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
    drawCardButton.setDisable(false);
    gameScreen.setVisible(true);

    displayAllCardsInGame();
  }

  @FXML
  void playCardButtonOnClick(ActionEvent event) throws Exception {
    int cardToPlayIndex = -1;
    if (event.getSource().equals(card1Button)) cardToPlayIndex = 0;
    if (event.getSource().equals(card2Button)) cardToPlayIndex = 1;
    if (event.getSource().equals(card3Button)) cardToPlayIndex = 2;
    if (event.getSource().equals(card4Button)) cardToPlayIndex = 3;
    if (event.getSource().equals(card5Button)) cardToPlayIndex = 4;
    if (event.getSource().equals(card6Button)) cardToPlayIndex = 5;
    if (event.getSource().equals(card7Button)) cardToPlayIndex = 6;
    if (event.getSource().equals(card8Button)) cardToPlayIndex = 7;
    if (event.getSource().equals(card9Button)) cardToPlayIndex = 8;
    if (event.getSource().equals(card10Button)) cardToPlayIndex = 9;

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
    assert bot1ImageView != null
        : "fx:id=\"bot1ImageView\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert bot3ImageView != null
        : "fx:id=\"bot3ImageView\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert bot2ImageView != null
        : "fx:id=\"bot2ImageView\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert card1Button != null
        : "fx:id=\"card1Button\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert card2Button != null
        : "fx:id=\"card2Button\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert card3Button != null
        : "fx:id=\"card3Button\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert card4Button != null
        : "fx:id=\"card4Button\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert card5Button != null
        : "fx:id=\"card5Button\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert card6Button != null
        : "fx:id=\"card6Button\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert card7Button != null
        : "fx:id=\"card7Button\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert card8Button != null
        : "fx:id=\"card8Button\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert card9Button != null
        : "fx:id=\"card9Button\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert card10Button != null
        : "fx:id=\"card10Button\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";

    gameModeSelector.getItems().add("2 players");
    gameModeSelector.getItems().add("3 players");
    gameModeSelector.getItems().add("4 players");
    gameModeSelector.setValue("2 players");

    newColorSelector.getItems().add("Hearts");
    newColorSelector.getItems().add("Diamonds");
    newColorSelector.getItems().add("Spades");
    newColorSelector.getItems().add("Clubs");
    newColorSelector.setValue("Hearts");

    generateCats();

    buttons =
        new Button[] {
          card1Button,
          card2Button,
          card3Button,
          card4Button,
          card5Button,
          card6Button,
          card7Button,
          card8Button,
          card9Button,
          card10Button
        };
    playerButtons = Arrays.asList(buttons);
  }

  private void gameEnd(Label label) {
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

    for (Button playerButton : playerButtons) playerButton.setVisible(false);
    playerCardList.getItems().clear();
    for (Card playerCard : playerCards) playerCardList.getItems().add(playerCard.toString());
    for (int i = 0; i < playerCards.size(); i++) {
      playerButtons.get(i).setVisible(true);
      playerButtons.get(i).setText(playerCards.get(i).toString());
    }
  }

  private void generateCats() {

    Image[] catPhotos = {
      new Image("/cats/cat1.jpg"),
      new Image("/cats/cat2.jpg"),
      new Image("/cats/cat3.jpg"),
      new Image("/cats/cat4.jpg"),
      new Image("/cats/cat5.jpg")
    };
    Random random = new Random();
    int r = random.nextInt(catPhotos.length);
    bot1ImageView.setImage(catPhotos[r]);
    int rOld1 = r;
    while (r == rOld1) {
      r = random.nextInt(catPhotos.length);
    }
    bot2ImageView.setImage(catPhotos[r]);
    int rOld2 = r;
    while (r == rOld1 || r == rOld2) {
      r = random.nextInt(catPhotos.length);
    }
    bot3ImageView.setImage(catPhotos[r]);
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
