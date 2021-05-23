package entity.ranger;

import logic.Side;

public class Slime extends Ranger {

    public Slime(double x, double y, Side side) {
        super("Slime",
                200,
                10,
                100,
                2,
                2,
                50,
                80,
                x,
                y,
                side,
                50,
                100,
                4,
                5,
                4
        );
    }

}