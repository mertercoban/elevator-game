package main;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import static main.ElevatorGame.TILE_SIZE;

public class ControlPanel extends GridPane {

    private ElevatorGame game;

    private Button upButton;
    private Button downButton;

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
            if (game.getCurrentFloor() < 3) {
                game.goUp();
                downButton.setDisable(false);
                if (game.getCurrentFloor() == 3)
                    upButton.setDisable(true);
            }
        });

        downButton.setOnAction(actionEvent -> {
            if (game.getCurrentFloor() > 0) {
                game.goDown();
                upButton.setDisable(false);
                if (game.getCurrentFloor() == 0)
                    downButton.setDisable(true);
            }
        });

        getChildren().add(upButton);
        getChildren().add(downButton);

        upButton.setStyle("-fx-background-color: #1fabab; -fx-border-color: black;-fx-font-family: Harrington; " +
                "-fx-font-size: 25px; -fx-border-width: 3px; -fx-border-radius: 7px; -fx-background-radius: 10px; " +
                "-fx-padding: 17px;-fx-translate-x: 100px;-fx-max-height: 65px;-fx-min-width: 110px");
        downButton.setStyle("-fx-background-color: #cb701d; -fx-border-color: black; -fx-font-family: Harrington; " +
                "-fx-font-size: 25px; -fx-border-width: 3px; -fx-border-radius: 7px; -fx-background-radius: 10px;" +
                " -fx-translate-y: 90px; -fx-translate-x: 100px; -fx-padding:16px;-fx-max-height: 65px;-fx-min-width: 110px");

    }

    public class ButtonFXMLController {

        public Button upButton = null;
        private int upButtonClickCount = 0;
        private int downButtonClickCount = 0;

        @FXML
        public void buttonClicked1(Event u) {
            this.upButtonClickCount++;

            String text = "Up Button clicked " + this.upButtonClickCount + " times";

            System.out.println(text);

            upButton.setText(text);
        }

        public void buttonClicked2(Event d) {
            this.downButtonClickCount++;

            String text = "Down Button clicked " + this.downButtonClickCount + " times";

            System.out.println(text);

            downButton.setText(text);
        }


    }

}
