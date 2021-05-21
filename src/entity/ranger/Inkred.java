package entity.ranger;

import application.SoundUtils;
import entity.Bullet;
import entity.base.Damageable;
import logic.GameController;
import logic.Side;

public class Inkred extends Shooter {

    public Inkred(double x, double y, Side side) {
        super("Inkred",
                100,
                10,
                1500,
                3,
                4,
                5,
                150,
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