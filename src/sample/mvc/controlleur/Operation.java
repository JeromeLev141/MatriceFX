package sample.mvc.controlleur;

import sample.mvc.modele.Matrice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Operation {

    public static Matrice addition(Matrice a, Matrice b) {
        if (memeFormat(a,b)) {
            Matrice r = new Matrice(a.getM(), a.getN());
            for (int x = 0; x < a.getElements().size(); x++)
                r.getElements().set(x, a.getElements().get(x) + b.getElements().get(x));
            return r;
        }
        else return null;
    }

    public static Matrice soustraction(Matrice a, Matrice b) {
        if (memeFormat(a,b)) {
            Matrice r = new Matrice(a.getM(), a.getN());
            for (int x = 0; x < a.getElements().size(); x++)
                r.getElements().set(x, a.getElements().get(x) - b.getElements().get(x));
            return r;
        }
        else return null;
    }

    public static Matrice multiplication(Matrice a, double k) {
        Matrice r = new Matrice(a.getM(), a.getN());
        r.setElements(a.getElements().stream().map((nombre) -> nombre * k).collect(Collectors.toList()));
        return r;
    }

    public static Matrice transposition(Matrice a) {
        Matrice t = new Matrice(a.getN(), a.getM());
        for (int m = 1; m <= t.getM(); m++)
            for (int n = 1; n <= t.getN(); n++)
                t.setElement(m, n, a.getElement(n, m));
        return t;
    }

    public static Matrice produitTensoriel(Matrice a, Matrice b){
        Matrice r = new Matrice(a.getM()*b.getM(), a.getN()*b.getN());
        for (int m = 0; m < a.getM(); m++)
            for (int n = 0; n < a.getN(); n++)
                for (int m2 = 1; m2 <= b.getM(); m2++)
                    for (int n2 = 1; n2 <= b.getN(); n2++)
                        r.setElement(m * b.getM() + m2,(n * b.getN()) + n2,a.getElement(m+1,n+1)*b.getElement(m2,n2));
        return r;
    }

    public static Matrice produitMatriciel(Matrice a, Matrice b) {
        Matrice r = new Matrice(a.getM(), b.getN());
        if (bonFormat(a, b)) {
            for(int m = 1; m <= a.getM(); m++) {
                for(int n = 1; n <= b.getN(); n++) {
                    double sommeProduits = 0;
                    for(int x = 1; x <= a.getN(); x++) {
                        sommeProduits += a.getElement(m, x) * b.getElement(x, n);
                    }
                    r.setElement(m, n, sommeProduits);
                }
            }
            return r;
        }
        else return null;
    }

    public static Matrice produitVectoriel(Matrice a, Matrice b){
        Matrice r = new Matrice(3,1 );
        if (Operation.isVecteur(a)){
            r.setElement(1,1,a.getElement(2,1) * b.getElement(3,1) - a.getElement(3,1) * b.getElement(2,1));
            r.setElement(2,1,a.getElement(3,1) * b.getElement(1,1) - a.getElement(1,1) * b.getElement(3,1));
            r.setElement(3,1,a.getElement(1,1) * b.getElement(2,1) - a.getElement(2,1) * b.getElement(1,1));
            return r;
        }
        else return null;
    }

    public static Matrice produitDHadamard(Matrice a, Matrice b){

        if (memeFormat(a,b)) {
            Matrice r = new Matrice(a.getM(), a.getN());
            for (int m = 1; m <= a.getM(); m++)
                for (int n = 1; n <= a.getN(); n++)
                    r.setElement(m,n,a.getElement(m,n) * b.getElement(m,n));
            return r;
        }
        else return null;
    }

    public static Matrice inverse(Matrice a){
        Matrice adj = new Matrice(a.getM(),a.getN());
        Matrice r = new Matrice(a.getM()-1, a.getN()-1);
        r.getElements().clear();
        if (a.isEstCarre()){
            for (int m1 = 1; m1 <= a.getM(); m1++){
                for (int n1 = 1; n1 <= a.getN(); n1++){

                    for(int m2 = 1; m2 <= a.getM(); m2++){
                        for (int n2 = 1; n2 <= a.getN(); n2++){
                            if (m2 != m1 && n2 != n1)
                                r.getElements().add(a.getElement(m2, n2));
                        }
                    }
                    adj.setElement(m1,n1,Operation.determinant(r).getElement(1, 1));
                    r.getElements().clear();
                }
            }
            adj = Operation.transposition(adj);
            return Operation.multiplication(adj, 1/Operation.determinant(a).getElement(1, 1));
        }
        else return null;
    }

    public static Matrice determinant(Matrice a){


        if (a.getM() == 1 && a.isEstCarre()){

            Matrice r = new Matrice(1, 1);
            r.setElement(1,1,a.getElements().get(0));
            return r;
        }
        else if (a.getM() == 2 && a.isEstCarre()){

            Matrice r = new Matrice(1, 1);
            r.setElement(1,1,((a.getElement(1, 1) * a.getElement(2,2)) - (a.getElement(1,2) * a.getElement(2,1))));
            return r;
        }

        else if (a.isEstCarre()){
            double resultat = 0;
            List<Matrice> liste = new ArrayList<>();
            for (int m = 1; m <= a.getM(); m++) {
                Matrice r = new Matrice(a.getM()-1, a.getN()-1);
                r.getElements().clear();
                System.out.println(r.getElements().size());
                for (int m2 = 1; m2 <= a.getM(); m2++) {
                    for (int n = 1; n <= a.getN(); n++) {
                        if (n != 1 && m2 != m) {

                            r.getElements().add(a.getElement(m2, n));
                        }
                    }
                }
                r = Operation.multiplication(Operation.determinant(r), a.getElement(m, 1) * (Math.pow(-1, m+1)));
                liste.add(r);

            }
            for (Matrice t: liste
                 ) { resultat += t.getElements().get(0);

            }

            Matrice t = new Matrice(1, 1);
            t.setElement(1, 1, resultat);
            return t;
        }
        else
            return null;
    }

    public static Matrice puissance(Matrice a , int pow){
        Matrice r =  new Matrice(a.getM(),a.getN());
        for (int m = 1; m <= a.getM(); m++ )
            for (int n = 1; n <= a.getN(); n++)
                r.setElement(m,n,a.getElement(m, n) );

        for (int x = 0; x < pow; x++){
            r = Operation.produitMatriciel(r,a );
        }
        return r;
    }


    private static boolean memeFormat(Matrice a, Matrice b){
        return a.getM() == b.getM() && a.getN() == b.getN();
    }

    private static boolean bonFormat(Matrice a, Matrice b) {
        return a.getN() == b.getM();
    }

    private static boolean isVecteur(Matrice a){
        if (a.getM() == 3 && a.getN() ==1)
            return true;
        else return false;
    }

    public static List<String> listeFraction(Matrice a) {
        return a.getElements().stream().map(Operation::doubleAFraction).collect(Collectors.toList());
    }

    public static String doubleAFraction(double d) {
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
