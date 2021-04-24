package gui;

import entity.EnemyTurret;
import entity.HeroTurret;
import entity.Turret;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import logic.GameController;
import logic.Side;

public class HpBar extends Canvas {
	private static final int height = 45;
	private static final int width = 400;
	private GraphicsContext gc;
	private Side side;
	private static WritableImage bg = new WritableImage(new Image(ClassLoader.getSystemResource("hpbar.png").toString(),width,height,false,false).getPixelReader(),0,0,width,height);
	
	public HpBar(Side side) {
		this.setHeight(height);
		this.setWidth(width);
		this.gc = this.getGraphicsContext2D();
        gc.setStroke(Color.valueOf("#0000ff"));
        gc.strokeRect(200, 200, 200, 200);
        this.getStyleClass().add("canvas");

        this.side = side;
	}

	public void draw() {
		this.gc.clearRect(0,0,width,height);
		gc.setFill(Color.WHITE);
		gc.fillRect(0,0,width, height);
		if(side == Side.HERO) {
			
			double lenHp = (GameController.getHeroTurret().getCurrentHP()/HeroTurret.getMaxHP()) * (double)width;
			gc.setFill(Color.valueOf("#ff0000"));
			gc.fillRect(0,0,lenHp, height);
			this.gc.drawImage(bg, 0,0,width, height);
			
		}
		if(side == Side.ENEMY) {
			double lenHp = (GameController.getEnemyTurret().getCurrentHP()/EnemyTurret.getMaxHP()) * (double)width;
			gc.setFill(Color.valueOf("#ff0000"));
			gc.fillRect(width-lenHp,0,lenHp, height);
			this.gc.drawImage(bg, 0,0,width, height);
		}
	}
}
