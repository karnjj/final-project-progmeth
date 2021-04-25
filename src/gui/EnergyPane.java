package gui;

import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import logic.GameController;

public class EnergyPane extends Canvas{
	private static GraphicsContext gc;
	private static int width = 190;
	private static int height = 122;
	public EnergyPane() {
		this.setHeight(height);
		this.setWidth(width);
		gc = this.getGraphicsContext2D();
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		update();
	}
	
	public static void update() {
		gc.setFill(Color.WHITE);
		gc.fillRect(0,0,width, height);
		gc.setFill(Color.BLACK);
		gc.setFont(new Font(20));
		gc.fillText("Energy: " + GameController.getCurrentEnergy(),Math.round(width / 2),Math.round(height / 2));
	}
}
