package sample.mvc.vue;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import sample.mvc.controlleur.Operation;

public class ScalaireAffichage extends HBox {

    private double valeur;
    private boolean valide;

    public ScalaireAffichage() {
        setAlignment(Pos.CENTER);
        valide = false;
        TextField scalaire = new TextField();
        scalaire.setPromptText("K");
        scalaire.setPrefColumnCount(2);
        scalaire.setOnAction(event -> {
            getChildren().remove(scalaire);
            valeur = Double.parseDouble(scalaire.getText());
            getChildren().add(Forme.genererScalaire(Operation.doubleAFraction(this.valeur)));
            valide = true;
        });
        getChildren().add(scalaire);
        setId("scalaire");
    }

    public ScalaireAffichage(String valeur) {
        setAlignment(Pos.CENTER);
        this.valeur = Double.parseDouble(valeur);
        getChildren().add(Forme.genererScalaire(Operation.doubleAFraction(this.valeur)));
        valide = true;
        setId("scalaire");
    }

    public double getValeur() { return valeur; }

    public boolean estValide() { return valide; }
}
