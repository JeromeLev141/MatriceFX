package sample;

import sample.mvc.controlleur.GenererMatrice;
import sample.mvc.controlleur.Operation;
import sample.mvc.modele.Matrice;

public class Main2 {

    public static void main(String[] args) {

        Matrice test = new Matrice(2,2);
        System.out.println(test.toString());
        System.out.println(test.estValide());
        Matrice a = GenererMatrice.genererMatrice(3,1);
        System.out.println(a.getElements().toString());
        System.out.println(a.toString());
        System.out.println(a.estValide());
        System.out.println(Operation.listeFraction(a).toString());
        System.out.println(Operation.transposition(a).toString());
        Matrice b = GenererMatrice.genererMatrice(3,1);
        System.out.println(b.toString());
        /*Matrice c = Operation.addition(a,b);
        if (c != null)
            System.out.println(c.toString());
        Matrice d = Operation.soustraction(a,b);
        if (d != null)
            System.out.println(d.toString());
        Matrice e = Operation.multiplication(a, 2);
        System.out.println(e.toString());
        Matrice g = Operation.produitMatriciel(a,b );
        if (g != null)
            System.out.println(g.toString());
        Matrice h = Operation.inverse(a);
        System.out.println(h);
        Matrice i = Operation.produitTensoriel(a,b );
        System.out.println(i);
         */
        Matrice j = Operation.produitVectoriel(a,b);
        System.out.println(j);

    }
}
