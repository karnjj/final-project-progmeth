package gui;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import logic.GameController;
import logic.GameState;
import logic.Side;
import application.Drawing;
import application.SoundUtils;
import javafx.geometry.Insets;

public class PlayPanel extends StackPane{

	public static HpBar hpHero = new HpBar(Side.HERO);
	public static HpBar hpEnemy = new HpBar(Side.ENEMY);

	public PlayPanel() {

		StackPane.setMargin(hpHero, new Insets(10, 10, 0, 0));
		StackPane.setAlignment(hpHero, Pos.TOP_LEFT);

		StackPane.setMargin(hpEnemy, new Insets(10, 0, 0, 10));
		StackPane.setAlignment(hpEnemy, Pos.TOP_RIGHT);
		
		Button bn = new Button("debugButton");
		bn.setOnMouseClicked(e ->{
//			System.out.println("Button on PlayPanel");
//			GameController.getHeroTurret().setCurrentHP(10);
//			System.out.println(GameController.getHeroTurret().getCurrentHP());
//			hpHero.update();
		});
		StackPane.setAlignment(bn, Pos.TOP_LEFT);
		
		Button pauseButton = new Button("pause");
		pauseButton.setOnMouseClicked(e ->{
			System.out.println("pause");
			Drawing.updatePanel(GameState.BeforePause);
		});
		StackPane.setMargin(pauseButton, new Insets(10, 0, 0, 0));
		StackPane.setAlignment(pauseButton, Pos.TOP_CENTER);
//		
		
		EnergyPane energyPane = new EnergyPane();
		StackPane.setMargin(energyPane, new Insets(0, 0, 10, 100));
		StackPane.setAlignment(energyPane, Pos.BOTTOM_LEFT);
		
		HeroPane heroPane = new HeroPane();
		StackPane.setMargin(heroPane, new Insets(0, 0, 10, 0));
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
		this.getChildren().addAll(detectMouse,hpHero,hpEnemy,pauseButton,heroPane,bn,energyPane);
	}

}
