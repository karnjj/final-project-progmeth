package entity.ranger;

import application.SoundUtils;
import entity.Bullet;
import entity.base.Damageable;
import logic.GameController;
import logic.Side;

public class Inkblue extends Shooter {
    public Inkblue(double x, double y, Side side) {
        super("Inkblue",
                100,
                20,
                1000,
                3,
                5,
                5,
                200,
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