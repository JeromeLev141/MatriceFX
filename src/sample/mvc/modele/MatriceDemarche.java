package sample.mvc.modele;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MatriceDemarche{

    private List<String> elements;
    private int m;
    private int n;

    public MatriceDemarche(int m, int n) {
        elements = new ArrayList<>(m * n);
        while (elements.size() < m * n) elements.add(null);
        this.m = m;
        this.n = n;
    }

    public List<String> getElements() { return this.elements; }

    public void setElements(List<String> elements) { this.elements = elements; }

    private void resizeElements() {
        elements = new ArrayList<>(m * n);
        while (elements.size() < m * n) elements.add(null);
    }

    public String getElement(int m, int n) { return elements.get((m - 1) * this.n + n - 1); }

    public void setElement(int m, int n, String element) { elements.set((m - 1) * this.n + n - 1, element); }

    public int getM() { return m; }

    public void setM(int m) {
        this.m = m;
        resizeElements();
    }

    public int getN() { return n; }

    public void setN(int n) {
        this.n = n;
        resizeElements();
    }

    public boolean estValide() {
        for (String element : elements) {
            if (element == null)
                return false;
        }
        return true;
    }

    public boolean isEstCarre() { return n == m;}

    @Override
    public String toString() {
        StringBuilder matrice = new StringBuilder();

        for (int m = 0; m < this.m; m++) {
            for (int n = 0; n < this.n; n++) {
                matrice.append(elements.get(m * this.n + n));
                if (n < this.n - 1)
                    matrice.append(' ');
            }
            if (m < this.m - 1)
                matrice.append("\n");
        }
        return matrice.toString();
    }
}
