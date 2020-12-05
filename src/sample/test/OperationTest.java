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
    private Matrice d;
    private Matrice e;
    private Matrice f;
    private Matrice g;
    private Matrice identite;
    private Matrice vecteur1;
    private Matrice vecteur2;

    @Before
    public void initialisationCircuitA() {
        a = new Matrice(3, 3);{
        a.setElements(DoubleStream.iterate(1, (chiffre) -> chiffre + 1).limit(9).boxed().collect(Collectors.toList()));}
        // 1 2 3
        // 4 5 6
        // 7 8 9

        b = new Matrice(2,2);{
            b.setElement(1, 1, 1.0);
            b.setElement(1, 2, 2.0);
            b.setElement(2, 1, 3.0);
            b.setElement(2, 2, 4.0);
        }
        // 1 2
        // 3 4

        c = new Matrice(3,2);{
            c.setElement(1, 1, 3);
            c.setElement(1, 2, -1);
            c.setElement(2, 1, 5);
            c.setElement(2, 2, 0);
            c.setElement(3, 1, 1);
            c.setElement(3, 2, 0);
        }
        // 3 -1
        // 5 0
        // 1 0

        d = new Matrice(3,3);{
            d.setElement(1, 1, 4);
            d.setElement(1, 2, 2);
            d.setElement(1, 3, -5);
            d.setElement(2, 1, -1);
            d.setElement(2, 2, 0);
            d.setElement(2, 3, 1);
            d.setElement(3, 1, -3);
            d.setElement(3, 2, 6);
            d.setElement(3, 3, 0);
        }
        //  4 2 -5
        // -1 0  1
        // -3 6  0

        e = new Matrice(4,4);{
            e.setElement(1, 1,-4 );
            e.setElement(1,2,2);
            e.setElement(1,3,4);
            e.setElement(1,4,9);
            e.setElement(2,1,0);
            e.setElement(2,2,-5);
            e.setElement(2,3,2);
            e.setElement(2,4,-6);
            e.setElement(3,1,1);
            e.setElement(3,2,2);
            e.setElement(3,3,-1);
            e.setElement(3,4,0);
            e.setElement(4,1,-1);
            e.setElement(4,2,4);
            e.setElement(4,3,2);
            e.setElement(4,4,-2);
        }
        // -4  2  4  9
        //  0 -5  2 -6
        //  1  2 -1  0
        // -1  4  2 -2

        f = new Matrice(3,3);{
            f.setElement(1,1,1);
            f.setElement(1,2,2);
            f.setElement(1,3,3);
            f.setElement(2,1,7);
            f.setElement(2,2,14);
            f.setElement(2,3,9);
            f.setElement(3,1,4);
            f.setElement(3,2,5);
            f.setElement(3,3,6);
        }
        //  1  2  3
        //  7 14  9
        //  4  5  6

        g = new Matrice(4,4);{
            g.setElement(1,1,0);
            g.setElement(1,2,0);
            g.setElement(1,3,5);
            g.setElement(1,4,2);
            g.setElement(2,1,3);
            g.setElement(2,2,2);
            g.setElement(2,3,-1);
            g.setElement(2,4,1);
            g.setElement(3,1,0);
            g.setElement(3,2,0);
            g.setElement(3,3,2);
            g.setElement(3,4,4);
            g.setElement(4,1,3);
            g.setElement(4,2,1);
            g.setElement(4,3,1);
            g.setElement(4,4,1);
        }
        //  0  0  5  2
        //  3  2 -1  3
        //  0  0  2  4
        //  3  2  1  4

        identite = new Matrice(3,3);{
            identite.setElement(1, 1, 1);
            identite.setElement(1, 2, 0);
            identite.setElement(1, 3, 0);
            identite.setElement(2, 1, 0);
            identite.setElement(2, 2, 1);
            identite.setElement(2, 3, 0);
            identite.setElement(3, 1, 0);
            identite.setElement(3, 2, 0);
            identite.setElement(3, 3, 1);
        }
        // 1 0 0
        // 0 1 0
        // 0 0 1

        vecteur1 = new Matrice(3,1);{
        vecteur1.setElement(1,1,1);
        vecteur1.setElement(2,1,2);
        vecteur1.setElement(3,1,3);
        }
        // 1
        // 2
        // 3

        vecteur2 = new Matrice(3,1);{
            vecteur2.setElement(1, 1, 4);
            vecteur2.setElement(2, 1, 5);
            vecteur2.setElement(3, 1, 6);
        }
        // 4
        // 5
        // 6
    }

    @Test
    public void addition(){
        assertEquals("2 4 6\n8 10 12\n14 16 18", Operation.addition(a,a).toString());
        //  3  4  6
        //  8 10 12
        // 14 16 18
    }

    @Test
    public void formatAddition(){
        assertNull(Operation.addition(a,b));
        assertNull(Operation.addition(b,a));
    }

    @Test
    public void soustraction(){
        assertEquals("0 0 0\n0 0 0\n0 0 0", Operation.soustraction(a,a).toString());
        // 0 0 0
        // 0 0 0
        // 0 0 0
    }

    @Test
    public void formatSoustraction(){
        assertNull(Operation.soustraction(a,b));
        assertNull(Operation.soustraction(b,a));
    }

    @Test
    public void multiplication(){
        assertEquals("3 6 9\n12 15 18\n21 24 27", Operation.multiplication(a,3).toString());
        //  3  6  9
        // 12 15 18
        // 21 24 27
        assertEquals("-3 -6 -9\n-12 -15 -18\n-21 -24 -27", Operation.multiplication(a,-3).toString());
        //  -3  -6  -9
        // -12 -15 -27
        // -21 -24 -27
        assertEquals("0,5 1\n1,5 2",Operation.multiplication(b,0.5).toString());
        // 0.5  1
        // 1.5  2
    }

    @Test
    public void transposition(){
        assertEquals("3 5 1\n-1 0 0",Operation.transposition(c).toString());
        //  3  5  1
        // -1  0  0
        assertEquals("1 4 7\n2 5 8\n3 6 9", Operation.transposition(a).toString());
        // 1 4 7
        // 2 5 8
        // 3 6 9
    }

    @Test
    public void produitTensoriel(){
        assertEquals("1 2 2 4\n3 4 6 8\n3 6 4 8\n9 12 12 16",Operation.produitTensoriel(b,b).toString());
        //  1  2  2  4
        //  3  4  6  8
        //  3  6  4  8
        //  9 12 12 16
        assertEquals("3 -1 6 -2\n5 0 10 0\n1 0 2 0\n9 -3 12 -4\n15 0 20 0\n3 0 4 0", Operation.produitTensoriel(b,c).toString());
        //  3 -1  6 -2
        //  5  0 10  0
        //  1  0  2  0
        //  9 -3 12 -4
        // 15  0 20  0
        //  3  0  4  0
    }

    @Test
    public void produitMatriciel(){
        assertEquals("1 2 3\n4 5 6\n7 8 9",Operation.produitMatriciel(a,identite).toString());
        assertEquals("0 2\n5 10\n1 2", Operation.produitMatriciel(c,b).toString());
        // 0  2
        // 5 10
        // 1  2
        assertEquals("16 -1\n43 -4\n70 -7",Operation.produitMatriciel(a,c).toString());
        // 16 -1
        // 43 -4
        // 70 -7

    }

    @Test
    public void produitMatricielFormat(){
        assertNull(Operation.produitMatriciel(b,c));
        assertNull(Operation.produitMatriciel(a,b));
    }

    @Test
    public void produitVectoriel(){
        assertEquals("-3\n6\n-3", Operation.produitVectoriel(vecteur1,vecteur2).toString());
        //  1    4   -3
        //  2 *  5 =  6
        //  3    6   -3
        assertEquals("3\n-6\n3", Operation.produitVectoriel(vecteur2,vecteur1).toString());
        //  3
        // -6
        //  3
    }

    @Test
    public void produitVectorielFormat(){
        assertNull(Operation.produitVectoriel(a,b));
        assertNull(Operation.produitVectoriel(vecteur1,a));
        assertNull(Operation.produitVectoriel(c,vecteur2));
    }

    @Test
    public void produitDHadamard(){
        assertEquals("4\n10\n18", Operation.produitDHadamard(vecteur1,vecteur2).toString());
        //  4
        // 10
        // 18
        assertEquals("1 0 0\n0 5 0\n0 0 9", Operation.produitDHadamard(a,identite).toString());
        // 1 0 0
        // 0 5 0
        // 0 0 9
        assertEquals("4 4 -15\n-4 0 6\n-21 48 0", Operation.produitDHadamard(a,d ).toString());
        //   4   4 -15
        //  -4   0   6
        // -21  48   0
    }

    @Test
    public void produitDHadamardFormat(){
        assertNull(Operation.produitDHadamard(a,b));
        assertNull(Operation.produitDHadamard(vecteur1,d));
    }

    @Test
    public void inverse(){
        assertEquals("-2 1\n1,5 -0,5",Operation.inverse(b).toString());
        //-2    1
        // 1.5 -0.5
        assertEquals("0,38 0,69 2,12 -0,38\n-0,02 -0,07 0,08 0,14\n0,35 0,56 1,27 -0,11\n0,13 0,08 0,36 -0,15",Operation.inverse(e).toString());
        //  0.38  0.69  2.12 -0.38
        // -0.02 -0.07  0.08  0.14
        //  0.35  0.56  1.27 -0.11
        //  0.13  0.08  0.36 -0.15
    }

    @Test
    public void inversedet0(){
        assertNull(Operation.inverse(a));
        assertNull(Operation.inverse(d));
    }

    @Test
    public void inverseFormat(){
        assertNull(Operation.inverse(c));
        assertNull(Operation.inverse(vecteur1));
    }

    @Test
    public void determinant(){
        assertEquals("-133", Operation.determinant(e).toString());
        assertEquals("0", Operation.determinant(a).toString());
        assertEquals("-2", Operation.determinant(b).toString());
        assertEquals("0", Operation.determinant(d).toString());
        assertEquals("1", Operation.determinant(identite).toString());
    }

    @Test
    public void determinantFormat(){
        assertNull(Operation.determinant(vecteur1));
        assertNull(Operation.determinant(vecteur2));
        assertNull(Operation.determinant(c));
    }

    @Test
    public void determinantOp(){
        assertEquals(-133.0,Operation.determinantOp(e));
        assertEquals(0.0,Operation.determinantOp(a));
        assertEquals(-2.0, Operation.determinantOp(b));
        assertEquals(0.0, Operation.determinantOp(d));
        assertEquals(-36.0,Operation.determinantOp(f));
        assertEquals(0.0,Operation.determinantOp(g));
    }

    @Test
    public void puissance(){
        assertEquals("24 -368 -38 75\n-98 111 150 -42\n32 46 -55 21\n-5 26 -76 286", Operation.puissance(e,3).toString());
        //   24 -368  -38   75
        //  -98  111  150  -42
        //   32   46  -55   21
        //   -5   26  -76  286
        assertEquals("1 0 0\n0 1 0\n0 0 1", Operation.puissance(a, 0).toString());
        // 1 0 0
        // 0 1 0
        // 0 0 1
        assertEquals("468 576 684\n1 062 1 305 1 548\n1 656 2 034 2 412",Operation.puissance(a,3).toString());
        //  468  576  684
        // 1062 1305 1548
        // 1656 2034 2412
        assertEquals("37 54\n81 118",Operation.puissance(b,3).toString());
        // 37  54
        // 81 118
        assertEquals("1 2 3\n4 5 6\n7 8 9",Operation.puissance(a,1).toString());
        // 1 2 3
        // 4 5 6
        // 7 8 9
    }

    @Test
    public void puissanceFormat(){
        assertNull(Operation.puissance(c,3));
        assertNull(Operation.puissance(vecteur1,2));
    }

    @Test
    public void puissancedet0(){
        assertNull(Operation.puissance(a,-2));
        assertNull(Operation.puissance(d,-5));
    }

    @Test
    public void changerligne(){
        Matrice tempo = Operation.changerligne(a,2,3);
        assertEquals("1 2 3\n-7 -8 -9\n4 5 6",tempo.toString());
        assertEquals("-4 -5 -6\n-7 -8 -9\n1 2 3",Operation.changerligne(tempo,1,3).toString());
    }





}
