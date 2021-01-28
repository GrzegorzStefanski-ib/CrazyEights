//Copyright (C) 2020, Grzegorz Stefański
package edu.ib;

import edu.ib.project.crazyeights.backend.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CrazyEightsTest {

  @Test
  @Order(1)
  public void testCardClass() {
    CardTest cardTest = new CardTest();

    cardTest.compare();
    cardTest.compareCrazyEight();
    cardTest.getColor();
    cardTest.getValue();
    cardTest.testToString();

    System.out.println();
  }

  @Test
  @Order(2)
  public void testDeckClass() throws Exception {
    DeckTest deckTest = new DeckTest();

    deckTest.getCardFromDeck();
    deckTest.getLastCardFromDiscardPile();
    deckTest.getLastCardFromDiscardPileForGUI();
    deckTest.addCardToDiscardPile();
    deckTest.addCardToDiscardPile_newColor();
    deckTest.getActualColor();

    System.out.println();
  }

  @Test
  @Order(3)
  public void testGameClass() throws Exception {
    GameTest gameTest = new GameTest();

    gameTest.loadCardsData();
    gameTest.getDeck();
    gameTest.getPlayer();
    gameTest.getBotsList();

    System.out.println();
  }

  @Test
  @Order(4)
  public void testPlayerClass() throws Exception {
    PlayerTest playerTest = new PlayerTest();

    playerTest.playCard();
    playerTest.playCrazyEight();
    playerTest.drawCard();
    playerTest.isPlayersCardEmpty();
    playerTest.isCheater();
    playerTest.getCard();
    playerTest.getPlayerCards();

    System.out.println();
  }

  @Test
  @Order(5)
  public void testBotsAlgorithmClass() throws Exception {
    BotsAlgorithmTest botsAlgorithmTest = new BotsAlgorithmTest();

    botsAlgorithmTest.makeBotMove();

    System.out.println();
  }
  //
  //  @Test
  //  @Order(1)
  //  public void testIfRunnable() throws InterruptedException {
  //    Thread thread =
  //        new Thread(
  //            new Runnable() {
  //
  //              @Override
  //              public void run() {
  //                new JFXPanel();
  //
  //                Platform.runLater(
  //                    new Runnable() {
  //
  //                      @Override
  //                      public void run() {
  //                        try {
  //                          new CrazyEights().start(new Stage());
  //                        } catch (IOException e) {
  //                          e.printStackTrace();
  //                        }
  //                      }
  //                    });
  //              }
  //            });
  //
  //    thread.start();
  //    Thread.sleep(3000);
  //  }
  //
  //  @Test
  //  @Order(2)
  //  public void testInput() {
  //    uiTest uit = new uiTest();
  //    uit.testInput();
  //  }
  //
  //  @Test
  //  @Order(3)
  //  public void testController() {
  //    Assert.assertTrue(CrazyEightsController.class.desiredAssertionStatus());
  //
  //    CrazyEightsController c = new CrazyEightsController();
  //    try {
  //      c.initialize();
  //      Assert.fail();
  //    } catch (AssertionError ae) {
  //    }
  //
  //    try {
  //      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/crazyEights.fxml"));
  //      Parent root = loader.load();
  //      CrazyEightsController controller = loader.getController();
  //
  //      controller.initialize();
  //    } catch (Exception e) {
  //      Assert.fail();
  //    }
  //  }
  //
  //  @Test
  //  @Order(4)
  //  public void testControllerDisabledAssert() {
  //    CrazyEightsController.class
  //        .getClassLoader()
  //        .setClassAssertionStatus(CrazyEightsController.class.getName(), false);
  //    Assert.assertFalse(CrazyEightsController.class.desiredAssertionStatus());
  //
  //    CrazyEightsController c = new CrazyEightsController();
  //    try {
  //      c.initialize();
  //      Assert.fail();
  //    } catch (AssertionError ae) {
  //    }
  //
  //    try {
  //      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/crazyEights.fxml"));
  //      Parent root = loader.load();
  //      CrazyEightsController controller = loader.getController();
  //
  //      controller.initialize();
  //    } catch (Exception e) {
  //      Assert.fail();
  //    }
  //  }
}
