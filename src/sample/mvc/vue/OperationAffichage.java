package sample.mvc.vue;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.mvc.controlleur.Operation;
import sample.mvc.modele.Matrice;

public class OperationAffichage {

    public static void libre(InterfaceUtilisateur iu) {

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        iu.setCenter(hbox);

        Menu scalaires = new Menu("Scalaires");
        Menu matrices = new Menu("Matrices");
        Menu indices = new Menu("Indices d'opération");

        MenuItem nombre = new MenuItem("Nombre");
        nombre.setOnAction(event -> hbox.getChildren().add(new ScalaireAffichage()));
        MenuItem determinant = new MenuItem("Déterminant");

        MenuItem matrice = new MenuItem("Matrice");
        matrice.setOnAction(event -> hbox.getChildren().add(new MatriceAffichage(new Matrice(3, 3)).afficherMatrice()));

        MenuItem matriceTransposee = new MenuItem("Matrice transposée");
        MenuItem matriceExposee = new MenuItem("Matrice exposée");

        MenuItem addition = new MenuItem("Addition");
        addition.setOnAction(event -> hbox.getChildren().add(Forme.genererIndiceAddition()));
        MenuItem soustraction = new MenuItem("Soustraction");
        soustraction.setOnAction(event -> hbox.getChildren().add(Forme.genererIndiceSoustraction()));
        MenuItem multiplication = new MenuItem("Multiplication");
        multiplication.setOnAction(event -> hbox.getChildren().add(Forme.genererIndiceMultiplication()));

        scalaires.getItems().addAll(nombre, determinant);
        matrices.getItems().addAll(matrice, matriceTransposee, matriceExposee);
        indices.getItems().addAll(addition, soustraction, multiplication);

        ContextMenu contextMenu = new ContextMenu(scalaires, matrices, indices);
        iu.getCenter().setOnContextMenuRequested(event -> contextMenu.show(iu.getCenter(), event.getScreenX(), event.getScreenY()));

        iu.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.BACK_SPACE)
                if (hbox.getChildren().size() != 0)
                hbox.getChildren().remove(hbox.getChildren().size() - 1);
        });

        Button egale = new Button("=");
        egale.setOnAction(event -> {

            MatriceAffichage resultat = new MatriceAffichage(null);
            int pemda = 1;
            
            if (hbox.getChildren().size() >= 3) {

                for (int i = 0; i < hbox.getChildren().size(); i++)
                    if (hbox.getChildren().get(i).getId().equals("multiplication"))
                        pemda = i;

                if (hbox.getChildren().get(pemda -1).getId().equals("matrice")) {
                    if (hbox.getChildren().get(pemda + 1).getId().equals("matrice")) {
                        MatriceAffichage a = (MatriceAffichage) hbox.getChildren().get(0);
                        MatriceAffichage b = (MatriceAffichage) hbox.getChildren().get(2);

                        switch (hbox.getChildren().get(pemda).getId()) {
                            case "addition" :
                                resultat = new MatriceAffichage(Operation.addition(a.getMatrice(), b.getMatrice()));
                                break;
                            case "soustraction" :
                                resultat = new MatriceAffichage(Operation.soustraction(a.getMatrice(), b.getMatrice()));
                                break;
                            case "multiplication" :
                                resultat = new MatriceAffichage(Operation.produitMatriciel(a.getMatrice(), b.getMatrice()));
                                break;
                        }
                    }
                }
                else if (hbox.getChildren().get(pemda - 1).getId().equals("scalaire")) {
                    if (hbox.getChildren().get(pemda + 1).getId().equals("matrice")) {
                        ScalaireAffichage k = (ScalaireAffichage) hbox.getChildren().get(pemda - 1);
                        MatriceAffichage a = (MatriceAffichage) hbox.getChildren().get(pemda + 1);
                        if (hbox.getChildren().get(pemda).getId().equals("multiplication"))
                            resultat = new MatriceAffichage(Operation.multiplication(a.getMatrice(), k.getValeur()));
                    }
                }
            }
            hbox.getChildren().remove(pemda - 1, pemda + 2);
            hbox.getChildren().add(pemda - 1, resultat.afficherMatrice());
        });
        iu.setRight(egale);
    }

    public static void addition(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3));

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide() && b.getMatrice().estValide()) {
                if (Operation.addition(a.getMatrice(), b.getMatrice()) != null)
                    iu.setCenter(new MatriceAffichage(Operation.addition(a.getMatrice(), b.getMatrice())).afficherMatriceResultat());
                else
                    iu.setMessage("Opération impossible!", "erreur");
            }
            else
                iu.setMessage("Matrices incomplètes!", "erreur");
        });

        HBox hbox = new HBox( a.afficherMatrice(), Forme.genererIndiceAddition(), b.afficherMatrice(), egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        iu.setCenter(hbox);
    }

    public static HBox soustraction(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3));

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            MatriceAffichage resultat = new MatriceAffichage(Operation.soustraction(a.getMatrice(), b.getMatrice()));
            iu.setCenter(resultat.afficherMatrice());
        });

        HBox hbox = new HBox(a.afficherMatrice(), Forme.genererIndiceSoustraction(), b.afficherMatrice(), egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        return hbox;
    }

    public static HBox multiplication(InterfaceUtilisateur iu) {
        ScalaireAffichage k = new ScalaireAffichage();
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            MatriceAffichage resultat = new MatriceAffichage(Operation.multiplication(a.getMatrice(), k.getValeur()));
            iu.setCenter(resultat.afficherMatrice());
        });

        HBox hbox = new HBox(k, Forme.genererIndiceMultiplication(), a.afficherMatrice(), egale);
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

    public static HBox transposition(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            MatriceAffichage resultat = new MatriceAffichage(Operation.transposition(a.getMatrice()));
            iu.setCenter(resultat.afficherMatrice());
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

    public static HBox produitMatriciel(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3));

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            MatriceAffichage resultat = new MatriceAffichage(Operation.produitMatriciel(a.getMatrice(), b.getMatrice()));
            iu.setCenter(resultat.afficherMatrice());
        });

        HBox hbox = new HBox(a.afficherMatrice(),Forme.genererIndiceMultiplication(), b.afficherMatrice(), egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        return hbox;
    }

    public static HBox produitVectoriel(BorderPane bdp) {

        //a tester avec 3*1

        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3));

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            MatriceAffichage resultat = new MatriceAffichage(Operation.produitVectoriel(a.getMatrice(), b.getMatrice()));
            bdp.setCenter(resultat.afficherMatrice());
        });

        HBox hbox = new HBox(a.afficherMatrice(), Forme.genererIndiceVectoriel(), b.afficherMatrice(), egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        return hbox;
    }

    public static HBox produitHadamard(BorderPane bdp) {

        //a tester

        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3));

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            MatriceAffichage resultat = new MatriceAffichage(Operation.produitDHadamard(a.getMatrice(), b.getMatrice()));
            bdp.setCenter(resultat.afficherMatrice());
        });

        HBox hbox = new HBox(a.afficherMatrice(), Forme.genererIndiceHadamard(), b.afficherMatrice(), egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        return hbox;
    }

    public static HBox produitTensoriel(BorderPane bdp) {

        // a tester

        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3));

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            MatriceAffichage resultat = new MatriceAffichage(Operation.produitTensoriel(a.getMatrice(), b.getMatrice()));
            bdp.setCenter(resultat.afficherMatrice());
        });

        HBox hbox = new HBox(a.afficherMatrice(), Forme.genererIndiceTensoriel(), b.afficherMatrice(), egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        return hbox;
    }

    public static HBox determinant(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            MatriceAffichage resultat = new MatriceAffichage(Operation.determinant(a.getMatrice()));
            iu.setCenter(resultat.afficherMatrice());
        });

        HBox hbox = new HBox(Forme.genererBordure(a.getMatrice()), a.afficherMatrice(),
                Forme.genererBordure(a.getMatrice()), egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        return hbox;
    }
}
