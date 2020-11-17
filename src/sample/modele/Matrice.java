package sample.modele;

import java.util.List;

public class Matrice {

    private List<Double> elements;
    private int m; //nombre de ligne
    private int n; //nombre de colonne

    public Matrice(int m, int n) {
        this.m = m;
        this.n = n;
    }

    public List<Double> getElements() { return elements; }

    public void setElements(List<Double> elements) { this.elements = elements; }

    public int getM() { return m; }

    public int getN() { return n; }

    @Override
    public String toString() {
        return "Matrice{" +
                "elements=" + elements +
                ", m=" + m +
                ", n=" + n +
                '}';
    }
}
