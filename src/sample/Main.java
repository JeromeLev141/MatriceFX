package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.mvc.vue.InterfaceUtilisateur;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("MatriceFX");
        primaryStage.setScene(new InterfaceUtilisateur(primaryStage).getApplication());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
