package org.example.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
    private final ScoreBoard playerScore;
    private final ScoreBoard opponentScore;
    private final TurnIndicator turnIndicator;
    private final StackPane centerPanel;

    public MainView(GameController controller, Stage stage) {
        this.controller = controller;
        this.stage = stage;
        this.root = new BorderPane();
        this.topRow = new HBox(15);
        this.bottomRow = new HBox(15);
        this.playerScore = new ScoreBoard(true);
        this.opponentScore = new ScoreBoard(false);
        this.turnIndicator = new TurnIndicator();
        this.centerPanel = new StackPane();
        buildLayout();
    }

    private void buildLayout() {
        root.setPadding(new Insets(20));
        root.getStyleClass().add("root");

        // Center panel
        centerPanel.getStyleClass().add("center-panel");

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
        root.setBottom(bottomRow);

        // Left panel — player's kazan
        playerScore.getNode().getStyleClass().add("kazan-pane");
        root.setLeft(playerScore.getNode());
        BorderPane.setAlignment(playerScore.getNode(), Pos.CENTER_LEFT);

        // Right panel — opponent's kazan
        opponentScore.getNode().getStyleClass().add("kazan-pane");
        root.setRight(opponentScore.getNode());
        BorderPane.setAlignment(opponentScore.getNode(), Pos.CENTER_RIGHT);

        // Center — turn indicator and buttons
        VBox centerBox = new VBox(15);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.getChildren().add(turnIndicator.getNode());

        Button newGameBtn = new Button("Новая игра");
        newGameBtn.setPrefWidth(140);
        newGameBtn.getStyleClass().add("new-game-button");

        Button backToMenuBtn = new Button("Назад в меню");
        backToMenuBtn.setPrefWidth(140);
        backToMenuBtn.getStyleClass().add("menu-button");

        centerBox.getChildren().addAll(newGameBtn, backToMenuBtn);
        centerPanel.getChildren().add(centerBox);
        root.setCenter(centerPanel);
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
        playerScore.setScore(controller.getKazan(0));
        opponentScore.setScore(controller.getKazan(1));
        turnIndicator.setCurrentPlayer(controller.getCurrentPlayer());
    }

    public BorderPane getRoot() {
        return root;
    }
}