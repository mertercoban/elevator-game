package main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ElevatorPanel extends Canvas {

    private ElevatorGame game;
    private Image elev;
    private GraphicsContext gc;

    public ElevatorPanel(ElevatorGame elevatorGame) {
        this.game = elevatorGame;
        setHeight(320);
        setWidth(320);
        loadImages();
    }

    public void paint() {
        if (gc==null)
            gc = this.getGraphicsContext2D();
        gc.clearRect(0,0,320,320);
        gc.drawImage(elev,0,0, 320, 320);
        gc.setFill(Color.BLUE);
        gc.strokeText("Sample Text", 10,10,320);
    }

    private void loadImages() {
        try {
            elev = new Image(new FileInputStream("res/elev.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
