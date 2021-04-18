package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class HeroPane extends HBox{
	private ObservableList<HeroButton> HeroButtonList = FXCollections.observableArrayList();
	
	public HeroPane() {
		this.setPadding(new Insets(5,5,5,5));
		this.setAlignment(Pos.CENTER);
		this.setSpacing(10);;
		
		for(int i=0;i<5;i++) {
			HeroButtonList.add(new HeroButton("1"));
		}
		
		for(HeroButton x:HeroButtonList) {
			DropShadow shadow = new DropShadow();
			x.addEventHandler(MouseEvent.MOUSE_ENTERED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			        	// createRanger
			            x.setEffect(shadow);
			        }
			});
			x.addEventHandler(MouseEvent.MOUSE_EXITED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			            x.setEffect(null);
			        }
			});
			
		}
		
		for(int i=0;i<5;i++) {
			this.getChildren().add(HeroButtonList.get(i));
		}
		
	}
}
