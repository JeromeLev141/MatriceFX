package sample.modele;

import java.util.ArrayList;
import java.util.List;

public class Matrice {

    private List<Double> elements;
    private int m; //nombre de ligne
    private int n; //nombre de colonne

    public Matrice(int m, int n) {
        elements = new ArrayList<>();
        this.m = m;
        this.n = n;
    }

    public List<Double> getElements() { return elements; }

    public int getM() { return m; }

    public int getN() { return n; }

    @Override
    public String toString() {
        StringBuilder matrice = new StringBuilder();
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                matrice.append(elements.get(x * n + y)).append(" ");
            }
            matrice.append("\n");
        }
        return matrice.toString();
    }
}
