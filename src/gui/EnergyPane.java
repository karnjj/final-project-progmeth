package gui;

import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import logic.GameController;

public class EnergyPane extends Canvas {
    private static GraphicsContext gc;
    private static final int width = 190;
    private static final int height = 122;
    private static final WritableImage bg = new WritableImage(new Image(ClassLoader.getSystemResource("energyPane.png").toString(), width, height, false, false).getPixelReader(), 0, 0, width, height);

    public EnergyPane() {
        this.setHeight(height);
        this.setWidth(width);
        gc = this.getGraphicsContext2D();
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
    }

    public static void draw() {
        gc.drawImage(bg, 0, 0);
        gc.setFont(new Font(30));
        gc.setFill(Color.WHITE);
        gc.fillText("" + GameController.getCurrentEnergy() + " / " + GameController.getMaxEnergy(), Math.round(width / 2), Math.round(height / 2) + 10);
    }
}
