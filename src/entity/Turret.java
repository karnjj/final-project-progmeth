package entity;

import application.Drawing;
import entity.base.Damageable;
import entity.base.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.GameController;
import logic.Side;

public class Turret extends Entity implements Damageable{
	private String name;
    private double maxHP;
    private double currentHP;
    private double pivotX;

    private Image image;

    public Turret(String name,
                  double maxHP,
                  double x,
                  double y,
                  double pivotX,
                  Side side
    ) {
        super(x, y);
        this.name = name;
        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.pivotX = pivotX;

        this.setSide(side);

        image = new Image(this.getUrl());
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
                this.getX() - (this.getSide().getVal() * this.pivotX) + Drawing.getMovePosBgX(),
                this.getY() + Drawing.getMovePosBgY(),
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

	public double getMaxHP() {
        return this.maxHP;
    }
    


}
