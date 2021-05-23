package entity;

import application.Drawing;
import entity.base.Damageable;
import entity.base.Entity;
import entity.ranger.Ranger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import logic.EntityManager;
import logic.GameController;
import logic.Side;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;

public class Turret extends Entity implements Damageable{
	protected String name;
    protected double maxHP;
    private double currentHP;
    protected double pivotX;
    protected double pivotY;

    private Image image;

    public Turret(String name,
                  double x,
                  double y,
                  Side side
    ) {
        super(x, y);
        this.setSide(side);

        String resourceName = "/" + name + "/detail.json";
        InputStream is = Ranger.class.getResourceAsStream(resourceName);
        if (is == null) {
            throw new NullPointerException("Cannot find resource file " + resourceName);
        }
        JSONTokener tokener = new JSONTokener(is);
        JSONObject turret = new JSONObject(tokener);

        this.name = turret.getString("name");
        this.maxHP = turret.getInt("maxHP");
        this.currentHP = this.maxHP;
        this.pivotX = turret.getInt("pivotX");
        this.pivotY = turret.getInt("pivotY");

        image = new Image(this.getUrl());

    }

    @Override
    public void takeDamage(int i) {
        setCurrentHP(currentHP - i);
        EntityManager.addEntities(new Smoke(this.getX(),this.getY()));
    }

    @Override
    public boolean isDead() {
        return false;
    }

    public String getUrl() {
        return name + "/default.png";
    }

    @Override
    public void draw(GraphicsContext gc, double t) {
        gc.drawImage(
                image,
                this.getX() - (this.getSide().getVal() * this.pivotX) + Drawing.getMovePosBgX(),
                this.getY() - this.pivotY + Drawing.getMovePosBgY(),
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
