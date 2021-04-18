package logic;

import entity.Ranger;
import entity.base.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

    public static Ranger getFrontRanger(Side side) {
        if(side == Side.HERO) {
            if(hero.isEmpty()) return null;
            return Collections.max(getHero(),
                    Comparator.comparing(Ranger::getX));
        }else if(side == Side.ENEMY) {
            if(enemy.isEmpty()) return null;
            return Collections.min(getEnemy(),
                    Comparator.comparing(Ranger::getX));
        }
        return null;
    }
}
