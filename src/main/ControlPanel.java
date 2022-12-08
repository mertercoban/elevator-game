package main;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import static main.ElevatorGame.TILE_SIZE;

public class ControlPanel extends VBox {

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
                game.setCurrentFloor(game.getCurrentFloor() + 1);
                downButton.setDisable(false);
                if (game.getCurrentFloor() == 3)
                    upButton.setDisable(true);
            }
        });

        downButton.setOnAction(actionEvent -> {
            if (game.getCurrentFloor() > 0) {
                game.setCurrentFloor(game.getCurrentFloor() - 1);
                upButton.setDisable(false);
                if (game.getCurrentFloor() == 0)
                    downButton.setDisable(true);
            }
        });

        getChildren().add(upButton);
        getChildren().add(downButton);
    }
}
