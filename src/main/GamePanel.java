package main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import q4.ElevatorPerson;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static main.ElevatorGame.HEIGHT;
import static main.ElevatorGame.WIDTH;

public class GamePanel extends Canvas {

    private Image building;
    private int yOffset;
    private GraphicsContext gc;
    private ElevatorGame game;

    private float qOffset = 0;

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
        gc.drawImage(building, 0, yOffset, WIDTH * 3.0 / 4, HEIGHT, 0, 0, 960, 640);
        paintPeople(currentFloor);
    }

    private void paintPeople(int currentFloor) {
        if (game.isElevatorMoving())
            return;
        ElevatorPerson[] ppl = NpcManager.people;
        int w = 0, e = 0;
        boolean messageShown = false;
        for (int i = 0; i < 8; i++) {
            if (ppl[i].isWaiting() && ppl[i].getInitialPosition() == currentFloor) {
                gc.drawImage(ppl[i].getIdleSprite(), 19 * 16, 0, 16, 32, 32 * (10 - w) + qOffset, 32 * 4.5, 32, 64);
                if (!messageShown && game.getElevator() != null) {
                    if (game.getElevator().isFull())
                        gc.strokeText("We need a bigger elevator!!!", 32 * (8-w), 32 * 4.5);
                    else if (!game.getElevator().isEmpty())
                        gc.strokeText("Hi, " + game.getElevator().lastEntered().getPerson().getName(), 32 * (10-w) + qOffset, 32 * 4.5);
                    else if (game.getElevator().isEmpty())
                        gc.strokeText("Wow! It's empty", 32 * (9-w) + qOffset, 32 * 4.5);
                    messageShown = true;
                }
                w++;
                if (game.getElevator() != null && !game.getElevator().isFull()) {
                    qOffset += 0.4;
                    if (qOffset >= 32 * 3) {
                        game.getElevator().enter(ppl[i]);
                        qOffset = 0;
                    }
                }
            }
            if (ppl[i].isExited() && ppl[i].getTarget() == currentFloor) {
                gc.drawImage(ppl[i].getIdleSprite(), 19 * 16, 0, 16, 32, 32 * 20, 32 * (4.5 + e), 32, 64);
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
        try {
            building = new Image(new FileInputStream("res/env/floors-merged.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
