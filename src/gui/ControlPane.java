package gui;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.GameController;

public class ControlPane extends HBox {
		
	private HeroPane heroPane;
	private Label energyLabel;
	
	public ControlPane() {
		this.setAlignment(Pos.CENTER);
		this.setPrefHeight(160);
		this.setPrefWidth(1289);
		this.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		energyLabel = new Label();
		energyLabel.setFont(new Font("Arial", 20));
		energyLabel.setAlignment(Pos.CENTER_LEFT);
		
		heroPane = new HeroPane();
		this.getChildren().add(energyLabel);
		this.getChildren().add(heroPane);
		setEnergyLabelText();
	}
	
	public void setEnergyLabelText() {
//		System.out.println("Energy: " + GameController.getCurrentEnergy());
		energyLabel.textProperty().setValue("Energy: " + GameController.getCurrentEnergy());
	}

}
