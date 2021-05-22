package application;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import gui.MuteButton;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import logic.GameController;
import logic.GameState;

public class SoundUtils {
	private static MediaPlayer backgroundMusic;
	private static MediaPlayer click = new MediaPlayer(new Media(ClassLoader.getSystemResource("sound/buttonSound.wav").toExternalForm()));
	private static MediaPlayer createdSound = new MediaPlayer(new Media(ClassLoader.getSystemResource("sound/createdRanger.mp3").toExternalForm()));
	private static MediaPlayer attackSound = new MediaPlayer(new Media(ClassLoader.getSystemResource("sound/attackSound.wav").toExternalForm()));
	private static Thread playMusic;
	public static AtomicInteger atomicInteger = new AtomicInteger(0);
	
	
	private static boolean soundOn = true;
    private static MuteButton muteButton ;
	
	public static void init() {
		playMusic = new BackgroundSound();
		playMusic.start();
	}
	
	public static void playBackgroundMusic() {
		if(SoundUtils.isSoundOn()) {
			if(GameController.getGameState() == GameState.Play) {
				BackgroundSound.lastState.set(1);			
			}
			else if(GameController.getGameState() == GameState.Home) {
				BackgroundSound.lastState.set(0);			
			}
			else {
				BackgroundSound.lastState.set(2);	
			}
		}
	}
	


	public static void attack() {
		if(SoundUtils.isSoundOn()) {
			attackSound.seek(Duration.ZERO);
			attackSound.setStartTime(Duration.millis(150));
			attackSound.setStopTime(Duration.millis(1200));
        	//Random volume
			attackSound.setVolume(0.2);
			attackSound.play();
		}
	}

	public static void showThread() {
		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
		System.out.println(threadSet.size());
	}
	
	public static void clickedSound() {
		if(SoundUtils.isSoundOn()) {
			click.seek(Duration.ZERO);
			click.setStartTime(Duration.millis(250));
	    	click.setStopTime(Duration.millis(1200));
	    	click.setVolume(5);
        	click.play(); 
		}
	}
	
	public static void createdRanger() {	
		if(SoundUtils.isSoundOn()) {
			createdSound.seek(Duration.ZERO);
			createdSound.setStartTime(Duration.millis(100));
			createdSound.setStopTime(Duration.millis(1200));
        	//Random volume
			createdSound.setVolume(0.2);
			createdSound.play(); 
		}
	}
	
	public static boolean isSoundOn() {
		return soundOn;
	}

	public static void setSoundOn(boolean isMuteSound) {
		SoundUtils.soundOn = isMuteSound;
		if(!isMuteSound) {
			BackgroundSound.lastState.set(2);	
		}else {
			playBackgroundMusic();
		}
	}

	public static void terminate() {
		if(playMusic != null) {
			System.out.println("in terminate(SoundUtils)");
			playMusic.interrupt();
		}
	}
	
	public static MuteButton getMuteButton() {
		return muteButton;
	}

	public static void setMuteButton(MuteButton muteButton) {
		SoundUtils.muteButton = muteButton;
	}
}
