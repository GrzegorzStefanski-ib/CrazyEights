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
  CrazyEightsController controller;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws IOException {
    loader = new FXMLLoader(getClass().getResource("/fxml/crazyEights.fxml"));
    root = loader.load();

    scene = new Scene(root, 300, 400);
    controller = loader.getController();

    stage.setScene(scene);
    stage.setResizable(false);
    stage.show();
  }
}
