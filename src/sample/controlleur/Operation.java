package sample.controlleur;

import sample.modele.Matrice;

import javax.swing.*;

public class Operation {

    public Operation() {

    }

    public static Matrice addition(Matrice a,Matrice b){
        if (verif1(a,b))
            for (int x=0 ; x<a.getElements().size();x++)
                a.getElements().set(x, a.getElements().get(x)+b.getElements().get(x));
        else
            return null;

        return a;
    }

    public static Matrice soustraction(Matrice a,Matrice b){
        if (verif1(a,b))
            for (int x=0 ; x<a.getElements().size();x++)
                a.getElements().set(x, a.getElements().get(x)-b.getElements().get(x));
        else
            return null;

        return a;
    }

    public static Matrice multiplication(Matrice a,double x){
        for (int i = 0;i<a.getElements().size();i++)
            a.getElements().set(i, a.getElements().get(i)*x);

        return a;
    }

    private static boolean verif1(Matrice matice1, Matrice matice2){

        if (matice1.getM() == matice2.getM() && matice1.getN() == matice2.getN() )
            return true;
        else
            return false;
    }
}
