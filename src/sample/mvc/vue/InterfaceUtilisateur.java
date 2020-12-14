package sample.mvc.vue;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.mvc.vue.audios.Son;
import sample.mvc.vue.visuels.Anime;

public class InterfaceUtilisateur extends BorderPane{

    private Stage stage;
    private Scene application;
    private HBox informations;
    private StackPane anime;
    private Timeline patience;
    private MediaView bruit;

    public InterfaceUtilisateur(Stage stage) {
        this.stage = stage;
        bruit = new MediaView();

        Menu operations = new Menu("Opérations");

        MenuItem libre = new MenuItem("Libre");
        libre.setOnAction(event -> OperationAffichage.libre(this));

        MenuItem addition = new MenuItem("Addition");
        addition.setOnAction(event -> OperationAffichage.addition(this));

        MenuItem soustraction = new MenuItem("Soustraction");
        soustraction.setOnAction(event -> OperationAffichage.soustraction(this));

        MenuItem multiplication = new MenuItem("multiplication par un scalaire");
        multiplication.setOnAction(event -> OperationAffichage.multiplication(this));

        MenuItem puissance = new MenuItem("Puissance");
        puissance.setOnAction(event -> OperationAffichage.puissance(this));

        MenuItem transposition = new MenuItem("Transposition");
        transposition.setOnAction(event -> OperationAffichage.transposition(this));

        MenuItem inversion = new MenuItem("Inversion");
        inversion.setOnAction(event -> OperationAffichage.inversion(this));

        MenuItem produitMatriciel = new MenuItem("Produit Matriciel");
        produitMatriciel.setOnAction(event -> OperationAffichage.produitMatriciel(this));

        MenuItem produitVectoriel = new MenuItem("Produit Vectoriel");
        produitVectoriel.setOnAction(event -> OperationAffichage.produitVectoriel(this));

        MenuItem produitHadamard = new MenuItem("Produit d'Hadamard");
        produitHadamard.setOnAction(event -> OperationAffichage.produitHadamard(this));

        MenuItem produitTensoriel = new MenuItem("Produit Tensoriel");
        produitTensoriel.setOnAction(event -> OperationAffichage.produitTensoriel(this));

        MenuItem determinant = new MenuItem("Calcul du déterminant");
        determinant.setOnAction(event -> OperationAffichage.determinant(this));

        operations.getItems().addAll(libre, addition, soustraction, multiplication, puissance,
                transposition, inversion, produitMatriciel, produitVectoriel,
                produitHadamard, produitTensoriel, determinant);
        MenuBar menuBar = new MenuBar(operations);

        //animation
        anime = Anime.animations();
        anime.setPadding(new Insets(0, 0, 0, 20));

        //aide invisible pour le centrage
        StackPane aide = Forme.genererAide(new Tooltip(null));
        aide.setVisible(false);

        //information
        Label information = new Label("");
        informations = new HBox(information);
        informations.setAlignment(Pos.CENTER);
        informations.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));

        //finalisation
        setTop(menuBar);
        setCenter(Anime.intro(this));
        setLeft(anime);
        setRight(aide);
        setBottom(informations);
        application = new Scene(this, 1000, 600);

        //animation en continue
        patience = new Timeline();
        patience.setCycleCount(Animation.INDEFINITE);
        patience.getKeyFrames().add(new KeyFrame(Duration.millis(60000),
                new KeyValue(anime.getChildren().get(0).visibleProperty(), true)));
        patience.getKeyFrames().add(new KeyFrame(Duration.millis(65000),
                new KeyValue(anime.getChildren().get(0).visibleProperty(), false)));
        patience.play();
    }

    public Stage getStage() { return stage; }

    public Scene getApplication() { return application; }

    public void setMessage(String message, String type) {
        Label information = new Label(message);
        if (type.equals("informative")) {
            information.setTextFill(Color.GREEN);
            patience.playFromStart();
            anime.getChildren().get(0).setVisible(false);
            anime.getChildren().get(2).setVisible(false);
            anime.getChildren().set(1, Anime.yesAnimation()); //animation avec un debut
            anime.getChildren().get(1).setVisible(true);
            bruit.setMediaPlayer(Son.egaleSon());
            bruit.getMediaPlayer().play();
        }
        if (type.equals("erreur")) {
            information.setTextFill(Color.RED);
            patience.playFromStart();
            anime.getChildren().get(0).setVisible(false);
            anime.getChildren().get(1).setVisible(false);
            anime.getChildren().get(2).setVisible(true);
            bruit.setMediaPlayer(Son.erreurSon());
            bruit.getMediaPlayer().play();
        }
        informations.getChildren().set(0, information);

        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5000),
                new KeyValue(informations.getChildren().get(0).visibleProperty(), false)));
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5000),
                new KeyValue(anime.getChildren().get(1).visibleProperty(), false)));
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5000),
                new KeyValue(anime.getChildren().get(2).visibleProperty(), false)));
        timeline.play();
    }
}
