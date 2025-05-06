package org.example.view;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HoleButton extends Button {
    private final int holeIndex;
    private final boolean playerSide;

    public HoleButton(int holeIndex, boolean playerSide) {
        super();
        this.holeIndex = holeIndex;
        this.playerSide = playerSide;

        setPrefSize(60, 60);
        setFont(Font.font(16));
        String bgColor = playerSide ? "#ffe4b5" : "#add8e6";
        setStyle(
                "-fx-background-radius: 30; " +
                        "-fx-background-color: " + bgColor + "; " +
                        "-fx-cursor: hand;"
        );

        setEffect(new DropShadow(5, Color.GRAY));

        addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            if (!isDisabled()) {
                setScaleX(0.9);
                setScaleY(0.9);
            }
        });
        addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
            if (!isDisabled()) {
                setScaleX(1.0);
                setScaleY(1.0);
            }
        });
    }

    public int getHoleIndex() {
        return holeIndex;
    }

    public void setCount(int count) {
        setText(String.valueOf(count));
    }

    public void setTuzdyk(boolean isTuz) {
        String baseStyle = "-fx-background-radius: 30; " +
                "-fx-background-color: " + (playerSide ? "#ffe4b5" : "#add8e6") + "; " +
                "-fx-cursor: hand;";
        if (isTuz) {
            setStyle(baseStyle +
                    "-fx-border-color: red; " +
                    "-fx-border-width: 3;"
            );
        } else {
            setStyle(baseStyle);
        }
    }
}