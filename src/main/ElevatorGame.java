package main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import q4.Elevator;

public class ElevatorGame extends Application {

    public static final int TILE_SIZE = 32;
    public static final int WIDTH = TILE_SIZE * 40;
    public static final int HEIGHT = TILE_SIZE * 20;

    private ElevatorPanel elevatorPanel;
    private GamePanel gamePanel;
    private ControlPanel controlPanel;
    private Thread loopThread;

    private Elevator elevator;
    private int currentFloor;

    @Override
    public void start(Stage stage) {

        elevator  = new Elevator(8,0,4);

        gamePanel = new GamePanel(this);
        elevatorPanel = new ElevatorPanel(this);
        controlPanel = new ControlPanel(this);
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
        loopThread = new Thread(() -> {
            while (true) {
                repaint();
                try {
                    Thread.sleep(120L);
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

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
        repaint();
    }

    private void repaint() {
        System.out.println("here");
        gamePanel.paint(currentFloor);
        elevatorPanel.paint(elevator);
    }

    public void goUp() {
        currentFloor++;
        elevator.goToFloor(currentFloor);
        repaint();
    }

    public void goDown() {
        currentFloor--;
        elevator.goToFloor(currentFloor);
        repaint();
    }
}
