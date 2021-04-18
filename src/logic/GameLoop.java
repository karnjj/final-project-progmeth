package logic;

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
        updatePerSecond = 60;
        GameController.InitGame();
        Ranger pirate = new Pirate(0,height-30,Side.HERO);
        Ranger pirate2 = new Pirate(width-25,height-60,Side.ENEMY);
        Ranger slime = new Slime(0,height-100,Side.HERO);
        Ranger slime2 = new Slime(width-25,height-150,Side.ENEMY);
//        GameController.getHero().add(pirate);
//        GameController.getEnemy().add(pirate2);
        GameController.getHero().add(slime);
        GameController.getEnemy().add(slime2);
    }

    private void update(double dt) {
        Iterator<Ranger> iterator = GameController.getHero().iterator();
        while (iterator.hasNext()) {
            Ranger e = iterator.next();
            e.update(dt);
            if(e.getState() == logic.State.DEAD) iterator.remove();
            if(e.getState() == logic.State.ATTACK && e.canAttack())
                e.attack(GameController.getFrontRanger(Side.ENEMY));
            if(e.getState() == logic.State.WALK) e.move(dt);
            System.out.println(e.getState());
        }
        iterator = GameController.getEnemy().iterator();
        while (iterator.hasNext()) {
            Ranger e = iterator.next();
            e.update(dt);
            if(e.getState() == logic.State.DEAD) iterator.remove();
            if(e.getState() == logic.State.ATTACK && e.canAttack())
                e.attack(GameController.getFrontRanger(Side.HERO));
            if(e.getState() == logic.State.WALK) e.move(dt);
        }
//        System.out.println("karn HP:" + karn.getCurrentHP() + " non HP:" + non.getCurrentHP());
        updates++;
    }

    private void draw(double t) {
        gc.clearRect(0,0,width,height);
        Drawing.drawBackground(gc);
        for(Ranger e : GameController.getHero()) {
            e.draw(gc,t);
        }
        for(Ranger e : GameController.getEnemy()) {
            e.draw(gc,t);
        }
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
                draw(currentNanoTime / 1e9);
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
