package logic;

import entity.ranger.Inkblue;
import entity.ranger.Ranger;
import entity.ranger.Slime;
import gui.HeroPane;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

import application.Drawing;
import application.SoundUtils;

public class GameLoop{
    int width, height;
    GraphicsContext gc;

    int framePerSecond; // FPS
    int updatePerSecond; // UPS
    private boolean running;

    int updates = 0; // count update
    int draws = 0; // count draw

    public GameLoop(GraphicsContext gc, int width, int height) {
        this.width = width;
        this.height = height;
        this.gc = gc;
    }

    public void start() {
        running = true;
        this.run();
    }

    private void init() {
        framePerSecond = 60;
        updatePerSecond = 60;
    }

    private void update(double dt) {
    	SoundUtils.showThread();
    	Drawing.updatePosiBg(dt);
    	if (GameController.getGameState() != GameState.Pause) {
    		GameController.isGameOver();
            GameController.updateEntities(dt);
            GameController.updateEnergy(dt);
            HeroPane.update(dt);
    	}
    	if(GameController.getGameState() == GameState.Play) {
    	    GameController.updateBot(dt);
        }
//        System.out.println("karn HP:" + karn.getCurrentHP() + " non HP:" + non.getCurrentHP());
        updates++;
    }

    private void draw(double t) {
        Drawing.drawEverything(gc,t);
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
                    System.out.println("UPS: " + updates + ", FPS: " + draws);
                    updates = 0;
                    draws = 0;
                    timer += 1000;
                }
            }
        }.start();
    }
}
