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
	private static MediaPlayer sound;
	private static MediaPlayer effect;
	private static Thread playMusic;
	
	private static boolean soundOn = true;
    private static MuteButton muteButton ;

//	private static Thread effectSound;
	private static final Media homeBackgroundSound = new Media(ClassLoader.getSystemResource("sound/HomeGameSound.mp3").toExternalForm());
	private static final Media playBackgroundSound = new Media(ClassLoader.getSystemResource("sound/gameLoop.wav").toExternalForm());
	private static final Media createdSound = new Media(ClassLoader.getSystemResource("sound/createdRanger.mp3").toExternalForm());
	private static final Media attackSound = new Media(ClassLoader.getSystemResource("sound/attackSound.wav").toExternalForm());
	
	public static void playBackgroundMusic() {
		if(isSoundOn()) {
			Runnable music = new Runnable()
			{
				public void run()
	            {
					stopBackgroundMusic();
	            	Media media = homeBackgroundSound;
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
		Thread hitThread;
		if(SoundUtils.isSoundOn()) {
			Runnable efSound = new Runnable()
			{
				public void run()
	            {

					effect = new MediaPlayer(attackSound);
	            	effect.setStartTime(Duration.millis(150));
	            	effect.setStopTime(Duration.millis(1200));
	            	//Random volume
	            	effect.setVolume(0.2);
	            	effect.play(); 
	            }
			};
			hitThread = new Thread(efSound);
			hitThread.start();
		}
	}
	
	
	
	public static void showThread() {
		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
		System.out.println(threadSet.size());
	}
	
	public static void clickedSound() {
		if(SoundUtils.isSoundOn()) {
			Thread effectSound;
			Runnable efSound = new Runnable()
			{
				public void run()
	            {
					
	            	Media media = new Media(ClassLoader.getSystemResource("sound/buttonSound.wav").toExternalForm());
	            	sound = new MediaPlayer(media); 
	            	sound.setStartTime(Duration.millis(250));
					sound.setStopTime(Duration.millis(1200));
	            	sound.setVolume(5);
	            	sound.play(); 
	            }
			};
			effectSound = new Thread(efSound);
			effectSound.start();
			effectSound.interrupt();
		}
	}
	
	public static void createdRanger() {
		Thread thread;
		if(SoundUtils.isSoundOn()) {
			Runnable efSound = new Runnable()
			{
				public void run()
	            {

					effect = new MediaPlayer(createdSound);
	            	effect.setStartTime(Duration.millis(100));
	            	effect.setStopTime(Duration.millis(1200));
	            	//Random volume
	            	effect.setVolume(0.2);
	            	effect.play(); 
	            }
			};
			thread = new Thread(efSound);
			thread.start();
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
