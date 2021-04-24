package gui;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import logic.GameController;
import logic.GameState;
import application.Drawing;
import application.SoundUtils;
import javafx.geometry.Insets;

public class PlayPanel extends StackPane{

	public PlayPanel() {
		Button HBbar2 = new Button("HBbar");
		HBbar2.setOnMouseClicked(e ->{
			System.out.println("hp2");
			SoundUtils.clickedSound();
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
			Drawing.updatePanel(GameState.BeforePause);
		});
		StackPane.setMargin(quit, new Insets(10, 0, 0, 0));
		StackPane.setAlignment(quit, Pos.TOP_CENTER);
//		
		
		HeroPane heroPane = new HeroPane();
		StackPane.setMargin(heroPane, new Insets(0, 0, 0, 0));
		StackPane.setAlignment(heroPane, Pos.BOTTOM_CENTER);

		VBox detectMouse = new VBox();
//		

		detectMouse.addEventFilter(MouseEvent.ANY,
				new EventHandler<MouseEvent>()
				{
					double startPosition = 0;
					double dMove = 0;
					public void handle(MouseEvent e)
					{
						if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
							startPosition = Drawing.getStartDraw();
							dMove = e.getX();
						}else if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
							Drawing.setStartDraw(startPosition-(dMove-e.getX()));
						}
					}
				});
		this.getChildren().addAll(detectMouse,HBbar2,HBbar1,quit,heroPane);
//		this.getChildren().add(HBbar2);
//		this.getChildren().add(HBbar1);
//		this.getChildren().addAll(HBbar2,HBbar1,quit);
	}

}
