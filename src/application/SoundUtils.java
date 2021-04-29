package application;

import java.util.Set;

import gui.MuteButton;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import logic.GameController;
import logic.GameState;

public class SoundUtils {
	private static MediaPlayer backgroundMusic;
	private static MediaPlayer sound;
	private static MediaPlayer effect;
	private static Thread musicplay;
	
	private static boolean soundOn = true;
    private static MuteButton muteButton ;

//	private static Thread effectSound;
	private static Media homeBackgroudSound = new Media(ClassLoader.getSystemResource("sound/HomeGameSound.mp3").toExternalForm()); 
	private static Media playBackgroudSound = new Media(ClassLoader.getSystemResource("sound/gameLoop.wav").toExternalForm());
	private static Media createdSound = new Media(ClassLoader.getSystemResource("sound/createdRanger.mp3").toExternalForm());
	private static Media hitSound = new Media(ClassLoader.getSystemResource("sound/hit.wav").toExternalForm());
	private static Media attrackSound = new Media(ClassLoader.getSystemResource("sound/attrackSound.wav").toExternalForm());
	
	public static void playBackgroundMusic() {
		if(isSoundOn()) {
			Runnable music = new Runnable()
			{
				public void run()
	            {
					stopBackgroundMusic();
	            	Media media = homeBackgroudSound;
	            	if(GameController.getGameState()==GameState.Play) {
	            		media=playBackgroudSound;
	            	}
	            	backgroundMusic = new MediaPlayer(media);  
	            	backgroundMusic.setVolume(0.5);
	            	backgroundMusic.setOnEndOfMedia(new Runnable() {
					       public void run() {
					    	   backgroundMusic.seek(Duration.ZERO);
					       }
					   });
	            	backgroundMusic.setVolume(0.5);
	            	backgroundMusic.play();
	            	if(GameController.getGameState() == GameState.Pause) {
	            		stopBackgroundMusic();
	            	}
	            }
			};
			musicplay = new Thread(music);
			musicplay.start();
		}else {
			stopBackgroundMusic();
		}
	}
	
	public static void stopBackgroundMusic() {
		if(backgroundMusic != null) {
//			System.out.println("in playbackgroundMusic");
			backgroundMusic.stop();
		}
	}
	
	
	public static void hitSound() {
		Thread hitThread;
		if(SoundUtils.isSoundOn()) {
			Runnable efSound = new Runnable()
			{
				public void run()
	            {
					
	            	Media media = hitSound; 
	            	effect = new MediaPlayer(media); 
	            	effect.setStartTime(Duration.millis(100));
	            	effect.setStopTime(Duration.millis(1200));
	            	//Random valume
	            	effect.setVolume(0.2);
	            	effect.play(); 
	            }
			};
			hitThread = new Thread(efSound);
			hitThread.run(); 
		}
	}
	
	
	public static void attrack() {
		Thread hitThread;
		if(SoundUtils.isSoundOn()) {
			Runnable efSound = new Runnable()
			{
				public void run()
	            {
					
	            	Media media = attrackSound; 
	            	effect = new MediaPlayer(media); 
	            	effect.setStartTime(Duration.millis(150));
	            	effect.setStopTime(Duration.millis(1200));
	            	//Random valume
	            	effect.setVolume(0.2);
	            	effect.play(); 
	            }
			};
			hitThread = new Thread(efSound);
			hitThread.run(); 
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
					
	            	Media media = new Media(getClass().getClassLoader().getResource("sound/buttonSound.wav").toExternalForm()); 
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
					
	            	Media media = createdSound; 
	            	effect = new MediaPlayer(media); 
	            	effect.setStartTime(Duration.millis(100));
	            	effect.setStopTime(Duration.millis(1200));
	            	//Random valume
	            	effect.setVolume(0.2);
	            	effect.play(); 
	            }
			};
			thread = new Thread(efSound);
			thread.run(); 
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

	public static MuteButton getMuteButton() {
		return muteButton;
	}

	public static void setMuteButton(MuteButton muteButton) {
		SoundUtils.muteButton = muteButton;
	}
}
