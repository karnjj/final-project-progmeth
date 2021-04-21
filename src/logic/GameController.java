package logic;

import entity.Bullet;
import entity.Ranger;
import entity.Smoke;
import entity.base.Entity;

import java.util.*;

import application.Drawing;

public class GameController {
    private static Energy energy;
    private static ArrayList<Ranger> hero;
    private static ArrayList<Ranger> enemy;
    private static ArrayList<Bullet> bullet;
    private static ArrayList<Smoke> smoke;
    private static GameState gameState;
    
    public static void InitGame() {
        energy = new Energy();
        hero = new ArrayList<Ranger>();
        enemy = new ArrayList<Ranger>();
        bullet = new ArrayList<Bullet>();
        smoke = new ArrayList<Smoke>();
        gameState = GameState.Home;
    }

    public static int getCurrentEnergy() {
        return energy.getCurrentEnergy();
    }
    
    public static void useEnergy(int energy) {
        if (!GameController.energy.Use(energy)) {
            System.out.println("error");
        }
    }

    public static void updateEnergy(double dt) {
        energy.update(dt);
    }

    public static void updateHero(double dt) {
        Iterator<Ranger> iterator = getHero().iterator();
        while (iterator.hasNext()) {
            Ranger e = iterator.next();
            e.update(dt);
            if(e.getState() == logic.State.DEAD) iterator.remove();
            if(e.getState() == logic.State.ATTACK && e.canAttack()) {
                Ranger target = getFrontRanger(Side.ENEMY);
                if (target != null) e.attack(target);
            }
            if(e.getState() == logic.State.WALK) e.move(dt);
        }
    }

    public static void updateEnemy(double dt) {
        Iterator<Ranger> iterator = getEnemy().iterator();
        while (iterator.hasNext()) {
            Ranger e = iterator.next();
            e.update(dt);
            if(e.getState() == logic.State.DEAD) iterator.remove();
            if(e.getState() == logic.State.ATTACK && e.canAttack()) {
                Ranger target = getFrontRanger(Side.HERO);
                if (target != null) e.attack(target);
            }
            if(e.getState() == logic.State.WALK) e.move(dt);
        }
    }

    public static void updateBullet(double dt) {
        Iterator<Bullet> iterator = getBullet().iterator();
        while (iterator.hasNext()) {
            Bullet e = iterator.next();
            e.update(dt);
            if(e.getState() == logic.State.ATTACK) {
                Ranger target = getFrontRanger(e.getSide().getOpposite());
                if (target != null) e.attack(target);
            }
            if(e.getState() == logic.State.DEAD) iterator.remove();
            if(e.getState() == logic.State.WALK) e.move(dt);
        }
    }

    public static void updateSmoke(double dt) {
        Iterator<Smoke> iterator = getSmoke().iterator();
        while (iterator.hasNext()) {
            Smoke e = iterator.next();
            e.update(dt);
            if(e.getState() == logic.State.DEAD) iterator.remove();
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

    public static ArrayList<Bullet> getBullet() {
        return bullet;
    }
    public static ArrayList<Smoke> getSmoke() {
        return smoke;
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

	public static GameState getGameState() {
		return gameState;
	}

	public static void setGameState(GameState gameState) {
		GameController.gameState = gameState;
	}
    
}
