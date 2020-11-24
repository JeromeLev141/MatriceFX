package sample.mvc.vue;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import sample.mvc.modele.Matrice;

public class OperationAffichage {

    public static HBox addition() {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3));

        HBox hbox = new HBox(a.afficherMatriceVide(), Forme.GenererIndiceAddition(), b.afficherMatriceVide());
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        return hbox;
    }

    public static HBox soustraction() {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3));

        HBox hbox = new HBox(a.afficherMatriceVide(), Forme.GenererIndiceSoustraction(), b.afficherMatriceVide());
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        return hbox;
    }

    public static HBox multiplication() { return null; }

    public static HBox transposition() { return null; }

    public static Matrice produitVectoriel() { return null; }

    public static HBox determinant(){ return null; }
}
