package sample.mvc.vue;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import sample.mvc.modele.Matrice;

public class MatriceAffichage extends HBox {

    private Matrice matrice;

    public MatriceAffichage(Matrice matrice) {
        this.matrice = matrice;
    }

    public HBox getAffichage() {
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

    public Matrice getMatrice() { return matrice; }

    public void setMatrice(Matrice matrice) { this.matrice = matrice; }
}
