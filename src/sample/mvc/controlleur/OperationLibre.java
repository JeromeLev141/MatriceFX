package sample.mvc.controlleur;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.mvc.vue.InterfaceUtilisateur;
import sample.mvc.vue.MatriceAffichage;
import sample.mvc.vue.ScalaireAffichage;

public class OperationLibre {

    public static void calculer(HBox operation, InterfaceUtilisateur iu) {

        MatriceAffichage resultatMatrice = null;
        ScalaireAffichage resultatScalaire = null;
        int pemda = 1;

        if (operation.getChildren().size() >= 3) {

            //Prioritée des opérations
            for (int i = operation.getChildren().size() - 1; i > 0; i--) {
                if (operation.getChildren().get(i).getId().equals("multiplication") ||
                        operation.getChildren().get(i).getId().equals("vectoriel") ||
                        operation.getChildren().get(i).getId().equals("hadamard") ||
                        operation.getChildren().get(i).getId().equals("tensoriel"))
                    pemda = i;
            }
            for (int i = operation.getChildren().size() - 1; i > 0; i--) {
                if (operation.getChildren().get(i).getId().equals("puissance") ||
                        operation.getChildren().get(i).getId().equals("transpose") ||
                        operation.getChildren().get(i).getId().equals("inverse"))
                    pemda = i;
            }

            //Opération qui commence avec un matrice
            if (operation.getChildren().get(pemda - 1).getId().equals("matrice")) {
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
                    else if (operation.getChildren().get(pemda + 1).getId().equals("matrice")) {
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
                    else if (operation.getChildren().get(pemda + 1).getId().equals("matrice")) {
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

            if (resultatMatrice == null && resultatScalaire == null)
                iu.setMessage("Opération impossible!", "erreur");
            if (resultatMatrice != null || resultatScalaire != null) {
                if (operation.getChildren().get(pemda).getId().equals("puissance") ||
                        operation.getChildren().get(pemda).getId().equals("transpose") ||
                        operation.getChildren().get(pemda).getId().equals("inverse"))
                    operation.getChildren().remove(pemda - 1, pemda + 1);
                else
                    operation.getChildren().remove(pemda - 1, pemda + 2);
                iu.setMessage("Opération effectué avec succès!", "informative");
            }

            if (resultatMatrice != null)
                operation.getChildren().add(pemda - 1, resultatMatrice.afficherMatrice());
            else if (resultatScalaire != null)
                operation.getChildren().add(pemda - 1, resultatScalaire);
        }
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
