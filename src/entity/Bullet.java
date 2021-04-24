package entity;

import application.Drawing;
import entity.base.Attackable;
import entity.base.Damageable;
import entity.base.Entity;
import entity.base.Movable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.GameController;
import logic.Side;
import logic.State;

public class Bullet extends Entity implements Attackable, Movable {
    private String name;
    private int attack;
    private final int attackRange = 0;
    private int speed;
    private Side side;
    private double sizeX;
    public Bullet(double x,
                  double y,
                  String name,
                  int attack,
                  int speed,
                  Side side,
                  double sizeX
    ) {
        super(x, y);
        this.name = name;
        this.attack = attack;
        this.speed = speed;
        this.side = side;
        this.sizeX = sizeX;
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
        Ranger nearest = GameController.getFrontRanger(this.getSide().getOpposite());
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
        gc.setFill(Color.RED);
        gc.fillOval(this.getX()+ Drawing.getStartDraw(),this.getY()+50,25,25);
    }
}
