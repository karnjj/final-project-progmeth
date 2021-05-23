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
        attack = 10;
        attackRange = 100;
        attackDelay = 2;
        buyDelay = 4;
        energyUsage = 5;
        speed = 150;
        sizeX = 200;
        sizeY = 200;
        walkFrame = 7;
        atkFrame = 10;
        idleFrame = 2;
    }

    public Minotaur(double x, double y, Side side) {
        super("Minotaur",
                100,
                15,
                100,
                1,
                3,
                200,
                200,
                x,
                y,
                side,
                100,
                200,
                7,
                10,
                2
        );
    }

}