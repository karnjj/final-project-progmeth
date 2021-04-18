package entity;

import logic.Side;

public class Pirate extends Ranger {
    private static final String name;
    private static final int mxHP;
    private static final int attack;
    private static final int attackRange;
    private static final double attackDelay;
    private static final double buyDelay;
    private static final int energyUsage;
    private static final int speed;
    private static final int sizeX;

    static {
        name = "Pirate";
        mxHP = 100;
        attack = 200;
        attackRange = 50;
        attackDelay = 3;
        buyDelay = 6;
        energyUsage = 80;
        speed = 150;
        sizeX = 100;
    }

    public Pirate(int x, int y, Side side) {
        super(name,
                mxHP,
                attack,
                attackRange,
                attackDelay,
                buyDelay,
                energyUsage,
                speed,
                x,
                y,
                side,
                sizeX
        );
    }

    public static String getName() {
        return name;
    }
}