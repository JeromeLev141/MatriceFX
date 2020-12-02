package sample.mvc.modele;

import java.util.ArrayList;
import java.util.List;

public class Matrice {

    private List<Double> elements;
    private int m; //nombre de ligne
    private int n; //nombre de colonne
    private boolean estCarre;

    public Matrice(int m, int n) {
        elements = new ArrayList<>(m * n);
        while (elements.size() < m * n) elements.add(null);
        this.m = m;
        this.n = n;
        if (n==m)
            estCarre = true;
        else
            estCarre = false;
    }

    public List<Double> getElements() { return elements; }

    public void setElements(List<Double> elements) { this.elements = elements; }

    public double getElement(int m, int n) { return elements.get((m - 1) * this.n + n - 1); }

    public void setElement(int m, int n, double element) { elements.set((m - 1) * this.n + n - 1, element); }

    public int getM() { return m; }

    public void setM(int m) { this.m = m; }

    public int getN() { return n; }

    public void setN(int n) { this.n = n; }

    public boolean estValide() {
        for (Double element : elements) {
            if (element == null)
                return false;
        }
        return true;
    }

    public boolean isEstCarre() { return estCarre; }

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
