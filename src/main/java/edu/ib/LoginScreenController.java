//Copyright (C) 2021, Grzegorz Stefański
package edu.ib;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

public class LoginScreenController {

  @FXML private ResourceBundle resources;

  @FXML private URL location;

  @FXML private Pane LoginScreen;

  @FXML private Pane GameScreen;

  @FXML private Button startGameButton;

  @FXML private ChoiceBox<String> gameModeSelector;

  @FXML private ListView<String> cardsList;

  @FXML private ListView<String> pileList;

  @FXML private Button playCardButton;

  @FXML private Button drawCardButton;

  @FXML private Label winPrompt;

  @FXML
  void startGameButtonOnClick(ActionEvent event) throws IOException {

    String n_players = gameModeSelector.getValue();

    if (n_players != null) {
      game = new Game(n_players);

      LoginScreen.setVisible(false);
      GameScreen.setVisible(true);

      getPlayerCards();
      getPileCards();
    }
  }

  @FXML
  void drawCardButtonOnClick(ActionEvent event) {

    Deck deck = game.getDeck();
    game.getPlayer(0).drawCard(deck);

    // TODO: Check if there are still cards in deck

    getPlayerCards();
  }

  @FXML
  void playCardButtonOnClick(ActionEvent event) {
    int index = cardsList.getSelectionModel().getSelectedIndex();
    DiscardPile discardPile = game.getDiscardPile();
    Player player = game.getPlayer(0);

    Card card = player.playCard(discardPile, index);
    if (card == null) return;

    discardPile.addCard(card);

    getPlayerCards();
    getPileCards();

    if (player.checkIfWin()) {
      winPrompt.setVisible(true);
    }
  }

  private Game game;

  private void addOptions() {
    gameModeSelector.getItems().add("2 players");
    gameModeSelector.getItems().add("3 players");
    gameModeSelector.getItems().add("4 players");

    gameModeSelector.setValue("2 players");
  }

  private void getPlayerCards() {
    Player player = game.getPlayer(0);
    List<Card> cards = player.getCards();

    cardsList.getItems().clear();

    for (Card card : cards) {
      cardsList.getItems().add(card.toString());
    }
  }

  private void getPileCards() {
    DiscardPile discardPile = game.getDiscardPile();
    Card card = discardPile.getLastCard();

    pileList.getItems().clear();
    pileList.getItems().add(card.toString());
  }

  @FXML
  void initialize() {
    assert LoginScreen != null
        : "fx:id=\"LoginScreen\" was not injected: check your FXML file 'loginScreen.fxml'.";
    assert startGameButton != null
        : "fx:id=\"startGameButton\" was not injected: check your FXML file 'loginScreen.fxml'.";
    assert gameModeSelector != null
        : "fx:id=\"gameModeSelector\" was not injected: check your FXML file 'loginScreen.fxml'.";
    assert GameScreen != null
        : "fx:id=\"GameScreen\" was not injected: check your FXML file 'loginScreen.fxml'.";
    assert cardsList != null
        : "fx:id=\"cardsList\" was not injected: check your FXML file 'loginScreen.fxml'.";
    assert playCardButton != null
        : "fx:id=\"playCardButton\" was not injected: check your FXML file 'loginScreen.fxml'.";
    assert drawCardButton != null
        : "fx:id=\"drawCardButton\" was not injected: check your FXML file 'loginScreen.fxml'.";
    assert pileList != null
        : "fx:id=\"pileList\" was not injected: check your FXML file 'loginScreen.fxml'.";
    assert winPrompt != null
        : "fx:id=\"winPrompt\" was not injected: check your FXML file 'loginScreen.fxml'.";

    addOptions();
  }
}
