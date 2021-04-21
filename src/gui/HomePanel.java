package gui;

import application.Drawing;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.GameController;
import logic.GameState;

public class HomePanel extends VBox {
	
	public HomePanel() {
		this.setSpacing(5);
		
		Image image = new Image("homePane.png");
		setBackground(image);
		Button playButton = new Button("Play");
		playButton.setOnMouseClicked(e ->{
			Drawing.updatePanel(GameState.Play);
		});
		this.setMaxHeight(500);
		this.setMaxWidth(550);
		this.setAlignment(Pos.CENTER);
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
