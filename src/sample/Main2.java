package sample;

import sample.mvc.controlleur.LecteurDeFichier;
import sample.mvc.controlleur.Operation;
import sample.mvc.modele.Matrice;
import sample.mvc.modele.MatriceDemarche;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main2 {

    public static void main(String[] args) throws IOException {

        /*Matrice test = new Matrice(2,2);
        System.out.println(test.toString());
        System.out.println(test.estValide());*/
        /*Matrice a = GenererMatrice.genererMatrice(3,3);
        System.out.println(a.getElements().toString());
        System.out.println(a.toString());
        System.out.println(Operation.determinantOp(a));*/
        /*System.out.println(a.estValide());
        System.out.println(Operation.listeFraction(a).toString());
        System.out.println(Operation.transposition(a).toString());
        Matrice b = GenererMatrice.genererMatrice(1,1);
        System.out.println(b.toString());*/
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

        Matrice j = Operation.inverse(a);
        System.out.println(j);*/

        //DecimalFormat df = new DecimalFormat("#,##0.##");

        //System.out.println(df.format(1244550.0512));

        LecteurDeFichier test = new LecteurDeFichier(new File("test2.csv"));
        Matrice a = LecteurDeFichier.stringtoMatrice(test.getliste().get(0));
        Matrice b = LecteurDeFichier.stringtoMatrice(test.getliste().get(4));
        //Matrice v1 = LecteurDeFichier.stringtoMatrice(test.getliste().get(2));
        //Matrice v2 = LecteurDeFichier.stringtoMatrice(test.getliste().get(3));
        List<String> liste = LecteurDeFichier.stringToOperation(test.getliste().get(1));
        System.out.println(a);
        //System.out.println(v1);
        //System.out.println(v2);
        //liste.forEach(System.out::println);
        List<MatriceDemarche> liste2 = new ArrayList<>();
        /*Operation.additionDemarche(liste2,a,a);
        Operation.soustractionDemarche(liste2,a,a);
        Operation.multiplicationDemarche(liste2,a,3);
        Operation.produitMatricielDemarche(liste2,a,a);
        Operation.produitVectorielDemarche(liste2, v1,v2);
        Operation.produitDHadamardDemarche(liste2,a,a);
        Operation.produitTensorielDemarche(liste2,a,a);*/
        Operation.puissanceDemarche(liste2,a,1);
        //Operation.changerligneDemarche(liste2,a,1,2);
        //Operation.determinantOpDemarche(liste2,a);
        //Operation.inverseDemarche(liste2,b);
        liste2.forEach(System.out::println);
        //System.out.println(Operation.inverse(b));
        //System.out.println(Operation.produitMatriciel(a,a));

    }
}
