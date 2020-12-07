package sample.mvc.vue;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.mvc.controlleur.Operation;
import sample.mvc.controlleur.OperationLibre;
import sample.mvc.modele.Matrice;

public class OperationAffichage {

    public static void libre(InterfaceUtilisateur iu) {

        HBox operation = new HBox();
        operation.setAlignment(Pos.CENTER);
        operation.setSpacing(10);

        Button egale = new Button("=");
        egale.setVisible(false);
        egale.setOnAction(event -> OperationLibre.calculer(operation, iu));

        HBox centre = new HBox(operation, egale);
        centre.setAlignment(Pos.CENTER);
        centre.setSpacing(20);
        iu.setCenter(centre);

        Menu scalaires = new Menu("Scalaires");
        Menu matrices = new Menu("Matrices");
        Menu indices = new Menu("Indices d'opération");

        MenuItem nombre = new MenuItem("Nombre");
        nombre.setOnAction(event -> operation.getChildren().add(new ScalaireAffichage()));
        MenuItem determinant = new MenuItem("Déterminant");

        MenuItem matrice = new MenuItem("Matrice");
        matrice.setOnAction(event -> {
            int lettre = 0;
            Character[] alphabet = new Character[]{'a','b','c','d','e','f','g','h','i','j','k','l',
                    'm','n','o','p','q','r','s','t','u','v','w','x','y','z'};
            for (int i = 0; i < operation.getChildren().size(); i++)
                if (operation.getChildren().get(i).getId().equals("matrice"))
                    lettre++;
                if (lettre > 25)
                    lettre = 0;
            operation.getChildren().add(new MatriceAffichage(new Matrice(3, 3),  alphabet[lettre]).afficherMatrice());
        });

        MenuItem matriceTransposee = new MenuItem("Matrice transposée");
        MenuItem matriceExposee = new MenuItem("Matrice exposée");

        MenuItem addition = new MenuItem("Addition");
        addition.setOnAction(event -> operation.getChildren().add(Forme.genererIndiceAddition()));
        MenuItem soustraction = new MenuItem("Soustraction");
        soustraction.setOnAction(event -> operation.getChildren().add(Forme.genererIndiceSoustraction()));
        MenuItem multiplication = new MenuItem("Multiplication");
        multiplication.setOnAction(event -> operation.getChildren().add(Forme.genererIndiceMultiplication()));

        scalaires.getItems().addAll(nombre, determinant);
        matrices.getItems().addAll(matrice, matriceTransposee, matriceExposee);
        indices.getItems().addAll(addition, soustraction, multiplication);

        ContextMenu contextMenu = new ContextMenu(scalaires, matrices, indices);

        iu.getCenter().setOnContextMenuRequested(event -> contextMenu.show(iu.getCenter(), event.getScreenX(), event.getScreenY()));

        iu.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.BACK_SPACE) {
                if (operation.getChildren().size() != 0)
                operation.getChildren().remove(operation.getChildren().size() - 1);
                if (operation.getChildren().size()  < 3)
                    egale.setVisible(false);
            }
        });
        
        iu.setOnMouseMoved(mouseEvent -> {
            if (operation.getChildren().size()  >= 3)
                egale.setVisible(true);
        });
    }

    public static void addition(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3), 'a');
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3), 'b');

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide() && b.getMatrice().estValide()) {
                if (Operation.addition(a.getMatrice(), b.getMatrice()) != null) {
                    iu.setCenter(new MatriceAffichage(Operation.addition(a.getMatrice(), b.getMatrice()), 'r').afficherMatriceResultat());
                    iu.setMessage("Opération effectué avec succès!", "informative");
                }
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
        iu.setRight(Forme.genererAide(new Tooltip("Addition de deux matrices de même format\n" +
                "Soit A = [ aij ]mxn et B = [ bij ]mxn\nA + B = [ aij + bij ]mxn")));
    }

    public static void soustraction(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3), 'a');
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3), 'b');

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide() && b.getMatrice().estValide()) {
                if (Operation.soustraction(a.getMatrice(), b.getMatrice()) != null) {
                    iu.setCenter(new MatriceAffichage(Operation.soustraction(a.getMatrice(), b.getMatrice()), 'r').afficherMatriceResultat());
                    iu.setMessage("Opération effectué avec succès!", "informative");
                }
                else
                    iu.setMessage("Opération impossible!", "erreur");
            }
            else
                iu.setMessage("Matrices incomplètes!", "erreur");
        });

        HBox hbox = new HBox(a.afficherMatrice(), Forme.genererIndiceSoustraction(), b.afficherMatrice(), egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        iu.setCenter(hbox);
        iu.setRight(Forme.genererAide(new Tooltip("Soustraction de deux matrices de même format\n" +
                "Soit A = [ aij ]mxn et B = [ bij ]mxn\nA - B = [ aij - bij ]mxn")));
    }

    public static void multiplication(InterfaceUtilisateur iu) {
        ScalaireAffichage k = new ScalaireAffichage();
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3), 'a');

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide() && k.estValide()) {
                    iu.setCenter(new MatriceAffichage(Operation.multiplication(a.getMatrice(), k.getValeur()), 'r').afficherMatriceResultat());
                    iu.setMessage("Opération effectué avec succès!", "informative");
            }
            else
                iu.setMessage("Élements incomplets!", "erreur");
        });

        HBox hbox = new HBox(k, Forme.genererIndiceMultiplication(), a.afficherMatrice(), egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(30);
        iu.setCenter(hbox);
        iu.setRight(Forme.genererAide(new Tooltip("Multiplication d'une matrice par un scalaire\n" +
                "Soit A = [ aij ]mxn et K = un nombre réel \nKA = [ Kaij ]mxn")));
    }

    public static void puissance(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3), 'a');
        VBox indicePuissance = Forme.genererIndicePuissance();

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            ScalaireAffichage k = (ScalaireAffichage) indicePuissance.getChildren().get(0);
            if (a.getMatrice().estValide() && k.estValide()) {
                if (Operation.puissance(a.getMatrice(), k.getValeur()) != null) {
                    iu.setCenter(new MatriceAffichage(Operation.puissance(a.getMatrice(), k.getValeur()), 'r').afficherMatriceResultat());
                    iu.setMessage("Opération effectué avec succès!", "informative");
                }
                else
                    iu.setMessage("Opération impossible!", "erreur");
            }
            else
                iu.setMessage("Élements incomplets!", "erreur");
        });

        HBox hbox = new HBox(a.afficherMatrice(), indicePuissance, egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        iu.setCenter(hbox);
    }

    public static void transposition(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3), 'a');

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide())
                iu.setCenter(new MatriceAffichage(Operation.transposition(a.getMatrice()), 'r').afficherMatriceResultat());
            else
                iu.setMessage("Matrice incomplète!", "erreur");
        });

        HBox hbox = new HBox(a.afficherMatrice(), Forme.genererIndiceTransposition(), egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        iu.setCenter(hbox);
        iu.setRight(Forme.genererAide(new Tooltip("Transposée d'une matrice\n" +
                "Soit A = [ aij ]mxn\nAt = [ aji ]nxm")));
    }

    public static void inversion(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3), 'a');

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide()) {
                if (Operation.inverse(a.getMatrice()) != null) {
                    iu.setCenter(new MatriceAffichage(Operation.inverse(a.getMatrice()), 'r').afficherMatriceResultat());
                    iu.setMessage("Opération effectué avec succès!", "informative");
                }
                else
                    iu.setMessage("Opération impossible!", "erreur");
            }
            else
                iu.setMessage("Matrice incomplète!", "erreur");
        });

        HBox hbox = new HBox(a.afficherMatrice(), Forme.genererIndiceInverse(), egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        iu.setCenter(hbox);
    }

    public static void produitMatriciel(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3), 'a');
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3), 'b');

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide() && b.getMatrice().estValide()) {
                if (Operation.produitMatriciel(a.getMatrice(), b.getMatrice()) != null) {
                    iu.setCenter(new MatriceAffichage(Operation.produitMatriciel(a.getMatrice(), b.getMatrice()), 'r').afficherMatriceResultat());
                    iu.setMessage("Opération effectué avec succès!", "informative");
                }
                else
                    iu.setMessage("Opération impossible!", "erreur");
            }
            else
                iu.setMessage("Matrices incomplètes!", "erreur");
        });

        HBox hbox = new HBox(a.afficherMatrice(),Forme.genererIndiceMultiplication(), b.afficherMatrice(), egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        iu.setCenter(hbox);
        iu.setRight(Forme.genererAide(new Tooltip("Produit de deux matrices de formats compatibles\n" +
                "Soit A = [ aij ]mxn et B = [ bij ]nxp\nA mxn * B nxp = C mxp = [ cij ] où cij = la somme des éléments de k = 1 à k = n de aik * bkj")));
    }

    public static void produitVectoriel(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 1), 'a');
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 1), 'b');

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide() && b.getMatrice().estValide()) {
                if (Operation.produitVectoriel(a.getMatrice(), b.getMatrice()) != null) {
                    iu.setCenter(new MatriceAffichage(Operation.produitVectoriel(a.getMatrice(), b.getMatrice()), 'r').afficherMatriceResultat());
                    iu.setMessage("Opération effectué avec succès!", "informative");
                }
                else
                    iu.setMessage("Opération impossible!", "erreur");
            }
            else
                iu.setMessage("Matrices incomplètes!", "erreur");
        });

        HBox hbox = new HBox(a.afficherVecteur(), Forme.genererIndiceVectoriel(), b.afficherVecteur(), egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        iu.setCenter(hbox);
    }

    public static void produitHadamard(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3), 'a');
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3), 'b');

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide() && b.getMatrice().estValide()) {
                if (Operation.produitDHadamard(a.getMatrice(), b.getMatrice()) != null) {
                    iu.setCenter(new MatriceAffichage(Operation.produitDHadamard(a.getMatrice(), b.getMatrice()), 'r').afficherMatriceResultat());
                    iu.setMessage("Opération effectué avec succès!", "informative");
                }
                else
                    iu.setMessage("Opération impossible!", "erreur");
            }
            else
                iu.setMessage("Matrices incomplètes!", "erreur");
        });

        HBox hbox = new HBox(a.afficherMatrice(), Forme.genererIndiceHadamard(), b.afficherMatrice(), egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        iu.setCenter(hbox);
    }

    public static void produitTensoriel(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3), 'a');
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3), 'b');

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide() && b.getMatrice().estValide()) {
                iu.setCenter(new MatriceAffichage(Operation.produitTensoriel(a.getMatrice(), b.getMatrice()), 'r').afficherMatriceResultat());
                iu.setMessage("Opération effectué avec succès!", "informative");
            }
            else
                iu.setMessage("Matrices incomplètes!", "erreur");
        });

        HBox hbox = new HBox(a.afficherMatrice(), Forme.genererIndiceTensoriel(), b.afficherMatrice(), egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        iu.setCenter(hbox);
    }

    public static void determinant(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3), 'a');

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide())
                if (Operation.determinant(a.getMatrice()) != null)
                    iu.setCenter(new ScalaireAffichage(String.valueOf(Operation.determinantOp(a.getMatrice()))));
                else
                    iu.setMessage("Opération impossible!", "erreur");
            else
                iu.setMessage("Matrice incomplète!", "erreur");
        });

        HBox hbox = new HBox(a.afficherMatriceDeterminant(), egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        iu.setCenter(hbox);
        iu.setRight(Forme.genererAide(new Tooltip("Déterminant d'une matrice carrée\n" +
                "Soit A = [ aij ]nxn et Aij = (-1)i+j * Mij (Mij étant le miner de l'élément aij)\n" +
                "det A = |A| = la somme de k = 1 à k = n de aik * Aik ou akj * Akj")));
    }
}
