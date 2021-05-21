package entity.ranger;

import application.Drawing;
import entity.Smoke;
import entity.base.*;
import exception.IndexOfFrameOutboundException;
import exception.NullImageToRenderException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import logic.AnimatedImage;
import logic.GameController;
import logic.Side;
import logic.State;

public abstract class Ranger extends Entity implements Attackable, Damageable, Movable{
    protected String name;
    protected int maxHP;
    private int currentHP;
    protected int attack;
    protected int attackRange;
    protected double attackDelay;
    private double attackCountdown;
    protected double buyDelay;
    protected int energyUsage;
    protected int speed;

    protected double pivotX;
    protected double pivotY;

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
                  double pivotX,
                  double pivotY,
                  int walkFrame,
                  int atkFrame,
                  int idleFrame
    ) {
        super(x, y-Drawing.getTargetPosBg());
        this.name = name;
        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.attack = attack;
        this.attackDelay = attackDelay;
        this.attackRange = attackRange;
        this.buyDelay = buyDelay;
        this.energyUsage = energyUsage;
        this.speed = speed;
        this.pivotX = pivotX;
        this.pivotY = pivotY;
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
        GameController.getEntityManager().addEntities(new Smoke(this.getX(),this.getY()-50));
    }

    @Override
    public boolean isDead() {
        return currentHP == 0;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
        if (this.currentHP < 0) this.currentHP = 0;
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
    }

    @Override
    public void draw(GraphicsContext gc, double t) throws NullImageToRenderException {
        Image ig = null;
        try {
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
        }catch (IndexOfFrameOutboundException e) {
            System.out.println(e.getMessage());
        }
        if (ig == null) throw new NullImageToRenderException();
        gc.drawImage(
                ig,
                this.getX() - (this.getSide().getVal() * this.pivotX) + Drawing.getStartDraw(),
                this.getY() - this.pivotY,
                this.getSide().getVal()*ig.getWidth(),
                ig.getHeight()
        );
        gc.setFill(Color.RED);
        gc.fillText(String.valueOf(this.currentHP),this.getX() + Drawing.getStartDraw(),this.getY());
    }

    public String getName() {
        return name;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getEnergyUsage() {
        return energyUsage;
    }

    public int getAttack() {
        return attack;
    }

    public String getUrl() {
        return name + "/default.png";
    }

    public double getBuyDelay() {
        return buyDelay;
    }
}