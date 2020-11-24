package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import sample.mvc.vue.InterfaceUtilisateur;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new InterfaceUtilisateur().getApplication());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
