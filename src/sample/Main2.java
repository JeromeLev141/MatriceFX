package sample;

import sample.controlleur.GenererMatrice;
import sample.controlleur.Operation;
import sample.modele.Matrice;

public class Main2 {

    public static void main(String[] args) {

        Matrice a = GenererMatrice.genererMatrice(2,3);
        System.out.println(a.toString());
        System.out.println(Operation.listeFraction(a).toString());
        System.out.println(Operation.transposition(a).toString());
        Matrice b = GenererMatrice.genererMatrice(3,4);
        System.out.println(b.toString());
        Matrice c = Operation.addition(a,b);
        if (c != null)
            System.out.println(c.toString());
        Matrice d = Operation.soustraction(a,b);
        if (d != null)
            System.out.println(d.toString());
        Matrice e = Operation.multiplication(a, 2);
        System.out.println(e.toString());
        Matrice g = Operation.produitVectoriel(a,b );
        if (g != null)
            System.out.println(g.toString());
    }
}
