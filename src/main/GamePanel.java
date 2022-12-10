package main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import q4.ElevatorPerson;

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
        gc = this.getGraphicsContext2D();
        //gc.drawImage(floors[currentFloor], 0, 0, WIDTH * 3.0 / 4, HEIGHT);
        gc.drawImage(merged, 0, yOffset, WIDTH * 3.0 / 4, HEIGHT, 0, 0, 960, 640);
        paintPeople(currentFloor);
    }

    private void paintPeople(int currentFloor) {
        if (game.isElevatorMoving())
            return;
        ElevatorPerson[] ppl = NpcManager.people;
        int w = 0, e = 0;
        for (int i = 0; i < 8; i++) {
            if (ppl[i].isWaiting() && ppl[i].getInitialPosition() == currentFloor) {
                gc.drawImage(ppl[i].getIdleSprire(), 19 * 16, 0, 16, 32, 32 * (10 - w), 32 * 4.5, 32, 64);
                gc.strokeText("Full!", 32 * (10-w), 32 * 4.5);
                w++;
            }
            if (ppl[i].isExited() && ppl[i].getTarget() == currentFloor) {
                gc.drawImage(ppl[i].getIdleSprire(), 19 * 16, 0, 16, 32, 32 * 20, 32 * (4.5 + e), 32, 64);
                gc.strokeText(ppl[i].toString(), 32 * 21, 32 * (5.5 + e));
                e++;
            }
        }
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
