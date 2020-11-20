package sample.test;

import org.junit.Before;
import org.junit.Test;
import sample.mvc.modele.Matrice;

import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static junit.framework.Assert.*;

public class MatriceTest {

    private Matrice a;
    private Matrice b;

    @Before
    public void initialisationCircuitA() {
        a = new Matrice(3, 3);
        a.setElements(DoubleStream.iterate(1, (chiffre) -> chiffre + 1).limit(9).boxed().collect(Collectors.toList()));
        // 1 2 3
        // 4 5 6
        // 7 8 9

        b = new Matrice(2, 4);
        b.setElements(DoubleStream.iterate(9, (chiffre) -> chiffre - 1).limit(8).boxed().collect(Collectors.toList()));
        // 9 8 7 6
        // 5 4 3 2
    }

    //peut etre ajouter constructeurtest

    @Test
    public void getElementsTest() {
        assertEquals(DoubleStream.iterate(1, (chiffre) -> chiffre + 1).limit(9).boxed().collect(Collectors.toList()), a.getElements());
        assertEquals("[1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0]", a.getElements().toString());
        assertEquals(9, a.getElements().size());

        assertEquals(DoubleStream.iterate(9, (chiffre) -> chiffre - 1).limit(8).boxed().collect(Collectors.toList()), b.getElements());
        assertEquals("[9.0, 8.0, 7.0, 6.0, 5.0, 4.0, 3.0, 2.0]", b.getElements().toString());
        assertEquals(8, b.getElements().size());
    }

    @Test
    public void setElementsTest() {
        a.setElements(DoubleStream.iterate(2, (chiffre) -> chiffre + 2).limit(5).boxed().collect(Collectors.toList()));
        assertEquals(DoubleStream.iterate(2, (chiffre) -> chiffre + 2).limit(5).boxed().collect(Collectors.toList()), a.getElements());
        assertEquals("[2.0, 4.0, 6.0, 8.0, 10.0]", a.getElements().toString());
        assertEquals(5, a.getElements().size());

        a.setElements(DoubleStream.iterate(1, (chiffre) -> chiffre + 2).limit(5).boxed().collect(Collectors.toList()));
        assertEquals(DoubleStream.iterate(1, (chiffre) -> chiffre + 2).limit(5).boxed().collect(Collectors.toList()), a.getElements());
        assertEquals("[1.0, 3.0, 5.0, 7.0, 9.0]", a.getElements().toString());
        assertEquals(5, a.getElements().size());
    }

    @Test
    public void getElementTest() {
        for (int m = 1; m <= 3; m++)
            for (int n = 1; n <= 3; n++)
                assertEquals((double) (m - 1) * 3 + n, a.getElement(m, n));

        for (int m = 1; m <= 2; m++)
            for (int n = 1; n <= 4; n++)
                assertEquals((double) 10 - n - (m - 1) * 4, b.getElement(m, n));
    }

    @Test
    public void setElementTest() {
        for (int m = 1; m <= 3; m++) {
            for (int n = 1; n <= 3; n++) {
                a.setElement(m, n, 10 - n - (m - 1) * 3);
                assertEquals((double) 10 - n - (m - 1) * 3, a.getElement(m, n));
            }
        }

        for (int m = 1; m <= 2; m++) {
            for (int n = 1; n <= 4; n++) {
                b.setElement(m, n, (m - 1) * 4 + n);
                assertEquals((double) (m - 1) * 4 + n, b.getElement(m, n));
            }
        }
    }

    @Test
    public void getMTest() {
        assertEquals(3, a.getM());

        assertEquals(2, b.getM());
    }

    @Test
    public void getNTest() {
        assertEquals(3, a.getN());

        assertEquals(4, b.getN());
    }

    @Test
    public void estValideTest() {
        Matrice incomplete = new Matrice(3, 3);
        for (int m = 1; m <= incomplete.getM(); m++) {
            for (int n = 1; n <= incomplete.getN(); n++) {
                assertFalse(incomplete.estValide());
                incomplete.setElement(m, n, 1);
            }
        }
        assertTrue(incomplete.estValide());


        assertTrue(a.estValide());

        assertTrue(b.estValide());
    }

    @Test
    public void toStringTest() {
        assertEquals("1.0 2.0 3.0\n4.0 5.0 6.0\n7.0 8.0 9.0", a.toString());
        assertEquals("9.0 8.0 7.0 6.0\n5.0 4.0 3.0 2.0", b.toString());
    }
}
