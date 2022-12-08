package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ElevatorGame extends Application {

    public static final int TILE_SIZE = 32;
    public static final int WIDTH = TILE_SIZE * 40;
    public static final int HEIGHT = TILE_SIZE * 20;

    private ElevatorPanel elevatorPanel;
    private GamePanel gamePanel;

    private int currentFloor;

    @Override
    public void start(Stage stage) {

        gamePanel = new GamePanel(this);
        elevatorPanel = new ElevatorPanel(this);
        HBox root = new HBox();
        VBox leftPane = new VBox();
        VBox controls = new VBox();

        controls.setPrefWidth(TILE_SIZE * 10);
        controls.setPrefHeight(TILE_SIZE * 10);

        Button upButton = new Button("UP");
        Button downButton = new Button("DOWN");
        downButton.setDisable(true);

        upButton.setOnAction(actionEvent -> {
            if (currentFloor < 3) {
                currentFloor++;
                gamePanel.paint(currentFloor);
                downButton.setDisable(false);
                if (currentFloor == 3)
                    upButton.setDisable(true);
            }
        });

        downButton.setOnAction(actionEvent -> {
            if (currentFloor > 0) {
                currentFloor--;
                gamePanel.paint(currentFloor);
                upButton.setDisable(false);
                if (currentFloor == 0)
                    downButton.setDisable(true);
            }
        });

        controls.getChildren().add(upButton);
        controls.getChildren().add(downButton);

        leftPane.setPrefWidth(WIDTH / 4.0);
        leftPane.getChildren().add(controls);
        leftPane.getChildren().add(elevatorPanel);

        root.getChildren().add(leftPane);
        root.getChildren().add(gamePanel);

        Scene scene = new Scene(root, WIDTH, HEIGHT);

        stage.setTitle("Elevator Game!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        gamePanel.paint(currentFloor);
        elevatorPanel.paint();
    }

    public static void main(String[] args) {
        launch();
    }
}
