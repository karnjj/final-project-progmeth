package application;

import java.util.Set;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import logic.GameController;
import logic.GameState;

public class SoundUtils {
	private static MediaPlayer backgroundMusic;
	private static MediaPlayer sound;
	private static Thread musicplay;
//	private static Thread effectSound;
	private static Media homeBackgroudSound = new Media(ClassLoader.getSystemResource("sound/HomeGameSound.mp3").toExternalForm()); 
	private static Media playBackgroudSound = new Media(ClassLoader.getSystemResource("sound/gameLoop.wav").toExternalForm());
	private static Media hitSound = new Media(ClassLoader.getSystemResource("sound/hit.wav").toExternalForm());
	
	public static void playBackgroundMusic() {
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
	}
	
	public static void stopBackgroundMusic() {
		if(backgroundMusic != null) {
			System.out.println("in playbackgroundMusic");
			backgroundMusic.stop();
		}
	}
	
	
//	public static void hitSound() {
//		Thread hitThread;
//		Runnable efSound = new Runnable()
//		{
//			public void run()
//            {
//				
//            	Media media = hitSound; 
//            	sound = new MediaPlayer(media); 
//            	sound.setStartTime(Duration.millis(250));
//				sound.setStopTime(Duration.millis(1200));
//            	sound.setVolume(5);
//            	sound.play(); 
//            }
//		};
////		hitThread = new 
//	}
	
	public static void showThread() {
		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
		System.out.println(threadSet.size());
	}
	
	public static void clickedSound() {
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
		showThread();
		effectSound.interrupt();
	}
}
