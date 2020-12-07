package sample.mvc.vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import sample.mvc.modele.Matrice;

public class Forme {

    public static Label genererScalaire(String nombre) {
        Label scalaire = new Label(nombre);
        scalaire.setTextFill(Color.GREY);
        scalaire.setScaleX(1.5);
        scalaire.setScaleY(1.5);
        scalaire.setMaxWidth(30);
        Tooltip tooltip = new Tooltip(nombre);
        scalaire.setTooltip(tooltip);
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
        return new Rectangle(4, 35 * matrice.getM(), Color.GREY);
    }

    public static StackPane genererAide(Tooltip tooltip) {
        Circle fond = new Circle(12);
        fond.setFill(Color.LIGHTGREY);
        fond.setStroke(Color.GREY);

        Label devant = new Label("?");
        devant.setScaleY(2);
        devant.setScaleX(2);
        devant.setTextFill(Color.GREY);
        devant.setTooltip(tooltip);
        devant.setPadding(new Insets(3,6,0,0));

        StackPane aide = new StackPane(fond, devant);
        aide.setAlignment(Pos.TOP_RIGHT);
        aide.setPadding(new Insets(10,10,10,10));
        return aide;
    }

    public static StackPane genererIndiceAddition() {
        Rectangle x = new Rectangle(20, 5, Color.GREY);
        Rectangle y = new Rectangle(5, 20, Color.GREY);
        StackPane indiceAddition = new StackPane(x, y);
        indiceAddition.setId("addition");
        return indiceAddition;
    }

    public static Rectangle genererIndiceSoustraction() {
        Rectangle indiceSoustraction = new Rectangle(20, 5, Color.GREY);
        indiceSoustraction.setId("soustraction");
        return indiceSoustraction;
    }

    public static Circle genererIndiceMultiplication() {
        Circle indiceMultiplication = new Circle(5);
        indiceMultiplication.setFill(Color.GREY);
        indiceMultiplication.setId("multiplication");
        return indiceMultiplication;
    }

    public static VBox genererIndicePuissance() {
        ScalaireAffichage indicePuissance = new ScalaireAffichage();
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
        indiceInverse.setScaleY(2);
        indiceInverse.setScaleX(2);
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
        Label indiceHadamard = new Label("o");
        indiceHadamard.setTextFill(Color.GREY);
        indiceHadamard.setScaleY(2);
        indiceHadamard.setScaleX(2);
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
