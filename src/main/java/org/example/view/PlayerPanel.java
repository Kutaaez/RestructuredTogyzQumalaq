package org.example.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PlayerPanel {
    private final VBox container;
    private final Label player1ScoreLabel;
    private final Label player2ScoreLabel;
    private final Label currentPlayerLabel;

    public PlayerPanel() {
        container = new VBox(10);
        container.setAlignment(Pos.TOP_CENTER);
        container.setPadding(new Insets(10));
        container.setPrefWidth(200);
        container.setMaxWidth(200);
        container.getStyleClass().add("player-panel");

        Label title = new Label("Игроки");
        title.setFont(Font.font("System", FontWeight.BOLD, 16));
        title.setTextFill(Color.WHITE);

        player1ScoreLabel = new Label("Player1: 0");
        player1ScoreLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        player1ScoreLabel.getStyleClass().add("score-label");

        player2ScoreLabel = new Label("Player2: 0");
        player2ScoreLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        player2ScoreLabel.getStyleClass().add("score-label");

        currentPlayerLabel = new Label("Текущий: Player1");
        currentPlayerLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        currentPlayerLabel.getStyleClass().add("current-player");

        container.getChildren().addAll(title, player1ScoreLabel, player2ScoreLabel, currentPlayerLabel);
    }

    public void setScores(int player1Score, int player2Score) {
        player1ScoreLabel.setText("Player1: " + player1Score);
        player2ScoreLabel.setText("Player2: " + player2Score);
    }

    public void setCurrentPlayer(int player) {
        currentPlayerLabel.setText("Текущий: " + (player == 0 ? "Player1" : "Player2"));
        if (player == 0) {
            currentPlayerLabel.getStyleClass().remove("opponent-turn");
            currentPlayerLabel.getStyleClass().add("player-turn");
        } else {
            currentPlayerLabel.getStyleClass().remove("player-turn");
            currentPlayerLabel.getStyleClass().add("opponent-turn");
        }
    }

    public VBox getNode() {
        return container;
    }
}