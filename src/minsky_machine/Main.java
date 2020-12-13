package minsky_machine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("app.fxml"));
        fxmlLoader.setController(new Controller(primaryStage));
        VBox vBox = fxmlLoader.load();
        primaryStage.setScene(new Scene(vBox));
        primaryStage.setTitle("Эмулятор машины Минского 2сМ");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
