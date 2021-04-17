package entity;

import entity.base.*;
import logic.GameController;

public class Ranger extends Entity implements Attackable, Damageable, Buyable, Movable {
    private String name;
    private int attack;
    private int mxHP;
    private int currentHP;
    private int attackRange;
    private int cooldown;
    private int delayTime;
    private int energyUsage;
    private int speed;
    private int side; // 1 is hero, -1 is enemy

    public Ranger(int x, int y,String name,int side) {
        super(x, y);
        switch (name) {
            case "Karn" -> {
                this.name = name;
                this.currentHP = 100;
                this.mxHP = 100;
                this.attack = 10;
                this.attackRange = 50;
                this.delayTime = 6;
                this.energyUsage = 50;
                this.speed = 10;
                this.side = side;
            }
            
            case "Non" -> {
                this.name = name;
                this.currentHP = 200;
                this.mxHP = 200;
                this.attack = 3;
                this.attackRange = 5;
                this.delayTime = 8;
                this.energyUsage = 80;
                this.speed = 30;
                this.side = side;
            }
        }
    }

    @Override
    public void attack(Damageable e) {
        e.takeDamage(this.attack);
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
        if(this.currentHP < 0) this.currentHP = 0;
    }

    public boolean canBuy() {
        return this.cooldown == 0 && GameController.getCurrentEnergy() > energyUsage;
    }

    public void Buy() {
        if(this.cooldown == 0 && GameController.getCurrentEnergy() > energyUsage) {
            GameController.useEnergy(energyUsage);
            setCooldown(this.delayTime);
        }
    }

    @Override
    public int getEnergyUsage() {
        return this.energyUsage;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
        if (this.cooldown < 0) this.cooldown = 0;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public void move(double dt) {
        this.setX(this.getX() + speed*side*dt);
    }
}
