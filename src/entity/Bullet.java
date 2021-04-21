package entity;

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
    private State state;
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

        this.state = State.NONE;
    }

    @Override
    public void attack(Damageable e) {
        e.takeDamage(this.attack);
        this.state = State.DEAD;
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

    public State getState() {
        return state;
    }

    @Override
    public void move(double dt) {
        this.setX(this.getX() + speed * side.getVal() * dt);
    }

    private State checkState() {
        if (side == Side.HERO) {
            Ranger nearest = GameController.getFrontRanger(Side.ENEMY);
            if (nearest == null) return State.WALK;
            if (this.getX() + attackRange < nearest.getX()) {
                return State.WALK;
            } else {
                return State.ATTACK;
            }
        } else if (side == Side.ENEMY) {
            Ranger nearest = GameController.getFrontRanger(Side.HERO);
            if (nearest == null) return State.WALK;
            if (this.getX() - attackRange > nearest.getX()) {
                return State.WALK;
            } else {
                return State.ATTACK;
            }
        }
        return State.NONE;
    }

    public void update(double dt) {
        this.state = checkState();
    }

    @Override
    public void draw(GraphicsContext gc, double t) {
        gc.setFill(Color.RED);
        gc.fillOval(this.getX(),this.getY()+50,25,25);
    }
}
