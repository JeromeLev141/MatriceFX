package sample.mvc.controlleur;

import sample.mvc.modele.Matrice;

import java.util.Scanner;

public class GenererMatrice {

    public static Matrice genererMatrice(int m, int n) {
        Matrice matrice = new Matrice(m,n);
        Scanner sc = new Scanner(System.in);
        for (m = 1; m <= matrice.getM(); m++) {
            for (n = 1; n <= matrice.getN(); n++) {
                System.out.println("Éléments a" + m + "" + n);
                matrice.setElement(m, n, sc.nextDouble());
            }
        }
        return matrice;
    }

}
