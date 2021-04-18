package logic;

import entity.Ranger;
import entity.base.Entity;

import java.util.ArrayList;

public class GameController {
    private static Energy energy;
    private static ArrayList<Ranger> hero;
    private static ArrayList<Ranger> enemy;
    public static void InitGame() {
        energy = new Energy();
        hero = new ArrayList<Ranger>();
        enemy = new ArrayList<Ranger>();
    }

    public static int getCurrentEnergy() {
        return energy.getCurrentEnergy();
    }
    
    public static void useEnergy(int energy) {
        if (!GameController.energy.Use(energy)) {
            System.out.println("error");
        }
    }
    
    public static void createRanger(String name,int side) {
    	// create Ranger at start position
    }
    

    public static ArrayList<Ranger> getHero() {
        return hero;
    }


    public static ArrayList<Ranger> getEnemy() {
        return enemy;
    }

}
