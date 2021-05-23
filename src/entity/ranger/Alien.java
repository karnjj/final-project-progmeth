package entity.ranger;

import logic.Side;

public class Alien extends Ranger {
    public Alien(double x, double y, Side side) {
        super("Alien",
                150,
                20,
                100,
                2,
                1,
                100,
                100,
                x,
                y,
                side,
                50,
                100,
                7,
                8,
                4
        );
    }

}