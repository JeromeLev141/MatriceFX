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
    }


}
