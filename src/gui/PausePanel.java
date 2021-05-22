package gui;

import application.Drawing;
import application.SoundUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.GameController;
import logic.GameState;

public class PausePanel extends VBox {
	
	public PausePanel() {
		Image bg = new Image("pausePane.png");
		setBackground(bg);
		this.setSpacing(10);
		
		Canvas empty = new Canvas(20,170);
		this.getChildren().add(empty);
		
		this.setSpacing(5);
		Button resumeButton = new Button();
		resumeButton.getStyleClass().add("resumeButton");
		resumeButton .setOnMouseClicked(e ->{
			System.out.println("Log: Continue Game(pausePanel)");
			GameController.updateGameState(GameState.Play);
			SoundUtils.clickedSound();
		});
		
		Button restartButton = new Button();
		restartButton.getStyleClass().add("restartButton");
		restartButton.setOnMouseClicked(e ->{
			System.out.println("Log: Restart(pausePanel)");
			GameController.reset();
			GameController.updateGameState(GameState.Play);
			SoundUtils.clickedSound();
		});
	
		
		Button homeButton = new Button();
		homeButton.getStyleClass().add("homeButton");
		homeButton .setOnMouseClicked(e ->{
			System.out.println("Log: OutToHome(pausePanel)");
			GameController.reset();
			GameController.updateGameState(GameState.Home);
			SoundUtils.clickedSound();
		});
		
		
		
		this.setAlignment(Pos.CENTER);
		this.setMaxHeight(500);
		this.setMaxWidth(550);
		this.getChildren().addAll(resumeButton,restartButton,homeButton);
	}
	
	private void setBackground(Image image) {
		BackgroundFill bgFill = new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY);
		BackgroundFill[] bgFillA = {bgFill};
		BackgroundSize bgSize = new BackgroundSize(550,500,false,false,false,false);
		BackgroundImage bgImg = new BackgroundImage(image, null, null, null, bgSize);
		BackgroundImage[] bgImgA = {bgImg};
		this.setBackground(new Background(bgFillA,bgImgA));
	}
}

