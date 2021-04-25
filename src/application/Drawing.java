package application;


import entity.EnemyTurret;
import entity.HeroTurret;
import entity.base.Entity;
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

	private static final int game_width = 3000;
	private static final int game_height = 595;

	private static double startDraw = 0;
	
	// backGround moving
	private static double currentPosiBg = 0;
	private static double targetPosiBg = 0;
	private static double speedPosiBg = 100;
	
	private static HomePanel homePanel;
	private static PlayPanel playPanel;
	private static PausePanel pausePanel;
	
	// posi Draw Ranger =  window_height - current.y + currentPosiBg;
	
	private static WritableImage bg = new WritableImage(new Image(ClassLoader.getSystemResource("roadmap3.png").toString(),game_width,715,false,false).getPixelReader(),0,0,game_width,715);
//	private static WritableImage bg = new WritableImage(new Image(ClassLoader.getSystemResource("roadmap.png").toString(),window_width,435,false,false).getPixelReader(),0,0,window_width,435);
	
	public static void updatePanel(GameState state) {
		SoundUtils.playBackgroundMusic();
		switch(state) {
			case Home :
				homePanel.setVisible(true);
				playPanel.setVisible(false);
				pausePanel.setVisible(false);
				GameController.clear();
				GameController.InitGame();
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

	public static void drawEntities(GraphicsContext gc, double t){
		for (Entity e : GameController.getEntityManager().getAllEntity()) {
			e.draw(gc, t);
		}
	}

    public  static void drawEverything(GraphicsContext gc, double t) {
    	
    	 if(GameController.getGameState() != GameState.Pause) {
    		EnergyPane.update();
    		HeroPane.update();
			gc.clearRect(0,0,window_width,window_height);	
			if(GameController.getGameState() == GameState.BeforePause) {
				gc.setGlobalAlpha(0.9);
	            gc.setEffect(new BoxBlur(7, 7, 3));
	            updatePanel(GameState.Pause);
			}
			if(GameController.getGameState() != GameState.Pause) {
				gc.setGlobalAlpha(1);
				gc.setEffect(null);
			}
			Drawing.drawBackground(gc);
			Drawing.drawEntities(gc,t);
			PlayPanel.hpEnemy.draw();
			PlayPanel.hpHero.draw();
    	 }
	}

	
	public static void drawBackground(GraphicsContext gc) {
		gc.drawImage(bg, 0 + startDraw, currentPosiBg,bg.getWidth(),bg.getHeight());

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

	public static int getGameWidth() {
		return game_width;
	}

	public static int getGameHeight() {
		return game_height;
	}

	public static double getStartDraw() {
		return startDraw;
	}

	public static void setStartDraw(double startDraw) {
		Drawing.startDraw = startDraw;
		if (Drawing.startDraw > 0) Drawing.startDraw = 0;
		if (Drawing.startDraw < -(Drawing.game_width - Drawing.window_width)) Drawing.startDraw = -(Drawing.game_width - Drawing.window_width);
	}
}
