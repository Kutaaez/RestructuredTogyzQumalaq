package org.example.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.controller.GameController;

public class MainView {
    private final GameController controller;
    private final Stage stage;
    private final BorderPane root;
    private final HBox topRow;
    private final HBox bottomRow;
    private final ScoreBoard kazan;
    private final PlayerPanel playerPanel;
    private final WinOverlay winOverlay;

    public MainView(GameController controller, Stage stage) {
        this.controller = controller;
        this.stage = stage;
        this.root = new BorderPane();
        this.topRow = new HBox(15);
        this.bottomRow = new HBox(15);
        this.kazan = new ScoreBoard();
        this.playerPanel = new PlayerPanel();
        this.winOverlay = new WinOverlay(controller, stage);
        buildLayout();
        // Установить минимальный размер сцены
        stage.setMinWidth(1200);
        stage.setMinHeight(700);
    }

    private void buildLayout() {
        root.setPadding(new Insets(20));
        root.getStyleClass().add("root");

        // Top row — opponent's holes
        topRow.setAlignment(Pos.CENTER);
        topRow.setPadding(new Insets(15));
        for (int i = 1; i <= 9; i++) {
            HolePane pane = new HolePane(i, false);
            pane.setOnMouseClicked(e -> controller.onHoleClicked(pane.getHoleIndex(), false));
            topRow.getChildren().add(pane);
        }
        root.setTop(topRow);

        // Bottom row — player's holes
        bottomRow.setAlignment(Pos.CENTER);
        bottomRow.setPadding(new Insets(15));
        for (int i = 1; i <= 9; i++) {
            HolePane pane = new HolePane(i, true);
            pane.setOnMouseClicked(e -> controller.onHoleClicked(pane.getHoleIndex(), true));
            bottomRow.getChildren().add(pane);
        }

        // Center — kazan
        VBox centerBox = new VBox(15);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.getChildren().add(kazan.getNode());
        root.setCenter(centerBox);

        // Right — player panel and buttons
        VBox rightBox = new VBox(15);
        rightBox.setAlignment(Pos.TOP_CENTER);
        rightBox.setPadding(new Insets(15));

        rightBox.getChildren().add(playerPanel.getNode());

        Button resetBtn = new Button("Reset");
        resetBtn.setPrefWidth(120);
        resetBtn.getStyleClass().add("reset-button");
        resetBtn.setOnAction(e -> controller.onNewGame());

        Button exitBtn = new Button("Exit");
        exitBtn.setPrefWidth(120);
        exitBtn.getStyleClass().add("exit-button");
        exitBtn.setOnAction(e -> {
            MainMenuView menuView = new MainMenuView(stage);
            stage.setScene(menuView.getScene());
        });

        rightBox.getChildren().addAll(resetBtn, exitBtn);
        root.setRight(rightBox);

        // Bottom — player's holes
        bottomRow.setAlignment(Pos.CENTER);
        root.setBottom(bottomRow);

        // Add win overlay
        root.getChildren().add(winOverlay);

        // Center winOverlay on top of other nodes
        StackPane.setAlignment(winOverlay, Pos.CENTER);  // Centering the overlay
        winOverlay.toFront();  // Bringing overlay to the front
    }


    public void update() {
        int currentPlayer = controller.getCurrentPlayer();
        for (Node node : topRow.getChildren()) {
            HolePane hp = (HolePane) node;
            hp.setCount(controller.getOpponentHoleCount(hp.getHoleIndex()));
            hp.setTuzdyk(controller.getTuzdyk(0) == hp.getHoleIndex());
            hp.setDisable(currentPlayer != 1);
        }
        for (Node node : bottomRow.getChildren()) {
            HolePane hp = (HolePane) node;
            hp.setCount(controller.getHoleCount(hp.getHoleIndex()));
            hp.setTuzdyk(controller.getTuzdyk(1) == hp.getHoleIndex());
            hp.setDisable(currentPlayer != 0);
        }
        kazan.setScores(controller.getKazan(0), controller.getKazan(1));
        playerPanel.setScores(controller.getKazan(0), controller.getKazan(1));
        playerPanel.setCurrentPlayer(currentPlayer);

        // Show win overlay if game is finished
        if (controller.isFinished()) {
            winOverlay.showResult(controller.getGameResult());
        } else {
            winOverlay.setVisible(false);
        }
    }

    public BorderPane getRoot() {
        return root;
    }
}