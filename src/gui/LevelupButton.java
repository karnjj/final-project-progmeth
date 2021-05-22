package gui;

import application.Drawing;
import application.SoundUtils;
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
		this.setStyle("-fx-border-color: #ff0000; -fx-border-width: 3px;-fx-border-radius: 10;");
//		this.setStyle("-fx-background-color: #00ff00; -fx-border-radius: 10;");
		this.setOnMouseClicked(e ->{
			System.out.println("LevelUp");
			SoundUtils.clickedSound();
			GameController.UpLevelEnergy();
		});
		canvas = new Canvas(width,height);
		gc = canvas.getGraphicsContext2D();
		update();
	}
	
	public void update() {
		System.out.println("in update LevelUp");
		this.setStyle("-fx-border-color: #ff0000; -fx-border-width: 3px;-fx-border-radius: 10;");
	}
}
