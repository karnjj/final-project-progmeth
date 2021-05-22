package logic;

import entity.*;
import entity.base.Attackable;
import entity.base.Damageable;
import entity.base.Entity;

import application.Drawing;
import application.SoundUtils;
import entity.base.Movable;
import entity.ranger.*;

import java.util.Random;

public class GameController {
    private static Energy energy;
    private static Bot bot;
    private static GameState gameState;
    private static boolean isWin;

    private static Turret heroTurret = new HeroTurret(150, 155);
    private static Turret enemyTurret = new EnemyTurret(2850, 155);

    public static void InitGame() {
        bot = new Bot();
        energy = new Energy();
        gameState = GameState.Home;
        isWin = false;
        EntityManager.addEntities(heroTurret, enemyTurret);
    }

    public static void clear() {
        EntityManager.clear();
        heroTurret = new HeroTurret(150, 155);
        enemyTurret = new EnemyTurret(2850, 155);
    }

    public static int getCurrentEnergy() {
        return energy.getCurrentEnergy();
    }

    public static int getMxEnergy() {
        return energy.getMaxEnergy();
    }

    public static void useEnergy(int energy) {
        if (!GameController.energy.Use(energy)) {
            System.out.println("error not enought");
        }
    }

    public static void UpLevelEnergy() {
        energy.LevelUp();
    }

    public static void updateBot(double dt) {
        bot.update(dt);
    }

    public static void updateEnergy(double dt) {
        energy.update(dt);
    }

    public static void updateEntities(double dt) {
        EntityManager.cleanupEntities();
        for (Entity e : EntityManager.getAllEntity()) {
            e.update(dt);
            if (e.getState() == logic.State.DEAD) EntityManager.addEntitiesToBeRemoved(e);
            if (e instanceof Movable) {
                if (e.getState() == logic.State.WALK) ((Movable) e).move(dt);
            }
            if (e instanceof Attackable) {
                if (e.getState() == logic.State.ATTACK && ((Attackable) e).canAttack()) {
                    Entity target = getFrontmost(e.getSide().getOpposite());
                    if (target != null) ((Attackable) e).attack((Damageable) target);
                }
            }
        }
    }

    public static void createRanger(String name, Side side) {
        Ranger ranger;
        SoundUtils.createdRanger();
        double x = side == Side.HERO ? 200 : Drawing.getGameWidth() - 200;
        double y = getRandomNumber(
                Drawing.getWindowHeight() - 70,
                Drawing.getWindowHeight()
        );
        switch (name) {
            case "Inkblue" -> ranger = new Inkblue(x, y, side);
            case "Slime" -> ranger = new Slime(x, y, side);
            case "Minotaur" -> ranger = new Minotaur(x, y, side);
            case "Alien" -> ranger = new Alien(x, y, side);
            case "Inkred" -> ranger = new Inkred(x, y, side);
            default -> throw new IllegalStateException("Unexpected value: " + name);
        }
        EntityManager.addEntities(ranger);
    }

    public static Entity getFrontmost(Side side) {
        Entity frontHero = null;
        Entity frontEnemy = null;
        double maxX = 0;
        double minX = Double.MAX_VALUE;
        for (Entity e : EntityManager.getAllEntity()) {
            if (e instanceof Damageable) {
                if (e.getSide() == Side.HERO) {
                    if (maxX < e.getX()) {
                        maxX = e.getX();
                        frontHero = e;
                    }
                }
                if (e.getSide() == Side.ENEMY) {
                    if (minX > e.getX()) {
                        minX = e.getX();
                        frontEnemy = e;
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

    public static Turret getHeroTurret() {
        return GameController.heroTurret;
    }

    public static Turret getEnemyTurret() {
        return GameController.enemyTurret;
    }

    public static boolean isWin() {
        return isWin;
    }

    public static boolean isGameOver() {
        if (getHeroTurret().getCurrentHP() <= 0) {
            setWin(false);
            Drawing.updatePanel(GameState.End);
            return true;
        }
        if (getEnemyTurret().getCurrentHP() <= 0) {
            setWin(true);
            Drawing.updatePanel(GameState.End);
            return true;
        }
        return false;
    }

    public static void setWin(boolean isWin) {
        GameController.isWin = isWin;
    }

	public static boolean canLevelup() {
		return energy.canLevelUp();
	}

    

}
