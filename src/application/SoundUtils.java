package application;

import java.util.Set;

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
	private static final MediaPlayer attackSound = new MediaPlayer(new Media(ClassLoader.getSystemResource("sound/attackSound.wav").toExternalForm()));
	private static Thread playMusic;
	private static Thread effectThread;
	
	private static boolean soundOn = true;
    private static MuteButton muteButton ;

//	private static Thread effectSound;
	private static final Media homeBackgroundSound = new Media(ClassLoader.getSystemResource("sound/HomeGameSound.mp3").toExternalForm());
	private static final Media playBackgroundSound = new Media(ClassLoader.getSystemResource("sound/gameLoop.wav").toExternalForm());
	
	public static void init() {
		playBackgroundMusic();
	}
	
	public static void playBackgroundMusic() {
		if(isSoundOn()) {
			Runnable music = new Runnable()
			{
				public void run()
	            {
					stopBackgroundMusic();
	            	Media media = homeBackgroundSound;
	            	System.out.println(GameController.getGameState());
	            	if(GameController.getGameState()==GameState.Play) {
	            		media= playBackgroundSound;
	            	}
	            	backgroundMusic = new MediaPlayer(media);
	            	backgroundMusic.setOnEndOfMedia(new Runnable() {
					       public void run() {
					    	   backgroundMusic.seek(Duration.ZERO);
					       }
					   });
	            	backgroundMusic.setVolume(0.3);
	            	backgroundMusic.play();
	            	if(GameController.getGameState() == GameState.Pause) {
	            		stopBackgroundMusic();
	            	}
	            }
			};
			playMusic = new Thread(music);
			playMusic.start();
		}else {
			terminate();
		}
	}
	
	public static void stopBackgroundMusic() {
		if(backgroundMusic != null) {
			backgroundMusic.stop();
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
	    	click.setOnEndOfMedia(effectThread);
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
			stopBackgroundMusic();
		}else {
			playBackgroundMusic();
		}
	}

	public static void terminate() {
		playMusic.interrupt();
	}
	
	public static MuteButton getMuteButton() {
		return muteButton;
	}

	public static void setMuteButton(MuteButton muteButton) {
		SoundUtils.muteButton = muteButton;
	}
}
