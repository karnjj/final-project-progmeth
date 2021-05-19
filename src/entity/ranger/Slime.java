package entity.ranger;

import logic.Side;

public class Slime extends Ranger {
    private static final String name;
    private static final int maxHP;
    private static final int attack;
    private static final int attackRange;
    private static final double attackDelay;
    private static final double buyDelay;
    private static final int energyUsage;
    private static final int speed;
    private static final double sizeX;
    private static final double sizeY;

    private static final int walkFrame;
    private static final int atkFrame;
    private static final int idleFrame;

    static {
        name = "Slime";
        maxHP = 200;
        attack = 5;
        attackRange = 100;
        attackDelay = 2;
        buyDelay = 4;
        energyUsage = 20;
        speed = 100;
        sizeX = 100;
        sizeY = 100;
        walkFrame = 4;
        atkFrame = 5;
        idleFrame = 4;
    }

    public Slime(double x, double y, Side side) {
        super(name,
                maxHP,
                attack,
                attackRange,
                attackDelay,
                buyDelay,
                energyUsage,
                speed,
                x,
                y,
                side,
                sizeX,
                sizeY,
                walkFrame,
                atkFrame,
                idleFrame
        );
    }

}