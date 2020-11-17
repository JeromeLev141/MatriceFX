package sample.controlleur;

import sample.modele.Matrice;

public class Operation {

    public Operation() {

    }

    static Matrice addition(Matrice a,Matrice b){
        if (verif1(a,b))
            for (int x=0 ; x<a.getElements().size();x++)
                a.getElements().set(x, a.getElements().get(x)+b.getElements().get(x));

        return a;
    }

    static boolean verif1(Matrice matice1, Matrice matice2){

        if (matice1.getM() == matice2.getM() && matice1.getN() == matice2.getN() )
            return true;
        else
            return false;
    }
}
