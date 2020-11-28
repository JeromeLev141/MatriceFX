package sample.mvc.vue;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import sample.mvc.modele.Matrice;
import javafx.scene.control.Label;

public class OperationAffichage {

    public static HBox addition() {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3));

        HBox hbox = new HBox(a.afficherMatrice(), Forme.genererIndiceAddition(), b.afficherMatrice());
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        return hbox;
    }

    public static HBox soustraction() {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3));

        HBox hbox = new HBox(a.afficherMatrice(), Forme.genererIndiceSoustraction(), b.afficherMatrice());
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        return hbox;
    }

    public static HBox multiplication() {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));
        Label scalaire = new Label("k");
        scalaire.setTextFill(Color.GREY);
        scalaire.setScaleY(4);
        scalaire.setScaleX(4);

        HBox hbox = new HBox(scalaire, a.afficherMatrice());
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(30);
        return hbox;
    }

    public static HBox puissance() { return null; }

    public static HBox transposition() {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));

        HBox hbox = new HBox(a.afficherMatrice(), Forme.genererIndiceTransposition());
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        return hbox;
    }

    public static HBox inversion() { return null; }

    public static HBox produitMatriciel() {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3));

        HBox hbox = new HBox(a.afficherMatrice(), b.afficherMatrice());
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        return hbox;
    }

    public static HBox produitVectoriel() {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3));

        HBox hbox = new HBox(a.afficherMatrice(), Forme.genererIndiceVectoriel(), b.afficherMatrice());
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        return hbox;
    }

    public static HBox produitHadamard() {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3));

        HBox hbox = new HBox(a.afficherMatrice(), Forme.genererIndiceHadamard(), b.afficherMatrice());
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        return hbox;
    }

    public static HBox produitTensoriel() { return null; }

    public static HBox determinant() {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));

        HBox hbox = new HBox(Forme.genererBordure(a.getMatrice()), a.afficherMatrice().getChildren().get(1),
                Forme.genererBordure(a.getMatrice()));
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        return hbox;
    }

    public static HBox matriceResultat() { return null; }
}
