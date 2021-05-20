package entity.ranger;

import application.SoundUtils;
import entity.Bullet;
import entity.base.Damageable;
import logic.GameController;
import logic.Side;

public class Inkblue extends Ranger {
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
        name = "Inkblue";
        maxHP = 100;
        attack = 20;
        attackRange = 1000;
        attackDelay = 3;
        buyDelay = 5;
        energyUsage = 5;
        speed = 200;
        sizeX = 100;
        sizeY = 100;
        walkFrame = 5;
        atkFrame = 5;
        idleFrame = 2;
    }

    public Inkblue(double x, double y, Side side) {
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

    @Override
    public void attack(Damageable e) {
    	SoundUtils.attrack();
        GameController.getEntityManager().addEntities(
                new Bullet(this.getX() + (50*this.getSide().getVal()),this.getY(),name,attack,1000,this.getSide(),2)
        );
        this.setAttackCountdown(attackDelay);
    }
}