//Copyright (C) 2021, Grzegorz Stefański
package edu.ib.project.crazyeights.backend;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/devCrazyEightsGUI.fxml"));
    Parent root = loader.load();

    Scene scene = new Scene(root, 600, 400);
    GUIController controller = loader.getController();

    stage.setScene(scene);
    stage.setTitle("Crazy Eights");
    stage.setResizable(false);
    stage.show();
  }
}
