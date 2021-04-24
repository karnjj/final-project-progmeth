package entity;

import application.Drawing;
import entity.base.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import logic.AnimatedImage;
import logic.GameController;
import logic.Side;
import logic.State;

public abstract class Ranger extends Entity implements Attackable, Damageable, Buyable, Movable {
    private String name;
    private int maxHP;
    private int currentHP;
    private int attack;
    private int attackRange;
    private double attackDelay;
    private double attackCountdown;
    private double buyCountdown;
    private double buyDelay;
    private int energyUsage;
    private int speed;
    private double sizeX;

    private final double startTime = System.nanoTime() / 1e9;

    AnimatedImage walkAnimated = new AnimatedImage();
    AnimatedImage atkAnimated = new AnimatedImage();
    AnimatedImage idleAnimated = new AnimatedImage();

    public Ranger(String name,
                  int maxHP,
                  int attack,
                  int attackRange,
                  double attackDelay,
                  double buyDelay,
                  int energyUsage,
                  int speed,
                  double x,
                  double y,
                  Side side,
                  double sizeX,
                  int walkFrame,
                  int atkFrame,
                  int idleFrame
    ) {
        super(x, y-Drawing.getTargetPosiBg());
        this.name = name;
        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.attack = attack;
        this.attackDelay = attackDelay;
        this.attackRange = attackRange;
        this.buyDelay = buyDelay;
        this.energyUsage = energyUsage;
        this.speed = speed;
        this.sizeX = sizeX;
        this.attackCountdown = this.attackDelay;
        this.setSide(side);

        for (int i = 0; i < walkFrame; i++)
            walkAnimated.frames.add(new Image(name + "/walk_" + i + ".png"));
        walkAnimated.duration = 20.0/this.getSpeed();

        for (int i = 0; i < atkFrame; i++)
            atkAnimated.frames.add(new Image( name + "/attack_" + i + ".png" ));
        atkAnimated.duration = this.getAttackDelay()*(20.0/this.getSpeed())/atkFrame;

        for (int i = 0; i < idleFrame; i++)
            idleAnimated.frames.add(new Image( name + "/idle_" + i + ".png" ));
        idleAnimated.duration = 20.0/this.getSpeed();
    }

    @Override
    public void attack(Damageable e) {
        e.takeDamage(this.attack);
        this.attackCountdown = this.attackDelay;
    }

    @Override
    public boolean canAttack() {
        return this.attackCountdown == 0;
    }

    @Override
    public int getAttackRange() {
        return this.attackRange;
    }

    @Override
    public void takeDamage(int i) {
        setCurrentHP(currentHP - i);
        GameController.getEntityManager().addEntities(new Smoke(this.getX(),this.getY()));
    }

    @Override
    public boolean isDead() {
        return currentHP == 0;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
        if (this.currentHP < 0) this.currentHP = 0;
    }

    public boolean canBuy() {
        return this.buyCountdown == 0 && GameController.getCurrentEnergy() > energyUsage;
    }

    public void Buy() {
        if (this.buyCountdown == 0 && GameController.getCurrentEnergy() > energyUsage) {
            GameController.useEnergy(energyUsage);
            setBuyCountdown(this.buyDelay);
        }
    }


    public void setBuyCountdown(double buyCountdown) {
        this.buyCountdown = buyCountdown;
        if (this.buyCountdown < 0) this.buyCountdown = 0;
    }

    public void setAttackCountdown(double attackCountdown) {
        this.attackCountdown = attackCountdown;
        if (this.attackCountdown < 0) this.attackCountdown = 0;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public void move(double dt) {
        this.setX(this.getX() + speed * this.getSide().getVal() * dt);
        
    }

    private State checkState() {
        if (this.getState() == State.DEAD || this.isDead()) return State.DEAD;

        Entity nearest = GameController.getFrontmost(this.getSide().getOpposite());
        if (nearest != null) {
            if (this.getSide().getVal()*(nearest.getX() - this.getX()) <= attackRange) {
                return State.ATTACK;
            }
        }

        return State.WALK;
    }

    public double getAttackDelay() {
        return attackDelay;
    }

    public double getAttackCountdown() {
        return attackCountdown;
    }

    public void update(double dt) {
        this.setState(checkState());
        if(this.getState() == State.ATTACK)
            setAttackCountdown(attackCountdown - dt);
        setBuyCountdown(buyCountdown - dt);
    }

    @Override
    public void draw(GraphicsContext gc, double t) {
        Image ig = null;
        if (this.getState() == State.WALK) {
            ig = this.walkAnimated.getFrame(t+this.startTime);
        }else if(this.getState() == State.ATTACK) {
            if (this.getAttackDelay() * (20.0/this.getSpeed()) < this.getAttackCountdown()) {
                ig = this.idleAnimated.getFrame(t+this.startTime);
            }else {
                ig = this.atkAnimated.getFrame(
                        this.getAttackDelay() * (20.0/this.getSpeed()) - this.getAttackCountdown()
                );
            }
        }
        if(ig == null) return;
        gc.drawImage(
                ig,
                this.getX() - (this.getSide().getVal() * this.sizeX/2) + Drawing.getStartDraw(),
                this.getY(),
                this.getSide().getVal()*ig.getWidth(),
                ig.getHeight()
        );
        gc.setFill(Color.RED);
        gc.fillText(String.valueOf(this.currentHP),this.getX() + Drawing.getStartDraw(),this.getY());
    }
}