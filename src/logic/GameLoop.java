package logic;

import entity.ranger.Inkblue;
import entity.ranger.Ranger;
import entity.ranger.Slime;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

import application.Drawing;

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
        GameController.InitGame();
        Ranger pirate = new Inkblue(0,height-100,Side.HERO);
        Ranger pirate2 = new Inkblue(width,height-120,Side.ENEMY);
        Ranger slime = new Slime(0,height-80,Side.HERO);
        Ranger slime2 = new Slime(width,height-100,Side.ENEMY);
//        GameController.getHero().add(slime);
//        GameController.getEnemy().add(slime2);
//        GameController.getHero().add(pirate);
//        GameController.getEnemy().add(pirate2);
    }

    private void update(double dt) {
    	Drawing.updatePosiBg(dt);
    	if (GameController.getGameState() != GameState.Pause) {
            GameController.updateEntities(dt);
            GameController.updateEnergy(dt);
            
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
        final double uOPTIMAL_TIME = 1e9 / updatePerSecond;
        final double fOPTIMAL_TIME = 1e9/ framePerSecond;

//        while (running) {
//            long currentNanoTime = System.nanoTime();
//            uDeltaTime += (currentNanoTime - startNanoTime);
//            fDeltaTime += (currentNanoTime - startNanoTime);
//            startNanoTime = currentNanoTime;
//            if (uDeltaTime >= uOPTIMAL_TIME) {
//                update(uDeltaTime / 1e9);
//
//                uDeltaTime -= uOPTIMAL_TIME;
//            }
//            if (fDeltaTime >= fOPTIMAL_TIME) {
//                draw(gc,currentNanoTime / 1e9);
//
//                fDeltaTime -= fOPTIMAL_TIME;
//            }
//
//            //check UPS and FPS
//            if (System.currentTimeMillis() - timer >= 1000) {
//                System.out.println("UPS: " + updates + ", FPS: " + draws);
//                updates = 0;
//                draws = 0;
//                timer += 1000;
//            }
//        }
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
