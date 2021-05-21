package logic;

import java.util.ArrayList;

public class Bot {
    private ArrayList<String> rangerList;
    private double time;

    public Bot() {
    	time = 0;
        rangerList = new ArrayList<>();
        rangerList.add("Inkblue");
        rangerList.add("Slime");
        rangerList.add("Minotaur");
        rangerList.add("Alien");
        rangerList.add("Inkred");
    }

    public void update(double dt) {
        time += dt * 1000;
        if(time % 1000 > 0) {
            int ran = GameController.getRandomNumber(0,10);
            if (ran < 2) GameController.createRanger(rangerList.get(GameController.getRandomNumber(0,5)),Side.ENEMY);
            time -= 1000;
        }
    }



}
