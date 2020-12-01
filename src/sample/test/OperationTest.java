package sample.test;

import org.junit.Before;
import org.junit.Test;
import sample.mvc.controlleur.Operation;
import sample.mvc.modele.Matrice;

import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class OperationTest {

    private Matrice a;
    private Matrice b;
    private Matrice c;
    private Matrice identité;
    private Matrice vecteur1;
    private Matrice vecteur2;

    @Before
    public void initialisationCircuitA() {
        a = new Matrice(3, 3);
        a.setElements(DoubleStream.iterate(1, (chiffre) -> chiffre + 1).limit(9).boxed().collect(Collectors.toList()));
        // 1 2 3
        // 4 5 6
        // 7 8 9

        b = new Matrice(2,2);
        b.setElement(1, 1, 1.0);
        b.setElement(1,2,2.0);
        b.setElement(2,1,3.0);
        b.setElement(2,2,4.0);
        // 1 2
        // 3 4

        c = new Matrice(3,2);
        c.setElement(1,1,3);
        c.setElement(1,2,-1);
        c.setElement(2,1,5);
        c.setElement(2,2,0);
        c.setElement(3,1,1);
        c.setElement(3,2,0);
        // 3 -1
        // 5 0
        // 1 0

        identité = new Matrice(3,3);
        identité.setElement(1,1,1);
        identité.setElement(1,2,0);
        identité.setElement(1,3,0);
        identité.setElement(2,1,0);
        identité.setElement(2,2,1);
        identité.setElement(2,3,0);
        identité.setElement(3,1,0);
        identité.setElement(3,2,0);
        identité.setElement(3,3,1);
        // 1 0 0
        // 0 1 0
        // 0 0 1

        vecteur1 = new Matrice(3,1);
        vecteur1.setElement(1,1,1);
        vecteur1.setElement(2,1,2);
        vecteur1.setElement(3,1,3);
        // 1
        // 2
        // 3

        vecteur2 = new Matrice(3,1);
        vecteur2.setElement(1,1,4);
        vecteur2.setElement(2,1,5);
        vecteur2.setElement(3,1,6);
        // 4
        // 5
        // 6
    }

    @Test
    public void addition(){
        assertEquals("2.0 4.0 6.0\n8.0 10.0 12.0\n14.0 16.0 18.0", Operation.addition(a,a).toString());
    }

    @Test
    public void formatAddition(){
        assertNull(Operation.addition(a,b));
        assertNull(Operation.addition(b,a));
    }

    @Test
    public void soustraction(){
        assertEquals("0.0 0.0 0.0\n0.0 0.0 0.0\n0.0 0.0 0.0", Operation.soustraction(a,a).toString());
    }

    @Test
    public void formatSoustraction(){
        assertNull(Operation.soustraction(a,b));
        assertNull(Operation.soustraction(b,a));
    }

    @Test
    public void multiplication(){
        assertEquals("3.0 6.0 9.0\n12.0 15.0 18.0\n21.0 24.0 27.0", Operation.multiplication(a,3).toString());
        assertEquals("-3.0 -6.0 -9.0\n-12.0 -15.0 -18.0\n-21.0 -24.0 -27.0", Operation.multiplication(a,-3).toString());
        assertEquals("0.5 1.0\n1.5 2.0",Operation.multiplication(b,0.5).toString());
    }

    @Test
    public void transposition(){
        assertEquals("3.0 5.0 1.0\n-1.0 0.0 0.0",Operation.transposition(c).toString());
        assertEquals("1.0 4.0 7.0\n2.0 5.0 8.0\n3.0 6.0 9.0", Operation.transposition(a).toString());
    }

    @Test
    public void produitTensoriel(){
        assertEquals("1.0 2.0 2.0 4.0\n3.0 4.0 6.0 8.0\n3.0 6.0 4.0 8.0\n9.0 12.0 12.0 16.0",Operation.produitTensoriel(b,b).toString());
        assertEquals("3.0 -1.0 6.0 -2.0\n5.0 0.0 10.0 0.0\n1.0 0.0 2.0 0.0\n9.0 -3.0 12.0 -4.0\n15.0 0.0 20.0 0.0\n3.0 0.0 4.0 0.0", Operation.produitTensoriel(b,c).toString());
        // 3 -1 6 -2
        // 5  0 10 0
        // 1  0 2  0
        // 9 -3 12 -4
        // 15 0 20 0
        // 3  0 4  0
    }

    @Test
    public void produitMatriciel(){
        assertEquals("1.0 2.0 3.0\n4.0 5.0 6.0\n7.0 8.0 9.0",Operation.produitMatriciel(a,identité).toString());
        assertEquals("0.0 2.0\n5.0 10.0\n1.0 2.0", Operation.produitMatriciel(c,b).toString());
        // 0 2
        // 5 10
        // 1 2
        assertEquals("16.0 -1.0\n43.0 -4.0\n70.0 -7.0",Operation.produitMatriciel(a,c).toString());
        // 16 -1
        // 43 -4
        // 70 -7
        assertNull(Operation.produitMatriciel(b,c));
        assertNull(Operation.produitMatriciel(a,b));
    }

    @Test
    public void produitVectoriel(){
        assertEquals("-3.0\n6.0\n-3.0", Operation.produitVectoriel(vecteur1,vecteur2).toString());
        //  1  4   -3
        //  2  5 =  6
        //  3  6   -3
        assertNull(Operation.produitVectoriel(a,b));
        assertNull(Operation.produitVectoriel(vecteur1,a));
        assertNull(Operation.produitVectoriel(c,vecteur2));
    }

    @Test
    public void produitDHadamard(){

    }




}
