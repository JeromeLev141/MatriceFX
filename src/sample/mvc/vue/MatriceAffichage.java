package sample.mvc.vue;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import sample.mvc.controlleur.Operation;
import sample.mvc.modele.Matrice;

public class MatriceAffichage extends HBox {

    private Matrice matrice;
    private int verif;

    public MatriceAffichage(Matrice matrice) {
        this.matrice = matrice;
        verif = 0;
    }

    public Matrice getMatrice() { return matrice; }

    public void setMatrice(Matrice matrice) { this.matrice = matrice; }

    public HBox afficherMatrice() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        for (int m = 1; m <= matrice.getM(); m++) {
            for (int n = 1; n <= matrice.getN(); n++) {

                TextField textfield = new TextField();
                textfield.setPromptText("a" + m + "" + n);
                textfield.setPrefColumnCount(2);
                textfield.setOnAction(event -> {
                    int mToken = Integer.parseInt(textfield.getPromptText().substring(1, 2));
                    int nToken = Integer.parseInt(textfield.getPromptText().substring(2, 3));

                    matrice.setElement(mToken, nToken, Double.parseDouble(textfield.getText()));
                    gridPane.getChildren().remove(textfield);
                    gridPane.add(Forme.genererScalaire(Operation.doubleAFraction(matrice.getElement(mToken, nToken))),
                            nToken - 1, mToken - 1);
                    verif++;

                    if (verif == gridPane.getChildren().size()) {
                        gridPane.setHgap(25);
                        gridPane.setVgap(20);
                    }
                });

                gridPane.add(textfield,n - 1, m - 1);
            }
        }

        HBox hbox =  new HBox(Forme.genererCrochetGauche(matrice), gridPane, Forme.genererCrochetDroite(matrice));
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(5);
        return hbox;
    }
}
