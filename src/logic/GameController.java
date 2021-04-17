package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameController extends Thread {
    int width, height;
    GraphicsContext gc;

    int framePerSecond; // FPS
    int updatePerSecond; // UPS
    private boolean running;

    int updates = 0; // count update
    int draws = 0; // count draw

    public GameController(GraphicsContext gc,int width,int height) {
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
    }

    private void update() {
        x+=1;
        updates++;
    }

    int x = 0;
    private void draw() {
        gc.clearRect(0,0,width,height);
        gc.setFill(Color.BLACK);
        gc.fillOval(x,height-25,25,25);
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
                update();
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
