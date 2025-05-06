package org.example;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.controller.GameController;
import org.example.view.MainView;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Создаём контроллер и View, связываем их
        GameController controller = new GameController();
        MainView view = new     MainView(controller);
        controller.setView(view);

        // Собираем сцену
        Scene scene = new Scene(view.getRoot(), 800, 600);
        // Подключаем стили (если есть styles.css в resources)
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        // Настраиваем и показываем окно
        primaryStage.setTitle("Тоғызқұмалақ");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}