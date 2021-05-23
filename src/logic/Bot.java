package logic;

import java.util.ArrayList;

public class Bot {
    private ArrayList<String> rangerList;
    private double time;
    private int maxIndex;

    public Bot() {
        time = 0;
        maxIndex = 2;
        rangerList = new ArrayList<>();
        rangerList.add("Slime");
        rangerList.add("Alien");
        rangerList.add("Minotaur");
        rangerList.add("Inkblue");
        rangerList.add("Inkred");
    }

    public void update(double dt) {
        time += dt * 1000;
        if (time % 1000 > 0) {
            if (GameController.getEnergy().getLevel() == 3) maxIndex = 3;
            else if (GameController.getEnergy().getLevel() == 4) maxIndex = 4;
            else if (GameController.getEnergy().getLevel() == 6) maxIndex = 5;
            int ran = GameController.getRandomNumber(0, 10);
            if (ran < 4)
                GameController.createRanger(rangerList.get(GameController.getRandomNumber(0, maxIndex)), Side.ENEMY);
            time -= 1000;
        }
    }


}
