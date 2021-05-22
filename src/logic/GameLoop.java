package logic;

import application.SoundUtils;
import gui.HeroPane;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

import application.Drawing;

public class GameLoop{
    private GraphicsContext gc;

    private int updates; // count update
    private int draws; // count draw

    public GameLoop(GraphicsContext gc) {
        this.gc = gc;
        this.updates = 0;
        this.draws = 0;
    }

    public void start() {
        this.run();
    }

    private void init() {
        GameController.init();
        Drawing.init(gc);
        SoundUtils.init();
    }

    private void update(double dt) {
//    	SoundUtils.showThread();
    	Drawing.updatePosBg(dt);
    	Drawing.getLevelupButton().update();
    	if (GameController.getGameState() != GameState.Pause) {
    		GameController.isGameOver();
            GameController.updateEntities(dt);
            GameController.updateEnergy(dt);
            HeroPane.update(dt);
    	}
    	if(GameController.getGameState() == GameState.Play) {
    	    GameController.updateBot(dt);
        }
        updates++;
    }

    private void draw(double t) {
        if(GameController.getGameState() != GameState.Pause) {
            Drawing.resetBackgroundEffect();
            Drawing.drawEverything(t);
        }
        draws++;
    }

    public void run() {
        this.init();
        new AnimationTimer() {
            double delta = 0;
            double pastTick = System.nanoTime();
            double timer = System.currentTimeMillis();
            @Override
            public void handle(long now) {
                delta = (now - pastTick) / 1e9;

                update(delta);

                draw(now / 1e9);

                pastTick = now;


//                check UPS and FPS
                if (System.currentTimeMillis() - timer >= 1000) {
//                    System.out.println("UPS: " + updates + ", FPS: " + draws);
                    updates = 0;
                    draws = 0;
                    timer += 1000;
                }
            }
        }.start();
    }
}
