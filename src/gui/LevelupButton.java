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
	private static Canvas canvas;
	private static int width = 190;
	private static int height = 122;
	private boolean isGreen;
	private double time;
	
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
		this.time = 0;
		isGreen=false;
	}
	
	public void update(double dt) {
		this.setStyle("-fx-border-color: none;");
		if(GameController.canLevelup()) {
			time+= dt*100;
			if(time%100>0) {
				time-=100;
				isGreen^=true;
			}
			if(isGreen) {
				this.setStyle("-fx-border-color: #00ff00; -fx-border-width: 4px;-fx-border-radius: 10;");
			}
		}
	}
}
