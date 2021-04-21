package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import logic.GameController;
import logic.GameState;

public class PausePanel extends VBox {
	
	public PausePanel() {
		this.setSpacing(5);
		Button resumeButton = new Button("Resume");
		resumeButton .setOnMouseClicked(e ->{
			System.out.println("Continue Game");
			GameController.setGameState(GameState.Play);
		});
		
		Button quitButton = new Button("Home");
		quitButton .setOnMouseClicked(e ->{
			System.out.println("OutToHome");
			GameController.setGameState(GameState.Home);
		});
		
		Button pauseImage = new Button("PauseImage");
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(pauseImage,resumeButton,quitButton);
	}
}

