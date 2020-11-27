package sample.mvc.vue;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import sample.mvc.modele.Matrice;

public class InterfaceUtilisateur {

    private Scene application;
    private BorderPane bdp;

    public InterfaceUtilisateur() {
        bdp = new BorderPane();

        Menu operations = new Menu("Opérations");
        Menu fichier = new Menu("Fichier");

        MenuItem addition = new MenuItem("Addition");
        addition.setOnAction(event -> bdp.setCenter(OperationAffichage.addition()));

        MenuItem soustraction = new MenuItem("Soustraction");
        soustraction.setOnAction(event -> bdp.setCenter(OperationAffichage.soustraction()));

        MenuItem multiplication = new MenuItem("multiplication par un scalaire");
        multiplication.setOnAction(event -> bdp.setCenter(OperationAffichage.multiplication()));

        MenuItem puissance = new MenuItem("Puissance");
        puissance.setOnAction(event -> bdp.setCenter(OperationAffichage.puissance()));

        MenuItem transposition = new MenuItem("Transposition");
        transposition.setOnAction(event -> bdp.setCenter(OperationAffichage.transposition()));

        MenuItem inversion = new MenuItem("Inversion");
        inversion.setOnAction(event -> bdp.setCenter(OperationAffichage.inversion()));

        MenuItem produitMatriciel = new MenuItem("Produit Matriciel");
        produitMatriciel.setOnAction(event -> bdp.setCenter(OperationAffichage.produitMatriciel()));

        MenuItem produitVectoriel = new MenuItem("Produit Vectoriel");
        produitVectoriel.setOnAction(event -> bdp.setCenter(OperationAffichage.produitVectoriel()));

        MenuItem produitHadamard = new MenuItem("Produit d'Hadamard");
        produitHadamard.setOnAction(event -> bdp.setCenter(OperationAffichage.produitHadamard()));

        MenuItem produitTensoriel = new MenuItem("Produit Tensoriel");
        produitTensoriel.setOnAction(event -> bdp.setCenter(OperationAffichage.produitTensoriel()));

        MenuItem determinant = new MenuItem("Calcul du déterminant");
        determinant.setOnAction(event -> bdp.setCenter(OperationAffichage.determinant()));

        operations.getItems().addAll(addition, soustraction, multiplication, puissance,
                transposition, inversion, produitMatriciel, produitVectoriel,
                produitHadamard, produitTensoriel, determinant);
        MenuBar menuBar = new MenuBar(operations, fichier);
        bdp.setTop(menuBar);

        application = new Scene(bdp, 1000, 600);
    }

    public Scene getApplication() { return application; }
}
