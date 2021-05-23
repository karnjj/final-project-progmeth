package entity.ranger;

import application.Drawing;
import application.SoundUtils;
import entity.Smoke;
import entity.base.*;
import exception.IndexOfFrameOutboundException;
import exception.NullImageToRenderException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.*;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;

public class Ranger extends Entity implements Attackable, Damageable, Movable{
    private String name;
    private int maxHP;
    private int currentHP;
    private int attack;
    private int attackRange;
    private double attackDelay;
    private double attackCountdown;
    private double buyDelay;
    private int energyUsage;
    private int speed;

    private double pivotX;
    private double pivotY;

    private int walkFrame;
    private int atkFrame;
    private int idleFrame;


    private final double startTime = System.nanoTime() / 1e9;

    protected AnimatedImage walkAnimated = new AnimatedImage();
    protected AnimatedImage atkAnimated = new AnimatedImage();
    protected AnimatedImage idleAnimated = new AnimatedImage();

    public Ranger(String name,
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
        JSONObject ranger = new JSONObject(tokener);

        this.name = ranger.getString("name");
        this.maxHP = ranger.getInt("maxHP");
        this.currentHP = maxHP;
        this.attack = ranger.getInt("attack");
        this.attackDelay = ranger.getInt("attackDelay");
        this.attackRange = ranger.getInt("attackRange");
        this.buyDelay = ranger.getInt("buyDelay");
        this.energyUsage = ranger.getInt("energyUsage");
        this.speed = ranger.getInt("speed");
        this.pivotX = ranger.getInt("pivotX");
        this.pivotY = ranger.getInt("pivotY");
        this.attackCountdown = this.attackDelay;

        this.walkFrame = ranger.getInt("walkFrame");
        this.atkFrame = ranger.getInt("atkFrame");
        this.idleFrame = ranger.getInt("idleFrame");

        for (int i = 0; i < walkFrame; i++)
            walkAnimated.getFrames().add(new Image(name + "/walk_" + i + ".png"));
        walkAnimated.setDuration(20.0/this.getSpeed());

        for (int i = 0; i < atkFrame; i++)
            atkAnimated.getFrames().add(new Image( name + "/attack_" + i + ".png" ));
        atkAnimated.setDuration(this.getAttackDelay()*(20.0/this.getSpeed())/atkFrame);

        for (int i = 0; i < idleFrame; i++)
            idleAnimated.getFrames().add(new Image( name + "/idle_" + i + ".png" ));
        idleAnimated.setDuration(20.0/this.getSpeed());

    }

    @Override
    public void attack(Damageable e) {
        SoundUtils.attack();
        e.takeDamage(this.attack);
        this.attackCountdown = this.attackDelay;
    }

    @Override
    public boolean canAttack() {
        return this.attackCountdown == 0;
    }

    @Override
    public void takeDamage(int i) {
        setCurrentHP(currentHP - i);
        EntityManager.addEntities(new Smoke(this.getX(),this.getY()-50));
    }

    @Override
    public boolean isDead() {
        return currentHP == 0;
    }


    @Override
    public void move(double dt) {
        this.setX(this.getX() + speed * this.getSide().getVal() * dt);

    }

    private State checkState() {
        if (this.getState() == State.DEAD || this.isDead()) return State.DEAD;

        Entity nearest = GameController.getFrontMost(this.getSide().getOpposite());
        if (nearest != null) {
            if (this.getSide().getVal()*(nearest.getX() - this.getX()) <= attackRange) {
                return State.ATTACK;
            }
        }

        return State.WALK;
    }


    public void update(double dt) {
        this.setState(checkState());
        if(this.getState() == State.ATTACK)
            setAttackCountdown(attackCountdown - dt);
    }

    @Override
    public void draw(GraphicsContext gc, double t) throws NullImageToRenderException {
        if(this.getState() == State.DEAD) return;
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
                this.getX() - (this.getSide().getVal() * this.pivotX) + Drawing.getMovePosBgX(),
                this.getY() - this.pivotY + Drawing.getMovePosBgY(),
                this.getSide().getVal()*ig.getWidth(),
                ig.getHeight()
        );
    }

    public String getUrl() {
        return name + "/default.png";
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
        if (this.currentHP < 0) this.currentHP = 0;
    }


    public void setAttackCountdown(double attackCountdown) {
        this.attackCountdown = attackCountdown;
        if (this.attackCountdown < 0) this.attackCountdown = 0;
    }

    public int getSpeed() {
        return this.speed;
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

    public double getBuyDelay() {
        return buyDelay;
    }
    public double getAttackDelay() {
        return attackDelay;
    }

    public double getAttackCountdown() {
        return attackCountdown;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    public void setAttackDelay(double attackDelay) {
        this.attackDelay = attackDelay;
    }

    public void setBuyDelay(double buyDelay) {
        this.buyDelay = buyDelay;
    }

    public void setEnergyUsage(int energyUsage) {
        this.energyUsage = energyUsage;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getPivotX() {
        return pivotX;
    }

    public void setPivotX(double pivotX) {
        this.pivotX = pivotX;
    }

    public double getPivotY() {
        return pivotY;
    }

    public void setPivotY(double pivotY) {
        this.pivotY = pivotY;
    }

    public double getStartTime() {
        return startTime;
    }

    public AnimatedImage getWalkAnimated() {
        return walkAnimated;
    }

    public void setWalkAnimated(AnimatedImage walkAnimated) {
        this.walkAnimated = walkAnimated;
    }

    public AnimatedImage getAtkAnimated() {
        return atkAnimated;
    }

    public void setAtkAnimated(AnimatedImage atkAnimated) {
        this.atkAnimated = atkAnimated;
    }

    public AnimatedImage getIdleAnimated() {
        return idleAnimated;
    }

    public void setIdleAnimated(AnimatedImage idleAnimated) {
        this.idleAnimated = idleAnimated;
    }
}
