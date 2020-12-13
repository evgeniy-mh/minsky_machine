package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        fxmlLoader.setController(new Controller());
        HBox hBox = fxmlLoader.load();
        primaryStage.setScene(new Scene(hBox));
        primaryStage.setTitle("Эмулятор машины Минского 2сМ");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
