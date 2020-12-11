package sample.mvc.vue;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import sample.mvc.controlleur.LecteurDeFichier;
import sample.mvc.controlleur.Operation;
import sample.mvc.controlleur.OperationLibre;
import sample.mvc.modele.Matrice;

public class OperationAffichage {

    public static void libre(InterfaceUtilisateur iu) {

        HBox operation = new HBox();
        operation.setAlignment(Pos.CENTER);
        operation.setSpacing(20);

        Button egale = new Button("=");
        egale.setVisible(false);
        egale.setOnAction(event -> {
            VBox demarche = new VBox();
            demarche.setAlignment(Pos.CENTER);
            demarche.setSpacing(20);
            OperationLibre.calculer(operation, demarche, iu, true);
        });

        HBox centre = new HBox(operation, egale);
        centre.setAlignment(Pos.CENTER);
        centre.setSpacing(20);
        iu.setCenter(centre);
        iu.getCenter().setEffect(new DropShadow(1, 1, -1, Color.GREY));

        Menu elements = new Menu("Éléments");
        Menu indices = new Menu("Indices d'opération");

        MenuItem nombre = new MenuItem("Nombre");
        nombre.setOnAction(event -> operation.getChildren().add(new ScalaireAffichage()));
        MenuItem determinant = new MenuItem("Déterminant");
        determinant.setOnAction(event -> operation.getChildren().add(new MatriceAffichage(new Matrice(3, 3),
                OperationLibre.genererNom(operation)).afficherMatriceDeterminant(iu)));
        MenuItem matrice = new MenuItem("Matrice");
        matrice.setOnAction(event -> operation.getChildren().add(new MatriceAffichage(new Matrice(3, 3),
                OperationLibre.genererNom(operation)).afficherMatrice(iu)));

        MenuItem addition = new MenuItem("Addition");
        addition.setOnAction(event -> operation.getChildren().add(Forme.genererIndiceAddition()));
        MenuItem soustraction = new MenuItem("Soustraction");
        soustraction.setOnAction(event -> operation.getChildren().add(Forme.genererIndiceSoustraction()));
        MenuItem multiplication = new MenuItem("Multiplication");
        multiplication.setOnAction(event -> operation.getChildren().add(Forme.genererIndiceMultiplication()));
        MenuItem puissance = new MenuItem("Puissance");
        puissance.setOnAction(event -> operation.getChildren().add(Forme.genererIndicePuissance()));
        MenuItem transposition = new MenuItem("Transposition");
        transposition.setOnAction(event -> operation.getChildren().add(Forme.genererIndiceTransposition()));
        MenuItem inverse = new MenuItem("Inverse");
        inverse.setOnAction(event -> operation.getChildren().add(Forme.genererIndiceInverse()));
        MenuItem produitVectoriel = new MenuItem("Produit vectoriel");
        produitVectoriel.setOnAction(event -> operation.getChildren().add(Forme.genererIndiceVectoriel()));
        MenuItem produitHadamard = new MenuItem("Produit d'Hadamard");
        produitHadamard.setOnAction(event -> operation.getChildren().add(Forme.genererIndiceHadamard()));
        MenuItem produitTensoriel = new MenuItem("Produit tensoriel");
        produitTensoriel.setOnAction(event -> operation.getChildren().add(Forme.genererIndiceTensoriel()));

        elements.getItems().addAll(nombre, determinant, matrice);
        indices.getItems().addAll(addition, soustraction, multiplication, puissance, transposition, inverse,
                produitVectoriel, produitHadamard, produitTensoriel);

        ContextMenu contextMenu = new ContextMenu(elements, indices);

        iu.getCenter().setOnContextMenuRequested(event -> contextMenu.show(iu.getCenter(), event.getScreenX(), event.getScreenY()));
        iu.setRight(Forme.genererAide(new Tooltip("Bienvenu sur le terrain libre!\n" +
                "Ici vous pouvez assembler des opérations mixtes!\n" +
                "- Veuillez utiliser le clic de droit pour accéder au menu des éléments\n" +
                "- Veuillez utiliser 'Backspace' afin d'effacer un élément")));
        iu.getScene().setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.BACK_SPACE) {
                if (operation.getChildren().size() != 0) {
                    operation.getChildren().remove(operation.getChildren().size() - 1);
                }
                if (operation.getChildren().size() == 0)
                    egale.setVisible(false);
            }
            else if (keyEvent.getCode() == KeyCode.R)
                libre(iu);
        });
        iu.setOnMouseMoved(mouseEvent -> {
            if (operation.getChildren().size()  > 0)
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
                    LecteurDeFichier.genererImprimer(iu);
                    iu.getCenter().setEffect(new DropShadow(1, 1, -1, Color.GREY));
                    iu.setMessage("Opération effectué avec succès!", "informative");
                }
                else
                    iu.setMessage("Veuillez utiliser des matrices de même format!", "erreur");
            }
            else
                iu.setMessage("Matrices incomplètes!", "erreur");
        });

        HBox hbox = new HBox( a.afficherMatrice(iu), Forme.genererIndiceAddition(), b.afficherMatrice(iu), egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        iu.setCenter(hbox);
        iu.getCenter().setEffect(new DropShadow(1, 1, -1, Color.GREY));
        iu.setRight(Forme.genererAide(new Tooltip("Addition de deux matrices de même format\n" +
                "Soit A = [ aij ]mxn et B = [ bij ]mxn\nA + B = [ aij + bij ]mxn")));
        iu.getScene().setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.R)
                addition(iu);
        });
    }

    public static void soustraction(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3), 'a');
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3), 'b');

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide() && b.getMatrice().estValide()) {
                if (Operation.soustraction(a.getMatrice(), b.getMatrice()) != null) {
                    iu.setCenter(new MatriceAffichage(Operation.soustraction(a.getMatrice(), b.getMatrice()), 'r').afficherMatriceResultat());
                    LecteurDeFichier.genererImprimer(iu);
                    iu.getCenter().setEffect(new DropShadow(1, 1, -1, Color.GREY));
                    iu.setMessage("Opération effectué avec succès!", "informative");
                }
                else
                    iu.setMessage("Veuillez utiliser des matrices de même format!", "erreur");
            }
            else
                iu.setMessage("Matrices incomplètes!", "erreur");
        });

        HBox hbox = new HBox(a.afficherMatrice(iu), Forme.genererIndiceSoustraction(), b.afficherMatrice(iu), egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        iu.setCenter(hbox);
        iu.getCenter().setEffect(new DropShadow(1, 1, -1, Color.GREY));
        iu.setRight(Forme.genererAide(new Tooltip("Soustraction de deux matrices de même format\n" +
                "Soit A = [ aij ]mxn et B = [ bij ]mxn\nA - B = [ aij - bij ]mxn")));
        iu.getScene().setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.R)
                soustraction(iu);
        });
    }

    public static void multiplication(InterfaceUtilisateur iu) {
        ScalaireAffichage k = new ScalaireAffichage();
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3), 'a');

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide() && k.estValide()) {
                    iu.setCenter(new MatriceAffichage(Operation.multiplication(a.getMatrice(), k.getValeur()), 'r').afficherMatriceResultat());
                    LecteurDeFichier.genererImprimer(iu);
                    iu.getCenter().setEffect(new DropShadow(1, 1, -1, Color.GREY));
                    iu.setMessage("Opération effectué avec succès!", "informative");
            }
            else
                iu.setMessage("Élements incomplets!", "erreur");
        });

        HBox hbox = new HBox(k, Forme.genererIndiceMultiplication(), a.afficherMatrice(iu), egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(30);
        iu.setCenter(hbox);
        iu.getCenter().setEffect(new DropShadow(1, 1, -1, Color.GREY));
        iu.setRight(Forme.genererAide(new Tooltip("Multiplication d'une matrice par un scalaire\n" +
                "Soit A = [ aij ]mxn et K = un nombre réel \nKA = [ Kaij ]mxn")));
        iu.getScene().setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.R)
                multiplication(iu);
        });
    }

    public static void puissance(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3), 'a');
        VBox indicePuissance = Forme.genererIndicePuissance();

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            ScalaireAffichage k = (ScalaireAffichage) indicePuissance.getChildren().get(0);
            if (a.getMatrice().estValide() && k.estValide()) {
                if (Operation.puissance(a.getMatrice(), k.getValeur()) != null) {
                    if (k.getValeur() == (int) k.getValeur()) {
                        iu.setCenter(new MatriceAffichage(Operation.puissance(a.getMatrice(), k.getValeur()), 'r').afficherMatriceResultat());
                        LecteurDeFichier.genererImprimer(iu);
                        iu.getCenter().setEffect(new DropShadow(1, 1, -1, Color.GREY));
                        iu.setMessage("Opération effectué avec succès!", "informative");
                    }
                    else
                        iu.setMessage("Veuillez entrer un nombre entier comme exposant!", "erreur");
                }
                else
                    iu.setMessage("Opération impossible!", "erreur");
            }
            else
                iu.setMessage("Élements incomplets!", "erreur");
        });

        HBox hbox = new HBox(a.afficherMatrice(iu), indicePuissance, egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        iu.setCenter(hbox);
        iu.getCenter().setEffect(new DropShadow(1, 1, -1, Color.GREY));
        iu.setRight(Forme.genererAide(new Tooltip("Puissance d'une matrice carrée\n" +
                "Soit A = [ aij ]nxn et K = un nombre réel\nAk = A * A * ... * A (un nombre k de facteurs A)")));
        iu.getScene().setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.R)
                puissance(iu);
        });
    }

    public static void transposition(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3), 'a');

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide()) {
                iu.setCenter(new MatriceAffichage(Operation.transposition(a.getMatrice()), 'r').afficherMatriceResultat());
                LecteurDeFichier.genererImprimer(iu);
                iu.getCenter().setEffect(new DropShadow(1, 1, -1, Color.GREY));
                iu.setMessage("Opération effectué avec succès!", "informative");
            }
            else
                iu.setMessage("Matrice incomplète!", "erreur");
        });

        HBox hbox = new HBox(a.afficherMatrice(iu), Forme.genererIndiceTransposition(), egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        iu.setCenter(hbox);
        iu.getCenter().setEffect(new DropShadow(1, 1, -1, Color.GREY));
        iu.setRight(Forme.genererAide(new Tooltip("Transposée d'une matrice\n" +
                "Soit A = [ aij ]mxn\nAt = [ aji ]nxm")));
        iu.getScene().setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.R)
                transposition(iu);
        });
    }

    public static void inversion(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3), 'a');

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide()) {
                if (Operation.inverse(a.getMatrice()) != null) {
                    iu.setCenter(new MatriceAffichage(Operation.inverse(a.getMatrice()), 'r').afficherMatriceResultat());
                    LecteurDeFichier.genererImprimer(iu);
                    iu.getCenter().setEffect(new DropShadow(1, 1, -1, Color.GREY));
                    iu.setMessage("Opération effectué avec succès!", "informative");
                }
                else
                    iu.setMessage("Opération impossible!", "erreur");
            }
            else
                iu.setMessage("Matrice incomplète!", "erreur");
        });

        HBox hbox = new HBox(a.afficherMatrice(iu), Forme.genererIndiceInverse(), egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        iu.setCenter(hbox);
        iu.getCenter().setEffect(new DropShadow(1, 1, -1, Color.GREY));
        iu.setRight(Forme.genererAide(new Tooltip("Inverse d'une matrice carrée\n" +
                "Soit A = [ aij ]nxn et In = matrice identitée d'ordre n\n" +
                "A *  A-1 = A-1 * A = In")));
        iu.getScene().setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.R)
                inversion(iu);
        });
    }

    public static void produitMatriciel(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3), 'a');
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3), 'b');

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide() && b.getMatrice().estValide()) {
                if (Operation.produitMatriciel(a.getMatrice(), b.getMatrice()) != null) {
                    iu.setCenter(new MatriceAffichage(Operation.produitMatriciel(a.getMatrice(), b.getMatrice()), 'r').afficherMatriceResultat());
                    LecteurDeFichier.genererImprimer(iu);
                    iu.getCenter().setEffect(new DropShadow(1, 1, -1, Color.GREY));
                    iu.setMessage("Opération effectué avec succès!", "informative");
                }
                else
                    iu.setMessage("Veuillez utiliser des matrices de formats compatibles!", "erreur");
            }
            else
                iu.setMessage("Matrices incomplètes!", "erreur");
        });

        HBox hbox = new HBox(a.afficherMatrice(iu),Forme.genererIndiceMultiplication(), b.afficherMatrice(iu), egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        iu.setCenter(hbox);
        iu.getCenter().setEffect(new DropShadow(1, 1, -1, Color.GREY));
        iu.setRight(Forme.genererAide(new Tooltip("Produit de deux matrices de formats compatibles\n" +
                "Soit A = [ aij ]mxn et B = [ bij ]nxp\n" +
                "A mxn * B nxp = C mxp = [ cij ] où cij = la somme des éléments de k = 1 à k = n de aik * bkj")));
        iu.getScene().setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.R)
                produitMatriciel(iu);
        });
    }

    public static void produitVectoriel(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 1), 'a');
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 1), 'b');

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide() && b.getMatrice().estValide()) {
                if (Operation.produitVectoriel(a.getMatrice(), b.getMatrice()) != null) {
                    iu.setCenter(new MatriceAffichage(Operation.produitVectoriel(a.getMatrice(), b.getMatrice()), 'r').afficherMatriceResultat());
                    LecteurDeFichier.genererImprimer(iu);
                    iu.getCenter().setEffect(new DropShadow(1, 1, -1, Color.GREY));
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
        iu.getCenter().setEffect(new DropShadow(1, 1, -1, Color.GREY));
        iu.setRight(Forme.genererAide(new Tooltip("Produit vectoriel de deux vecteurs\n" +
                "Soit A = [xa ya za]t et B = [xb yb zb]t\n" +
                "A ^ B = [ya*zb-za*yb za*xb-xa*zb xa*yb-ya*xb]t")));
        iu.getScene().setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.R)
                produitVectoriel(iu);
        });
    }

    public static void produitHadamard(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3), 'a');
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3), 'b');

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide() && b.getMatrice().estValide()) {
                if (Operation.produitDHadamard(a.getMatrice(), b.getMatrice()) != null) {
                    iu.setCenter(new MatriceAffichage(Operation.produitDHadamard(a.getMatrice(), b.getMatrice()), 'r').afficherMatriceResultat());
                    LecteurDeFichier.genererImprimer(iu);
                    iu.getCenter().setEffect(new DropShadow(1, 1, -1, Color.GREY));
                    iu.setMessage("Opération effectué avec succès!", "informative");
                }
                else
                    iu.setMessage("Veuillez utiliser des matrices de même format!", "erreur");
            }
            else
                iu.setMessage("Matrices incomplètes!", "erreur");
        });

        HBox hbox = new HBox(a.afficherMatrice(iu), Forme.genererIndiceHadamard(), b.afficherMatrice(iu), egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        iu.setCenter(hbox);
        iu.getCenter().setEffect(new DropShadow(1, 1, -1, Color.GREY));
        iu.setRight(Forme.genererAide(new Tooltip("Produit d'Hademard de deux matrices de même format\n" +
                "Soit A = [ aij ]mxn et B = [ bij ]mxn\nA o B = [ aij * bij ]mxn")));
        iu.getScene().setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.R)
                produitHadamard(iu);
        });
    }

    public static void produitTensoriel(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3), 'a');
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3), 'b');

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide() && b.getMatrice().estValide()) {
                iu.setCenter(new MatriceAffichage(Operation.produitTensoriel(a.getMatrice(), b.getMatrice()), 'r').afficherMatriceResultat());
                LecteurDeFichier.genererImprimer(iu);
                iu.getCenter().setEffect(new DropShadow(1, 1, -1, Color.GREY));
                iu.setMessage("Opération effectué avec succès!", "informative");
            }
            else
                iu.setMessage("Matrices incomplètes!", "erreur");
        });

        HBox hbox = new HBox(a.afficherMatrice(iu), Forme.genererIndiceTensoriel(), b.afficherMatrice(iu), egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        iu.setCenter(hbox);
        iu.getCenter().setEffect(new DropShadow(1, 1, -1, Color.GREY));
        iu.setRight(Forme.genererAide(new Tooltip("Produit tensoriel de deux espaces vectoriels\n" +
                "Soit A = [ aij ]m*n et B = [ bij ]m*n\n" +
                "A ⊗ B = [ aij * B ](ma*mb)*(na*nb)")));
        iu.getScene().setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.R)
                produitTensoriel(iu);
        });
    }

    public static void determinant(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3), 'a');

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide())
                if (Operation.determinant(a.getMatrice()) != null) {
                    iu.setCenter(new ScalaireAffichage(String.valueOf(Operation.determinantOp(a.getMatrice()))));
                    LecteurDeFichier.genererImprimer(iu);
                    iu.getCenter().setEffect(new DropShadow(1, 1, -1, Color.GREY));
                    iu.setMessage("Opération effectué avec succès!", "informative");
                }
                else
                    iu.setMessage("Veuillez utilisé une matrice carrée", "erreur");
            else
                iu.setMessage("Matrice incomplète!", "erreur");
        });

        HBox hbox = new HBox(a.afficherMatriceDeterminant(iu), egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        iu.setCenter(hbox);
        iu.getCenter().setEffect(new DropShadow(1, 1, -1, Color.GREY));
        iu.setRight(Forme.genererAide(new Tooltip("Déterminant d'une matrice carrée\n" +
                "Soit A = [ aij ]nxn et Aij = (-1)i+j * Mij (Mij étant le miner de l'élément aij)\n" +
                "det A = |A| = la somme de k = 1 à k = n de aik * Aik ou akj * Akj")));
        iu.getScene().setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.R)
                determinant(iu);
        });
    }
}
