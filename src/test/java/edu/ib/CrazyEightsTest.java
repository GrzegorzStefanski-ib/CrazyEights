//Copyright (C) 2020, Grzegorz Stefański
package edu.ib;

import java.io.IOException;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CrazyEightsTest {

    @Test
    @Order(1)
    public void testIfRunnable() throws InterruptedException {
        Thread thread = new Thread(
            new Runnable() {

                @Override
                public void run() {
                    new JFXPanel();

                    Platform.runLater(
                        new Runnable() {

                            @Override
                            public void run() {
                                try {
                                    new CrazyEights().start(new Stage());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    );
                }
            }
        );

        thread.start();
        Thread.sleep(2000);
    }

    @Test
    @Order(2)
    public void testInput() {
        uiTest uit = new uiTest();
        uit.testInput();
    }

    @Test
    @Order(3)
    public void testController() {
        CrazyEightsController c = new CrazyEightsController();
        try {
            c.initialize();
            Assert.fail();
        } catch (AssertionError ae) {}

        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/crazyEights.fxml")
            );
            Parent root = loader.load();
            CrazyEightsController controller = loader.getController();

            controller.initialize();
        } catch (Exception e) {
            Assert.fail();
        }
    }
}
