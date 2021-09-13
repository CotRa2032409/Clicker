package com.example.clicker;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Main extends Application {

    private int compteur = 0;
    private int upgradePtsd = 0; //C'est tout à fait normal qu'il y ait un "D" en trop
    private int prixPlusClics = 125;
    private int prixAutoClics = 2000;
    private int ptsAutoClics = 2;
    private boolean remise = false;


    @Override

    public void start(Stage stage) throws IOException {
        final var temps = new PauseTransition(Duration.seconds(1));
        var timer = new SequentialTransition(temps);

        stage.setTitle("Greedier Mode");

        //Bouton Clicker
        Button bouton = new Button("Wooden Nickel");
        bouton.setPrefSize(150, 150);
        bouton.setTranslateY(50);


        //Création des Labels
        Label nbPoints = new Label("Penny : ");
        Label points = new Label("0");
        Label upTexte1 = new Label(125 + " $$$");
        Label upTexte2 = new Label(500 + " $$$");
        Label upTexte3 = new Label(750 + " $$$");
        Label upTexte4 = new Label(1000 + " $$$");
        Label upTexte5 = new Label(2000 + " $$$");

        //Debug button
        /*Button debug = new Button("Blank Card + Car Battery + Tarot Cloth + 2 of Diamonds");
        debug.setTranslateY(250);
        debug.setOnAction((actionEvent) -> {
            points.setText(Integer.toString(compteur += 10000000));
        });*/

        //Modif des Labels
        upTexte1.setTranslateX(360);
        upTexte1.setTranslateY(25);
        upTexte2.setTranslateX(360);
        upTexte2.setTranslateY(55);
        upTexte3.setTranslateX(360);
        upTexte3.setTranslateY(85);
        upTexte4.setTranslateX(360);
        upTexte4.setTranslateY(115);
        upTexte5.setTranslateX(360);
        upTexte5.setTranslateY(145);
        points.setTranslateX(50);

        //Création des upgrades et modifications
        Button plusClic = new Button("1 + 1 Free 4 Evar");
        Button bigGamble = new Button("Slot Machine");
        Button ptsRendus = new Button("Steam Sales");
        Button unlock = new Button("Mom's Key");
        Button auto = new Button("Broken Watch");
        plusClic.setPrefSize(152,20);
        plusClic.setTranslateY(20);
        plusClic.setTranslateX(200);
        bigGamble.setPrefSize(152, 20);
        bigGamble.setTranslateY(50);
        bigGamble.setTranslateX(200);
        ptsRendus.setPrefSize(152, 20);
        ptsRendus.setTranslateY(80);
        ptsRendus.setTranslateX(200);
        unlock.setPrefSize(152, 20);
        unlock.setTranslateY(110);
        unlock.setTranslateX(200);
        auto.setPrefSize(152, 20);
        auto.setTranslateY(140);
        auto.setTranslateX(200);
        auto.setDisable(true);


        //setOnAction
        bouton.setOnAction((event) -> {
            compteur();
            points.setText(Integer.toString(compteur));
        });
        plusClic.setOnAction((actionEvent) -> {
            if (compteur >= prixPlusClics) {
                upgradePtsd += 1;
                if (remise == true) {
                    compteur -= prixPlusClics * 0.9;
                } else
                    compteur -= prixPlusClics;
                points.setText(Integer.toString(compteur));
                prixPlusClics *= 2;
                upTexte1.setText(prixPlusClics + " pts");
            }
        });
        bigGamble.setOnAction((actionEvent) -> {
            if (compteur >= 500) {
                if (remise == true) {
                    compteur -= 500 * 0.9;
                } else
                    compteur -= 500;
                if (Math.random() * 1 >= 0.5) {
                    compteur = 0;
                }
                points.setText(Integer.toString(compteur));
            }
        });
        ptsRendus.setOnAction((actionEvent) -> {
            if (compteur >= 750) {
                points.setText(Integer.toString(compteur -= 750));
                remise = true;
                ptsRendus.setDisable(true);
            }
        });
        unlock.setOnAction((actionEvent) -> {
            if (compteur >= 1000) {
                unlock.setDisable(true);
                auto.setDisable(false);
                if (remise == true) {
                    compteur -= 1000 * 0.9;
                } else
                    compteur -= 1000;
                points.setText(Integer.toString(compteur));
            }
        });
        auto.setOnAction((actionEvent) -> {
            if (compteur >= prixAutoClics) {
                if (remise == true)
                    compteur -= prixAutoClics * 0.9;
                else
                    compteur -= prixAutoClics;
                points.setText(Integer.toString(compteur));
                ptsAutoClics *= 2;

                temps.setOnFinished(event -> {
                    compteur = compteur + ptsAutoClics;
                    points.setText(String.valueOf(compteur));
                });
                timer.setCycleCount(PauseTransition.INDEFINITE);
                timer.play();

                prixAutoClics *= 2;
                upTexte5.setText(prixAutoClics + " pts");
            }
        });

        //Scene
        Group upgrades = new Group(plusClic, upTexte1, bigGamble, upTexte2, ptsRendus, upTexte3, unlock, upTexte4, auto, upTexte5);
        Group root = new Group(bouton, nbPoints, points, upgrades/*, debug*/);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void compteur() {
        compteur += 1 + (upgradePtsd * 5);
    }
}

