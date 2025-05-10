package org.example.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.controller.GameController;

/**
 * The main menu view for the Toguz Kumalak game, allowing users to select game modes.
 */
public class MainMenuView {
    private final Stage stage;
    private final VBox root;

    /**
     * Constructs the main menu view.
     *
     * @param stage The primary stage for the application.
     */
    public MainMenuView(Stage stage) {
        this.stage = stage;
        this.root = new VBox(20);
        buildLayout();
    }

    /**
     * Builds the layout of the main menu with a title and buttons for game modes.
     */
    private void buildLayout() {
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f0f0f0;");

        Text title = new Text("Тоғызқұмалақ");
        title.setStyle("-fx-font-size: 32px; -fx-font-weight: bold;");

        Button twoPlayerButton = new Button("Игра вдвоем");
        twoPlayerButton.setPrefWidth(200);
        twoPlayerButton.getStyleClass().add("menu-button");
        twoPlayerButton.setOnAction(e -> startGame(true));

        Button botButton = new Button("Игра против бота");
        botButton.setPrefWidth(200);
        botButton.getStyleClass().add("menu-button");
        botButton.setOnAction(e -> startGame(false));

        root.getChildren().addAll(title, twoPlayerButton, botButton);
    }

    /**
     * Starts a new game with the specified mode (two players or against bot).
     *
     * @param twoPlayers True for two-player mode, false for bot mode.
     */
    private void startGame(boolean twoPlayers) {
        try {
            // Create a new controller and view
            GameController controller = new GameController(twoPlayers);
            MainView view = new MainView(controller, stage);
            controller.setView(view);

            // Create a new scene with a fresh root
            Scene scene = new Scene(view.getRoot(), 1200, 700);
            scene.getStylesheets().add(getStylesheet());
            stage.setScene(scene);
            stage.setMinWidth(1200);
            stage.setMinHeight(700);
            stage.sizeToScene();
            stage.centerOnScreen();
        } catch (Exception e) {
            System.err.println("Failed to start game: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the path to the CSS stylesheet.
     *
     * @return The external form of the CSS resource.
     * @throws IllegalStateException if the stylesheet is not found.
     */
    private String getStylesheet() {
        String cssPath = getClass().getResource("/styles.css") != null
                ? getClass().getResource("/styles.css").toExternalForm()
                : "";
        if (cssPath.isEmpty()) {
            throw new IllegalStateException("Stylesheet /styles.css not found.");
        }
        return cssPath;
    }

    /**
     * Returns the scene for the main menu.
     *
     * @return The configured scene.
     */
    public Scene getScene() {
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getStylesheet());
        return scene;
    }
}