//Copyright (C) 2021, Grzegorz Stefański
package edu.ib.project.crazyeights.backend;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class GUIController {

  @FXML private ResourceBundle resources;

  @FXML private URL location;

  @FXML private Pane menuScreen;

  @FXML private ChoiceBox<String> gameModeSelector;

  @FXML private Button startGameButton;

  @FXML private Pane gameScreen;

  @FXML private Button drawCardButton;

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

  @FXML private AnchorPane cardsPane;

  @FXML private ImageView discardView;

  @FXML private GridPane colorPicker;

  @FXML private Button heartsButton;

  @FXML private Button spadesButton;

  @FXML private Button diamondsButton;

  @FXML private Button clubsButton;

  @FXML private Label newColorLabel;

  private Game game;
  private ImageView[] cardImages;
  private Image[] cardImagesRaw;
  private int cardToPlayIndexHolder;

  @FXML
  void colorPickOnClick(ActionEvent event) throws Exception {
    Button button = (Button) event.getSource();
    String colorEncoding = button.getText();

    byte newColor =
        switch (colorEncoding) {
          case "Hearts" -> (byte) 0;
          case "Spades" -> (byte) 1;
          case "Diamonds" -> (byte) 2;
          case "Clubs" -> (byte) 3;
          default -> throw new IllegalStateException("Unexpected value: " + colorEncoding);
        };

    Deck deck = game.getDeck();
    Player player = game.getPlayer();

    player.playCrazyEight(deck, cardToPlayIndexHolder, newColor);
    removeCard(cardToPlayIndexHolder);

    cardsPane.setMouseTransparent(false);
    discardView.setMouseTransparent(false);
    colorPicker.setVisible(false);

    turnEnd(player);
  }

  @FXML
  void startGameButtonOnClick(ActionEvent event) {
    String gameMode = gameModeSelector.getValue();

    byte numberOfCards = 7;
    if (gameMode != "2 players") {
      numberOfCards = 5;
    }

    for (int i = 0; i < numberOfCards; i++) {
      addCard();
    }

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
  void drawCardButtonOnClick(ActionEvent event) throws Exception {
    Deck deck = game.getDeck();
    Player player = game.getPlayer();

    player.drawCard(deck);
    addCard();

    if (player.isCheater()) {
      gameEnd(cheaterPrompt);
    }

    turnEnd(player);
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
    assert drawCardButton != null
        : "fx:id=\"drawCardButton\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
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
    assert cardsPane != null
        : "fx:id=\"cardsPane\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert discardView != null
        : "fx:id=\"discardView\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert colorPicker != null
        : "fx:id=\"colorPicker\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert heartsButton != null
        : "fx:id=\"heartsButton\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert spadesButton != null
        : "fx:id=\"spadesButton\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert diamondsButton != null
        : "fx:id=\"diamondsButton\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert clubsButton != null
        : "fx:id=\"clubsButton\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert newColorLabel != null
        : "fx:id=\"newColorLabel\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";

    gameModeSelector.getItems().add("2 players");
    gameModeSelector.getItems().add("3 players");
    gameModeSelector.getItems().add("4 players");
    gameModeSelector.setValue("2 players");

    initializeCardImages();
    generateCats();
  }

  private void initializeCardImages() {
    File dir = new File("src/main/resources/cards/");
    File[] files = dir.listFiles();

    //    for (File file : files) {
    //      System.out.println(file);
    //    }

    cardImagesRaw = new Image[52];
    cardImages = new ImageView[52];

    for (int i = 0; i < Objects.requireNonNull(files).length - 1; i++) {
      System.out.println(files[i].toString().substring(18));
      cardImagesRaw[i] = new Image(files[i].toString().substring(18));
      cardImages[i] = new ImageView(cardImagesRaw[i]);
      cardImages[i].setFitWidth(116);
      cardImages[i].setPreserveRatio(true);
    }

    Image cardBack = new Image("/cards/5.png");
    ImageView cardBackView = new ImageView(cardBack);
    cardBackView.setFitHeight(116);
    cardBackView.setPreserveRatio(true);

    discardView.setPreserveRatio(true);
    drawCardButton.setGraphic(cardBackView);
  }

  private void showPlayerCards() {

    Player player = game.getPlayer();
    List<Card> playerCards = player.getPlayerCards();

    ObservableList<Node> cards = cardsPane.getChildren();
    Button card;

    byte color;
    byte value;

    int i = 0;
    for (Card playerCard : playerCards) {
      card = (Button) cards.get(i);

      color = playerCard.getColor();
      value = playerCard.getValue();

      card.setGraphic(cardImages[color * 13 + value]);

      i++;
    }
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
    Card discardPileLastCard = deck.getLastCardFromDiscardPileForGUI();

    if (deck.getActualColor() != null) {
      String actualColor =
          switch (deck.getActualColor()) {
            case 0 -> "Hearts";
            case 1 -> "Spades";
            case 2 -> "Diamonds";
            case 3 -> "Clubs";
            default -> throw new IllegalStateException(
                "Unexpected value: " + deck.getActualColor());
          };

      newColorLabel.setText("Actual color is " + actualColor);
    } else newColorLabel.setText("");

    byte color = discardPileLastCard.getColor();
    byte value = discardPileLastCard.getValue();

    discardView.setImage(cardImagesRaw[color * 13 + value]);
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

  private void addCard() {
    Button button = new Button();
    button.setPrefWidth(65);
    button.setPrefHeight(116);
    button.setLayoutX(cardsPane.getChildren().size() * 70);

    button.setOnAction(
        event -> {
          int cardToPlayIndex = -1;

          ObservableList<Node> cards = cardsPane.getChildren();
          for (int i = 0; i < cards.size(); i++) {
            if (button.equals(cards.get(i))) cardToPlayIndex = i;
          }

          Deck deck = game.getDeck();
          Player player = game.getPlayer();

          Card cardToPlay = player.getCard(cardToPlayIndex);
          if (Card.compareCrazyEight(cardToPlay)) {
            showColorPicker(cardToPlayIndex);
          } else {
            try {
              player.playCard(deck, cardToPlayIndex);
              removeCard(cardToPlayIndex);
            } catch (Exception e) {
              e.printStackTrace();
              return;
            }
          }

          try {
            turnEnd(player);
          } catch (Exception e) {
            e.printStackTrace();
          }
        });
    cardsPane.getChildren().add(button);
  }

  private void showColorPicker(int cardToPlayIndex) {
    cardToPlayIndexHolder = cardToPlayIndex;
    cardsPane.setMouseTransparent(true);
    discardView.setMouseTransparent(true);
    colorPicker.setVisible(true);
  }

  private void removeCard(int index) throws Exception {
    ObservableList<Node> cards = cardsPane.getChildren();
    if (index > cards.size() - 1) throw new Exception("Error: CrazyEightsTest.java 716");

    cards.remove(index);

    for (int i = index; i < cards.size(); i++) {
      cards.get(i).setLayoutX(i * 70);
    }
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

  private void turnEnd(Player player) throws Exception {
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
}
