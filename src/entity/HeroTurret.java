package entity;

import logic.Side;

public class HeroTurret extends Turret{
    private static final String name;
    private static final int maxHP;
    private static final int attack;
    private static final int attackRange;
    private static final double attackDelay;
    private static final double sizeX;

    static {
        name = "HeroTurret";
        maxHP = 3000;
        attack = 10;
        attackRange = 1000;
        attackDelay = 120;
        sizeX = 300;
    }

    public HeroTurret(double x,double y) {
        super(name, maxHP, attack, attackRange, attackDelay, x, y, sizeX, Side.HERO);
    }

}
