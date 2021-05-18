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

public class EndgamePanel extends VBox {
	private static Image losebg = new Image("gameoverPane.png");
	private static Image Winbg = new Image("winPane.png");
	
	public EndgamePanel() {
		this.setSpacing(10);
		
		Canvas empty = new Canvas(20,120);
		this.getChildren().add(empty);
		this.setSpacing(5);
		
		Button restartButton = new Button();
		restartButton.getStyleClass().add("restartButton");
		restartButton.setOnMouseClicked(e ->{
			System.out.println("Log: Restart(pausePanel)");
			GameController.clear();
			GameController.InitGame();
			Drawing.updatePanel(GameState.Play);
			SoundUtils.clickedSound();
		});
	
		
		Button quitButton = new Button();
		quitButton.getStyleClass().add("homeButton");
		quitButton .setOnMouseClicked(e ->{
			System.out.println("Log: OutToHome(pausePanel)");
			Drawing.updatePanel(GameState.Home);
			SoundUtils.clickedSound();
		});
		
		
		update();
		this.setAlignment(Pos.CENTER);
		this.setMaxHeight(500);
		this.setMaxWidth(550);
		this.getChildren().addAll(restartButton,quitButton);
	}
	
	public void update() {
		if(GameController.isWin()) {
			setBackground(Winbg);
		}
		else {
			setBackground(losebg);
		}
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
