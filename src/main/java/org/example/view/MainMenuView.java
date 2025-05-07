package org.example.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.controller.GameController;

public class MainMenuView {
    private final Stage stage;
    private final VBox root;

    public MainMenuView(Stage stage) {
        this.stage = stage;
        this.root = new VBox(20);
        buildLayout();
    }

    private void buildLayout() {
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f0f0f0;");

        Text title = new Text("Тоғызқұмалақ");
        title.setStyle("-fx-font-size: 32px; -fx-font-weight: bold;");

        Button twoPlayerButton = new Button("Игра вдвоем");
        twoPlayerButton.setPrefWidth(200);
        twoPlayerButton.setStyle(
                "-fx-font-size: 16px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-background-radius: 5; " +
                        "-fx-cursor: hand;"
        );
        twoPlayerButton.setOnAction(e -> startGame(true));

        Button botButton = new Button("Игра против бота");
        botButton.setPrefWidth(200);
        botButton.setStyle(
                "-fx-font-size: 16px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-background-radius: 5; " +
                        "-fx-cursor: hand;"
        );
        botButton.setOnAction(e -> startGame(false));

        root.getChildren().addAll(title, twoPlayerButton, botButton);
    }

    private void startGame(boolean twoPlayers) {
        GameController controller = new GameController(twoPlayers);
        MainView view = new MainView(controller, stage);
        controller.setView(view);
        Scene scene = new Scene(view.getRoot(), 800, 600);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setScene(scene);
    }

    public Scene getScene() {
        return new Scene(root, 800, 600);
    }
}