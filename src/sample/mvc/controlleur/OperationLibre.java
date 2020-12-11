package sample.mvc.controlleur;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import sample.mvc.vue.*;

public class OperationLibre {

    public static void calculer(HBox operation, VBox demarche, InterfaceUtilisateur iu, boolean premierCycle) {

        if (premierCycle)
            demarche.getChildren().add(genererOperation(operation));

        MatriceAffichage resultatMatrice = null;
        ScalaireAffichage resultatScalaire = null;
        int pemda = 1;
        boolean determinant = false;

        if (operation.getChildren().size() >= 2 || operation.getChildren().get(0).getId().equals("determinant")) {

            //Prioritée des opérations
            for (int i = operation.getChildren().size() - 1; i >= 0; i--) {
                if (operation.getChildren().get(i).getId().equals("multiplication") ||
                        operation.getChildren().get(i).getId().equals("vectoriel") ||
                        operation.getChildren().get(i).getId().equals("hadamard") ||
                        operation.getChildren().get(i).getId().equals("tensoriel"))
                    pemda = i;
            }
            for (int i = operation.getChildren().size() - 1; i >= 0; i--) {
                if (operation.getChildren().get(i).getId().equals("puissance") ||
                        operation.getChildren().get(i).getId().equals("transpose") ||
                        operation.getChildren().get(i).getId().equals("inverse") ||
                        operation.getChildren().get(i).getId().equals("determinant"))
                    pemda = i;
            }
            //Determinant
            if (operation.getChildren().get(pemda).getId().equals("determinant"))
                determinant = true;
            if (determinant) {
                MatriceAffichage a = (MatriceAffichage) operation.getChildren().get(pemda);
                if (a.getMatrice().estValide())
                    if (Operation.determinant(a.getMatrice()) != null)
                        resultatScalaire = new ScalaireAffichage(String.valueOf(Operation.determinantOp(a.getMatrice())));
            }
            //Opération qui commence avec un matrice
            else if (operation.getChildren().get(pemda - 1).getId().equals("matrice")) {
                MatriceAffichage a = (MatriceAffichage) operation.getChildren().get(pemda - 1);
                if (a.getMatrice().estValide()) {
                    if (operation.getChildren().get(pemda).getId().equals("puissance")) {
                        VBox indicePuissance = (VBox) operation.getChildren().get(pemda);
                        ScalaireAffichage k = (ScalaireAffichage) indicePuissance.getChildren().get(0);
                        if (k.estValide())
                            resultatMatrice = new MatriceAffichage(Operation.puissance(a.getMatrice(), k.getValeur()), 'r');
                    }
                    else if (operation.getChildren().get(pemda).getId().equals("transpose"))
                        resultatMatrice = new MatriceAffichage(Operation.transposition(a.getMatrice()), 'r');
                    else if (operation.getChildren().get(pemda).getId().equals("inverse"))
                        resultatMatrice = new MatriceAffichage(Operation.inverse(a.getMatrice()), 'r');
                    else if (pemda != operation.getChildren().size() - 1) {
                        if (operation.getChildren().get(pemda + 1).getId().equals("matrice")) {
                            MatriceAffichage b = (MatriceAffichage) operation.getChildren().get(pemda + 1);
                            if (b.getMatrice().estValide()) {
                                switch (operation.getChildren().get(pemda).getId()) {
                                    case "addition":
                                        resultatMatrice = new MatriceAffichage(Operation.addition(a.getMatrice(), b.getMatrice()), 'r');
                                        break;
                                    case "soustraction":
                                        resultatMatrice = new MatriceAffichage(Operation.soustraction(a.getMatrice(), b.getMatrice()), 'r');
                                        break;
                                    case "multiplication":
                                        resultatMatrice = new MatriceAffichage(Operation.produitMatriciel(a.getMatrice(), b.getMatrice()), 'r');
                                        break;
                                    case "vectoriel":
                                        resultatMatrice = new MatriceAffichage(Operation.produitVectoriel(a.getMatrice(), b.getMatrice()), 'r');
                                        break;
                                    case "hadamard":
                                        resultatMatrice = new MatriceAffichage(Operation.produitDHadamard(a.getMatrice(), b.getMatrice()), 'r');
                                        break;
                                    case "tensoriel":
                                        resultatMatrice = new MatriceAffichage(Operation.produitTensoriel(a.getMatrice(), b.getMatrice()), 'r');
                                        break;
                                }
                            }
                        }
                    }
                    else if (operation.getChildren().get(pemda + 1).getId().equals("scalaire")) {
                        ScalaireAffichage k = (ScalaireAffichage) operation.getChildren().get(pemda + 1);
                        if (k.estValide()) {
                            if (operation.getChildren().get(pemda).getId().equals("multiplication"))
                                resultatMatrice = new MatriceAffichage(Operation.multiplication(a.getMatrice(), k.getValeur()), 'r');
                        }
                    }
                }
            }
            //Opération qui commence avec un scalaire
            else if (operation.getChildren().get(pemda - 1).getId().equals("scalaire")) {
                ScalaireAffichage k = (ScalaireAffichage) operation.getChildren().get(pemda - 1);
                if (k.estValide()) {
                    if (operation.getChildren().get(pemda).getId().equals("puissance")) {
                        VBox indicePuissance = (VBox) operation.getChildren().get(pemda);
                        ScalaireAffichage n = (ScalaireAffichage) indicePuissance.getChildren().get(0);
                        if (n.estValide())
                            resultatScalaire = new ScalaireAffichage(String.valueOf(Math.pow(k.getValeur(), n.getValeur())));
                    }
                    else if (operation.getChildren().get(pemda).getId().equals("inverse"))
                        resultatScalaire = new ScalaireAffichage(String.valueOf(Math.pow(k.getValeur(), -1)));
                    else if (pemda != operation.getChildren().size() - 1) {
                        if (operation.getChildren().get(pemda + 1).getId().equals("matrice")) {
                            MatriceAffichage a = (MatriceAffichage) operation.getChildren().get(pemda + 1);
                            if (a.getMatrice().estValide())
                                if (operation.getChildren().get(pemda).getId().equals("multiplication"))
                                    resultatMatrice = new MatriceAffichage(Operation.multiplication(a.getMatrice(), k.getValeur()), 'r');
                        }
                        else if (operation.getChildren().get(pemda + 1).getId().equals("scalaire")) {
                            ScalaireAffichage k2 = (ScalaireAffichage) operation.getChildren().get(pemda + 1);
                            if (k2.estValide()) {
                                switch (operation.getChildren().get(pemda).getId()) {
                                    case "addition":
                                        resultatScalaire = new ScalaireAffichage(String.valueOf(k.getValeur() + k2.getValeur()));
                                        break;
                                    case "soustraction":
                                        resultatScalaire = new ScalaireAffichage(String.valueOf(k.getValeur() - k2.getValeur()));
                                        break;
                                    case "multiplication":
                                        resultatScalaire = new ScalaireAffichage(String.valueOf(k.getValeur() * k2.getValeur()));
                                        break;
                                }
                            }
                        }
                    }
                }
            }

            if (resultatMatrice == null && resultatScalaire == null) {
                iu.setMessage("Opération impossible!", "erreur");
                iu.setCenter(demarche);
                LecteurDeFichier.genererImprimer(iu);
                iu.getCenter().setEffect(new DropShadow(1, 1, -1, Color.GREY));
            }
            else {
                if (determinant)
                    operation.getChildren().remove(pemda);
                else if (operation.getChildren().get(pemda).getId().equals("puissance") ||
                        operation.getChildren().get(pemda).getId().equals("transpose") ||
                        operation.getChildren().get(pemda).getId().equals("inverse"))
                    operation.getChildren().remove(pemda - 1, pemda + 1);
                else
                    operation.getChildren().remove(pemda - 1, pemda + 2);
                iu.setMessage("Opération effectué avec succès!", "informative");

                if (resultatMatrice != null)
                    operation.getChildren().add(pemda - 1, resultatMatrice.afficherMatrice(iu));
                else {
                    if (determinant)
                        operation.getChildren().add(pemda, resultatScalaire);
                    else
                        operation.getChildren().add(pemda - 1, resultatScalaire);
                }

                HBox token = new HBox(Forme.genererIndiceEgalite(), genererOperation(operation));
                token.setAlignment(Pos.CENTER);
                token.setSpacing(20);

                demarche.getChildren().add(token);

                if (operation.getChildren().size() == 1) {
                    iu.setCenter(demarche);
                    LecteurDeFichier.genererImprimer(iu);
                    iu.getCenter().setEffect(new DropShadow(1, 1, -1, Color.GREY));
                }
                else {
                    calculer(operation, demarche, iu, false);
                }
            }
        }
    }

    public static HBox genererOperation(HBox operation) {
        HBox token = new HBox();
        token.setAlignment(Pos.CENTER);
        token.setSpacing(20);
        for (int i = 0; i < operation.getChildren().size(); i++) {
            switch (operation.getChildren().get(i).getId()) {
                case "scalaire" :
                    ScalaireAffichage k = (ScalaireAffichage) operation.getChildren().get(i);
                    token.getChildren().add(new ScalaireAffichage(String.valueOf(k.getValeur())));
                    break;
                case "determinant" :
                    MatriceAffichage d = (MatriceAffichage) operation.getChildren().get(i);
                    token.getChildren().add(new MatriceAffichage(d.getMatrice(), 'r').afficherDeterminantResultat());
                    break;
                case "matrice" :
                    MatriceAffichage a = (MatriceAffichage) operation.getChildren().get(i);
                    token.getChildren().add(new MatriceAffichage(a.getMatrice(), 'r').afficherMatriceResultat());
                    break;
                case "addition" :
                    token.getChildren().add(Forme.genererIndiceAddition());
                    break;
                case "soustraction" :
                    token.getChildren().add(Forme.genererIndiceSoustraction());
                    break;
                case "multiplication" :
                    token.getChildren().add(Forme.genererIndiceMultiplication());
                    break;
                case "puissance" :
                    VBox vBoxToken = (VBox) operation.getChildren().get(i);
                    ScalaireAffichage ntoken = (ScalaireAffichage) vBoxToken.getChildren().get(0);
                    VBox vBox = Forme.genererIndicePuissance();
                    vBox.getChildren().set(0, new ScalaireAffichage(String.valueOf(ntoken.getValeur())));
                    token.getChildren().add(vBox);
                    break;
                case "transpose" :
                    token.getChildren().add(Forme.genererIndiceTransposition());
                    break;
                case "inverse" :
                    token.getChildren().add(Forme.genererIndiceInverse());
                    break;
                case "vectoriel" :
                    token.getChildren().add(Forme.genererIndiceVectoriel());
                    break;
                case "hadamard" :
                    token.getChildren().add(Forme.genererIndiceHadamard());
                    break;
                case "tensoriel" :
                    token.getChildren().add(Forme.genererIndiceTensoriel());
                    break;
            }
        }
        return token;
    }

    public static Character genererNom(HBox operation) {
        int lettre = 0;
        Character[] alphabet = new Character[]{'a','b','c','d','e','f','g','h','i','j','k','l',
                'm','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        for (int i = 0; i < operation.getChildren().size(); i++)
            if (operation.getChildren().get(i).getId().equals("matrice") || operation.getChildren().get(i).getId().equals("determinant"))
                lettre++;
        if (lettre > 25)
            lettre = 0;
        return alphabet[lettre];
    }
}
