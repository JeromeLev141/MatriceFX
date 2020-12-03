package sample.mvc.vue;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ScalaireAffichage extends HBox {

    private int valeur;

    public ScalaireAffichage() {
        setAlignment(Pos.CENTER);
        TextField scalaire = new TextField();
        scalaire.setPromptText("K");
        scalaire.setPrefColumnCount(2);
        scalaire.setOnAction(event -> {
            getChildren().remove(scalaire);
            getChildren().add(Forme.genererScalaire(scalaire.getText()));
            valeur = Integer.parseInt(scalaire.getText());
        });
        getChildren().add(scalaire);
        setId("scalaire");
    }

    public ScalaireAffichage(String valeur) {
        setAlignment(Pos.CENTER);
        getChildren().add(Forme.genererScalaire(valeur));
        this.valeur = Integer.parseInt(valeur);
        setId("scalaire");
    }

    public int getValeur() { return valeur; }
}
