//Copyright (C) 2020, Grzegorz Stefański
package edu.ib;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CrazyEights extends Application {
  FXMLLoader loader;
  Parent root;
  Scene scene;
  LoginScreenController controller;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws IOException {
    loader = new FXMLLoader(getClass().getResource("/fxml/loginScreen.fxml"));
    root = loader.load();

    scene = new Scene(root, 600, 400);
    controller = loader.getController();

    stage.setScene(scene);
    stage.setTitle("Crazy Eights");
    stage.setResizable(false);
    stage.show();
  }
}
