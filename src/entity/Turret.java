package entity;

import application.Drawing;
import entity.base.Attackable;
import entity.base.Buyable;
import entity.base.Damageable;
import entity.base.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.GameController;
import logic.Side;
import logic.State;

public class Turret extends Entity implements Attackable, Damageable{
	private String name;
    private double maxHP;
    private double currentHP;
    private int attack;
    private int attackRange;
    private double attackDelay;
    private double attackCountdown;
    private double sizeX;

    private Image image;

    public Turret(String name,
                  double maxHP,
                  int attack,
                  int attackRange,
                  double attackDelay,
                  double x,
                  double y,
                  double sizeX,
                  Side side
    ) {
        super(x, y);
        this.name = name;
        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.attack = attack;
        this.attackDelay = attackDelay;
        this.attackRange = attackRange;
        this.attackCountdown = this.attackDelay;
        this.sizeX = sizeX;

        this.setSide(side);

        image = new Image(this.getUrl());
    }

    @Override
    public void attack(Damageable e) {

    }

    @Override
    public boolean canAttack() {
        return false;
    }

    @Override
    public int getAttackRange() {
        return 0;
    }

    @Override
    public void takeDamage(int i) {
        setCurrentHP(currentHP - i);
        GameController.getEntityManager().addEntities(new Smoke(this.getX(),this.getY()+100));
    }

    @Override
    public boolean isDead() {
        return false;
    }

    public String getUrl() {
        return name + ".png";
    }

    @Override
    public void draw(GraphicsContext gc, double t) {
        gc.drawImage(
                image,
                this.getX() - (this.getSide().getVal() * this.sizeX/2) + Drawing.getStartDraw(),
                this.getY() + Drawing.getCurrentPosiBg(),
                this.getSide().getVal()*image.getWidth(),
                image.getHeight());
    }
	public double getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(double currentHP) {
        this.currentHP = currentHP;
        if (this.currentHP < 0) this.currentHP = 0;
	}
    
    


}
