package org.example.view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class TurnIndicator {
    private final HBox container;
    private final Label label;

    public TurnIndicator() {
        container = new HBox();
        container.setAlignment(Pos.CENTER);
        container.setStyle("-fx-padding: 10;");

        label = new Label("Ход: Ваш");
        label.setStyle(
                "-fx-font-size: 16px; " +
                        "-fx-font-weight: bold;"
        );

        container.getChildren().add(label);
    }

    /** @param player 0 — ваш ход, 1 — ход оппонента */
    public void setCurrentPlayer(int player) {
        if (player == 0) {
            label.setText("Ход: Ваш");
        } else {
            label.setText("Ход: Оппонент");
        }
    }

    public Node getNode() {
        return container;
    }
}
