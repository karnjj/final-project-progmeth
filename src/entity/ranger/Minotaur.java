package entity.ranger;

import logic.Side;

public class Minotaur extends Ranger {
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
        name = "Minotaur";
        maxHP = 200;
        attack = 500;
        attackRange = 100;
        attackDelay = 2;
        buyDelay = 4;
        energyUsage = 5;
        speed = 300;
        sizeX = 0;
        sizeY = 100;
        walkFrame = 7;
        atkFrame = 10;
        idleFrame = 2;
    }

    public Minotaur(double x, double y, Side side) {
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

    public static String getName() {
        return name;
    }

    public static int getMaxHP() {
        return maxHP;
    }

    public static int getAttack() {
        return attack;
    }

    public static int getEnergyUsage() {
        return energyUsage;
    }

    public static String getUrl() {
        return name + "/default.png";
    }

}