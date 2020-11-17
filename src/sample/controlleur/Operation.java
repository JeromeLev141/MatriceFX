package sample.controlleur;

import sample.modele.Matrice;

import javax.swing.*;

public class Operation {

    public Operation() {

    }

    public static Matrice addition(Matrice a,Matrice b){
        Matrice r = new Matrice(a.getM(), a.getN());
        if (verif1(a,b)) {
            for (int x = 0; x < a.getElements().size(); x++)
                r.getElements().add(a.getElements().get(x) + b.getElements().get(x));
            return r;
        }
        else
            return null;


    }

    public static Matrice soustraction(Matrice a,Matrice b){
        if (verif1(a,b)) {
            Matrice r = new Matrice(a.getM(), a.getN());
            for (int x = 0; x < a.getElements().size(); x++)
                r.getElements().add(a.getElements().get(x) - b.getElements().get(x));
            return r;
        }
        else
            return null;


    }

    public static Matrice multiplication(Matrice a,double x){
        Matrice r = new Matrice(a.getM(), a.getN());
        for (int i = 0;i<a.getElements().size();i++)
            r.getElements().add(a.getElements().get(i) * x);
        return r;
    }

    private static boolean verif1(Matrice matice1, Matrice matice2){

        if (matice1.getM() == matice2.getM() && matice1.getN() == matice2.getN() )
            return true;
        else
            return false;
    }
}
