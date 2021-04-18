package application;


import entity.Ranger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import logic.GameController;

public class Drawing {
	private static final int window_width = 1289;
	private static final int window_height = 595;
	
	private static WritableImage bg = new WritableImage(new Image(ClassLoader.getSystemResource("roadmap.png").toString(),window_width,434,false,false).getPixelReader(),0,0,window_width,434);
	private static WritableImage turrentHero = new WritableImage(new Image(ClassLoader.getSystemResource("turrentHero.png").toString(),300,434,false,false).getPixelReader(),0,0,300,434);
	private static WritableImage turrentEnemy = new WritableImage(new Image(ClassLoader.getSystemResource("turrentEnemy.png").toString(),300,434,false,false).getPixelReader(),0,0,300,434);
	
	public static void drawPlayingRangers(GraphicsContext gc,double t) {
        gc.clearRect(0,0,window_width,434);
        Drawing.drawBackground(gc);
        for(Ranger e : GameController.getHero()) {
            e.draw(gc,t);
        }
        for(Ranger e : GameController.getEnemy()) {
            e.draw(gc,t);
        }
        
    }
	
	public static void drawTurrent(GraphicsContext gc) {
		gc.drawImage(turrentHero, 0, 0);
		gc.drawImage(turrentEnemy, 1000, 0);
	}
	
	
	public static void drawBackground(GraphicsContext gc) {
		gc.drawImage(bg, 0, 0);
		drawTurrent(gc);
	}
	
	public static int getWindowWidth() {
		return window_width;
	}
	public static int getWindowHeight() {
		return window_height;
	}
}
