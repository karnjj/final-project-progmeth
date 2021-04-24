package entity;

import entity.base.Attackable;
import entity.base.Buyable;
import entity.base.Damageable;
import entity.base.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.Side;
import logic.State;

public class Turret extends Entity implements Attackable, Damageable{
	private String name;
    private int maxHP;
    private int currentHP;
    private int attack;
    private int attackRange;
    private double attackDelay;
    private double attackCountdown;

    private Image image;

    public Turret(String name,
                  int maxHP,
                  int attack,
                  int attackRange,
                  double attackDelay,
                  double x,
                  double y,
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

    }

    @Override
    public boolean isDead() {
        return false;
    }

    public String getUrl() {
        return name + ".png";
    }

    public void update(double x,double y) {
        this.setX(x);
        this.setY(y);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(image, this.getX(), this.getY());
    }


}
