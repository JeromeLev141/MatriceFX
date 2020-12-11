package sample.mvc.vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.mvc.controlleur.LecteurDeFichier;
import sample.mvc.controlleur.Operation;
import sample.mvc.modele.Matrice;

import java.io.IOException;

public class MatriceAffichage extends HBox {

    private Character nom;
    private Matrice matrice;
    private int verif;

    public MatriceAffichage(Matrice matrice, Character nom) {
        this.nom = nom;
        this.matrice = matrice;
        setAlignment(Pos.CENTER);
        setSpacing(10);
        setId("matrice");
        verif = 0;
    }

    public Matrice getMatrice() {
        return matrice;
    }

    public void setMatrice(Matrice matrice) {
        this.matrice = matrice;
    }

    private GridPane genererGridpane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(15);
        gridPane.setVgap(10);

        for (int m = 1; m <= matrice.getM(); m++) {
            for (int n = 1; n <= matrice.getN(); n++) {

                TextField textfield = new TextField();
                textfield.setPromptText(nom + "" + m + "" + n);
                textfield.setPrefColumnCount(2);
                textfield.setOnAction(event -> {
                    try {
                        Double.parseDouble(textfield.getText());
                    }catch (Exception entreeInvalide) {
                        textfield.setText("");
                    }
                    if (!textfield.getText().equals("")) {
                        int mToken = Integer.parseInt(textfield.getPromptText().substring(1, 2));
                        int nToken = Integer.parseInt(textfield.getPromptText().substring(2, 3));

                        matrice.setElement(mToken, nToken, Double.parseDouble(textfield.getText()));
                        gridPane.getChildren().remove(textfield);
                        gridPane.add(Forme.genererScalaire(Operation.doubleAFraction(matrice.getElement(mToken, nToken))),
                                nToken - 1, mToken - 1);
                        verif++;

                        if (verif == gridPane.getChildren().size()) {
                            gridPane.setHgap(30);
                            gridPane.setVgap(20);
                        }
                    }
                });

                if (!matrice.estValide()) {
                    gridPane.add(textfield, n - 1, m - 1);
                }
                else {
                    gridPane.setHgap(30);
                    gridPane.setVgap(20);
                    gridPane.add(Forme.genererScalaire(Operation.doubleAFraction(matrice.getElement(m, n))),
                            n - 1, m - 1);
                }
            }
        }
        return gridPane;
    }

    private GridPane genererGridpaneVecteur() {
        GridPane gridPane = genererGridpane();

        for (int i = 1; i <= 3; i++) {
            TextField textField = (TextField) gridPane.getChildren().get(i - 1);
            int m = i;
            if (i == 1)
                textField.setPromptText(nom + "x");
            else if (i == 2)
                textField.setPromptText(nom + "y");
            else
                textField.setPromptText(nom + "z");
            textField.setOnAction(event -> {
                if (!textField.getText().equals("")) {
                    matrice.setElement(m, 1, Double.parseDouble(textField.getText()));
                    gridPane.getChildren().remove(textField);
                    gridPane.add(Forme.genererScalaire(Operation.doubleAFraction(matrice.getElement(m, 1))),0, m - 1);
                    verif++;

                    if (verif == gridPane.getChildren().size()) {
                        gridPane.setHgap(30);
                        gridPane.setVgap(20);
                    }
                }
            });
            gridPane.getChildren().set(i - 1, textField);
        }

        return gridPane;
    }

    public MatriceAffichage afficherMatrice(InterfaceUtilisateur iu) {

        Button plusM = new Button("+");
        plusM.setFocusTraversable(false);
        Button moinsM = new Button("-");
        moinsM.setFocusTraversable(false);
        Button plusN = new Button("+");
        plusN.setFocusTraversable(false);
        Button moinsN = new Button("-");
        moinsN.setFocusTraversable(false);

        plusM.setOnAction(event -> {
            matrice.setM(matrice.getM() + 1);
            getChildren().clear();
            afficherMatrice(iu);
        });
        moinsM.setOnAction(event -> {
            if (matrice.getM() > 1) {
                matrice.setM(matrice.getM() - 1);
                getChildren().clear();
                afficherMatrice(iu);
            }
        });
        plusN.setOnAction(event -> {
            matrice.setN(matrice.getN() + 1);
            getChildren().clear();
            afficherMatrice(iu);
        });
        moinsN.setOnAction(event -> {
            if (matrice.getN() > 1) {
                matrice.setN(matrice.getN() - 1);
                getChildren().clear();
                afficherMatrice(iu);
            }
        });

        VBox vBox = new VBox(moinsM, genererGridpane(), plusM);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);

        VBox centre = new VBox(vBox, ajouterBoutonImporterTest(iu));
        centre.setAlignment(Pos.CENTER);
        centre.setSpacing(30);
        centre.setTranslateY(vBox.getTranslateY() + 28);

        getChildren().addAll(moinsN, Forme.genererCrochetGauche(matrice), centre, Forme.genererCrochetDroite(matrice), plusN);
        return this;
    }

    public MatriceAffichage afficherMatriceDeterminant(InterfaceUtilisateur iu) {
        setId("determinant");

        Button plusM = new Button("+");
        plusM.setFocusTraversable(false);
        Button moinsM = new Button("-");
        moinsM.setFocusTraversable(false);
        Button plusN = new Button("+");
        plusN.setFocusTraversable(false);
        Button moinsN = new Button("-");
        moinsN.setFocusTraversable(false);

        plusM.setOnAction(event -> {
            matrice.setM(matrice.getM() + 1);
            getChildren().clear();
            afficherMatriceDeterminant(iu);
        });
        moinsM.setOnAction(event -> {
            if (matrice.getM() > 1) {
                matrice.setM(matrice.getM() - 1);
                getChildren().clear();
                afficherMatriceDeterminant(iu);
            }
        });
        plusN.setOnAction(event -> {
            matrice.setN(matrice.getN() + 1);
            getChildren().clear();
            afficherMatriceDeterminant(iu);
        });
        moinsN.setOnAction(event -> {
            if (matrice.getN() > 1) {
                matrice.setN(matrice.getN() - 1);
                getChildren().clear();
                afficherMatriceDeterminant(iu);
            }
        });

        VBox vBox = new VBox(moinsM, genererGridpane(), plusM);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        VBox centre = new VBox(vBox, ajouterBoutonImporterTest(iu));
        centre.setAlignment(Pos.CENTER);
        centre.setSpacing(30);
        centre.setTranslateY(vBox.getTranslateY() + 28);

        getChildren().addAll(moinsN, Forme.genererBordure(matrice), centre, Forme.genererBordure(matrice), plusN);
        return this;
    }

    public MatriceAffichage afficherVecteur() {
        getChildren().addAll(Forme.genererCrochetGauche(matrice), genererGridpaneVecteur(), Forme.genererCrochetDroite(matrice));
        return this;
    }

    public MatriceAffichage afficherMatriceResultat() {
        getChildren().addAll(Forme.genererCrochetGauche(matrice), genererGridpane(), Forme.genererCrochetDroite(matrice));
        return this;
    }

    public MatriceAffichage afficherDeterminantResultat() {
        setId("determinant");
        getChildren().addAll(Forme.genererBordure(matrice), genererGridpane(), Forme.genererBordure(matrice));
        return this;
    }

    public Button ajouterBoutonImporterTest(InterfaceUtilisateur iu) {
        Button importer = new Button("Importer");
        importer.setFocusTraversable(false);
        importer.setOnAction(event -> {
            LecteurDeFichier ldf;
            try {
                ldf = new LecteurDeFichier(LecteurDeFichier.chercherFichier(iu.getStage()));
                setMatrice(LecteurDeFichier.stringtoMatrice(ldf.getliste().get(0)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            getChildren().clear();
            afficherMatrice(iu);
        });
        return importer;
    }
}
