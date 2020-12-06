package sample.mvc.vue;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
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
        matrice.setOnAction(event -> operation.getChildren().add(new MatriceAffichage(new Matrice(3, 3)).afficherMatrice()));

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
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3));

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide() && b.getMatrice().estValide()) {
                if (Operation.addition(a.getMatrice(), b.getMatrice()) != null) {
                    iu.setCenter(new MatriceAffichage(Operation.addition(a.getMatrice(), b.getMatrice())).afficherMatriceResultat());
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
    }

    public static HBox soustraction(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3));

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide() && b.getMatrice().estValide()) {
                if (Operation.soustraction(a.getMatrice(), b.getMatrice()) != null) {
                    iu.setCenter(new MatriceAffichage(Operation.soustraction(a.getMatrice(), b.getMatrice())).afficherMatriceResultat());
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
        return hbox;
    }

    public static HBox multiplication(InterfaceUtilisateur iu) {
        ScalaireAffichage k = new ScalaireAffichage();
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide() && k.estValide()) {
                    iu.setCenter(new MatriceAffichage(Operation.multiplication(a.getMatrice(), k.getValeur())).afficherMatriceResultat());
                    iu.setMessage("Opération effectué avec succès!", "informative");
            }
            else
                iu.setMessage("Élements incomplets!", "erreur");
        });

        HBox hbox = new HBox(k, Forme.genererIndiceMultiplication(), a.afficherMatrice(), egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(30);
        return hbox;
    }

    public static HBox puissance(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));
        VBox indicePuissance = Forme.genererIndicePuissance();

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            ScalaireAffichage k = (ScalaireAffichage) indicePuissance.getChildren().get(0);
            if (a.getMatrice().estValide() && k.estValide()) {
                if (Operation.puissance(a.getMatrice(), k.getValeur()) != null) {
                    iu.setCenter(new MatriceAffichage(Operation.puissance(a.getMatrice(), k.getValeur())).afficherMatriceResultat());
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
        return hbox;
    }

    public static HBox transposition(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide())
                iu.setCenter(new MatriceAffichage(Operation.transposition(a.getMatrice())).afficherMatriceResultat());
            else
                iu.setMessage("Matrice incomplète!", "erreur");
        });

        HBox hbox = new HBox(a.afficherMatrice(), Forme.genererIndiceTransposition(), egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        return hbox;
    }

    public static HBox inversion(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide()) {
                if (Operation.inverse(a.getMatrice()) != null) {
                    iu.setCenter(new MatriceAffichage(Operation.inverse(a.getMatrice())).afficherMatriceResultat());
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
        return hbox;
    }

    public static HBox produitMatriciel(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3));

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide() && b.getMatrice().estValide()) {
                if (Operation.produitMatriciel(a.getMatrice(), b.getMatrice()) != null) {
                    iu.setCenter(new MatriceAffichage(Operation.produitMatriciel(a.getMatrice(), b.getMatrice())).afficherMatriceResultat());
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
        return hbox;
    }

    public static HBox produitVectoriel(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 1));
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 1));

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide() && b.getMatrice().estValide()) {
                if (Operation.produitVectoriel(a.getMatrice(), b.getMatrice()) != null) {
                    iu.setCenter(new MatriceAffichage(Operation.produitVectoriel(a.getMatrice(), b.getMatrice())).afficherMatriceResultat());
                    iu.setMessage("Opération effectué avec succès!", "informative");
                }
                else
                    iu.setMessage("Opération impossible!", "erreur");
            }
            else
                iu.setMessage("Matrices incomplètes!", "erreur");
        });

        HBox hbox = new HBox(a.afficherMatriceResultat(), Forme.genererIndiceVectoriel(), b.afficherMatriceResultat(), egale);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        return hbox;
    }

    public static HBox produitHadamard(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3));

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide() && b.getMatrice().estValide()) {
                if (Operation.produitDHadamard(a.getMatrice(), b.getMatrice()) != null) {
                    iu.setCenter(new MatriceAffichage(Operation.produitDHadamard(a.getMatrice(), b.getMatrice())).afficherMatriceResultat());
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
        return hbox;
    }

    public static HBox produitTensoriel(InterfaceUtilisateur iu) {
        MatriceAffichage a = new MatriceAffichage(new Matrice(3, 3));
        MatriceAffichage b = new MatriceAffichage(new Matrice(3, 3));

        Button egale = new Button("=");
        egale.setOnAction(event -> {
            if (a.getMatrice().estValide() && b.getMatrice().estValide()) {
                iu.setCenter(new MatriceAffichage(Operation.produitTensoriel(a.getMatrice(), b.getMatrice())).afficherMatriceResultat());
                iu.setMessage("Opération effectué avec succès!", "informative");
            }
            else
                iu.setMessage("Matrices incomplètes!", "erreur");
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
        return hbox;
    }
}
