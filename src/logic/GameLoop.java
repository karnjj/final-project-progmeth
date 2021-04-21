package logic;

import entity.Inkblue;
import entity.Pirate;
import entity.Ranger;
import entity.Slime;
import entity.base.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Iterator;

import application.Drawing;

public class GameLoop extends Thread {
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

    @Override
    public void start() {
        super.start();
        running = true;
    }

    private void init() {
        framePerSecond = 60;
        updatePerSecond = 360;
        GameController.InitGame();
        Ranger pirate = new Inkblue(0,height-100,Side.HERO);
        Ranger pirate2 = new Inkblue(width,height-120,Side.ENEMY);
        Ranger slime = new Slime(0,height-80,Side.HERO);
        Ranger slime2 = new Slime(width,height-100,Side.ENEMY);
        GameController.getHero().add(slime);
        GameController.getEnemy().add(slime2);
        GameController.getHero().add(pirate);
        GameController.getEnemy().add(pirate2);
    }

    private void update(double dt) {
        GameController.updateHero(dt);
        GameController.updateEnemy(dt);
        GameController.updateEnergy(dt);
        GameController.updateBullet(dt);
        GameController.updateSmoke(dt);
//        System.out.println("karn HP:" + karn.getCurrentHP() + " non HP:" + non.getCurrentHP());
        updates++;
    }

    private void draw(GraphicsContext gc,double t) {
        Drawing.drawEverything(gc,t);
        draws++;
    }

    @Override
    public void run() {
        this.init();
        final double uOPTIMAL_TIME = 1e9 / updatePerSecond;
        final double fOPTIMAL_TIME = 1e9 / framePerSecond;
        long startNanoTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double uDeltaTime = 0, fDeltaTime = 0;

        while (running) {
            long currentNanoTime = System.nanoTime();
            uDeltaTime += (currentNanoTime - startNanoTime);
            fDeltaTime += (currentNanoTime - startNanoTime);
            startNanoTime = currentNanoTime;
            if (uDeltaTime >= uOPTIMAL_TIME) {
                update(uDeltaTime / 1e9);
                
                uDeltaTime -= uOPTIMAL_TIME;
            }
            if (fDeltaTime >= fOPTIMAL_TIME) {
                draw(gc,currentNanoTime / 1e9);

                fDeltaTime -= fOPTIMAL_TIME;
            }

            //check UPS and FPS
            if (System.currentTimeMillis() - timer >= 1000) {
                System.out.println("UPS: " + updates + ", FPS: " + draws);
                updates = 0;
                draws = 0;
                timer += 1000;
            }
        }
    }
}
