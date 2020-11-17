package sample.controlleur;

import sample.modele.Matrice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
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

    private static boolean memeFormat(Matrice a, Matrice b){
        return a.getM() == b.getM() && a.getN() == b.getN();
    }

    public static List<String> listeFraction(Matrice a) {
        return a.getElements().stream().map(Operation::doubleAFraction).collect(Collectors.toList());
    }

    public static String doubleAFraction(double d) {
        String nombre = String.format("%.2f", d);
        String entier = nombre.substring(0, nombre.indexOf(','));
        String decimal = nombre.substring(nombre.indexOf(',') + 1, nombre.length());

        if (!decimal.equals("00")) {
            for (int x = 100; x > 0; x--) {

                String numerateur = String.valueOf((double) Integer.parseInt(decimal) / x);
                String denominateur = String.valueOf((double) 100 / x);

                if (numerateur.substring(numerateur.indexOf('.') + 1, numerateur.length()).equals("0") &&
                        denominateur.substring(denominateur.indexOf('.') + 1, denominateur.length()).equals("0"))

                    return String.valueOf(Integer.parseInt(entier) * Integer.parseInt(denominateur.substring(0, denominateur.indexOf('.'))) +
                        Integer.parseInt(numerateur.substring(0, numerateur.indexOf('.')))) +
                            "/" + String.valueOf(Integer.parseInt(denominateur.substring(0, denominateur.indexOf('.'))));
            }
        }
        return entier;
    }
}
