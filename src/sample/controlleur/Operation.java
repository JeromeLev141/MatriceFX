package sample.controlleur;

import sample.modele.Matrice;

public class Operation {

    public Operation() {

    }

    public static Matrice addition(Matrice a, Matrice b){
        if (memeFormat(a,b)) {
            Matrice r = new Matrice(a.getM(), a.getN());
            for (int x = 0; x < a.getElements().size(); x++)
                r.getElements().add(a.getElements().get(x) + b.getElements().get(x));
            return r;
        }
        else return null;
    }

    public static Matrice soustraction(Matrice a, Matrice b){
        if (memeFormat(a,b)) {
            Matrice r = new Matrice(a.getM(), a.getN());
            for (int x = 0; x < a.getElements().size(); x++)
                r.getElements().add(a.getElements().get(x) - b.getElements().get(x));
            return r;
        }
        else return null;
    }

    public static Matrice multiplication(Matrice a, double k){
        Matrice r = new Matrice(a.getM(), a.getN());
        for (int x = 0; x < a.getElements().size(); x++)
            r.getElements().add(a.getElements().get(x) * k);
        return r;
    }

    public static Matrice transposition(Matrice a){
        Matrice t = new Matrice(a.getN(), a.getM());
        for (int n = 0; n < a.getN(); n++)
            for (int m = 0; m < a.getM(); m++)
                t.getElements().add(a.getElements().get(n + m * a.getN()));
        return t;
    }

    private static boolean memeFormat(Matrice a, Matrice b){
        return a.getM() == b.getM() && a.getN() == b.getN();
    }
}
