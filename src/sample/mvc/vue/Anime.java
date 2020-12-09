package sample.mvc.vue;

import javafx.animation.TranslateTransition;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class Anime {

    public static ImageView logo() { return new ImageView("sample/mvc/vue/sonics/MatriceFX_Logo.png"); }

    public static StackPane intro() {
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
        return new StackPane(logo(), glint);
    }

    public static StackPane animations() { return new StackPane(waitAnimation(), yesAnimation(), noAnimation()); }

    private static ImageView waitAnimation() {
        ImageView wait =  new ImageView("sample/mvc/vue/sonics/sonic.wait.gif");
        wait.setFitWidth(80);
        wait.setFitHeight(100);
        wait.setVisible(false);
        return wait;
    }

    public static ImageView yesAnimation() {
        ImageView yes = new ImageView("sample/mvc/vue/sonics/sonic.yes.gif");
        yes.setFitWidth(110);
        yes.setFitHeight(100);
        yes.setVisible(false);
        return yes;
    }

    private static ImageView noAnimation() {
        ImageView no = new ImageView("sample/mvc/vue/sonics/sonic.no.gif");
        no.setFitWidth(80);
        no.setFitHeight(100);
        no.setVisible(false);
        return no;
    }
}
