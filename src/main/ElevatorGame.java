/**
 * @author 20050111034 Merter Çoban
 * @author 20050111008 Selçuk Gençay
 */
package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import q4.Elevator;

public class ElevatorGame extends Application {

    public static final int TILE_SIZE = 32;
    public static final int WIDTH = TILE_SIZE * 40;
    public static final int HEIGHT = TILE_SIZE * 20;

    private final ElevatorPanel elevatorPanel = new ElevatorPanel();
    private final GamePanel gamePanel = new GamePanel(this);
    private final ControlPanel controlPanel = new ControlPanel(this);

    private Elevator elevator;
    private int currentFloor;
    private int targetFloor;
    private boolean elevatorMoving;

    @Override
    public void start(Stage stage) {

        elevator = new Elevator(4, 0, 4);

        HBox root = new HBox();
        VBox leftPane = new VBox();

        leftPane.setPrefWidth(WIDTH / 4.0);
        leftPane.getChildren().add(controlPanel);
        leftPane.getChildren().add(elevatorPanel);

        root.getChildren().add(leftPane);
        root.getChildren().add(gamePanel);

        Scene scene = new Scene(root, WIDTH, HEIGHT);

        stage.setTitle("Elevator Game!");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> System.exit(0));
        stage.setResizable(false);
        stage.show();

        startLoop();
    }

    private void startLoop() {
        Thread loopThread = new Thread(() -> {
            while (true) {
                repaint();
                try {
                    //noinspection BusyWait
                    Thread.sleep(10L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        loopThread.start();
    }

    public static void main(String[] args) {
        launch();
    }

    public int getCurrentFloor() {
        return currentFloor;
    }


    private void repaint() {
        Platform.runLater(() -> {
            gamePanel.paint(currentFloor);
            elevatorPanel.paint(elevator);
        });
    }

    public void goUp() {
        targetFloor = currentFloor + 1;
        setElevatorMoving(true);
    }

    public void goDown() {
        targetFloor = currentFloor - 1;
        setElevatorMoving(true);
    }

    public void elevatorReachedTarget() {
        elevatorMoving = false;
        //noinspection ConstantConditions
        if (controlPanel != null)
            controlPanel.updateButtons();
        if (currentFloor == targetFloor)
            return;
        currentFloor = targetFloor;
        elevator.goToFloor(currentFloor);
    }

    public boolean isElevatorMoving() {
        return elevatorMoving;
    }

    public void setElevatorMoving(boolean elevatorMoving) {
        this.elevatorMoving = elevatorMoving;
    }

    public int getTargetFloor() {
        return targetFloor;
    }

    public Elevator getElevator() {
        return elevator;
    }
}
