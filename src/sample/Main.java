package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.mvc.modele.Matrice;
import sample.mvc.vue.Affichage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(Affichage.genererMatrice(new Matrice(3, 3)), 1000, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
