package org.example.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ScoreBoard {
    private final VBox container;
    private final Label player1Score;
    private final Label player2Score;

    public ScoreBoard() {
        container = new VBox(10);
        container.setAlignment(Pos.CENTER);
        container.setPadding(new Insets(10));
        container.getStyleClass().add("score-board");

        player1Score = new Label("0");
        player1Score.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #1a1a1a;");
        player1Score.setPadding(new Insets(5));

        player2Score = new Label("0");
        player2Score.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #1a1a1a;");
        player2Score.setPadding(new Insets(5));

        Label label1 = new Label("Player 1 Kazan:");
        label1.setStyle("-fx-font-size: 14px; -fx-text-fill: #333333;");
        Label label2 = new Label("Player 2 Kazan:");
        label2.setStyle("-fx-font-size: 14px; -fx-text-fill: #333333;");

        HBox player1Box = new HBox(5, label1, player1Score);
        player1Box.setAlignment(Pos.CENTER);
        HBox player2Box = new HBox(5, label2, player2Score);
        player2Box.setAlignment(Pos.CENTER);

        container.getChildren().addAll(player1Box, player2Box);
    }

    public void setScores(int player1, int player2) {
        player1Score.setText(String.valueOf(player1));
        player2Score.setText(String.valueOf(player2));
    }

    public VBox getNode() {
        return container;
    }
}