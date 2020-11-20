package sample.mvc.vue;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.mvc.modele.Matrice;

import java.awt.*;

public class Affichage {

    public static HBox genererMatrice(Matrice matrice) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        for (int m = 1; m <= matrice.getM(); m++) {
            for (int n = 1; n <= matrice.getN(); n++) {

                TextField textfield = new TextField();
                textfield.setPromptText("a" + m + "" + n);
                textfield.setPrefColumnCount(2);
                textfield.setOnAction(event -> {
                    matrice.setElement(Integer.parseInt(textfield.getPromptText().substring(1, 2)),
                            Integer.parseInt(textfield.getPromptText().substring(2, 3)),
                            Double.parseDouble(textfield.getText()));
                });

                gridPane.add(textfield,n - 1, m - 1);
            }
        }

        HBox hbox =  new HBox(Crochet.genererCrochetGauche(matrice), gridPane, Crochet.genererCrochetDroite(matrice));
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(5);
        return hbox;
    }
}
