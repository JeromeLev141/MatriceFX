package sample.controlleur;

import sample.modele.Matrice;

import java.util.Scanner;

public class GenererMatrice {

    public static Matrice genererMatrice(int m, int n) {
        Matrice matrice = new Matrice(m,n);
        Scanner sc = new Scanner(System.in);
        for (int x = 1; x <= matrice.getM(); x++) {
            for (int y = 1; y <= matrice.getN(); y++) {
                System.out.println("Éléments a" + x + "" + y);
                matrice.getElements().add(sc.nextDouble());
            }
        }
        return matrice;
    }
}
