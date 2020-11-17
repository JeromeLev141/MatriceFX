package sample;

import sample.controlleur.GenererMatrice;
import sample.modele.Matrice;

public class Main2 {

    public static void main(String[] args) {
        Matrice a = GenererMatrice.genererMatrice(3,3);
        System.out.println(a.toString());
    }
}
