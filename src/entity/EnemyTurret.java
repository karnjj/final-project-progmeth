package entity;

import logic.Side;

public class EnemyTurret extends Turret{

    public EnemyTurret(double x,double y) {
        super("EnemyTurret", 500, x, y, 150,Side.ENEMY);
    }
    

}
