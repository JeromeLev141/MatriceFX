package sample;

import sample.controlleur.GenererMatrice;
import sample.controlleur.Operation;
import sample.modele.Matrice;

public class Main2 {

    public static void main(String[] args) {
        Matrice a = GenererMatrice.genererMatrice(3,3);
        System.out.println(a.toString());
        Matrice b = GenererMatrice.genererMatrice(3,3);
        System.out.println(b.toString());
        Matrice c = Operation.addition(a,b);
        System.out.println(c.toString());
        Matrice d = Operation.soustraction(a,b);
        System.out.println(d.toString());
        Matrice e = Operation.multiplication(a, 2);
        System.out.println(e.toString());
    }
}
