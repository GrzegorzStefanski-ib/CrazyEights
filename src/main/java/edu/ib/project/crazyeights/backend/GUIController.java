//Copyright (C) 2021, Grzegorz Stefański
package edu.ib.project.crazyeights.backend;

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

  private Game game;
  private ImageView[] cardImages;
  private Image[] cardImagesRaw;

  // TODO: Support for crazy eights.

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
  void playCardButtonOnClick(ActionEvent event) throws Exception {
    int cardToPlayIndex = -1;

    ObservableList<Node> cards = cardsPane.getChildren();
    for (int i = 0; i < cards.size(); i++) {
      if (event.getSource().equals(cards.get(i))) cardToPlayIndex = i;
    }

    Deck deck = game.getDeck();
    Player player = game.getPlayer();

    Card cardToPlay = player.getCard(cardToPlayIndex);
    if (Card.compareCrazyEight(cardToPlay)) {
      Random random = new Random();
      player.playCrazyEight(
          deck, cardToPlayIndex, (byte) random.nextInt(4)); // TODO: Color selection by player.
    } else {
      try {
        player.playCard(deck, cardToPlayIndex);
        removeCard(cardToPlayIndex);
      } catch (Exception e) {
        e.printStackTrace();
        return;
      }
    }

    turnEnd(player);
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

    gameModeSelector.getItems().add("2 players");
    gameModeSelector.getItems().add("3 players");
    gameModeSelector.getItems().add("4 players");
    gameModeSelector.setValue("2 players");

    initializeCardImages();
    generateCats();
  }

  private void initializeCardImages() {
    Image c2 = new Image("/cards/C2.png");
    Image c3 = new Image("/cards/C3.png");
    Image c4 = new Image("/cards/C4.png");
    Image c5 = new Image("/cards/C5.png");
    Image c6 = new Image("/cards/C6.png");
    Image c7 = new Image("/cards/C7.png");
    Image c8 = new Image("/cards/C8.png");
    Image c9 = new Image("/cards/C9.png");
    Image c10 = new Image("/cards/C10.png");
    Image cA = new Image("/cards/CA.png");
    Image cQ = new Image("/cards/CQ.png");
    Image cJ = new Image("/cards/CJ.png");
    Image cK = new Image("/cards/CK.png");
    ImageView c2V = new ImageView(c2);
    ImageView c3V = new ImageView(c3);
    ImageView c4V = new ImageView(c4);
    ImageView c5V = new ImageView(c5);
    ImageView c6V = new ImageView(c6);
    ImageView c7V = new ImageView(c7);
    ImageView c8V = new ImageView(c8);
    ImageView c9V = new ImageView(c9);
    ImageView c10V = new ImageView(c10);
    ImageView cAV = new ImageView(cA);
    ImageView cQV = new ImageView(cQ);
    ImageView cJV = new ImageView(cJ);
    ImageView cKV = new ImageView(cK);
    c2V.setFitHeight(116);
    c2V.setPreserveRatio(true);
    c3V.setFitHeight(116);
    c3V.setPreserveRatio(true);
    c4V.setFitHeight(116);
    c4V.setPreserveRatio(true);
    c5V.setFitHeight(116);
    c5V.setPreserveRatio(true);
    c6V.setFitHeight(116);
    c6V.setPreserveRatio(true);
    c7V.setFitHeight(116);
    c7V.setPreserveRatio(true);
    c8V.setFitHeight(116);
    c8V.setPreserveRatio(true);
    c9V.setFitHeight(116);
    c9V.setPreserveRatio(true);
    c10V.setFitHeight(116);
    c10V.setPreserveRatio(true);
    cAV.setFitHeight(116);
    cAV.setPreserveRatio(true);
    cJV.setFitHeight(116);
    cJV.setPreserveRatio(true);
    cQV.setFitHeight(116);
    cQV.setPreserveRatio(true);
    cKV.setFitHeight(116);
    cKV.setPreserveRatio(true);

    Image d2 = new Image("/cards/D2.png");
    Image d3 = new Image("/cards/D3.png");
    Image d4 = new Image("/cards/D4.png");
    Image d5 = new Image("/cards/D5.png");
    Image d6 = new Image("/cards/D6.png");
    Image d7 = new Image("/cards/D7.png");
    Image d8 = new Image("/cards/D8.png");
    Image d9 = new Image("/cards/D9.png");
    Image d10 = new Image("/cards/D10.png");
    Image dA = new Image("/cards/DA.png");
    Image dQ = new Image("/cards/DQ.png");
    Image dJ = new Image("/cards/DJ.png");
    Image dK = new Image("/cards/DK.png");
    ImageView d2V = new ImageView(d2);
    ImageView d3V = new ImageView(d3);
    ImageView d4V = new ImageView(d4);
    ImageView d5V = new ImageView(d5);
    ImageView d6V = new ImageView(d6);
    ImageView d7V = new ImageView(d7);
    ImageView d8V = new ImageView(d8);
    ImageView d9V = new ImageView(d9);
    ImageView d10V = new ImageView(d10);
    ImageView dAV = new ImageView(dA);
    ImageView dQV = new ImageView(dQ);
    ImageView dJV = new ImageView(dJ);
    ImageView dKV = new ImageView(dK);
    d2V.setFitHeight(116);
    d2V.setPreserveRatio(true);
    d3V.setFitHeight(116);
    d3V.setPreserveRatio(true);
    d4V.setFitHeight(116);
    d4V.setPreserveRatio(true);
    d5V.setFitHeight(116);
    d5V.setPreserveRatio(true);
    d6V.setFitHeight(116);
    d6V.setPreserveRatio(true);
    d7V.setFitHeight(116);
    d7V.setPreserveRatio(true);
    d8V.setFitHeight(116);
    d8V.setPreserveRatio(true);
    d9V.setFitHeight(116);
    d9V.setPreserveRatio(true);
    d10V.setFitHeight(116);
    d10V.setPreserveRatio(true);
    dAV.setFitHeight(116);
    dAV.setPreserveRatio(true);
    dJV.setFitHeight(116);
    dJV.setPreserveRatio(true);
    dQV.setFitHeight(116);
    dQV.setPreserveRatio(true);
    dKV.setFitHeight(116);
    dKV.setPreserveRatio(true);

    Image h2 = new Image("/cards/H2.png");
    Image h3 = new Image("/cards/H3.png");
    Image h4 = new Image("/cards/H4.png");
    Image h5 = new Image("/cards/H5.png");
    Image h6 = new Image("/cards/H6.png");
    Image h7 = new Image("/cards/H7.png");
    Image h8 = new Image("/cards/H8.png");
    Image h9 = new Image("/cards/H9.png");
    Image h10 = new Image("/cards/H10.png");
    Image hA = new Image("/cards/HA.png");
    Image hQ = new Image("/cards/HQ.png");
    Image hJ = new Image("/cards/HJ.png");
    Image hK = new Image("/cards/HK.png");
    ImageView h2V = new ImageView(h2);
    ImageView h3V = new ImageView(h3);
    ImageView h4V = new ImageView(h4);
    ImageView h5V = new ImageView(h5);
    ImageView h6V = new ImageView(h6);
    ImageView h7V = new ImageView(h7);
    ImageView h8V = new ImageView(h8);
    ImageView h9V = new ImageView(h9);
    ImageView h10V = new ImageView(h10);
    ImageView hAV = new ImageView(hA);
    ImageView hQV = new ImageView(hQ);
    ImageView hJV = new ImageView(hJ);
    ImageView hKV = new ImageView(hK);
    h2V.setFitHeight(116);
    h2V.setPreserveRatio(true);
    h3V.setFitHeight(116);
    h3V.setPreserveRatio(true);
    h4V.setFitHeight(116);
    h4V.setPreserveRatio(true);
    h5V.setFitHeight(116);
    h5V.setPreserveRatio(true);
    h6V.setFitHeight(116);
    h6V.setPreserveRatio(true);
    h7V.setFitHeight(116);
    h7V.setPreserveRatio(true);
    h8V.setFitHeight(116);
    h8V.setPreserveRatio(true);
    h9V.setFitHeight(116);
    h9V.setPreserveRatio(true);
    h10V.setFitHeight(116);
    h10V.setPreserveRatio(true);
    hAV.setFitHeight(116);
    hAV.setPreserveRatio(true);
    hJV.setFitHeight(116);
    hJV.setPreserveRatio(true);
    hQV.setFitHeight(116);
    hQV.setPreserveRatio(true);
    hKV.setFitHeight(116);
    hKV.setPreserveRatio(true);

    Image s2 = new Image("/cards/S2.png");
    Image s3 = new Image("/cards/S3.png");
    Image s4 = new Image("/cards/S4.png");
    Image s5 = new Image("/cards/S5.png");
    Image s6 = new Image("/cards/S6.png");
    Image s7 = new Image("/cards/S7.png");
    Image s8 = new Image("/cards/S8.png");
    Image s9 = new Image("/cards/S9.png");
    Image s10 = new Image("/cards/S10.png");
    Image sA = new Image("/cards/SA.png");
    Image sQ = new Image("/cards/SQ.png");
    Image sJ = new Image("/cards/SJ.png");
    Image sK = new Image("/cards/SK.png");
    ImageView s2V = new ImageView(s2);
    ImageView s3V = new ImageView(s3);
    ImageView s4V = new ImageView(s4);
    ImageView s5V = new ImageView(s5);
    ImageView s6V = new ImageView(s6);
    ImageView s7V = new ImageView(s7);
    ImageView s8V = new ImageView(s8);
    ImageView s9V = new ImageView(s9);
    ImageView s10V = new ImageView(s10);
    ImageView sAV = new ImageView(sA);
    ImageView sQV = new ImageView(sQ);
    ImageView sJV = new ImageView(sJ);
    ImageView sKV = new ImageView(sK);
    s2V.setFitHeight(116);
    s2V.setPreserveRatio(true);
    s3V.setFitHeight(116);
    s3V.setPreserveRatio(true);
    s4V.setFitHeight(116);
    s4V.setPreserveRatio(true);
    s5V.setFitHeight(116);
    s5V.setPreserveRatio(true);
    s6V.setFitHeight(116);
    s6V.setPreserveRatio(true);
    s7V.setFitHeight(116);
    s7V.setPreserveRatio(true);
    s8V.setFitHeight(116);
    s8V.setPreserveRatio(true);
    s9V.setFitHeight(116);
    s9V.setPreserveRatio(true);
    s10V.setFitHeight(116);
    s10V.setPreserveRatio(true);
    sAV.setFitHeight(116);
    sAV.setPreserveRatio(true);
    sJV.setFitHeight(116);
    sJV.setPreserveRatio(true);
    sQV.setFitHeight(116);
    sQV.setPreserveRatio(true);
    sKV.setFitHeight(116);
    sKV.setPreserveRatio(true);

    Image cardback = new Image("/cards/cardback.png");
    ImageView cardbackView = new ImageView(cardback);
    cardbackView.setPreserveRatio(true);
    cardbackView.setFitHeight(116);
    discardView.setPreserveRatio(true);

    drawCardButton.setGraphic(cardbackView);

    cardImages =
        new ImageView[] {
          h2V, h3V, h4V, h5V, h6V, h7V, h8V, h9V, h10V, hJV, hQV, hKV, hAV,
          s2V, s3V, s4V, s5V, s6V, s7V, s8V, s9V, s10V, sJV, sQV, sKV, sAV,
          d2V, d3V, d4V, d5V, d6V, d7V, d8V, d9V, d10V, dJV, dQV, dKV, dAV,
          c2V, c3V, c4V, c5V, c6V, c7V, c8V, c9V, c10V, cJV, cQV, cKV, cAV
        };
    cardImagesRaw =
        new Image[] {
          h2, h3, h4, h5, h6, h7, h8, h9, h10, hJ, hQ, hK, hA,
          s2, s3, s4, s5, s6, s7, s8, s9, s10, sJ, sQ, sK, sA,
          d2, d3, d4, d5, d6, d7, d8, d9, d10, dJ, dQ, dK, dA,
          c2, c3, c4, c5, c6, c7, c8, c9, c10, cJ, cQ, cK, cA
        };
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
    Card discardPileLastCard = deck.getLastCardFromDiscardPile();

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
            Random random = new Random();
            player.playCrazyEight(
                deck,
                cardToPlayIndex,
                (byte) random.nextInt(4)); // TODO: Color selection by player.
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
