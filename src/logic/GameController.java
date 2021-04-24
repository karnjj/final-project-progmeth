package logic;

import entity.*;
import entity.base.Attackable;
import entity.base.Entity;

import application.Drawing;
import entity.base.Movable;

import java.util.Random;

public class GameController {
    private static Energy energy;
    private static GameState gameState;
    private static EntityManager entityManager;

    private static HeroTurret heroTurret;
    private static EnemyTurret enemyTurret;

    public static void InitGame() {
        energy = new Energy();
        entityManager = new EntityManager();
        gameState = GameState.Home;

        heroTurret = new HeroTurret(0,155);
        enemyTurret = new EnemyTurret(2711,155);

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

    public static void updateEntities(double dt) {
        entityManager.cleanupEntities();
        for (Entity e : entityManager.getAllEntity()) {
            e.update(dt);
            if(e.getState() == logic.State.DEAD) entityManager.addEntitiesToBeRemoved(e);
            if (e instanceof Movable) {
                if(e.getState() == logic.State.WALK) ((Movable) e).move(dt);
            }
            if (e instanceof Attackable) {
                if(e.getState() == logic.State.ATTACK && ((Attackable) e).canAttack()) {
                Ranger target = getFrontRanger(e.getSide().getOpposite());
                if (target != null) ((Attackable) e).attack(target);
            }
            }
        }
    }
    
    public static void createRanger(String name,Side side) {
    	Ranger ranger;
	    double x = side == Side.HERO ? 100 : Drawing.getGameWidth() - 100;
	    double y = (double) getRandomNumber(
	            Drawing.getWindowHeight() - 380,
                Drawing.getWindowHeight() - 330
                );
    	switch (name) {
    	    case "Inkblue" -> ranger = new Inkblue(x,y,side);
    	    case "Slime" -> ranger = new Slime(x,y,side);
            default -> throw new IllegalStateException("Unexpected value: " + name);
        }
        entityManager.addEntities(ranger);
    }

    public static Ranger getFrontRanger(Side side) {
        Ranger frontHero = null;
        Ranger frontEnemy = null;
        double maxX = 0;
        double minX = Double.MAX_VALUE;
        for (Entity e : entityManager.getAllEntity()) {
            if (e instanceof Ranger) {
                if (((Ranger) e).getSide() == Side.HERO) {
                    if (maxX < e.getX()) {
                        maxX = e.getX();
                        frontHero = (Ranger) e;
                    }
                }
                if (((Ranger) e).getSide() == Side.ENEMY) {
                    if (minX > e.getX()) {
                        minX = e.getX();
                        frontEnemy = (Ranger) e;
                    }
                }
            }
        }
        return side == Side.HERO ? frontHero : frontEnemy;
    }

	public static GameState getGameState() {
		return gameState;
	}

	public static void setGameState(GameState gameState) {
		GameController.gameState = gameState;
	}

    public static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }

    public static HeroTurret getHeroTurret() {
        return heroTurret;
    }

    public static EnemyTurret getEnemyTurret() {
        return enemyTurret;
    }
}
