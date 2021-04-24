package entity;

import application.Drawing;
import entity.base.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.AnimatedImage;
import logic.State;

public class Smoke extends Entity {

    private double timeLeft = 0.4;

    AnimatedImage animated = new AnimatedImage();

    public Smoke(double x, double y) {
        super(x, y);

        for (int i = 0; i < 4; i++)
            animated.frames.add(new Image( "Smoke/smoke_" + i + ".png" ));
        animated.duration = timeLeft / 4;
    }

    @Override
    public void draw(GraphicsContext gc, double t) {
        gc.drawImage(
                animated.getFrame(0.4-this.timeLeft),
                this.getX()-50 + Drawing.getStartDraw(),
                this.getY()
        );
    }

    public void setTimeLeft(double timeLeft) {
        this.timeLeft = timeLeft;
        if (this.timeLeft < 0) this.timeLeft = 0;
    }

    public void update(double t){
        setTimeLeft(this.timeLeft - t);
        if (this.timeLeft == 0) this.setState(State.DEAD);
    }

}
