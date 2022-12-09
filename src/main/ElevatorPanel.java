package main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import q4.Elevator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static main.ElevatorGame.TILE_SIZE;

public class ElevatorPanel extends Canvas {

    private ElevatorGame game;
    private Image elev;
    private GraphicsContext gc;
    private Image personSprite;
    private int animIndex, animTick;
    private boolean animFlag = true;

    public ElevatorPanel(ElevatorGame elevatorGame) {
        this.game = elevatorGame;
        setHeight(320);
        setWidth(320);
        loadImages();
    }

    public void paint(Elevator elevator) {
        updateAnim();
        if (gc == null)
            gc = this.getGraphicsContext2D();
        gc.clearRect(0, 0, 320, 320);
        gc.drawImage(elev, 0, 0, 320, 320);
        gc.setFill(Color.BLUE);
        gc.strokeText(elevator.toString(), 10, 10, 320);
        gc.drawImage(personSprite, animIndex * 16, 0, 16, 32, 4.25 * TILE_SIZE, 4.5 * TILE_SIZE, 1.5 * TILE_SIZE, 3 * TILE_SIZE);
    }

    private void updateAnim() {
        animTick++;
        if (animTick >= 15) {

            if (animFlag) {
                animIndex++;
                if (animIndex >= 9) {
                    animIndex = 8;
                    animFlag = false;
                }
            } else {
                animIndex--;
                if (animIndex < 0) {
                    animIndex = 0;
                    animFlag = true;
                }
            }
            animTick = 0;
        }
    }

    private void loadImages() {
        try {
            elev = new Image(new FileInputStream("res/elev.png"));
            personSprite = new Image(new FileInputStream("res/ali/ali_phone.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
