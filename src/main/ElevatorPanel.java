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

    private Image elev;
    private int animIndex, animTick;
    private boolean animFlag = true;

    public ElevatorPanel() {
        setHeight(10 * TILE_SIZE);
        setWidth(10 * TILE_SIZE);
        loadImages();
    }

    public void paint(Elevator elevator) {
        updateAnim();
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.clearRect(0, 0, 320, 320);
        gc.drawImage(elev, 0, 0, 320, 320);
        gc.strokeText(elevator.toString(), 10, 10, 160);
        gc.strokeText("Number of people who\nused the elevator: " + elevator.getTotalUsers(), 170, 10, 160);
        if (elevator.lastEntered() != null){
            gc.drawImage(elevator.lastEntered().getPhoneSprite(), animIndex * 16, 0, 16, 32, 4.25 * TILE_SIZE, 4.5 * TILE_SIZE, 1.5 * TILE_SIZE, 3 * TILE_SIZE);
            gc.setStroke(Color.WHITE);
            gc.strokeText("I am " + elevator.lastEntered().getPerson().getName() + "\nI will go to floor " + elevator.lastEntered().getTarget(),32*3.5,32*2.5,128);
            gc.setStroke(Color.BLACK);
        }
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
            elev = new Image(new FileInputStream("res/env/elev.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
