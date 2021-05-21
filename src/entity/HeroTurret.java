package entity;

import logic.Side;

public class HeroTurret extends Turret{

    public HeroTurret(double x,double y) {
        super("HeroTurret", 500, x, y, 150, Side.HERO);
    }

}
