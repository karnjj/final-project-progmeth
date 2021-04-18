package logic;

import entity.Ranger;
import entity.base.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Iterator;

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
        Ranger karn = new Ranger(0,height-30,"Pirate",Side.HERO);
        Ranger non = new Ranger(width-25,height-30,"Slime",Side.ENEMY);
        Ranger karn2 = new Ranger(0,height-40,"Slime",Side.HERO);
        Ranger non2 = new Ranger(width-25,height-40,"Pirate",Side.ENEMY);
        GameController.getHero().add(karn);
        GameController.getEnemy().add(non);
        GameController.getHero().add(karn2);
        GameController.getEnemy().add(non2);
    }

    private void update(double dt) {
        Iterator<Ranger> iterator = GameController.getHero().iterator();
        while (iterator.hasNext()) {
            Ranger e = iterator.next();
            e.update(dt);
            if(e.getState() == logic.State.WALK) e.move(dt);
            if(e.getState() == logic.State.ATTACK && e.canAttack()) e.attack(e.nearestTarget());
            if(e.getState() == logic.State.DEAD ) iterator.remove();
        }
        iterator = GameController.getEnemy().iterator();
        while (iterator.hasNext()) {
            Ranger e = iterator.next();
            e.update(dt);
            if(e.getState() == logic.State.WALK) e.move(dt);
            if(e.getState() == logic.State.ATTACK && e.canAttack()) e.attack(e.nearestTarget());
            if(e.getState() == logic.State.DEAD ) iterator.remove();
        }
//        System.out.println("karn HP:" + karn.getCurrentHP() + " non HP:" + non.getCurrentHP());
        updates++;
    }

    private void draw() {
        gc.clearRect(0,0,width,height);
        for(Ranger e : GameController.getHero()) {
            e.draw(gc);
        }
        for(Ranger e : GameController.getEnemy()) {
            e.draw(gc);
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
                draw();
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
