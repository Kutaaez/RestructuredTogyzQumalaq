package org.example.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ScoreBoard {
    private final StackPane container;
    private final StackPane titleContainer;
    private final Label titleLabel;
    private final GridPane ballsGrid;
    private final StackPane stackedBalls;
    private final Label scoreLabel;

    public ScoreBoard(boolean playerSide) {
        container = new StackPane();
        container.setAlignment(Pos.CENTER);
        container.setPrefSize(160, 120);
        container.setMaxSize(160, 120);
        container.setPadding(new Insets(10));
        container.getStyleClass().add("kazan-pane");

        // Rectangular background
        Rectangle background = new Rectangle(140, 100);
        background.setArcWidth(16);
        background.setArcHeight(16);
        background.setFill(Color.web("#F9F0DF"));
        background.getStyleClass().add("hole-background");

        // Title container
        titleContainer = new StackPane();
        titleContainer.setPrefSize(140, 20);
        titleContainer.getStyleClass().add("title-container");
        titleLabel = new Label(playerSide ? "Ваш казан" : "Казан оппонента");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        titleLabel.setTextFill(Color.BLACK);
        titleContainer.getChildren().add(titleLabel);
        StackPane.setAlignment(titleContainer, Pos.TOP_CENTER);
        StackPane.setMargin(titleContainer, new Insets(5));

        // Balls grid (2x5)
        ballsGrid = new GridPane();
        ballsGrid.setAlignment(Pos.CENTER);
        ballsGrid.setHgap(6);
        ballsGrid.setVgap(6);

        // Stacked balls for counts >= 11
        stackedBalls = new StackPane();
        stackedBalls.setAlignment(Pos.CENTER);

        // Score label
        scoreLabel = new Label("0");
        scoreLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        scoreLabel.setTextFill(Color.BLACK);
        StackPane.setAlignment(scoreLabel, Pos.BOTTOM_CENTER);
        StackPane.setMargin(scoreLabel, new Insets(0, 0, 5, 0));

        container.getChildren().addAll(background, ballsGrid, stackedBalls, titleContainer, scoreLabel);
    }

    public void setScore(int score) {
        scoreLabel.setText(String.valueOf(score));
        updateBalls(score);
    }

    public Node getNode() {
        return container;
    }

    private void updateBalls(int count) {
        ballsGrid.getChildren().clear();
        stackedBalls.getChildren().clear();

        int gridCount = Math.min(count, 10);
        for (int i = 0; i < gridCount; i++) {
            Circle ball = new Circle(10);
            ball.getStyleClass().add("ball");
            int row = i / 5;
            int col = i % 5;
            ballsGrid.add(ball, col, row);
        }
        if (count > 10) {
            for (int i = 10; i < count; i++) {
                Circle ball = new Circle(10);
                ball.getStyleClass().add("ball");
                ball.setTranslateX((i - 10) * 2);
                ball.setTranslateY((i - 10) * 2);
                stackedBalls.getChildren().add(ball);
            }
        }
    }
}