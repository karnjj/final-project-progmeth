package gui;

import application.Drawing;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import logic.GameController;
import logic.GameState;

public class HomePanel extends VBox {
	
	public HomePanel() {
		this.setSpacing(5);
		
//		ImageView imageView = new ImageView("ks.png");
//		imageView.setFitHeight(50);
//		imageView.setPreserveRatio(true);
//		this.setPrefSize(50,50);
		Button playButton = new Button("Play");
		playButton.setOnMouseClicked(e ->{
			GameController.setGameState(GameState.Play);
			Drawing.updatePanel();
		});
		
		Button nameOfGame = new Button("nameOfGame");
		this.setAlignment(Pos.CENTER);
		this.getChildren().add(nameOfGame );
		this.getChildren().add(playButton );
	}
}