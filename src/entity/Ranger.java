package entity;

import entity.base.*;
import logic.GameController;
import logic.Side;
import logic.State;

public abstract class Ranger extends Entity implements Attackable, Damageable, Buyable, Movable {
    private String name;
    private int mxHP;
    private int currentHP;
    private int attack;
    private int attackRange;
    private double attackDelay;
    private double attackCountdown;
    private double buyCountdown;
    private double buyDelay;
    private int energyUsage;
    private int speed;
    private Side side;
    private State state;

    public Ranger(String name,
                  int mxHP,
                  int attack,
                  int attackRange,
                  double attackDelay,
                  double buyDelay,
                  int energyUsage,
                  int speed,
                  int x,
                  int y,
                  Side side
    ) {
        super(x, y);
        this.name = name;
        this.mxHP = mxHP;
        this.currentHP = mxHP;
        this.attack = attack;
        this.attackDelay = attackDelay;
        this.attackRange = attackRange;
        this.buyDelay = buyDelay;
        this.energyUsage = energyUsage;
        this.speed = speed;
        this.side = side;
        this.state = State.NONE;
        this.attackCountdown = this.attackDelay;
    }

    @Override
    public void attack(Damageable e) {
        e.takeDamage(this.attack);
        this.attackCountdown = this.attackDelay;
    }

    @Override
    public boolean canAttack() {
        return this.attackCountdown == 0;
    }

    @Override
    public int getAttackRange() {
        return this.attackRange;
    }

    @Override
    public void takeDamage(int i) {
        setCurrentHP(currentHP - i);
    }

    @Override
    public boolean isDead() {
        return currentHP == 0;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
        if (this.currentHP < 0) this.currentHP = 0;
    }

    public boolean canBuy() {
        return this.buyCountdown == 0 && GameController.getCurrentEnergy() > energyUsage;
    }

    public void Buy() {
        if (this.buyCountdown == 0 && GameController.getCurrentEnergy() > energyUsage) {
            GameController.useEnergy(energyUsage);
            setBuyCountdown(this.buyDelay);
        }
    }

    @Override
    public int getEnergyUsage() {
        return this.energyUsage;
    }

    public void setBuyCountdown(double buyCountdown) {
        this.buyCountdown = buyCountdown;
        if (this.buyCountdown < 0) this.buyCountdown = 0;
    }

    public void setAttackCountdown(double attackCountdown) {
        this.attackCountdown = attackCountdown;
        if (this.attackCountdown < 0) this.attackCountdown = 0;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    public State getState() {
        return state;
    }

    @Override
    public void move(double dt) {
        this.setX(this.getX() + speed * side.getVal() * dt);
    }

    private State checkState() {
        if (this.isDead()) {
            return State.DEAD;
        }
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

    public Side getSide() {
        return side;
    }

    public double getAttackDelay() {
        return attackDelay;
    }

    public double getAttackCountdown() {
        return attackCountdown;
    }

    public void update(double dt) {
        this.state = checkState();
        if(this.state == State.ATTACK)
            setAttackCountdown(attackCountdown - dt);
        setBuyCountdown(buyCountdown - dt);
    }
}