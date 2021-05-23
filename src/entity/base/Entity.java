package entity.base;

import exception.NullImageToRenderException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.Side;
import logic.State;

public abstract class Entity {
    private double x;
    private double y;

    private State state;
    private Side side;

    public Entity(double x, double y) {
        this.x = x;
        this.y = y;
        this.state = State.NONE;
    }

    public void draw(GraphicsContext gc, double t) throws NullImageToRenderException {
        gc.setFill(Color.BLACK);
        gc.fillOval(x, y, 25, 25);
    }

    public void update(double dt) {
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Side getSide() {
        return this.side;
    }

    public void setSide(Side side) {
        this.side = side;
    }
}
