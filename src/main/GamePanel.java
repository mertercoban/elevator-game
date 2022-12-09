package main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static main.ElevatorGame.HEIGHT;
import static main.ElevatorGame.WIDTH;

public class GamePanel extends Canvas {

    private Image[] floors;
    private Image merged;
    private int yOffset;
    private GraphicsContext gc;
    private ElevatorGame game;

    public GamePanel(ElevatorGame game) {
        this.game = game;
        this.yOffset = 640 * 3;
        setHeight(HEIGHT);
        setWidth(WIDTH * 3.0 / 4);
        loadImages();
        paint(0);
    }

    public void paint(int currentFloor) {
        calcYOffset();
        if (gc == null)
            gc = this.getGraphicsContext2D();
        //gc.drawImage(floors[currentFloor], 0, 0, WIDTH * 3.0 / 4, HEIGHT);
        gc.drawImage(merged, 0, yOffset, WIDTH * 3.0 / 4, HEIGHT, 0, 0, 960, 640);
    }

    private void calcYOffset() {
        if (game.getCurrentFloor() > game.getTargetFloor()) {
            yOffset += 5;
            if (yOffset >= 640 * (3 - game.getTargetFloor())) {
                yOffset = 640 * (3 - game.getTargetFloor());
                game.elevatorReachedTarget();
            }
        } else if (game.getCurrentFloor() < game.getTargetFloor()) {
            yOffset -= 5;
            if (yOffset <= 640 * (3 - game.getTargetFloor())) {
                yOffset = 640 * (3 - game.getTargetFloor());
                game.elevatorReachedTarget();
            }
        } else
            game.elevatorReachedTarget();
    }

    private void loadImages() {
        floors = new Image[4];
        try {
            floors[0] = new Image(new FileInputStream("res/floor0.png"));
            floors[1] = new Image(new FileInputStream("res/floor1.png"));
            floors[2] = new Image(new FileInputStream("res/floor2.png"));
            floors[3] = new Image(new FileInputStream("res/floor3.png"));
            merged = new Image(new FileInputStream("res/floors-merged.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
