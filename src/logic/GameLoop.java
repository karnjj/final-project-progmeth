package logic;

import entity.Ranger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameLoop extends Thread {
    int width, height;
    GraphicsContext gc;

    int framePerSecond; // FPS
    int updatePerSecond; // UPS
    private boolean running;

    int updates = 0; // count update
    int draws = 0; // count draw

    Ranger karn,non;

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
        karn = new Ranger(0,height-25,"Karn",1);
        non = new Ranger(width-25,height-25,"Non",-1);
    }

    private void update(double dt) {
        karn.move(dt);
        updates++;
    }

    private void draw() {
        gc.clearRect(0,0,width,height);
        karn.draw(gc);
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
