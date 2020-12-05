package sample.mvc.vue;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

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
            getChildren().add(Forme.genererScalaire(scalaire.getText()));
            valeur = Integer.parseInt(scalaire.getText());
            valide = true;
        });
        getChildren().add(scalaire);
        setId("scalaire");
    }

    public ScalaireAffichage(String valeur) {
        setAlignment(Pos.CENTER);
        getChildren().add(Forme.genererScalaire(valeur));
        this.valeur = Integer.parseInt(valeur);
        valide = true;
        setId("scalaire");
    }

    public double getValeur() { return valeur; }

    public boolean estValide() { return valide; }
}
