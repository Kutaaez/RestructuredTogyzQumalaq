package org.example.view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ScoreBoard {
    private final VBox container;
    private final Label titleLabel;
    private final Label scoreLabel;

    /**
     * @param playerSide true — ваша панель, false — панель оппонента
     */
    public ScoreBoard(boolean playerSide) {
        container = new VBox(5);
        container.setAlignment(Pos.CENTER_LEFT);
        container.setStyle("-fx-padding: 10;");

        titleLabel = new Label(playerSide ? "Ваш казан" : "Казан оппонента");
        titleLabel.setStyle(
                "-fx-font-size: 14px; " +
                        "-fx-font-weight: bold;"
        );

        scoreLabel = new Label("0");
        scoreLabel.setStyle("-fx-font-size: 18px;");

        container.getChildren().addAll(titleLabel, scoreLabel);
    }

    /** Обновить отображаемый счёт */
    public void setScore(int score) {
        scoreLabel.setText(String.valueOf(score));
    }

    public Node getNode() {
        return container;
    }
}
