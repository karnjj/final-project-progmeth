package gui;

import application.Drawing;
import application.SoundUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.GameController;
import logic.GameState;

public class HomePanel extends VBox {
	public HomePanel() {
		Image bg = new Image("homePane.png");
		setBackground(bg);
		

		Button playButton = new Button();
		playButton.getStyleClass().add("playButton");
		playButton.setOnMouseClicked(e ->{
			Drawing.updatePanel(GameState.Play);
			SoundUtils.clickedSound();
		});
//		
		Canvas empty = new Canvas(20,50);
		
		this.setMaxHeight(500);
		this.setMaxWidth(550);
		this.setAlignment(Pos.CENTER);
		this.getChildren().add(empty);
		this.getChildren().add(playButton);
	}
	
	private void setBackground(Image image) {
		BackgroundFill bgFill = new BackgroundFill(Color.MOCCASIN, CornerRadii.EMPTY, Insets.EMPTY);
		BackgroundFill[] bgFillA = {bgFill};
		BackgroundSize bgSize = new BackgroundSize(550,500,false,false,false,false);
		BackgroundImage bgImg = new BackgroundImage(image, null, null, null, bgSize);
		BackgroundImage[] bgImgA = {bgImg};
		this.setBackground(new Background(bgFillA,bgImgA));
	}
}
