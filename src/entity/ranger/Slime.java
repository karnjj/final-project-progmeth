package entity.ranger;

import logic.Side;

public class Slime extends Ranger {

    public Slime(double x, double y, Side side) {
        super("Slime",
                300,
                10,
                100,
                2,
                2,
                40,
                100,
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