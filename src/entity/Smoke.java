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

    private double timeDelay = 0.4;

    private AnimatedImage animated = new AnimatedImage();

    public Smoke(double x, double y) {
        super(x, y);

        for (int i = 0; i < 4; i++)
            animated.getFrames().add(new Image( "Smoke/smoke_" + i + ".png" ));
        animated.setDuration(timeDelay / 4) ;
    }

    @Override
    public void draw(GraphicsContext gc, double t) throws NullImageToRenderException {
        if(this.getState() == State.DEAD) return;
        Image ig = null;
        try {
            ig = animated.getFrame(0.4-this.timeDelay);
        }catch (IndexOfFrameOutboundException e) {
            System.out.println(e.getMessage());
        }
        if (ig == null) throw new NullImageToRenderException();
        gc.drawImage(
                ig,
                this.getX() - 50 + Drawing.getMovePosBgX(),
                this.getY() - 50 + Drawing.getMovePosBgY()
        );
    }

    public void setTimeDelay(double timeDelay) {
        this.timeDelay = timeDelay;
        if (this.timeDelay < 0) this.timeDelay = 0;
    }

    public void update(double t){
    	setTimeDelay(this.timeDelay - t);
        if (this.timeDelay == 0) this.setState(State.DEAD);
    }

}
