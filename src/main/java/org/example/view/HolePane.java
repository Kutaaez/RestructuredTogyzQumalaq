package org.example.view;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class HolePane extends Pane {
    private final int holeIndex;
    private final boolean playerSide;
    private final StackPane container;
    private final Rectangle background;
    private final GridPane ballsGrid;
    private final StackPane stackedBalls;
    private final Label countLabel;

    public HolePane(int holeIndex, boolean playerSide) {
        this.holeIndex = holeIndex;
        this.playerSide = playerSide;

        // Main container
        container = new StackPane();
        container.setPrefSize(140, 120);
        container.setMaxSize(140, 120);
        container.setPadding(new Insets(10));
        container.getStyleClass().add("hole-pane");
        if (playerSide) {
            container.getStyleClass().add("player");
        } else {
            container.getStyleClass().add("opponent");
        }

        // Rectangular background
        background = new Rectangle(120, 100);
        background.setArcWidth(16);
        background.setArcHeight(16);
        background.setFill(Color.web("#F9F0DF"));
        background.getStyleClass().add("hole-background");

        // Balls grid (2x5)
        ballsGrid = new GridPane();
        ballsGrid.setAlignment(Pos.CENTER);
        ballsGrid.setHgap(6);
        ballsGrid.setVgap(6);

        // Stacked balls for counts >= 11
        stackedBalls = new StackPane();
        stackedBalls.setAlignment(Pos.CENTER);

        // Count label
        countLabel = new Label("0");
        countLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        countLabel.setTextFill(Color.BLACK);
        StackPane.setAlignment(countLabel, Pos.BOTTOM_CENTER);
        StackPane.setMargin(countLabel, new Insets(0, 0, 5, 0));

        // Assemble
        container.getChildren().addAll(background, ballsGrid, stackedBalls, countLabel);
        getChildren().add(container);

        // Click animation
        setOnMousePressed(this::handleMousePressed);
        setOnMouseReleased(this::handleMouseReleased);
    }

    public int getHoleIndex() {
        return holeIndex;
    }

    public void setCount(int count) {
        countLabel.setText(String.valueOf(count));
        updateBalls(count);
    }

    public void setTuzdyk(boolean isTuz) {
        if (isTuz) {
            container.getStyleClass().add("red-tuzdyk");
        } else {
            container.getStyleClass().remove("red-tuzdyk");
        }
    }

//    public void setDisable(boolean disable) {
//        setDisable(disable);
//        container.setOpacity(disable ? 0.6 : 1.0);
//    }

    private void updateBalls(int count) {
        ballsGrid.getChildren().clear();
        stackedBalls.getChildren().clear();

        if (count == 255) {
            Circle redBall = new Circle(10);
            redBall.getStyleClass().add("red-ball");
            ballsGrid.add(redBall, 0, 0);
        } else {
            int gridCount = Math.min(count, 10);
            for (int i = 0; i < gridCount; i++) {
                Circle ball = new Circle(10);
                ball.getStyleClass().add("ball");
                int row = i / 5;
                int col = i % 5;
                ballsGrid.add(ball, col, row);
            }
            if (count > 10) {
                int remainingCount = count - 10;  // Количество оставшихся шаров
                for (int i = 0; i < remainingCount; i++) {
                    Circle ball = new Circle(10);
                    ball.getStyleClass().add("ball");

                    // Смещение по осям X и Y
                    double translateX = i * 2;  // Смещение по оси X (по столбцам)

                    // Небольшое наложение
                    translateX += (i % 5) * 2;  // Дополнительное смещение для наложения

                    // Добавляем шар в сетку и применяем смещение
                    ballsGrid.add(ball, i % 5, i / 5);
                    ball.setTranslateX(translateX);  // Применяем смещение по X
                }
            }

        }
    }

    private void handleMousePressed(MouseEvent event) {
        if (!isDisabled()) {
            ScaleTransition scale = new ScaleTransition(Duration.millis(10), ballsGrid);
            scale.setToX(1.1);
            scale.setToY(1.1);
            scale.play();
            ScaleTransition scaleStacked = new ScaleTransition(Duration.millis(10), stackedBalls);
            scaleStacked.setToX(1.1);
            scaleStacked.setToY(1.1);
            scaleStacked.play();
        }
    }

    private void handleMouseReleased(MouseEvent event) {
        if (!isDisabled()) {
            ScaleTransition scale = new ScaleTransition(Duration.millis(10), ballsGrid);
            scale.setToX(1.0);
            scale.setToY(1.0);
            scale.play();
            ScaleTransition scaleStacked = new ScaleTransition(Duration.millis(10), stackedBalls);
            scaleStacked.setToX(1.0);
            scaleStacked.setToY(1.0);
            scaleStacked.play();
        }
    }
}