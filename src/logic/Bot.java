package logic;

import java.util.ArrayList;

public class Bot {
    ArrayList<String> rangerList;
    private double time = 0;

    public Bot() {
        rangerList = new ArrayList<>();
        rangerList.add("Inkblue");
        rangerList.add("Slime");
        rangerList.add("Minotaur");
        rangerList.add("Alien");
        rangerList.add("Inkred");
    }

    void update(double dt) {
        time += dt*1000;
        if(time % 1000 > 0) {
            int ran = GameController.getRandomNumber(1,10);
            if (ran <= 2) GameController.createRanger(rangerList.get(GameController.getRandomNumber(0,4)),Side.ENEMY);
            time -= 1000;
        }
    }



}
