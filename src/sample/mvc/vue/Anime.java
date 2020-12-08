package sample.mvc.vue;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Anime {

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
