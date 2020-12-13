package edu.ib;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CrazyEights extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/crazyEights.fxml"));
        Parent root = loader.load();


        Scene scene = new Scene(root,300,400);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }
}
