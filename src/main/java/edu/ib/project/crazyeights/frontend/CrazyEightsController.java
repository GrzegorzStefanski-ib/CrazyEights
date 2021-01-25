//Copyright (C) 2021, Grzegorz Stefański
package edu.ib.project.crazyeights.frontend;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CrazyEightsController {

  @FXML private ResourceBundle resources;

  @FXML private URL location;

  @FXML private VBox centerVbox;

  @FXML private HBox botHbox;

  @FXML private Button botCard1Button;

  @FXML private Button botCard2Button;

  @FXML private Button botCard3Button;

  @FXML private Button botCard4Button;

  @FXML private Button botCard5Button;

  @FXML private Button botCard6Button;

  @FXML private Button botCard7Button;

  @FXML private Button botCard8Button;

  @FXML private HBox centerHbox;

  @FXML private Button drawFromDeckButton;

  @FXML private HBox playerHbox;

  @FXML private Button playerCard1Button;

  @FXML private Button playerCard2Button;

  @FXML private Button playerCard3Button;

  @FXML private Button playerCard4Button;

  @FXML private Button playerCard5Button;

  @FXML private Button playerCard6Button;

  @FXML private Button playerCard7Button;

  @FXML private Button playerCard8Button;

  @FXML private ImageView botImageView;

  @FXML private ImageView playerImageView;

  @FXML
  void initialize() {
    assert centerVbox != null
        : "fx:id=\"centerVbox\" was not injected: check your FXML file 'crazyEights.fxml'.";
    assert botHbox != null
        : "fx:id=\"botHbox\" was not injected: check your FXML file 'crazyEights.fxml'.";
    assert botCard1Button != null
        : "fx:id=\"botCard1Button\" was not injected: check your FXML file 'crazyEights.fxml'.";
    assert botCard2Button != null
        : "fx:id=\"botCard2Button\" was not injected: check your FXML file 'crazyEights.fxml'.";
    assert botCard3Button != null
        : "fx:id=\"botCard3Button\" was not injected: check your FXML file 'crazyEights.fxml'.";
    assert botCard4Button != null
        : "fx:id=\"botCard4Button\" was not injected: check your FXML file 'crazyEights.fxml'.";
    assert botCard5Button != null
        : "fx:id=\"botCard5Button\" was not injected: check your FXML file 'crazyEights.fxml'.";
    assert botCard6Button != null
        : "fx:id=\"botCard6Button\" was not injected: check your FXML file 'crazyEights.fxml'.";
    assert botCard7Button != null
        : "fx:id=\"botCard7Button\" was not injected: check your FXML file 'crazyEights.fxml'.";
    assert botCard8Button != null
        : "fx:id=\"botCard8Button\" was not injected: check your FXML file 'crazyEights.fxml'.";
    assert centerHbox != null
        : "fx:id=\"centerHbox\" was not injected: check your FXML file 'crazyEights.fxml'.";
    assert drawFromDeckButton != null
        : "fx:id=\"drawFromDeckButton\" was not injected: check your FXML file 'crazyEights.fxml'.";
    assert playerHbox != null
        : "fx:id=\"playerHbox\" was not injected: check your FXML file 'crazyEights.fxml'.";
    assert playerCard1Button != null
        : "fx:id=\"playerCard1Button\" was not injected: check your FXML file 'crazyEights.fxml'.";
    assert playerCard2Button != null
        : "fx:id=\"playerCard2Button\" was not injected: check your FXML file 'crazyEights.fxml'.";
    assert playerCard3Button != null
        : "fx:id=\"playerCard3Button\" was not injected: check your FXML file 'crazyEights.fxml'.";
    assert playerCard4Button != null
        : "fx:id=\"playerCard4Button\" was not injected: check your FXML file 'crazyEights.fxml'.";
    assert playerCard5Button != null
        : "fx:id=\"playerCard5Button\" was not injected: check your FXML file 'crazyEights.fxml'.";
    assert playerCard6Button != null
        : "fx:id=\"playerCard6Button\" was not injected: check your FXML file 'crazyEights.fxml'.";
    assert playerCard7Button != null
        : "fx:id=\"playerCard7Button\" was not injected: check your FXML file 'crazyEights.fxml'.";
    assert playerCard8Button != null
        : "fx:id=\"playerCard8Button\" was not injected: check your FXML file 'crazyEights.fxml'.";
    assert botImageView != null
        : "fx:id=\"botImageView\" was not injected: check your FXML file 'crazyEights.fxml'.";
    assert playerImageView != null
        : "fx:id=\"playerImageView\" was not injected: check your FXML file 'crazyEights.fxml'.";
  }
}
