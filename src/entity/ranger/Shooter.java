package entity.ranger;

import application.SoundUtils;
import entity.Bullet;
import entity.base.Damageable;
import logic.EntityManager;
import logic.Side;

public class Shooter extends Ranger{
    public Shooter(String name, int maxHP, int attack, int attackRange, double attackDelay, double buyDelay, int energyUsage, int speed, double x, double y, Side side, double pivotX, double pivotY, int walkFrame, int atkFrame, int idleFrame) {
        super(name, maxHP, attack, attackRange, attackDelay, buyDelay, energyUsage, speed, x, y, side, pivotX, pivotY, walkFrame, atkFrame, idleFrame);
    }

    @Override
    public void attack(Damageable e) {
        SoundUtils.attack();
        EntityManager.addEntities(
                new Bullet(this.getX() + (50*this.getSide().getVal()),this.getY(),name,attack,1000,this.getSide(),2)
        );
        this.setAttackCountdown(attackDelay);
    }
}
