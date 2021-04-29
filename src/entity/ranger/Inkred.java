package entity.ranger;

import application.SoundUtils;
import entity.Bullet;
import entity.base.Damageable;
import logic.GameController;
import logic.Side;

public class Inkred extends Ranger {
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
        name = "Inkred";
        maxHP = 100;
        attack = 10;
        attackRange = 1500;
        attackDelay = 3;
        buyDelay = 4;
        energyUsage = 5;
        speed = 150;
        sizeX = 100;
        walkFrame = 5;
        atkFrame = 5;
        idleFrame = 2;
    }

    public Inkred(double x, double y, Side side) {
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

    @Override
    public void attack(Damageable e) {
    	SoundUtils.attrack();
        GameController.getEntityManager().addEntities(
                new Bullet(this.getX()+40,this.getY()+10,name,attack,1000,this.getSide(),100,2)
        );
        this.setAttackCountdown(attackDelay);
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