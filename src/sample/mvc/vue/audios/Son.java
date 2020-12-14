package sample.mvc.vue.audios;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Son {

    public static MediaPlayer introSon() {
        MediaPlayer son =  new MediaPlayer(new Media(
                new File("src/sample/mvc/vue/audios/intro.mp3").toURI().toString()));
        son.setVolume(0.5);
        return son;
    }

    public static MediaPlayer egaleSon() {
        MediaPlayer son = new MediaPlayer(new Media(
                new File("src/sample/mvc/vue/audios/egale.mp3").toURI().toString()));
        son.setVolume(0.2);
        return son;
    }

    public static MediaPlayer erreurSon() {
        MediaPlayer son = new MediaPlayer(new Media(
                new File("src/sample/mvc/vue/audios/erreur.mp3").toURI().toString()));
        son.setVolume(0.2);
        return son;
    }

    public static MediaPlayer reloadSon() {
        MediaPlayer son = new MediaPlayer(new Media(
                new File("src/sample/mvc/vue/audios/reload.mp3").toURI().toString()));
        son.setVolume(0.2);
        return son;
    }

    public static MediaPlayer plusSon() {
        MediaPlayer son = new MediaPlayer(new Media(
                new File("src/sample/mvc/vue/audios/plus.mp3").toURI().toString()));
        son.setVolume(0.2);
        return son;
    }

    public static MediaPlayer moinsSon() {
        MediaPlayer son = new MediaPlayer(new Media(
                new File("src/sample/mvc/vue/audios/moins.mp3").toURI().toString()));
        son.setVolume(0.2);
        return son;
    }

    public static MediaPlayer entreSon() {
        MediaPlayer son = new MediaPlayer(new Media(
                new File("src/sample/mvc/vue/audios/entre.mp3").toURI().toString()));
        son.setVolume(0.2);
        return son;
    }

    public static MediaPlayer fermeSon() {
        MediaPlayer son = new MediaPlayer(new Media(
                new File("src/sample/mvc/vue/audios/ferme.mp3").toURI().toString()));
        son.setVolume(0.2);
        return son;
    }
}
