package entity;

import entity.base.Damageable;
import logic.GameController;
import logic.Side;

public class Inkblue extends Ranger {
    private static final String name;
    private static final int mxHP;
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
        name = "Inkblue";
        mxHP = 100;
        attack = 10;
        attackRange = 1000;
        attackDelay = 3;
        buyDelay = 4;
        energyUsage = 60;
        speed = 200;
        sizeX = 100;
        walkFrame = 5;
        atkFrame = 5;
        idleFrame = 2;
    }

    public Inkblue(double x, double y, Side side) {
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
                sizeX,
                walkFrame,
                atkFrame,
                idleFrame
        );

    }

    public static String getName() {
        return name;
    }

    @Override
    public void attack(Damageable e) {
        GameController.getBullet().add(
                new Bullet(this.getX(),this.getY(),name,attack,200,this.getSide(),0)
        );
        this.setAttackCountdown(attackDelay);
    }
}