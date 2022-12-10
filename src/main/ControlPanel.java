package main;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import static main.ElevatorGame.TILE_SIZE;

public class ControlPanel extends GridPane {

    private ElevatorGame game;

    private Button upButton;
    private Button downButton;
    private int upButtonClickCount = 0;
    private int downButtonClickCount = 0;

    public ControlPanel(ElevatorGame game) {
        this.game = game;
        setPrefWidth(TILE_SIZE * 10);
        setPrefHeight(TILE_SIZE * 10);
        addButtons();

    }

    private void addButtons() {
        upButton = new Button("UP");
        downButton = new Button("DOWN");
        downButton.setDisable(true);

        upButton.setOnAction(actionEvent -> {
            upButtonClickCount++;
            //System.out.println("Up : " + upButtonClickCount);
            game.goUp();
            upButton.setDisable(true);
            downButton.setDisable(true);
        });

        downButton.setOnAction(actionEvent -> {
            downButtonClickCount++;
            //System.out.println("Down : " + downButtonClickCount);
            game.goDown();
            upButton.setDisable(true);
            downButton.setDisable(true);
        });

        getChildren().add(upButton);
        getChildren().add(downButton);

        upButton.setStyle("-fx-background-color: #1fabab; -fx-border-color: black;-fx-font-family: 'Arial Black'; " +
                "-fx-font-size: 25px; -fx-border-width: 3px; -fx-border-radius: 7px; -fx-background-radius: 10px; " +
                "-fx-padding: 17px;-fx-translate-y: 30px;-fx-translate-x: 100px;-fx-max-height: 65px;-fx-max-width: 130px");
        downButton.setStyle("-fx-background-color: #cb701d; -fx-border-color: black; -fx-font-family: 'Arial Black'; " +
                "-fx-font-size: 25px; -fx-border-width: 3px;-fx-border-radius: 7px; -fx-background-radius: 10px;" +
                " -fx-translate-y: 120px; -fx-translate-x: 100px; -fx-padding:16px;-fx-max-height: 65px;-fx-max-width: 130px");

    }

    public void updateButtons() {
        switch (game.getCurrentFloor()) {
            case 0 -> {
                upButton.setDisable(false);
                downButton.setDisable(true);
            }
            case 1, 2 -> {
                upButton.setDisable(false);
                downButton.setDisable(false);
            }
            case 3 -> {
                upButton.setDisable(true);
                downButton.setDisable(false);
            }
        }
    }
}
