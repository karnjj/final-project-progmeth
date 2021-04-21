package entity;

import entity.base.Attackable;
import entity.base.Buyable;
import entity.base.Damageable;
import entity.base.Entity;
import logic.Side;
import logic.State;

public class Turret extends Entity implements Attackable, Damageable{
	private String name;
    private int mxHP;
    private int currentHP;
    private int attack;
    private int attackRange;
    private double attackDelay;
    private double attackCountdown;

    public Turret(String name,
                  int mxHP,
                  int attack,
                  int attackRange,
                  double attackDelay,
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
        this.attackCountdown = this.attackDelay;
    }

    @Override
    public void attack(Damageable e) {

    }

    @Override
    public boolean canAttack() {
        return false;
    }

    @Override
    public int getAttackRange() {
        return 0;
    }

    @Override
    public void takeDamage(int i) {

    }

    @Override
    public boolean isDead() {
        return false;
    }
}
