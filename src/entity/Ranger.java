package entity;

import entity.base.*;
import logic.GameController;
import logic.Side;
import logic.State;

import java.util.Collections;
import java.util.Comparator;

public class Ranger extends Entity implements Attackable, Damageable, Buyable, Movable {
    private String name;
    private int attack;
    private int mxHP;
    private int currentHP;
    private int attackRange;
    private double buyCooldown;
    private double buyDelay;
    private int energyUsage;
    private int speed;
    private Side side; // 1 is hero, -1 is enemy
    private double attackCooldown;
    private double attackDelay;
    private State state;

    public Ranger(int x, int y, String name, Side side) {
        super(x, y);
        switch (name) {
            case "Pirate" -> {
                this.name = name;
                this.currentHP = 100;
                this.mxHP = 100;
                this.attack = 30;
                this.attackRange = 50;
                this.buyDelay = 6;
                this.energyUsage = 50;
                this.speed = 100;
                this.side = side;
                this.attackDelay = 1;
            }
            case "Slime" -> {
                this.name = name;
                this.currentHP = 200;
                this.mxHP = 200;
                this.attack = 20;
                this.attackRange = 100;
                this.buyDelay = 6;
                this.energyUsage = 50;
                this.speed = 80;
                this.side = side;
                this.attackDelay = 2;
            }
        }
    }

    @Override
    public void attack(Damageable e) {
        e.takeDamage(this.attack);
        this.attackCooldown = this.attackDelay;
    }

    @Override
    public boolean canAttack() {
        return this.attackCooldown == 0;
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

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
        if (this.currentHP < 0) this.currentHP = 0;
    }

    public boolean canBuy() {
        return this.buyCooldown == 0 && GameController.getCurrentEnergy() > energyUsage;
    }

    public void Buy() {
        if (this.buyCooldown == 0 && GameController.getCurrentEnergy() > energyUsage) {
            GameController.useEnergy(energyUsage);
            setBuyCooldown(this.buyDelay);
        }
    }

    @Override
    public int getEnergyUsage() {
        return this.energyUsage;
    }

    public void setBuyCooldown(double buyCooldown) {
        this.buyCooldown = buyCooldown;
        if (this.buyCooldown < 0) this.buyCooldown = 0;
    }

    public void setAttackCooldown(double attackCooldown) {
        this.attackCooldown = attackCooldown;
        if (this.attackCooldown < 0) this.attackCooldown = 0;
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

    private void updateState() {
        if (getCurrentHP() == 0) {
            this.state = State.DEAD;
            return;
        }
        Ranger nearest = nearestTarget();
        if (nearest == null) {
            this.state = State.WALK;
            return;
        }
        if (side == Side.HERO) {
            if (this.getX() + attackRange < nearest.getX()) {
                this.state = State.WALK;
            } else {
                this.state = State.ATTACK;
            }
        } else if (side == Side.ENEMY) {
            if (this.getX() - attackRange > nearest.getX()) {
                this.state = State.WALK;
            } else {
                this.state = State.ATTACK;
            }
        }
    }

    public Ranger nearestTarget() {
        Ranger target = null;
        if (side == Side.HERO) {
            if (GameController.getEnemy().isEmpty()) return null;
            target = Collections.min(GameController.getEnemy(),
                    Comparator.comparing(Ranger::getX));

        } else if (side == Side.ENEMY) {
            if (GameController.getHero().isEmpty()) return null;
            target = Collections.max(GameController.getHero(),
                    Comparator.comparing(Ranger::getX));

        }
        return target;
    }

    public void update(double dt) {
        updateState();
        setAttackCooldown(attackCooldown - dt);
        setBuyCooldown(buyCooldown - dt);
    }
}