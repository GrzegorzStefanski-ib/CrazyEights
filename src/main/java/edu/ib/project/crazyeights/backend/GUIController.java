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
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class GUIController {

  @FXML private ResourceBundle resources;

  @FXML private URL location;

  @FXML private Pane menuScreen;

  @FXML private ChoiceBox<String> gameModeSelector;

  @FXML private Button startGameButton;

  @FXML private Pane gameScreen;

  @FXML private Button drawCardButton;

  @FXML private Text winPrompt;
  @FXML private Text defeatPrompt;
  @FXML private Text cheaterPrompt;

  @FXML private Circle bot1ImageCircle;
  @FXML private Circle bot3ImageCircle;
  @FXML private Circle bot2ImageCircle;

  @FXML private AnchorPane cardsPane;

  @FXML private ImageView discardView;

  @FXML private GridPane colorPicker;

  @FXML private Button heartsButton;

  @FXML private Button spadesButton;

  @FXML private Button diamondsButton;

  @FXML private Button clubsButton;

  @FXML private Text actualColorLabel;

  @FXML private ImageView bot3EmptyCard;

  @FXML private ImageView bot2EmptyCard;

  @FXML private ImageView bot1EmptyCard;
  @FXML private Text bot3CardCount;

  @FXML private Text bot2CardCount;

  @FXML private Text bot1CardCount;

  @FXML private TextArea logWindow;

  @FXML private Text clickToDrawLabel;

  @FXML private Text selectNewColorLabel;

  private Game game;
  private ImageView[] cardImages;
  private Image[] cardImagesRaw;
  private int cardToPlayIndexHolder;

  @FXML
  void colorPickOnClick(ActionEvent event) throws Exception {
    Button button = (Button) event.getSource();
    String colorEncoding = button.getId();

    byte newColor =
        switch (colorEncoding) {
          case "heartsButton" -> (byte) 0;
          case "spadesButton" -> (byte) 1;
          case "diamondsButton" -> (byte) 2;
          case "clubsButton" -> (byte) 3;
          default -> throw new IllegalStateException("Unexpected value: " + colorEncoding);
        };

    Deck deck = game.getDeck();
    Player player = game.getPlayer();

    player.playCrazyEight(deck, cardToPlayIndexHolder, newColor);
    writeToLogWindow(player.getLog());
    removeCard(cardToPlayIndexHolder);

    cardsPane.setMouseTransparent(false);
    discardView.setMouseTransparent(false);
    colorPicker.setVisible(false);
    selectNewColorLabel.setVisible(false);

    turnEnd(player);
  }

  @FXML
  void startGameButtonOnClick(ActionEvent event) {
    String gameMode = gameModeSelector.getValue();

    byte numberOfCards = 7;
    if (!gameMode.equals("2 players")) {
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

    drawCardButton.setMouseTransparent(false);
    cardsPane.setMouseTransparent(false);
    gameScreen.setVisible(true);

    displayAllCardsInGame();
    generateCats();
  }

  @FXML
  void drawCardButtonOnClick(ActionEvent event) throws Exception {
    Deck deck = game.getDeck();
    Player player = game.getPlayer();

    player.drawCard(deck);
    writeToLogWindow(player.getLog());
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
    assert cardsPane != null
        : "fx:id=\"cardsPane\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
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
    assert bot3ImageCircle != null
        : "fx:id=\"bot3ImageCircle\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert bot3EmptyCard != null
        : "fx:id=\"bot3EmptyCard\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert bot3CardCount != null
        : "fx:id=\"bot3CardCount\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert bot2CardCount != null
        : "fx:id=\"bot2CardCount\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert bot2EmptyCard != null
        : "fx:id=\"bot2EmptyCard\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert bot2ImageCircle != null
        : "fx:id=\"bot2ImageCircle\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert bot1ImageCircle != null
        : "fx:id=\"bot1ImageCircle\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert bot1EmptyCard != null
        : "fx:id=\"bot1EmptyCard\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert bot1CardCount != null
        : "fx:id=\"bot1CardCount\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert drawCardButton != null
        : "fx:id=\"drawCardButton\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert discardView != null
        : "fx:id=\"discardView\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert actualColorLabel != null
        : "fx:id=\"actualColorLabel\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert logWindow != null
        : "fx:id=\"logWindow\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert cheaterPrompt != null
        : "fx:id=\"cheaterPrompt\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert winPrompt != null
        : "fx:id=\"winPrompt\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert defeatPrompt != null
        : "fx:id=\"defeatPrompt\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert clickToDrawLabel != null
        : "fx:id=\"clickToDrawLabel\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";
    assert selectNewColorLabel != null
        : "fx:id=\"selectNewColorLabel\" was not injected: check your FXML file 'devCrazyEightsGUI.fxml'.";

    gameModeSelector.getItems().add("2 players");
    gameModeSelector.getItems().add("3 players");
    gameModeSelector.getItems().add("4 players");
    gameModeSelector.setValue("2 players");

    logWindow.clear();
    logWindow.setText("Game started. \nYou start first.");

    initializeCardImages();
    BackgroundImage myBI =
        new BackgroundImage(
            new Image("/misc/bg.png", 1280, 720, false, true),
            BackgroundRepeat.REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
    gameScreen.setBackground(new Background(myBI));

    BackgroundImage myBI2 =
        new BackgroundImage(
            new Image("/misc/bg2.png", 1280, 720, false, true),
            BackgroundRepeat.REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
    menuScreen.setBackground(new Background(myBI2));
  }

  private void initializeCardImages() {
    File dir = new File("src/main/resources/cards/");
    File[] files = dir.listFiles();

    cardImagesRaw = new Image[52];
    cardImages = new ImageView[52];

    for (int i = 0; i < Objects.requireNonNull(files).length - 1; i++) {
      cardImagesRaw[i] = new Image(files[i].toString().substring(18));

      cardImages[i] = new ImageView(cardImagesRaw[i]);
      cardImages[i].setFitWidth(116);
      cardImages[i].setPreserveRatio(true);
    }

    Image cardBack = new Image("/cards/5.png");
    ImageView cardBackView = new ImageView(cardBack);
    cardBackView.setFitHeight(136);
    cardBackView.setFitWidth(100);
    cardBackView.setPreserveRatio(true);
    drawCardButton.setGraphic(cardBackView);

    dir = new File("src/main/resources/icons/");
    files = dir.listFiles();

    Image[] icons = new Image[4];
    ImageView[] iconsView = new ImageView[4];

    for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
      icons[i] = new Image(files[i].toString().substring(18));
      iconsView[i] = new ImageView(icons[i]);
      iconsView[i].setFitHeight(50);
      iconsView[i].setFitWidth(50);
      iconsView[i].setPreserveRatio(true);
    }

    heartsButton.setGraphic(iconsView[0]);
    spadesButton.setGraphic(iconsView[1]);
    diamondsButton.setGraphic(iconsView[2]);
    clubsButton.setGraphic(iconsView[3]);
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

  private void gameEnd(Text label) {
    drawCardButton.setMouseTransparent(true);
    cardsPane.setMouseTransparent(true);

    cardsPane.getChildren().clear();

    writeToLogWindow("Game ended. It will restart in 5 second.");

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

      actualColorLabel.setText("Current color is " + actualColor);
    } else actualColorLabel.setText("");

    byte color = discardPileLastCard.getColor();
    byte value = discardPileLastCard.getValue();

    Random random = new Random();
    discardView.setImage(cardImagesRaw[color * 13 + value]);
    discardView.setRotate(random.nextInt(50) - 25);
  }

  private void generateCats() {

    int rOld1 = -1;
    int rOld2;
    Image cardBack = new Image("/cards/5.png");
    List<Player> bots = game.getBotsList();

    Image[] catPhotos = {
      new Image("/cats/cat4.jpg", false),
      new Image("/cats/cat5.jpg", false),
      new Image("/cats/cat6.jpg", false),
      new Image("/cats/cat7.jpg", false),
      new Image("/cats/cat3.gif", false),
      new Image("/cats/cat2.gif", false)
    };
    Random random = new Random();
    int r = random.nextInt(catPhotos.length);
    bot1ImageCircle.setFill(new ImagePattern(catPhotos[r]));
    bot1ImageCircle.setVisible(true);
    bot1EmptyCard.setImage(cardBack);
    bot1EmptyCard.setFitHeight(136);
    bot1EmptyCard.setPreserveRatio(true);
    if (bots.size() > 1) {
      rOld1 = r;
      while (r == rOld1) {
        r = random.nextInt(catPhotos.length);
      }
      bot2ImageCircle.setFill(new ImagePattern(catPhotos[r]));
      bot2ImageCircle.setVisible(true);
      bot2EmptyCard.setImage(cardBack);
      bot2EmptyCard.setFitHeight(136);
      bot2EmptyCard.setPreserveRatio(true);
    }
    if (bots.size() > 2) {
      rOld2 = r;
      while (r == rOld1 || r == rOld2) {
        r = random.nextInt(catPhotos.length);
      }
      bot3ImageCircle.setFill(new ImagePattern(catPhotos[r]));
      bot3ImageCircle.setVisible(true);
      bot3EmptyCard.setImage(cardBack);
      bot3EmptyCard.setFitHeight(136);
      bot3EmptyCard.setPreserveRatio(true);
    }
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
              if (player.playCard(deck, cardToPlayIndex)) {
                removeCard(cardToPlayIndex);
                writeToLogWindow(player.getLog());
                turnEnd(player);
              } else {
                writeToLogWindow(player.getLog());
              }

            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        });
    cardsPane.getChildren().add(button);
  }

  private void showColorPicker(int cardToPlayIndex) {
    cardToPlayIndexHolder = cardToPlayIndex;
    cardsPane.setMouseTransparent(true);
    discardView.setMouseTransparent(true);
    colorPicker.setVisible(true);
    selectNewColorLabel.setVisible(true);
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
    List<Text> botCardCounts = Arrays.asList(bot1CardCount, bot2CardCount, bot3CardCount);

    for (int i = 0; i < bots.size(); i++) {
      Player bot = bots.get(i);
      List<Card> botCards = bot.getPlayerCards();
      botCardCounts.get(i).setText(String.valueOf(botCards.size()));
    }
  }

  public void writeToLogWindow(String message) {
    String logWindowText = logWindow.getText();
    logWindow.setText(logWindowText + "\n" + message);
    logWindow.end();
  }

  private void turnEnd(Player player) throws Exception {
    showDiscardPileLastCard();
    showPlayerCards();

    if (player.isPlayersCardEmpty()) gameEnd(winPrompt);
    else {
      List<Player> bots = game.getBotsList();
      BotsAlgorithm botsAlgorithm = new BotsAlgorithm(game);

      for (Player bot : bots) {
        String botLog = botsAlgorithm.makeBotMove(bot);

        writeToLogWindow(botLog);

        showDiscardPileLastCard();
        showBotsCards();

        if (bot.isPlayersCardEmpty()) gameEnd(defeatPrompt);
      }
    }
  }
}
