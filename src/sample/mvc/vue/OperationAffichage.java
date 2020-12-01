package sample.mvc.vue;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import sample.mvc.controlleur.Operation;
import sample.mvc.modele.Matrice;

public class OperationAffichage {

    public static HBox addition(BorderPane bdp) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3));

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            MatriceAffichage resultat = new MatriceAffichage(Operation.addition(a.getMatrice(), b.getMatrice()));
            bdp.setCenter(resultat.afficherMatrice());
        });

        HBox hbox = new HBox(a.afficherMatrice(), Forme.genererIndiceAddition(), b.afficherMatrice(), egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        return hbox;
    }

    public static HBox soustraction(BorderPane bdp) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3));

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            MatriceAffichage resultat = new MatriceAffichage(Operation.soustraction(a.getMatrice(), b.getMatrice()));
            bdp.setCenter(resultat.afficherMatrice());
        });

        HBox hbox = new HBox(a.afficherMatrice(), Forme.genererIndiceSoustraction(), b.afficherMatrice(), egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        return hbox;
    }

    public static HBox multiplication(BorderPane bdp) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));
        TextField scalaire = new TextField();
        scalaire.setPromptText("K");
        scalaire.setPrefColumnCount(2);

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            MatriceAffichage resultat = new MatriceAffichage(Operation.multiplication(a.getMatrice(), Double.parseDouble(scalaire.getText())));
            bdp.setCenter(resultat.afficherMatrice());
        });

        HBox hbox = new HBox(scalaire, a.afficherMatrice(), egale);

        scalaire.setOnAction(event -> {
            hbox.getChildren().remove(scalaire);
            hbox.getChildren().add(0, Forme.genererScalaire(scalaire.getText()));
        });

        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(30);
        return hbox;
    }

    public static HBox puissance(BorderPane bdp) {

        //marche pas

        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));
        TextField indicePuissance = new TextField();
        indicePuissance.setPromptText("N");
        indicePuissance.setPrefColumnCount(2);
        VBox vBox = new VBox(indicePuissance, new Label());
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(80);

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            MatriceAffichage resultat = new MatriceAffichage(Operation.puissance(a.getMatrice(), Integer.parseInt(indicePuissance.getText())));
            bdp.setCenter(resultat.afficherMatrice());
        });

        HBox hbox = new HBox(a.afficherMatrice(), vBox, egale);

        indicePuissance.setOnAction(event -> {
            vBox.getChildren().remove(indicePuissance);
            vBox.getChildren().add(0, Forme.genererScalaire(indicePuissance.getText()));
        });

        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        return hbox;
    }

    public static HBox transposition(BorderPane bdp) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            MatriceAffichage resultat = new MatriceAffichage(Operation.transposition(a.getMatrice()));
            bdp.setCenter(resultat.afficherMatrice());
        });

        HBox hbox = new HBox(a.afficherMatrice(), Forme.genererIndiceTransposition(), egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        return hbox;
    }

    public static HBox inversion(BorderPane bdp) {

        //marche pas

        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            MatriceAffichage resultat = new MatriceAffichage(Operation.inverse(a.getMatrice()));
            bdp.setCenter(resultat.afficherMatrice());
        });

        HBox hbox = new HBox(a.afficherMatrice(), Forme.genererIndiceInverse(), egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        return hbox;
    }

    public static HBox produitMatriciel(BorderPane bdp) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3));

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            MatriceAffichage resultat = new MatriceAffichage(Operation.produitMatriciel(a.getMatrice(), b.getMatrice()));
            bdp.setCenter(resultat.afficherMatrice());
        });

        HBox hbox = new HBox(a.afficherMatrice(), b.afficherMatrice(), egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        return hbox;
    }

    public static HBox produitVectoriel(BorderPane bdp) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3));

        HBox hbox = new HBox(a.afficherMatrice(), Forme.genererIndiceVectoriel(), b.afficherMatrice());
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        return hbox;
    }

    public static HBox produitHadamard(BorderPane bdp) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3));

        HBox hbox = new HBox(a.afficherMatrice(), Forme.genererIndiceHadamard(), b.afficherMatrice());
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        return hbox;
    }

    public static HBox produitTensoriel(BorderPane bdp) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3));

        HBox hbox = new HBox(a.afficherMatrice(), Forme.genererIndiceTensoriel(), b.afficherMatrice());
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        return hbox;
    }

    public static HBox determinant(BorderPane bdp) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));

        HBox hbox = new HBox(Forme.genererBordure(a.getMatrice()), a.afficherMatrice().getChildren().get(1),
                Forme.genererBordure(a.getMatrice()));
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        return hbox;
    }
}
