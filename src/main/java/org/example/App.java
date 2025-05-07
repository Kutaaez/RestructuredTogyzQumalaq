package org.example;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.view.MainMenuView;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        MainMenuView menuView = new MainMenuView(primaryStage);
        primaryStage.setTitle("Тоғызқұмалақ");
        primaryStage.setScene(menuView.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}