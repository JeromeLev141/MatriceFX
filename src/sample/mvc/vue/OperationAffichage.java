package sample.mvc.vue;

import javafx.geometry.Pos;
import javafx.scene.control.*;
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
        MenuItem determinant = new MenuItem("Déterminant");

        MenuItem matrice = new MenuItem("Matrice");
        matrice.setOnAction(event -> hbox.getChildren().add(new MatriceAffichage(new Matrice(3, 3)).afficherMatrice()));

        MenuItem matriceTransposee = new MenuItem("Matrice transposée");
        MenuItem matriceExposee = new MenuItem("Matrice exposée");

        MenuItem addition = new MenuItem("Addition");
        addition.setOnAction(event -> hbox.getChildren().add(Forme.genererIndiceAddition()));

        scalaires.getItems().addAll(nombre, determinant);
        matrices.getItems().addAll(matrice, matriceTransposee, matriceExposee);
        indices.getItems().addAll(addition);

        ContextMenu contextMenu = new ContextMenu(scalaires, matrices, indices);
        iu.getCenter().setOnContextMenuRequested(event -> contextMenu.show(iu.getCenter(), event.getScreenX(), event.getScreenY()));

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (hbox.getChildren().size() >= 3) {
                if (hbox.getChildren().get(0).getId().equals("matrice")) {
                    if (hbox.getChildren().get(1).getId().equals("addition")) {
                        if (hbox.getChildren().get(2).getId().equals("matrice"))  {
                            MatriceAffichage a = (MatriceAffichage) hbox.getChildren().get(0);
                            MatriceAffichage b = (MatriceAffichage) hbox.getChildren().get(2);
                            MatriceAffichage resultat = new MatriceAffichage(Operation.addition(a.getMatrice(), b.getMatrice()));
                            hbox.getChildren().remove(0, 3);
                            hbox.getChildren().add(0, resultat.afficherMatrice());
                        }
                    }
                }
            }

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
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));
        TextField scalaire = new TextField();
        scalaire.setPromptText("K");
        scalaire.setPrefColumnCount(2);

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            MatriceAffichage resultat = new MatriceAffichage(Operation.multiplication(a.getMatrice(), Double.parseDouble(scalaire.getText())));
            iu.setCenter(resultat.afficherMatrice());
        });

        HBox hbox = new HBox(scalaire, a.afficherMatrice(), egale);

        scalaire.setOnAction(event -> {
            hbox.getChildren().remove(scalaire);
            hbox.getChildren().add(0, Forme.genererScalaire(scalaire.getText()));
        });

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

        HBox hbox = new HBox(a.afficherMatrice(), b.afficherMatrice(), egale);
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
