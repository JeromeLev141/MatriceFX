package sample.mvc.vue;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Anime {

    public static StackPane intro(InterfaceUtilisateur iu) {
        ImageView logo = new ImageView("sample/mvc/vue/sonics/MatriceFX_Logo.png");
        ImageView glint = new ImageView("sample/mvc/vue/sonics/glint.png");

        BoxBlur boxBlur = new BoxBlur();
        boxBlur.setHeight(10);
        boxBlur.setWidth(10);
        boxBlur.setIterations(20);
        glint.setEffect(boxBlur);

        TranslateTransition trans = new TranslateTransition(Duration.seconds(5), glint);
        trans.setFromX(-600);
        trans.setToX(600);
        trans.setCycleCount(1);
        trans.setAutoReverse(false);
        trans.setOnFinished(event -> glint.setVisible(false));
        trans.play();

        iu.getTop().setVisible(false);
        Timeline intro = new Timeline();
        intro.setCycleCount(1);
        intro.getKeyFrames().add(new KeyFrame(Duration.millis(4000),
                new KeyValue(iu.getTop().visibleProperty(), true)));
        intro.play();

        return new StackPane(logo, glint);
    }

    public static StackPane animations() { return new StackPane(waitAnimation(), yesAnimation(), noAnimation()); }

    private static ImageView waitAnimation() {
        ImageView wait =  new ImageView("sample/mvc/vue/sonics/sonic.wait.gif");
        wait.setFitWidth(80);
        wait.setFitHeight(100);
        wait.setEffect(new DropShadow(10, 0, 5, Color.GREY));
        wait.setVisible(false);
        return wait;
    }

    public static ImageView yesAnimation() {
        ImageView yes = new ImageView("sample/mvc/vue/sonics/sonic.yes.gif");
        yes.setFitWidth(110);
        yes.setFitHeight(100);
        yes.setEffect(new DropShadow(10, 0, 5, Color.GREY));
        yes.setVisible(false);
        return yes;
    }

    private static ImageView noAnimation() {
        ImageView no = new ImageView("sample/mvc/vue/sonics/sonic.no.gif");
        no.setFitWidth(80);
        no.setFitHeight(100);
        no.setEffect(new DropShadow(10, 0, 5, Color.GREY));
        no.setVisible(false);
        return no;
    }
}
