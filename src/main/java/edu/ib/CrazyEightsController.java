//Copyright (C) 2020, Grzegorz Stefański
package edu.ib;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CrazyEightsController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField input;

    @FXML
    void initialize() {
        assert input !=
        null : "fx:id=\"input\" was not injected: check your FXML file 'crazyEights.fxml'.";
    }
}
