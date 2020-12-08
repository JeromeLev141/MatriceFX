package sample.mvc.controlleur;

import javafx.scene.layout.HBox;
import sample.mvc.vue.InterfaceUtilisateur;
import sample.mvc.vue.MatriceAffichage;
import sample.mvc.vue.ScalaireAffichage;

public class OperationLibre {

    public static void calculer(HBox operation, InterfaceUtilisateur iu) {

        MatriceAffichage resultatMatrice = null;
        ScalaireAffichage resultatScalaire = null;
        int pemda = 1;

        if (operation.getChildren().size() >= 3) {

            for (int i = 0; i < operation.getChildren().size(); i++) {
                if (operation.getChildren().get(i).getId().equals("multiplication"))
                    pemda = i;
            }

            if (operation.getChildren().get(pemda - 1).getId().equals("matrice")) {
                MatriceAffichage a = (MatriceAffichage) operation.getChildren().get(pemda - 1);
                if (a.getMatrice().estValide()) {
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
            else if (operation.getChildren().get(pemda - 1).getId().equals("scalaire")) {
                ScalaireAffichage k = (ScalaireAffichage) operation.getChildren().get(pemda - 1);
                if (k.estValide()) {
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
            if (resultatMatrice.getMatrice() == null && resultatScalaire == null)
                iu.setMessage("Opération impossible!", "erreur");
            if (resultatMatrice.getMatrice() != null || resultatScalaire != null) {
                operation.getChildren().remove(pemda - 1, pemda + 2);
                iu.setMessage("Opération effectué avec succès!", "informative");
            }

            if (resultatMatrice.getMatrice() != null)
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
