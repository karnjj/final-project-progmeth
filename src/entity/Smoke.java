package entity;

import application.Drawing;
import entity.base.Entity;
import exception.IndexOfFrameOutboundException;
import exception.NullImageToRenderException;
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
    public void draw(GraphicsContext gc, double t) throws NullImageToRenderException {
        Image ig = null;
        try {
            ig = animated.getFrame(0.4-this.timeLeft);
        }catch (IndexOfFrameOutboundException e) {
            System.out.println(e.getMessage());
        }
        if (ig == null) throw new NullImageToRenderException();
        gc.drawImage(
                ig,
                this.getX() - 50 + Drawing.getMovePosBgX(),
                this.getY() - 50
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
