package sample.mvc.controlleur;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.mvc.modele.Matrice;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LecteurDeFichier {

    private File file;
    private List<String> liste;


    public LecteurDeFichier(File file) throws IOException {
        this.file = file;
        liste = Files.readAllLines(file.toPath());
    }

    public static File chercherFichier(Stage primaryStage){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("fichiers texte","*.csv"));
        fc.setTitle("Veuillez sélectionner un fichier");
        return fc.showOpenDialog(primaryStage);
    }

    public static Matrice stringtoMatrice(String matriceString){
        boolean transposition = false;
        if (matriceString.charAt(matriceString.length()-1) == 't') {
            transposition = true;
            matriceString = matriceString.substring(0,matriceString.length()-2);
        }
        matriceString = matriceString.substring(1).replaceAll("]","");
        String[] matriceElement1D =  matriceString.split(",");
        int m = matriceElement1D.length;
        int n = matriceElement1D[0].split(" ").length;
        String[][] matriceElement2D = new String[m][];

        for (int x = 0; x < matriceElement1D.length ; x++) {
            matriceElement2D[x] = matriceElement1D[x].split(" ");
            if (matriceElement2D[x].length != n) {
                System.out.println("Erreur dans le format de la matrice");
                return null;
            }
        }

        Matrice a = new Matrice(matriceElement2D.length,matriceElement2D[0].length);
        a.getElements().clear();
        try {
            for (String[] strings : matriceElement2D)
                for (int y = 0; y < matriceElement2D[0].length; y++)
                    a.getElements().add(Double.parseDouble(strings[y]));
        }catch ( Exception e){
            System.out.println("Erreur dans le document");
            return null;
        }
        if (transposition)
            return Operation.transposition(a);
        return a;
    }

    public static List<String> stringToOperation(String operation) {
        List<String> listeOperation = new ArrayList<>();
        int x = 0;
        int position =0;
        while(operation.length() != 0) {
            if (x % 2 == 0) {
                position = operation.indexOf(']');
                String matrice;
                if (position + 1 != operation.length()) {

                    if (operation.charAt(position + 1) == 't') {
                        matrice = operation.substring(0, position + 2);
                        operation = operation.substring(position + 2);
                    }
                    else {
                        matrice = operation.substring(0, position + 1);
                        operation = operation.substring(position + 1);
                    }
                        listeOperation.add(matrice);
                }
                else{
                    listeOperation.add(operation);
                    operation = "";
                }
            }
            else{
                position = operation.indexOf('[');
                String symbole = operation.substring(0,position);
                operation = operation.substring(position);
                listeOperation.add(symbole.trim());
            }
            x++;
        }
        return listeOperation;
    }

    public List<String> getliste() {
        return liste;
    }
}
