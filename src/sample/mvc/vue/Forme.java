package sample.mvc.vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import sample.mvc.modele.Matrice;

public class Forme {

    public static Label genererScalaire(String nombre) {
        Label scalaire = new Label(nombre);
        scalaire.setTextFill(Color.rgb(37, 37, 86));
        scalaire.setScaleX(1.5);
        scalaire.setScaleY(1.5);
        scalaire.setMaxWidth(35);
        Tooltip tooltip = new Tooltip(nombre);
        tooltip.setShowDuration(Duration.INDEFINITE);
        scalaire.setTooltip(tooltip);
        return scalaire;
    }

    public static VBox genererCrochetGauche(Matrice matrice) {
        Rectangle dessus = new Rectangle(10, 4, Color.rgb(37, 37, 86));
        Rectangle dessous = new Rectangle(10, 4, Color.rgb(37, 37, 86));
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
        return new Rectangle(4, 35 * matrice.getM(), Color.rgb(37, 37, 86));
    }

    public static StackPane genererAide(Tooltip tooltip) {
        Circle fond = new Circle(12);
        fond.setFill(Color.LIGHTGREY);
        fond.setStroke(Color.rgb(37, 37, 86));
        fond.setStrokeWidth(2);
        fond.setEffect(new BoxBlur(2,2,1));

        Label devant = new Label("?");
        devant.setStyle("-fx-font-family: 'Bauhaus 93';");
        devant.setScaleY(2);
        devant.setScaleX(2);
        devant.setTextFill(Color.rgb(37, 37, 86));
        tooltip.setShowDuration(Duration.INDEFINITE);
        devant.setTooltip(tooltip);
        devant.setPadding(new Insets(3,7,0,0));

        StackPane aide = new StackPane(fond, devant);
        aide.setAlignment(Pos.TOP_RIGHT);
        aide.setPadding(new Insets(10,10,10,90));
        return aide;
    }

    public static VBox genererIndiceEgalite() {
        Rectangle a = new Rectangle(20, 5, Color.rgb(37, 37, 86));
        Rectangle b = new Rectangle(20, 5, Color.rgb(37, 37, 86));
        VBox vBox = new VBox(a, b);
        vBox.setSpacing(1);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

    public static StackPane genererIndiceAddition() {
        Rectangle x = new Rectangle(20, 5, Color.rgb(37, 37, 86));
        Rectangle y = new Rectangle(5, 20, Color.rgb(37, 37, 86));
        StackPane indiceAddition = new StackPane(x, y);
        indiceAddition.setId("addition");
        return indiceAddition;
    }

    public static Rectangle genererIndiceSoustraction() {
        Rectangle indiceSoustraction = new Rectangle(20, 5, Color.rgb(37, 37, 86));
        indiceSoustraction.setId("soustraction");
        return indiceSoustraction;
    }

    public static Circle genererIndiceMultiplication() {
        Circle indiceMultiplication = new Circle(5);
        indiceMultiplication.setFill(Color.rgb(37, 37, 86));
        indiceMultiplication.setId("multiplication");
        return indiceMultiplication;
    }

    public static VBox genererIndicePuissance() {
        ScalaireAffichage indicePuissance = new ScalaireAffichage();
        VBox vBox = new VBox(indicePuissance, new Label());
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(80);
        vBox.setId("puissance");
        return vBox;
    }

    public static VBox genererIndiceTransposition() {
        Label indiceTransposition = new Label("t");
        indiceTransposition.setTextFill(Color.rgb(37, 37, 86));
        indiceTransposition.setScaleY(3);
        indiceTransposition.setScaleX(3);
        VBox vBox = new VBox(indiceTransposition, new Label());
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(80);
        vBox.setId("transpose");
        return vBox;
    }

    public static VBox genererIndiceInverse() {
        Label indiceInverse = new Label(" -1");
        indiceInverse.setTextFill(Color.rgb(37, 37, 86));
        indiceInverse.setScaleY(2);
        indiceInverse.setScaleX(2);
        VBox vBox = new VBox(indiceInverse, new Label());
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(80);
        vBox.setId("inverse");
        return vBox;
    }

    public static Label genererIndiceVectoriel() {
        Label indiceVectoriel = new Label("^");
        indiceVectoriel.setTextFill(Color.rgb(37, 37, 86));
        indiceVectoriel.setScaleY(4);
        indiceVectoriel.setScaleX(4);
        indiceVectoriel.setId("vectoriel");
        return indiceVectoriel;
    }

    public static Label genererIndiceHadamard() {
        Label indiceHadamard = new Label("o");
        indiceHadamard.setTextFill(Color.rgb(37, 37, 86));
        indiceHadamard.setScaleY(2);
        indiceHadamard.setScaleX(2);
        indiceHadamard.setId("hadamard");
        return indiceHadamard;
    }

    public static Label genererIndiceTensoriel() {
        Label indiceTensoriel = new Label("⊗");
        indiceTensoriel.setTextFill(Color.rgb(37, 37, 86));
        indiceTensoriel.setScaleY(5);
        indiceTensoriel.setScaleX(5);
        indiceTensoriel.setId("tensoriel");
        return indiceTensoriel;
    }
}
