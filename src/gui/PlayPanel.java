package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import logic.GameController;
import logic.GameState;
import application.Drawing;
import javafx.geometry.Insets;

public class PlayPanel extends StackPane{

	public PlayPanel() {
		Button HBbar2 = new Button("HBbar");
		HBbar2.setOnMouseClicked(e ->{
			System.out.println("hp2");
		});
		StackPane.setMargin(HBbar2, new Insets(10, 10, 0, 0));
		StackPane.setAlignment(HBbar2, Pos.TOP_RIGHT);
		
		Button HBbar1 = new Button("HBbar");
		HBbar1.setOnMouseClicked(e ->{
			System.out.println("hp1");
		});
		StackPane.setMargin(HBbar1, new Insets(10, 0, 0, 10));
		StackPane.setAlignment(HBbar1, Pos.TOP_LEFT);
		
		Button quit = new Button("pause");
		quit.setOnMouseClicked(e ->{
			System.out.println("pause");
			GameController.setGameState(GameState.Pause);
		});
		StackPane.setMargin(quit, new Insets(10, 0, 0, 0));
		StackPane.setAlignment(quit, Pos.TOP_CENTER);
//		
		
		HeroPane heroPane = new HeroPane();
		StackPane.setMargin(heroPane, new Insets(0, 0, 0, 0));
		StackPane.setAlignment(heroPane, Pos.BOTTOM_CENTER);
//		
		this.getChildren().addAll(HBbar2,HBbar1,quit,heroPane);
//		this.getChildren().add(HBbar2);
//		this.getChildren().add(HBbar1);
//		this.getChildren().addAll(HBbar2,HBbar1,quit);
	}

}
