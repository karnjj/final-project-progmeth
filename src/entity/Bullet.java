package entity;

import application.Drawing;
import entity.base.Attackable;
import entity.base.Damageable;
import entity.base.Entity;
import entity.base.Movable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import logic.AnimatedImage;
import logic.GameController;
import logic.Side;
import logic.State;

import java.awt.font.ImageGraphicAttribute;

public class Bullet extends Entity implements Attackable, Movable {
    private String name;
    private int attack;
    private final int attackRange = 0;
    private int speed;
    private Side side;
    private double sizeX;
    private double sizeY;

    private final double startTime = System.nanoTime() / 1e9;

    AnimatedImage animated = new AnimatedImage();
    public Bullet(double x,
                  double y,
                  String name,
                  int attack,
                  int speed,
                  Side side,
                  int frame
    ) {
        super(x, y);
        this.name = name;
        this.attack = attack;
        this.speed = speed;
        this.side = side;
        this.sizeX = 100;
        this.sizeY = 100;

        for (int i = 0; i < frame; i++)
            animated.frames.add(new Image(name + "/bullet_" + i + ".png"));
        animated.duration = 20.0/this.getSpeed();
    }

    @Override
    public void attack(Damageable e) {
        e.takeDamage(this.attack);
        this.setState(State.DEAD);
    }

    @Override
    public boolean canAttack() {
        return true;
    }

    @Override
    public int getAttackRange() {
        return this.attackRange;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    public Side getSide() {
        return side;
    }

    @Override
    public void move(double dt) {
        this.setX(this.getX() + speed * side.getVal() * dt);
    }

    private State checkState() {
        if (this.getState() == State.DEAD) return State.DEAD;
        Entity nearest = GameController.getFrontmost(this.getSide().getOpposite());
        if (nearest == null) return State.WALK;
        if (this.getSide().getVal()*(nearest.getX() - this.getX()) > attackRange) {
            return State.WALK;
        } else {
            return State.ATTACK;
        }
    }

    @Override
    public void update(double dt) {
        this.setState(checkState());
    }

    @Override
    public void draw(GraphicsContext gc, double t) {
        Image ig = animated.getFrame(t + startTime);
        gc.drawImage(
                ig,
                this.getX() - (this.getSide().getVal() * (this.sizeX/2)) + Drawing.getStartDraw(),
                this.getY() - (this.sizeY/2) - 30,
                this.getSide().getVal()*ig.getWidth(),
                ig.getHeight()
        );
    }
}
