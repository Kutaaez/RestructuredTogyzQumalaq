package org.example.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.controller.GameController;

public class MainView {
    private final GameController controller;
    private final BorderPane root;
    private final HBox topRow;
    private final HBox bottomRow;
    private final ScoreBoard playerScore;
    private final ScoreBoard opponentScore;
    private final TurnIndicator turnIndicator;

    public MainView(GameController controller) {
        this.controller = controller;
        this.root = new BorderPane();
        this.topRow = new HBox(10);
        this.bottomRow = new HBox(10);
        this.playerScore = new ScoreBoard(true);
        this.opponentScore = new ScoreBoard(false);
        this.turnIndicator = new TurnIndicator();
        buildLayout();
    }

    private void buildLayout() {
        root.setPadding(new Insets(20));
        // Верхняя строка — луночки оппонента
        topRow.setAlignment(Pos.CENTER);
        for (int i = 1; i <= 9; i++) {
            HoleButton btn = new HoleButton(i, false);
            btn.setOnAction(e -> controller.onHoleClicked(btn.getHoleIndex(), false));
            topRow.getChildren().add(btn);
        }
        root.setTop(topRow);

        // Нижняя строка — луночки игрока
        bottomRow.setAlignment(Pos.CENTER);
        for (int i = 1; i <= 9; i++) {
            HoleButton btn = new HoleButton(i, true);
            btn.setOnAction(e -> controller.onHoleClicked(btn.getHoleIndex(), true));
            bottomRow.getChildren().add(btn);
        }
        root.setBottom(bottomRow);

        // Левая панель — ваш казан
        root.setLeft(playerScore.getNode());
        BorderPane.setAlignment(playerScore.getNode(), Pos.CENTER_LEFT);

        // Правая панель — казан оппонента
        root.setRight(opponentScore.getNode());
        BorderPane.setAlignment(opponentScore.getNode(), Pos.CENTER_RIGHT);

        // Центр — индикатор хода и кнопка «Новая игра»
        VBox centerBox = new VBox(15);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.getChildren().add(turnIndicator.getNode());

        Button newGameBtn = new Button("Новая игра");
        newGameBtn.setPrefWidth(140);
        newGameBtn.setStyle(
                "-fx-font-size: 14px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-background-radius: 5; " +
                        "-fx-cursor: hand;"
        );
        newGameBtn.setOnAction(e -> controller.onNewGame());
        centerBox.getChildren().add(newGameBtn);

        root.setCenter(centerBox);
    }

    public void update() {
        int currentPlayer = controller.getCurrentPlayer();
        // Обновляем луночки оппонента
        for (Node node : topRow.getChildren()) {
            HoleButton hb = (HoleButton) node;
            hb.setCount(controller.getOpponentHoleCount(hb.getHoleIndex()));
            hb.setTuzdyk(controller.getTuzdyk(1) == hb.getHoleIndex());
            hb.setDisable(currentPlayer != 1); // Активны только при ходе оппонента
        }
        // Обновляем луночки игрока
        for (Node node : bottomRow.getChildren()) {
            HoleButton hb = (HoleButton) node;
            hb.setCount(controller.getHoleCount(hb.getHoleIndex()));
            hb.setTuzdyk(controller.getTuzdyk(0) == hb.getHoleIndex());
            hb.setDisable(currentPlayer != 0); // Активны только при ходе игрока
        }
        // Обновляем казаны
        playerScore.setScore(controller.getKazan(0));
        opponentScore.setScore(controller.getKazan(1));
        // Обновляем индикатор хода
        turnIndicator.setCurrentPlayer(controller.getCurrentPlayer());
    }

    public BorderPane getRoot() {
        return root;
    }
}