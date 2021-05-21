package application;


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
	private static double inertia = 0;

	// backGround moving
	private static double currentPosBg = 0;
	private static double targetPosBg = 0;
	private static final double speedPosBg = 100;

	private static HomePanel homePanel;
	private static PlayPanel playPanel;
	private static PausePanel pausePanel;
	private static EndgamePanel endgamePanel;
	// posi Draw Ranger =  window_height - current.y + currentPosiBg;
	
	private static final WritableImage bg = new WritableImage(new Image(ClassLoader.getSystemResource("roadmap3.png").toString(),game_width,715,false,false).getPixelReader(),0,0,game_width,715);
//	private static WritableImage bg = new WritableImage(new Image(ClassLoader.getSystemResource("roadmap.png").toString(),window_width,435,false,false).getPixelReader(),0,0,window_width,435);
	
	public static void updatePanel(GameState state) {
		switch (state) {
			case Home -> {
				homePanel.setVisible(true);
				playPanel.setVisible(false);
				pausePanel.setVisible(false);
				endgamePanel.setVisible(false);
				GameController.clear();
				GameController.InitGame();
			}
			case Play -> {
				homePanel.setVisible(false);
				playPanel.setVisible(true);
				pausePanel.setVisible(false);
				endgamePanel.setVisible(false);
			}
			case Pause, BeforePause -> {
				pausePanel.setVisible(true);
				playPanel.setVisible(false);
				endgamePanel.setVisible(false);
			}
			case End -> {
				homePanel.setVisible(false);
				playPanel.setVisible(false);
				pausePanel.setVisible(false);
				GameController.clear();
				endgamePanel.update();
				endgamePanel.setVisible(true);
			}
		}
		SoundUtils.playBackgroundMusic();
		GameController.setGameState(state);
	}

	public static void updatePosiBg(double dt) {
		targetPosBg = (GameController.getGameState()== GameState.Home)? 0:-120;
		if (GameController.getGameState()!= GameState.Home) {
			if(targetPosBg < currentPosBg) {
				currentPosBg -= speedPosBg *dt;
			}
		}
		else {
			if(targetPosBg > currentPosBg) {
				currentPosBg += speedPosBg *dt;}
		}
		setStartDraw(getStartDraw() + getInertia());
	}

	public static void drawEntities(GraphicsContext gc, double t){
		for (Entity e : GameController.getEntityManager().getAllEntity()) {
			e.draw(gc, t);
		}
	}

    public  static void drawEverything(GraphicsContext gc, double t) {
    	
    	 if(GameController.getGameState() != GameState.Pause && !GameController.isGameOver()) {
    		EnergyPane.update();
    		HeroPane.draw();
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
		gc.drawImage(bg, 0 + startDraw, currentPosBg,bg.getWidth(),bg.getHeight());
		
	}
	
	public static int getWindowWidth() {
		return window_width;
	}
	public static int getWindowHeight() {
		return window_height;
	}

	public static double getCurrentPosBg() {
		return currentPosBg;
	}

	public static void setCurrentPosBg(double currentPosBg) {
		Drawing.currentPosBg = currentPosBg;
	}

	public static double getTargetPosBg() {
		return targetPosBg;
	}

	public static void setTargetPosBg(double targetPosBg) {
		Drawing.targetPosBg = targetPosBg;
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
	

	public static EndgamePanel getEndgamePanel() {
		return endgamePanel;
	}

	public static void setEndgamePanel(EndgamePanel endgamePanel) {
		Drawing.endgamePanel = endgamePanel;
	}

	public static void setStartDraw(double startDraw) {
		Drawing.startDraw = startDraw;
		if (Drawing.startDraw > 0) Drawing.startDraw = 0;
		if (Drawing.startDraw < -(Drawing.game_width - Drawing.window_width)) Drawing.startDraw = -(Drawing.game_width - Drawing.window_width);
	}

	public static double getInertia() {
		return inertia;
	}

	public static void setInertia(double inertia) {
		Drawing.inertia = (int)(inertia);
	}
}
