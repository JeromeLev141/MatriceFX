package sample.mvc.vue;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import sample.mvc.modele.Matrice;
import sample.mvc.modele.MatriceDemarche;

public class MatriceDemarcheAffichage extends HBox {

    private MatriceDemarche matrice;

    public MatriceDemarcheAffichage(MatriceDemarche matrice) {
        this.matrice = matrice;
        setAlignment(Pos.CENTER);
        setSpacing(10);
    }

    private GridPane genererGridpane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(15);
        gridPane.setVgap(10);

        for (int m = 1; m <= matrice.getM(); m++) {
            for (int n = 1; n <= matrice.getN(); n++) {
                gridPane.setHgap(30);
                gridPane.setVgap(20);
                gridPane.add(Forme.genererScalaire(matrice.getElement(m, n)),n - 1, m - 1);
            }
        }
        return gridPane;
    }

    public MatriceDemarcheAffichage afficherMatrice() {
        getChildren().addAll(Forme.genererCrochetGauche(new Matrice(matrice.getM(), matrice.getN())),
                genererGridpane(), Forme.genererCrochetDroite(new Matrice(matrice.getM(), matrice.getN())));
        return this;
    }
}
