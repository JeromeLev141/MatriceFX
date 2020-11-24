package sample.mvc.vue;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class InterfaceUtilisateur {

    private Scene application;
    private BorderPane bdp;

    public InterfaceUtilisateur() {
        bdp = new BorderPane();

        Menu operations = new Menu("Opérations");
        Menu fichier = new Menu("Fichier");

        MenuItem addition = new MenuItem("Addition");
        MenuItem soustraction = new MenuItem("Soustraction");
        MenuItem multiplication = new MenuItem("multiplication par un scalaire");
        MenuItem transposition = new MenuItem("Transposition");
        MenuItem produitVectoriel = new MenuItem("Produit Vectoriel");
        MenuItem determinant = new MenuItem("Calcul du déterminant");

        operations.getItems().addAll(addition, soustraction, multiplication, transposition,
                produitVectoriel, determinant);
        MenuBar menuBar = new MenuBar(operations, fichier);
        bdp.setTop(menuBar);

        application = new Scene(bdp, 1000, 600);
    }

    public Scene getApplication() { return application; }
}
