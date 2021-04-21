package application;


import entity.Bullet;
import entity.Ranger;
import entity.Smoke;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import gui.*;
import logic.GameController;
import logic.GameState;

public class Drawing {
	private static final int window_width = 1289;
	private static final int window_height = 595;
	
	// backGround moving
	private static double currentPosiBg = 0;
	private static double targetPosiBg = 0;
	private static double speedPosiBg = 100;
	
	private static HomePanel homePanel;
	private static PlayPanel playPanel;
	private static PausePanel pausePanel;
	
	// posi Draw Ranger =  window_height - current.y + currentPosiBg;
	
	private static WritableImage bg = new WritableImage(new Image(ClassLoader.getSystemResource("roadmap2.png").toString(),window_width,715,false,false).getPixelReader(),0,0,window_width,715);
//	private static WritableImage bg = new WritableImage(new Image(ClassLoader.getSystemResource("roadmap.png").toString(),window_width,435,false,false).getPixelReader(),0,0,window_width,435);
	private static WritableImage turretHero = new WritableImage(new Image(ClassLoader.getSystemResource("turretHero.png").toString(),300,434,false,false).getPixelReader(),0,0,300,434);
	private static WritableImage turretEnemy = new WritableImage(new Image(ClassLoader.getSystemResource("turretEnemy.png").toString(),300,434,false,false).getPixelReader(),0,0,300,434);
	
	
	public static void updatePanel(GameState state) {
		switch(state) {
			case Home :
			
				homePanel.setVisible(true);
				playPanel.setVisible(false);
				pausePanel.setVisible(false);
				break;
			case Play :
				homePanel.setVisible(false);
				playPanel.setVisible(true);
				pausePanel.setVisible(false);
				break;
			case Pause :
				pausePanel.setVisible(true);
				playPanel.setVisible(false);
				break;
			case BeforePause :
				pausePanel.setVisible(true);
				playPanel.setVisible(false);
				break;
		}
		GameController.setGameState(state);
	}

	public static void updatePosiBg(double dt) {
		targetPosiBg = (GameController.getGameState()== GameState.Home)? 0:-120;
		if (GameController.getGameState()!= GameState.Home) {
			if(targetPosiBg<currentPosiBg) {
				currentPosiBg -= speedPosiBg*dt;
			}
		}
		else {
			if(targetPosiBg>currentPosiBg) {currentPosiBg += speedPosiBg*dt;}
		}

	}
	
	
	public static void drawPlayingRangers(GraphicsContext gc,double t) {
        for(Ranger e : GameController.getHero()) {
            e.draw(gc,t);
        }
        for(Ranger e : GameController.getEnemy()) {
            e.draw(gc,t);
        }
        
    }

    public static void drawBullet(GraphicsContext gc,double t) {
		for(Bullet e : GameController.getBullet()) {
			e.draw(gc,t);
		}
	}
	public static void drawSmoke(GraphicsContext gc,double t) {
		for(Smoke e : GameController.getSmoke()) {
			e.draw(gc,t);
		}
	}

    public  static void drawEverything(GraphicsContext gc, double t) {
    	 if(GameController.getGameState() != GameState.Pause) {
			gc.clearRect(0,0,window_width,window_height);	
			if(GameController.getGameState() == GameState.BeforePause) {
				gc.setGlobalAlpha(0.9);
				System.out.println("before");
	            gc.setEffect(new BoxBlur(7, 7, 3));
	            GameController.setGameState(GameState.Pause);
			}
			if(GameController.getGameState() != GameState.Pause) {
				gc.setGlobalAlpha(1);
				gc.setEffect(null);
			}
			Drawing.drawBackground(gc);
			Drawing.drawPlayingRangers(gc,t);
			Drawing.drawBullet(gc,t);
			Drawing.drawSmoke(gc,t);
			
    	 }
	}

	public static void drawTurrent(GraphicsContext gc) {
		int k = 155; //for debug 155
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

	public static HomePanel getHomePanel() {
		return homePanel;
	}

	public static void setHomePanel(HomePanel homePanel) {
		Drawing.homePanel = homePanel;
	}

	public static PlayPanel getPlayPanel() {
		return playPanel;
	}

	public static void setPlayPanel(PlayPanel playPanel) {
		Drawing.playPanel = playPanel;
	}

	public static PausePanel getPausePanel() {
		return pausePanel;
	}

	public static void setPausePanel(PausePanel pausePanel) {
		Drawing.pausePanel = pausePanel;
	}
	
	
	
	
}
