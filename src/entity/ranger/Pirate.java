package entity.ranger;

import logic.Side;

public class Pirate extends Ranger {
    private static final String name;
    private static final int maxHP;
    private static final int attack;
    private static final int attackRange;
    private static final double attackDelay;
    private static final double buyDelay;
    private static final int energyUsage;
    private static final int speed;
    private static final double sizeX;

    private static final int walkFrame;
    private static final int atkFrame;
    private static final int idleFrame;

    static {
        name = "Pirate";
        maxHP = 100;
        attack = 200;
        attackRange = 50;
        attackDelay = 3;
        buyDelay = 6;
        energyUsage = 80;
        speed = 150;
        sizeX = 100;
        walkFrame = 0;
        atkFrame = 0;
        idleFrame = 0;
    }

    public Pirate(double x, double y, Side side) {
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
                walkFrame,
                atkFrame,
                idleFrame
        );
    }

    public static String getName() {
        return name;
    }
}