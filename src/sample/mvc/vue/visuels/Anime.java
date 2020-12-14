package sample.mvc.vue.visuels;

import javafx.animation.*;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sample.mvc.vue.InterfaceUtilisateur;
import sample.mvc.vue.audios.Son;


public class Anime {

    public static StackPane intro(InterfaceUtilisateur iu) {
        ImageView logo = new ImageView("sample/mvc/vue/visuels/MatriceFX_Logo.png");
        ImageView glint = new ImageView("sample/mvc/vue/visuels/glint.png");
        Label info = info();

        BoxBlur boxBlur = new BoxBlur();
        boxBlur.setHeight(10);
        boxBlur.setWidth(10);
        boxBlur.setIterations(20);
        glint.setEffect(boxBlur);

        TranslateTransition trans = new TranslateTransition(Duration.seconds(3), glint);
        trans.setFromX(-600);
        trans.setToX(600);
        trans.setCycleCount(1);
        trans.setAutoReverse(false);
        trans.setOnFinished(event -> glint.setVisible(false));
        trans.play();

        FadeTransition fade = new FadeTransition(Duration.seconds(1), logo);
        fade.setDelay(Duration.seconds(3));
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();

        iu.getTop().setVisible(false);
        Timeline intro = new Timeline();
        intro.setCycleCount(1);
        intro.getKeyFrames().add(new KeyFrame(Duration.seconds(5),
                new KeyValue(iu.getTop().visibleProperty(), true)));
        intro.getKeyFrames().add(new KeyFrame(Duration.seconds(5),
                new KeyValue(info.visibleProperty(), true)));
        intro.play();

        MediaView bruit = new MediaView();
        bruit.setMediaPlayer(Son.introSon());
        bruit.getMediaPlayer().play();

        return new StackPane(info, logo, glint);
    }

    public static Label info() {
        Label info = new Label("Bienvenue sur MatriceFX!\n" +
                "Ce programme vous permettra d'effectuer diverses opérations sur des matrices\n\n" +
                "Faites 'enter' pour confirmer une donnée rentrée\n" +
                "Veuillez utiliser la touche R pour réinitialiser une opération au besoin\n" +
                "Glissez votre curseur vers le '?' en cas de questionnement\n" +
                "Consultez le fichier.csv pour les instructions afin de correctement importer une matrice\n" +
                "Dans le cas où un chiffre est trop grand '...' sera affiché\n" +
                "Il suffit de de poser le curseur sur le chiffre pour le voir au complet\n\n" +
                "Enjoy:)\n\n" +
                "@JL JG");
        info.setStyle("-fx-font-family: 'Bauhaus 93';");
        info.setTextFill(Color.rgb(37, 37, 86));
        info.setScaleX(2);
        info.setScaleY(2);
        info.setEffect(new DropShadow(5, 1, -1, Color.GREY));
        info.setAlignment(Pos.CENTER);
        info.setVisible(false);
        return info;
    }

    public static StackPane animations() { return new StackPane(waitAnimation(), yesAnimation(), noAnimation()); }

    private static ImageView waitAnimation() {
        ImageView wait =  new ImageView("sample/mvc/vue/visuels/sonic.wait.gif");
        wait.setFitWidth(80);
        wait.setFitHeight(100);
        wait.setEffect(new DropShadow(10, 0, 5, Color.GREY));
        wait.setVisible(false);
        return wait;
    }

    public static ImageView yesAnimation() {
        ImageView yes = new ImageView("sample/mvc/vue/visuels/sonic.yes.gif");
        yes.setFitWidth(110);
        yes.setFitHeight(100);
        yes.setEffect(new DropShadow(10, 0, 5, Color.GREY));
        yes.setVisible(false);
        return yes;
    }

    private static ImageView noAnimation() {
        ImageView no = new ImageView("sample/mvc/vue/visuels/sonic.no.gif");
        no.setFitWidth(80);
        no.setFitHeight(100);
        no.setEffect(new DropShadow(10, 0, 5, Color.GREY));
        no.setVisible(false);
        return no;
    }
}
