package entity.ranger;

import application.SoundUtils;
import entity.Bullet;
import entity.base.Damageable;
import logic.EntityManager;
import logic.Side;

public class Shooter extends Ranger{
    public Shooter(String name,
                   double x,
                   double y,
                   Side side
                   ) {
        super(name,
                x,
                y,
                side
        );
    }

    @Override
    public void attack(Damageable e) {
        SoundUtils.attack();
        EntityManager.addEntities(
                new Bullet(this.getX() + (50*this.getSide().getVal()),this.getY(),this.getName(),this.getAttack(),1000,this.getSide(),2)
        );
        this.setAttackCountdown(this.getAttackDelay());
    }
}
