package com.example.clicker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private int compteur = 0;
    private int upgradePtsd = 0;
    private int essai = 125;
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Clicker!");

        Button bouton = new Button("Clique içi!");
        bouton.setPrefSize(150, 150);
        bouton.setTranslateY(50);

        Label nbPoints = new Label("Points : ");
        Label points = new Label("0");
        Label upTexte1 = new Label(125 + " pts");
        upTexte1.setTranslateX(360);
        upTexte1.setTranslateY(25);
        points.setTranslateX(50);

        Button upgrade1 = new Button("Plus de points en cliquant");
        upgrade1.setTranslateY(20);
        upgrade1.setTranslateX(200);

        bouton.setOnAction((event) -> {
            compteur();
            points.setText(Integer.toString(compteur));
        });

        upgrade1.setOnAction((actionEvent) -> {
            if (compteur >= essai) {
                upgradePtsd++;
                points.setText(Integer.toString(compteur -= essai));
                essai *= 2;
                upTexte1.setText(essai + " pts");
            }
        });


        Group root = new Group(bouton, nbPoints, points, upgrade1, upTexte1);

        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void compteur() {
        compteur += 1 + upgradePtsd;
    }
}

