package sample.mvc.vue;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class InterfaceUtilisateur extends BorderPane{

    private Scene application;
    private HBox informations;

    public InterfaceUtilisateur() {

        Menu operations = new Menu("Opérations");
        Menu fichier = new Menu("Fichier");

        MenuItem libre = new MenuItem("Libre");
        libre.setOnAction(event -> OperationAffichage.libre(this));

        MenuItem addition = new MenuItem("Addition");
        addition.setOnAction(event -> OperationAffichage.addition(this));

        MenuItem soustraction = new MenuItem("Soustraction");
        soustraction.setOnAction(event -> setCenter(OperationAffichage.soustraction(this)));

        MenuItem multiplication = new MenuItem("multiplication par un scalaire");
        multiplication.setOnAction(event -> setCenter(OperationAffichage.multiplication(this)));

        MenuItem puissance = new MenuItem("Puissance");
        puissance.setOnAction(event -> setCenter(OperationAffichage.puissance(this)));

        MenuItem transposition = new MenuItem("Transposition");
        transposition.setOnAction(event -> setCenter(OperationAffichage.transposition(this)));

        MenuItem inversion = new MenuItem("Inversion");
        inversion.setOnAction(event -> setCenter(OperationAffichage.inversion(this)));

        MenuItem produitMatriciel = new MenuItem("Produit Matriciel");
        produitMatriciel.setOnAction(event -> setCenter(OperationAffichage.produitMatriciel(this)));

        MenuItem produitVectoriel = new MenuItem("Produit Vectoriel");
        produitVectoriel.setOnAction(event -> setCenter(OperationAffichage.produitVectoriel(this)));

        MenuItem produitHadamard = new MenuItem("Produit d'Hadamard");
        produitHadamard.setOnAction(event -> setCenter(OperationAffichage.produitHadamard(this)));

        MenuItem produitTensoriel = new MenuItem("Produit Tensoriel");
        produitTensoriel.setOnAction(event -> setCenter(OperationAffichage.produitTensoriel(this)));

        MenuItem determinant = new MenuItem("Calcul du déterminant");
        determinant.setOnAction(event -> setCenter(OperationAffichage.determinant(this)));

        operations.getItems().addAll(libre, addition, soustraction, multiplication, puissance,
                transposition, inversion, produitMatriciel, produitVectoriel,
                produitHadamard, produitTensoriel, determinant);
        MenuBar menuBar = new MenuBar(operations, fichier);

        //information
        Label information = new Label("");
        informations = new HBox(information);
        informations.setAlignment(Pos.CENTER);
        informations.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));

        //finalisation
        setTop(menuBar);
        setBottom(informations);
        application = new Scene(this, 1000, 600);
    }

    public Scene getApplication() { return application; }

    public void setMessage(String message, String type) {
        Label information = new Label(message);
        if (type.equals("informative"))
            information.setTextFill(Color.GREEN);
        if (type.equals("erreur"))
            information.setTextFill(Color.RED);
        informations.getChildren().set(0, information);

        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5000),
                new KeyValue(informations.getChildren().get(0).visibleProperty(), false)));
        timeline.play();
    }
}
