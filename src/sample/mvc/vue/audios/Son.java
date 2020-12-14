package sample.mvc.vue.audios;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Son {

    public static MediaPlayer introSon() {
        return new MediaPlayer(new Media(
                new File("src/sample/mvc/vue/audios/intro.mp3").toURI().toString()));
    }

    public static MediaPlayer egaleSon() {
        return new MediaPlayer(new Media(
                new File("src/sample/mvc/vue/audios/egale.mp3").toURI().toString()));
    }

    public static MediaPlayer erreurSon() {
        return new MediaPlayer(new Media(
                new File("src/sample/mvc/vue/audios/erreur.mp3").toURI().toString()));
    }

    public static MediaPlayer reloadSon() {
        return new MediaPlayer(new Media(
                new File("src/sample/mvc/vue/audios/reload.mp3").toURI().toString()));
    }

    public static MediaPlayer plusSon() {
        return new MediaPlayer(new Media(
                new File("src/sample/mvc/vue/audios/plus.mp3").toURI().toString()));
    }

    public static MediaPlayer moinsSon() {
        return new MediaPlayer(new Media(
                new File("src/sample/mvc/vue/audios/moins.mp3").toURI().toString()));
    }

    public static MediaPlayer entreSon() {
        return new MediaPlayer(new Media(
                new File("src/sample/mvc/vue/audios/entre.mp3").toURI().toString()));
    }

    public static MediaPlayer fermeSon() {
        return new MediaPlayer(new Media(
                new File("src/sample/mvc/vue/audios/ferme.mp3").toURI().toString()));
    }
}
