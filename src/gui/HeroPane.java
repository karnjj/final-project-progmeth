package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import logic.GameController;
import logic.Side;

public class HeroPane extends HBox{
	private ObservableList<HeroButton> HeroButtonList = FXCollections.observableArrayList();
	
	public HeroPane() {
		this.setPadding(new Insets(5,5,5,5));
		this.setSpacing(10);
		this.setMaxHeight(20);
		this.setMaxWidth(20);
		this.setAlignment(Pos.BOTTOM_CENTER);
		for(int i=0;i<5;i++) {
			HeroButtonList.add(new HeroButton("Inkblue"));
		}
		
		for(HeroButton x:HeroButtonList) {
			DropShadow shadow = new DropShadow();
			x.addEventHandler(MouseEvent.MOUSE_CLICKED,
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			        	// createRanger
						GameController.createRanger(x.hero.getName(), Side.ENEMY);
						GameController.createRanger(x.hero.getName(), Side.HERO);
			            x.setEffect(shadow);
						System.out.println("Click");
			        }
			});
			x.addEventHandler(MouseEvent.MOUSE_EXITED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			            x.setEffect(null);
			        }
			});
			
		}
		
		this.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		for(int i=0;i<5;i++) {
			this.getChildren().add(HeroButtonList.get(i));
		}
		
	}
}
