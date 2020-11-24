package sample.mvc.vue;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.mvc.modele.Matrice;

public class Crochet {

    public static VBox genererCrochetGauche(Matrice matrice) {
        Rectangle dessus = new Rectangle(10, 2, Color.GREY);
        Rectangle bordure = new Rectangle(2, 30 * matrice.getM(), Color.GREY);
        Rectangle dessous = new Rectangle(10, 2, Color.GREY);
        VBox vBox = new VBox(dessus, bordure, dessous);
        vBox.setAlignment(Pos.CENTER_LEFT);
        return vBox;
    }

    public static VBox genererCrochetDroite(Matrice matrice) {
       VBox vBox = genererCrochetGauche(matrice);
       vBox.setAlignment(Pos.CENTER_RIGHT);
       return vBox;
    }
}
