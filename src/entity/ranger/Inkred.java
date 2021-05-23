package entity.ranger;

import application.SoundUtils;
import entity.Bullet;
import entity.base.Damageable;
import logic.GameController;
import logic.Side;

public class Inkred extends Shooter {

    public Inkred(double x, double y, Side side) {
        super("Inkred",
                20,
                100,
                1500,
                3,
                8,
                600,
                100,
                x,
                y,
                side,
                50,
                100,
                5,
                5,
                2
        );

    }

}