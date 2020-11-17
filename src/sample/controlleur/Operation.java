package sample.controlleur;

import sample.modele.Matrice;

import java.util.List;
import java.util.stream.Collectors;

public class Operation {

    public Operation() {

    }

    public static Matrice addition(Matrice a, Matrice b) {
        if (memeFormat(a,b)) {
            Matrice r = new Matrice(a.getM(), a.getN());
            for (int x = 0; x < a.getElements().size(); x++)
                r.getElements().add(a.getElements().get(x) + b.getElements().get(x));
            return r;
        }
        else return null;
    }

    public static Matrice soustraction(Matrice a, Matrice b) {
        if (memeFormat(a,b)) {
            Matrice r = new Matrice(a.getM(), a.getN());
            for (int x = 0; x < a.getElements().size(); x++)
                r.getElements().add(a.getElements().get(x) - b.getElements().get(x));
            return r;
        }
        else return null;
    }

    public static Matrice multiplication(Matrice a, double k) {
        Matrice r = new Matrice(a.getM(), a.getN());
        for (int x = 0; x < a.getElements().size(); x++)
            r.getElements().add(a.getElements().get(x) * k);
        return r;
    }

    public static Matrice transposition(Matrice a) {
        Matrice t = new Matrice(a.getN(), a.getM());
        for (int n = 0; n < a.getN(); n++)
            for (int m = 0; m < a.getM(); m++)
                t.getElements().add(a.getElements().get(n + m * a.getN()));
        return t;
    }

    public static Matrice produitVectorielle(Matrice a, Matrice b) {
        Matrice r = new Matrice(a.getM(), b.getN());
        if (!bonFormat(a, b))
            return null;
        else {
            for(int x = 0; x < a.getM(); x++)
                for(int y = 0; y < b.getN(); y++) {

                    double resultat = 0.0D;
                    for (int n = 0, m = 0; n * m < a.getN() * b.getM(); m++, n++)
                        resultat += (Double) a.getElements().get(m + x * a.getM()) * (Double) b.getElements().get(n * b.getN() + y);

                    r.getElements().add(resultat);
                }
            return r;
        }
    }


    private static boolean memeFormat(Matrice a, Matrice b){
        return a.getM() == b.getM() && a.getN() == b.getN();
    }

    private static boolean bonFormat(Matrice a, Matrice b) {
        return a.getN() == b.getM();
    }

    public static List<String> listeFraction(Matrice a) {
        return a.getElements().stream().map(Operation::doubleAFraction).collect(Collectors.toList());
    }

    private static String doubleAFraction(double d) {
        String nombre = String.format("%.2f", d);
        String entier = nombre.substring(0, nombre.indexOf(','));
        String decimal = nombre.substring(nombre.indexOf(',') + 1);

        if (!decimal.equals("00")) {
            for (int x = 100; x > 0; x--) {

                String numerateur = String.valueOf((double) Integer.parseInt(decimal) / x);
                String denominateur = String.valueOf((double) 100 / x);

                if (numerateur.substring(numerateur.indexOf('.') + 1).equals("0") &&
                        denominateur.substring(denominateur.indexOf('.') + 1).equals("0")) {

                    int numerateurInt = Integer.parseInt(numerateur.substring(0, numerateur.indexOf('.')));
                    int denominateurInt = Integer.parseInt(denominateur.substring(0, denominateur.indexOf('.')));

                    return Integer.parseInt(entier) * denominateurInt + numerateurInt + "/" + denominateurInt;
                }
            }
        }
        return entier;
    }
}
