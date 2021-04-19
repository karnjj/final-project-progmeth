package application;


import entity.Ranger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import logic.GameController;

public class Drawing {
	private static final int window_width = 1289;
	private static final int window_height = 595;
	
	// backGround moving
	private static double currentPosiBg = 0;
	private static double targetPosiBg = 0;
	private static double speedPosiBg = 100;
	
	// posi Draw Ranger =  window_height - current.y + currentPosiBg;
	
//	private static WritableImage bg = new WritableImage(new Image(ClassLoader.getSystemResource("roadmap2.png").toString(),window_width,715,false,false).getPixelReader(),0,0,window_width,715);
	private static WritableImage bg = new WritableImage(new Image(ClassLoader.getSystemResource("roadmap.png").toString(),window_width,435,false,false).getPixelReader(),0,0,window_width,435);
	private static WritableImage turretHero = new WritableImage(new Image(ClassLoader.getSystemResource("turretHero.png").toString(),300,434,false,false).getPixelReader(),0,0,300,434);
	private static WritableImage turretEnemy = new WritableImage(new Image(ClassLoader.getSystemResource("turretEnemy.png").toString(),300,434,false,false).getPixelReader(),0,0,300,434);
	
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
	
	public static void updatePosiBg(double dt) {
		targetPosiBg = (GameController.getIsGameMode()==1)? -120:0;
		
		if (GameController.getIsGameMode()==1) {
			if(targetPosiBg<currentPosiBg) {
				System.out.println("in");
				currentPosiBg -= speedPosiBg*dt;
			}
		}
		else {
			if(targetPosiBg>currentPosiBg) {currentPosiBg += speedPosiBg*dt;}
		}
			
	}
	
	
	public static void drawTurrent(GraphicsContext gc) {
		int k = 0; //for debug 155
		gc.drawImage(turretHero, 0, k+currentPosiBg);
		gc.drawImage(turretEnemy, 1000, k+currentPosiBg);
	}
	
	public static void drawBackground(GraphicsContext gc) {
		gc.drawImage(bg, 0, currentPosiBg);
		drawTurrent(gc);
	}
	
	public static int getWindowWidth() {
		return window_width;
	}
	public static int getWindowHeight() {
		return window_height;
	}

	public static double getCurrentPosiBg() {
		return currentPosiBg;
	}

	public static void setCurrentPosiBg(double currentPosiBg) {
		Drawing.currentPosiBg = currentPosiBg;
	}

	public static double getTargetPosiBg() {
		return targetPosiBg;
	}

	public static void setTargetPosiBg(double targetPosiBg) {
		Drawing.targetPosiBg = targetPosiBg;
	}
	
	
}
