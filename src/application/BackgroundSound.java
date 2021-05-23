package application;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class BackgroundSound extends Thread {
    public static AtomicInteger lastState = new AtomicInteger(0); // 0 = home, 1=play, 2= stop
    private final MediaPlayer homeSound = new MediaPlayer(new Media(ClassLoader.getSystemResource("sound/HomeGameSound.mp3").toExternalForm()));
    private final MediaPlayer playSound = new MediaPlayer(new Media(ClassLoader.getSystemResource("sound/gameLoop.wav").toExternalForm()));
    private MediaPlayer mediaPlayer;
    private int previous = -1;


    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
                if (lastState.get() != previous) {
                    stopSound();
                    switch (lastState.get()) {
                        case 0, 2 -> {
                            mediaPlayer = homeSound;
                            break;
                        }
                        case 1 -> {
                            mediaPlayer = playSound;
                            break;
                        }
                    }
                    mediaPlayer.setOnEndOfMedia(new Runnable() {
                        public void run() {
                            mediaPlayer.seek(Duration.ZERO);
                        }
                    });
                    mediaPlayer.setVolume(0.3);
                    if (lastState.get() == 2) {
                        stopSound();
                    } else {
                        mediaPlayer.play();
                    }
                }
                previous = lastState.get();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void stopSound() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}
