package gui;

import application.Drawing;
import application.SoundUtils;
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
			Drawing.updatePanel(GameState.Play);
		});
		
		Button quitButton = new Button("Home");
		quitButton .setOnMouseClicked(e ->{
			System.out.println("OutToHome");
			Drawing.updatePanel(GameState.Home);
//			SoundUtils.attrack();
		});
		
		Button pauseImage = new Button("PauseImage");
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(pauseImage,resumeButton,quitButton);
	}
}

