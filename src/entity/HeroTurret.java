package entity;

import logic.Side;

public class HeroTurret extends Turret{
    public static final String name;
    public static final int maxHP;
    public static final int attack;
    public static final int attackRange;
    public static final double attackDelay;
    public static final double sizeX;

    static {
        name = "HeroTurret";
        maxHP = 500;
        attack = 10;
        attackRange = 1000;
        attackDelay = 120;
        sizeX = 300;
    }

    public HeroTurret(double x,double y) {
        super(name, maxHP, attack, attackRange, attackDelay, x, y, sizeX, Side.HERO);
    }

}
