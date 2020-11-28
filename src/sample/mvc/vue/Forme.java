package sample.mvc.vue;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.mvc.modele.Matrice;

public class Forme {

    public static Label genererScalaire(String nombre) {
        Label scalaire = new Label(nombre);
        scalaire.setTextFill(Color.GREY);
        scalaire.setScaleX(1.5);
        scalaire.setScaleY(1.5);
        return scalaire;
    }

    public static VBox genererCrochetGauche(Matrice matrice) {
        Rectangle dessus = new Rectangle(10, 4, Color.GREY);
        Rectangle dessous = new Rectangle(10, 4, Color.GREY);
        VBox vBox = new VBox(dessus, genererBordure(matrice), dessous);
        vBox.setAlignment(Pos.CENTER_LEFT);
        return vBox;
    }

    public static VBox genererCrochetDroite(Matrice matrice) {
        VBox vBox = genererCrochetGauche(matrice);
        vBox.setAlignment(Pos.CENTER_RIGHT);
        return vBox;
    }

    public static Rectangle genererBordure(Matrice matrice) {
        return new Rectangle(4, 30 * matrice.getM(), Color.GREY);
    }

    public static StackPane genererIndiceAddition() {
        Rectangle x = new Rectangle(20, 5, Color.GREY);
        Rectangle y = new Rectangle(5, 20, Color.GREY);
        return new StackPane(x, y);
    }

    public static Rectangle genererIndiceSoustraction() {
        return new Rectangle(20, 5, Color.GREY);
    }

    public static VBox genererIndicePuissance() {
        Label indicePuissance = new Label("K");
        indicePuissance.setTextFill(Color.GREY);
        indicePuissance.setScaleY(3);
        indicePuissance.setScaleX(3);
        VBox vBox = new VBox(indicePuissance, new Label());
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(80);
        return vBox;
    }

    public static VBox genererIndiceTransposition() {
        Label indiceTransposition = new Label("t");
        indiceTransposition.setTextFill(Color.GREY);
        indiceTransposition.setScaleY(3);
        indiceTransposition.setScaleX(3);
        VBox vBox = new VBox(indiceTransposition, new Label());
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(80);
        return vBox;
    }

    public static VBox genererIndiceInverse() {
        Label indiceInverse = new Label(" -1");
        indiceInverse.setTextFill(Color.GREY);
        indiceInverse.setScaleY(3);
        indiceInverse.setScaleX(3);
        VBox vBox = new VBox(indiceInverse, new Label());
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(80);
        return vBox;
    }

    public static Label genererIndiceVectoriel() {
        Label indiceVectoriel = new Label("^");
        indiceVectoriel.setTextFill(Color.GREY);
        indiceVectoriel.setScaleY(4);
        indiceVectoriel.setScaleX(4);
        return indiceVectoriel;
    }

    public static Label genererIndiceHadamard() {
        Label indiceHadamard = new Label("*");
        indiceHadamard.setTextFill(Color.GREY);
        indiceHadamard.setScaleY(4);
        indiceHadamard.setScaleX(4);
        return indiceHadamard;
    }

    public static Label genererIndiceTensoriel() {
        Label indiceTensoriel = new Label("⊗");
        indiceTensoriel.setTextFill(Color.GREY);
        indiceTensoriel.setScaleY(5);
        indiceTensoriel.setScaleX(5);
        return indiceTensoriel;
    }
}
