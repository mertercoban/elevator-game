import javafx.application.Application;
import javafx.stage.Stage;

public class ElevatorGame extends Application {

    public static final int TILE_SIZE = 32;
    public static final int WIDTH = TILE_SIZE * 40;
    public static final int HEIGHT = TILE_SIZE * 20;

    @Override
    public void start(Stage stage) {

        stage.setTitle("Elevator Game!");
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
