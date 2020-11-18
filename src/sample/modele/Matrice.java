package sample.modele;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Matrice {

    private List<Double> elements;
    private int m; //nombre de ligne
    private int n; //nombre de colonne

    public Matrice(int m, int n) {
        elements = new ArrayList<>(m * n);
        elements = DoubleStream.iterate(0, (nombre) -> nombre).limit(m * n).boxed().collect(Collectors.toList());
        this.m = m;
        this.n = n;
    }

    public List<Double> getElements() { return elements; }

    public void setElements(List<Double> elements) { this.elements = elements; }

    public double getElement(int m, int n) { return elements.get((m - 1) * this.n + n - 1); }

    public void setElement(int m, int n, double element) { elements.set((m - 1) * this.n + n - 1, element); }

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
