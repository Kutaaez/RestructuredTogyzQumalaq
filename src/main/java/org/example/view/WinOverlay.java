package org.example.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.example.controller.GameController;
import javafx.stage.Stage;
public class WinOverlay extends StackPane {
    private final Label resultLabel;
    private final Button newGameButton;
    private final Button mainMenuButton;

    public WinOverlay(GameController controller, Stage stage) {
        // Полупрозрачный фон, растягивается на весь родитель
        setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);");
        setVisible(false); // Изначально скрыт
        setAlignment(Pos.CENTER);  // Ensure it is centered

        // Контейнер для содержимого
        VBox content = new VBox(20);
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(20));
        content.setStyle("-fx-background-color: #F9F0DF; -fx-background-radius: 10; -fx-padding: 20;");
        content.setMaxWidth(300); // Ограничение ширины
        content.setPrefWidth(300);

        // Метка результата
        resultLabel = new Label();
        resultLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #1a1a1a; -fx-wrap-text: true; -fx-alignment: center;");
        resultLabel.setMaxWidth(260); // Ограничение ширины текста

        // Кнопка "Начать новую игру"
        newGameButton = new Button("Start New Game");
        newGameButton.setPrefWidth(150);
        newGameButton.setStyle("-fx-font-size: 14px; -fx-background-color: #8B4513; -fx-text-fill: white; -fx-background-radius: 5;");
        newGameButton.setOnAction(e -> {
            controller.onNewGame();
            setVisible(false);
        });

        // Кнопка "На главную"
        mainMenuButton = new Button("Main Menu");
        mainMenuButton.setPrefWidth(150);
        mainMenuButton.setStyle("-fx-font-size: 14px; -fx-background-color: #8B4513; -fx-text-fill: white; -fx-background-radius: 5;");
        mainMenuButton.setOnAction(e -> {
            MainMenuView menuView = new MainMenuView(stage);
            stage.setScene(menuView.getScene());
            setVisible(false);
        });

        content.getChildren().addAll(resultLabel, newGameButton, mainMenuButton);
        getChildren().add(content);
    }

    public void showResult(int result) {
        if (result == 1) {
            resultLabel.setText("Player 1 Wins!");
        } else if (result == -1) {
            resultLabel.setText("Player 2 Wins!");
        } else if (result == 0) {
            resultLabel.setText("Draw!");
        }
        setVisible(true);
    }
}
