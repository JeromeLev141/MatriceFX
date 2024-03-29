package sample.mvc.vue;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import sample.mvc.controlleur.LecteurDeFichier;
import sample.mvc.controlleur.Operation;
import sample.mvc.controlleur.OperationLibre;
import sample.mvc.modele.Matrice;
import sample.mvc.modele.MatriceDemarche;
import sample.mvc.vue.audios.Son;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OperationAffichage {

    public static VBox genererDemarche(HBox operation, List<MatriceDemarche> matrices) {
        operation.setEffect(null);
        VBox demarche = new VBox(operation);
        demarche.setAlignment(Pos.CENTER);
        demarche.setSpacing(20);
        for (MatriceDemarche matrix : matrices) demarche.getChildren().add(new MatriceDemarcheAffichage(matrix).afficherMatrice());
        return demarche;
    }

    public static void operer(VBox demarche, InterfaceUtilisateur iu) {
        ScrollPane sp = new ScrollPane(demarche);
        sp.setFitToHeight(true);
        sp.setFitToWidth(true);
        sp.setPrefViewportHeight(400);
        sp.setFocusTraversable(false);
        iu.setCenter(sp);
        sp.getContent().setEffect(new DropShadow(1, 1, -1, Color.GREY));
        LecteurDeFichier.genererImprimer(iu);
        iu.setMessage("Opération effectué avec succès!", "informative");
    }

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
            else if (keyEvent.getCode() == KeyCode.R) {
                libre(iu);
                MediaView bruit = new MediaView();
                bruit.setMediaPlayer(Son.reloadSon());
                bruit.getMediaPlayer().play();
            }
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
                    HBox operation = (HBox) iu.getCenter();
                    operation.getChildren().remove(3);
                    operation.getChildren().set(0, a.afficherMatriceResultat());
                    operation.getChildren().set(2, b.afficherMatriceResultat());
                    operer(genererDemarche(operation, Operation.additionDemarche(a.getMatrice(), b.getMatrice())), iu);
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
            if (keyEvent.getCode() == KeyCode.R) {
                addition(iu);
                MediaView bruit = new MediaView();
                bruit.setMediaPlayer(Son.reloadSon());
                bruit.getMediaPlayer().play();
            }
        });
    }

    public static void soustraction(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3), 'a');
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3), 'b');

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide() && b.getMatrice().estValide()) {
                if (Operation.soustraction(a.getMatrice(), b.getMatrice()) != null) {
                    HBox operation = (HBox) iu.getCenter();
                    operation.getChildren().remove(3);
                    operation.getChildren().set(0, a.afficherMatriceResultat());
                    operation.getChildren().set(2, b.afficherMatriceResultat());
                    operer(genererDemarche(operation, Operation.soustractionDemarche(a.getMatrice(), b.getMatrice())), iu);
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
            if (keyEvent.getCode() == KeyCode.R) {
                soustraction(iu);
                MediaView bruit = new MediaView();
                bruit.setMediaPlayer(Son.reloadSon());
                bruit.getMediaPlayer().play();
            }
        });
    }

    public static void multiplication(InterfaceUtilisateur iu) {
        ScalaireAffichage k = new ScalaireAffichage();
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3), 'a');

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide() && k.estValide()) {
                HBox operation = (HBox) iu.getCenter();
                operation.getChildren().remove(3);
                operation.getChildren().set(2, a.afficherMatriceResultat());
                operer(genererDemarche(operation,
                        Operation.multiplicationDemarche(new ArrayList<>(), a.getMatrice(), k.getValeur())), iu);
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
            if (keyEvent.getCode() == KeyCode.R) {
                multiplication(iu);
                MediaView bruit = new MediaView();
                bruit.setMediaPlayer(Son.reloadSon());
                bruit.getMediaPlayer().play();
            }
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
                        HBox operation = (HBox) iu.getCenter();
                        operation.getChildren().remove(2);
                        operation.getChildren().set(0, a.afficherMatriceResultat());
                        operer(genererDemarche(operation, Operation.puissanceDemarche(a.getMatrice(), k.getValeur())), iu);
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
            if (keyEvent.getCode() == KeyCode.R) {
                puissance(iu);
                MediaView bruit = new MediaView();
                bruit.setMediaPlayer(Son.reloadSon());
                bruit.getMediaPlayer().play();
            }
        });
    }

    public static void transposition(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3), 'a');

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide()) {
                HBox operation = (HBox) iu.getCenter();
                operation.getChildren().remove(2);
                operation.getChildren().set(0, a.afficherMatriceResultat());
                operer(genererDemarche(operation, Operation.transpositionDemarche(new ArrayList<>(), a.getMatrice())), iu);
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
            if (keyEvent.getCode() == KeyCode.R) {
                transposition(iu);
                MediaView bruit = new MediaView();
                bruit.setMediaPlayer(Son.reloadSon());
                bruit.getMediaPlayer().play();
            }
        });
    }

    public static void inversion(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3), 'a');

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide()) {
                if (Operation.inverse(a.getMatrice()) != null) {
                    HBox operation = (HBox) iu.getCenter();
                    operation.getChildren().remove(2);
                    operation.getChildren().set(0, a.afficherMatriceResultat());
                    operer(genererDemarche(operation, Operation.inverseDemarche(new ArrayList<>(), a.getMatrice())), iu);
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
            if (keyEvent.getCode() == KeyCode.R) {
                inversion(iu);
                MediaView bruit = new MediaView();
                bruit.setMediaPlayer(Son.reloadSon());
                bruit.getMediaPlayer().play();
            }
        });
    }

    public static void produitMatriciel(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3), 'a');
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3), 'b');

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide() && b.getMatrice().estValide()) {
                if (Operation.produitMatriciel(a.getMatrice(), b.getMatrice()) != null) {
                    HBox operation = (HBox) iu.getCenter();
                    operation.getChildren().remove(3);
                    operation.getChildren().set(0, a.afficherMatriceResultat());
                    operation.getChildren().set(2, b.afficherMatriceResultat());
                    operer(genererDemarche(operation,
                            Operation.produitMatricielDemarche(new ArrayList<>(), a.getMatrice(), b.getMatrice())), iu);
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
            if (keyEvent.getCode() == KeyCode.R) {
                produitMatriciel(iu);
                MediaView bruit = new MediaView();
                bruit.setMediaPlayer(Son.reloadSon());
                bruit.getMediaPlayer().play();
            }
        });
    }

    public static void produitVectoriel(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 1), 'a');
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 1), 'b');

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide() && b.getMatrice().estValide()) {
                if (Operation.produitVectoriel(a.getMatrice(), b.getMatrice()) != null &&
                Operation.produitVectoriel(a.getMatrice(), b.getMatrice()) != null) {
                    HBox operation = (HBox) iu.getCenter();
                    operation.getChildren().remove(3);
                    operation.getChildren().set(0, a.afficherMatriceResultat());
                    operation.getChildren().set(2, b.afficherMatriceResultat());
                    operer(genererDemarche(operation, Objects.requireNonNull(Operation.produitVectorielDemarche(a.getMatrice(), b.getMatrice()))), iu);
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
            if (keyEvent.getCode() == KeyCode.R) {
                produitVectoriel(iu);
                MediaView bruit = new MediaView();
                bruit.setMediaPlayer(Son.reloadSon());
                bruit.getMediaPlayer().play();
            }
        });
    }

    public static void produitHadamard(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3), 'a');
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3), 'b');

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide() && b.getMatrice().estValide()) {
                if (Operation.produitDHadamard(a.getMatrice(), b.getMatrice()) != null) {
                    HBox operation = (HBox) iu.getCenter();
                    operation.getChildren().remove(3);
                    operation.getChildren().set(0, a.afficherMatriceResultat());
                    operation.getChildren().set(2, b.afficherMatriceResultat());
                    operer(genererDemarche(operation, Operation.produitDHadamardDemarche(a.getMatrice(), b.getMatrice())), iu);
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
            if (keyEvent.getCode() == KeyCode.R) {
                produitHadamard(iu);
                MediaView bruit = new MediaView();
                bruit.setMediaPlayer(Son.reloadSon());
                bruit.getMediaPlayer().play();
            }
        });
    }

    public static void produitTensoriel(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3), 'a');
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3), 'b');

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide() && b.getMatrice().estValide() && 
            Operation.produitTensoriel(a.getMatrice(), b.getMatrice()) != null) {
                HBox operation = (HBox) iu.getCenter();
                operation.getChildren().remove(3);
                operation.getChildren().set(0, a.afficherMatriceResultat());
                operation.getChildren().set(2, b.afficherMatriceResultat());
                operer(genererDemarche(operation, Objects.requireNonNull(Operation.produitTensorielDemarche(a.getMatrice(), b.getMatrice()))), iu);
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
            if (keyEvent.getCode() == KeyCode.R) {
                produitTensoriel(iu);
                MediaView bruit = new MediaView();
                bruit.setMediaPlayer(Son.reloadSon());
                bruit.getMediaPlayer().play();
            }
        });
    }

    public static void determinant(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3), 'a');

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide())
                if (Operation.determinant(a.getMatrice()) != null) {
                    HBox operation = (HBox) iu.getCenter();
                    operation.getChildren().remove(1);
                    operation.getChildren().set(0, a.afficherDeterminantResultat());
                    operer(genererDemarche(operation, Operation.determinantOpDemarche(new ArrayList<>(), a.getMatrice())), iu);
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
            if (keyEvent.getCode() == KeyCode.R) {
                determinant(iu);
                MediaView bruit = new MediaView();
                bruit.setMediaPlayer(Son.reloadSon());
                bruit.getMediaPlayer().play();
            }
        });
    }
}
