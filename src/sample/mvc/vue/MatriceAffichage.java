package sample.mvc.vue;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sample.mvc.controlleur.Operation;
import sample.mvc.modele.Matrice;

public class MatriceAffichage extends HBox {

    private Matrice matrice;
    private Stage stage;

    public MatriceAffichage(Matrice matrice, Stage stage) {
        this.matrice = matrice;
        this.stage = stage;
    }


    public Matrice getMatrice() { return matrice; }

    public void setMatrice(Matrice matrice) { this.matrice = matrice; }

    public HBox afficherMatriceVide() {
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
                    if (matrice.estValide())
                        stage.setScene(new Scene(afficherMatrice(), 1000, 600));
                });

                gridPane.add(textfield,n - 1, m - 1);
            }
        }

        HBox hbox =  new HBox(Crochet.genererCrochetGauche(matrice), gridPane, Crochet.genererCrochetDroite(matrice));
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(5);
        return hbox;
    }

    private HBox afficherMatrice() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        for (int m = 1; m <= matrice.getM(); m++) {
            for (int n = 1; n <= matrice.getN(); n++) {

                Label valeur = new Label(Operation.doubleAFraction(matrice.getElement(m, n)));
                gridPane.add(valeur,n - 1, m - 1);
            }
        }

        HBox hbox =  new HBox(Crochet.genererCrochetGauche(matrice), gridPane, Crochet.genererCrochetDroite(matrice));
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(5);
        return hbox;
    }
}
