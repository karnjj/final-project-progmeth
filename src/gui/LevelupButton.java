package gui;

import application.Drawing;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import logic.GameController;
import logic.GameState;

public class LevelupButton extends Button {
	private static GraphicsContext gc;
	private static Canvas canvas;
	private static int width = 190;
	private static int height = 122;
	
	public LevelupButton() {
		this.setHeight(height);
		this.setWidth(width);
		this.getStyleClass().add("levelupButton");
		this.setOnMouseClicked(e ->{
			System.out.println("LevelUp");
			GameController.UpLevelEnergy();
		});
		canvas = new Canvas(width,height);
		gc = canvas.getGraphicsContext2D();
		update();
	}
	
	public static void update() {
		gc.setFill(Color.RED);
		gc.fillOval(10,10, 50, 50);
	}
}
