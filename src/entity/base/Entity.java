package entity.base;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Entity {
    private double x;
    private double y;

    public Entity(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void draw(GraphicsContext gc,double t) {
        gc.setFill(Color.BLACK);
        gc.fillOval(x,y,25,25);
    }

    public double getCenter() {return this.x; }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
