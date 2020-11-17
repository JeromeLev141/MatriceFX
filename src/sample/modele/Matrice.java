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

    public void setElements(List<Double> elements) { this.elements = elements; }

    public int getM() { return m; }

    public int getN() { return n; }

    @Override
    public String toString() {
        String matrice = "";
        for (int x = 1; x <= m; x++) {
            for (int y = 1; y <= n; y++) {
                matrice += elements.get((x - 1) * n + y - 1) + " ";
            }
            matrice += "\n";
        }
        return matrice;
    }
}
