package gui;

import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.GameController;

import java.util.concurrent.atomic.AtomicInteger;

import entity.AtomicFloat;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;


public class HeroButton extends Button {
	private Hero hero;
	private boolean enoughEnergy = false;
	private ImageView imageView;

	
	public HeroButton(String name){
		this.setPadding(new Insets(5,5,5,5));
		this.hero = new Hero(name);
		imageView = new ImageView(this.hero.getRanger().getUrl());
		imageView.setFitWidth(70);
		imageView.setFitHeight(70);
		this.setGraphic(imageView);
		setBackGround();
		this.setTooltip();
		this.draw();
	
		this.addEventHandler(MouseEvent.MOUSE_CLICKED,
		    new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent e) {
		        	mouseClickHandler();
					System.out.println("Log: Click summon ranger(HeroPane)");
					
		        }
		});
		
	}
	


	private void mouseClickHandler() {
		if(this.haveEnoughEnergy()) {
			this.getHero().Buy();
		}
	}
	
	private void setBackGround() {
		if(enoughEnergy) {
			
			this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		}
		else {
			this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		}
		this.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID,CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}
	
	public void update(double dt) {
		hero.update(dt);
		this.enoughEnergy = hero.canBuy();
		if(!hero.canBuy()) {
			
		}
	}

	public void draw() {
		this.setBackGround();
	}
	
	private void setTooltip() {
		Tooltip	tooltip = new Tooltip();
		tooltip.setFont(new Font(12));
		tooltip.setText(hero.tooltipMassage());
		this.setOnMouseMoved((MouseEvent e) -> {
			if (hero != null)
			tooltip.show(this, e.getScreenX(), e.getScreenY()+10);
		});
		this.setOnMouseExited((MouseEvent e) -> {
			tooltip.hide();
		});		
	}
	
	public Boolean haveEnoughEnergy() {
		return enoughEnergy;
	}

	public Hero getHero() {
		return this.hero;
	}
}
