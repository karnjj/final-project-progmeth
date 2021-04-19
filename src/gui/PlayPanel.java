package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import logic.GameController;
import javafx.geometry.Insets;

public class PlayPanel extends StackPane{

	public PlayPanel() {
		Button HBbar2 = new Button("HBbar");
		StackPane.setMargin(HBbar2, new Insets(10, 10, 0, 0));
		StackPane.setAlignment(HBbar2, Pos.TOP_RIGHT);
		
		Button HBbar1 = new Button("HBbar");
		StackPane.setMargin(HBbar1, new Insets(10, 0, 0, 10));
		StackPane.setAlignment(HBbar1, Pos.TOP_LEFT);
		
		Button Quit = new Button("pause");
		Quit.setOnMouseClicked(e ->{
			GameController.setIsGameMode(0);
		});
		StackPane.setMargin(Quit, new Insets(10, 0, 0, 0));
		StackPane.setAlignment(Quit, Pos.TOP_CENTER);
		
		HeroPane heroPane = new HeroPane();
		StackPane.setMargin(heroPane, new Insets(0, 0, 0, 0));
		StackPane.setAlignment(heroPane, Pos.BOTTOM_CENTER);
		
		this.getChildren().addAll(HBbar2,HBbar1,Quit,heroPane);
	}

}
