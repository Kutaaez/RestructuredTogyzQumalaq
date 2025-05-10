package org.example;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.view.MainMenuView;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Toguz Kumalak");
        MainMenuView menu = new MainMenuView(primaryStage);
        primaryStage.setScene(menu.getScene());
        primaryStage.show();
    }
}
