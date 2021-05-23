package entity;

import application.Drawing;
import entity.base.Attackable;
import entity.base.Damageable;
import entity.base.Entity;
import entity.base.Movable;
import exception.IndexOfFrameOutboundException;
import exception.NullImageToRenderException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.AnimatedImage;
import logic.GameController;
import logic.Side;
import logic.State;

public class Bullet extends Entity implements Attackable, Movable {
    private String name;
    private int attack;
    private final int attackRange = 0;
    private int speed;
    private Side side;
    private double sizeX;
    private double sizeY;

    private final double startTime = System.nanoTime() / 1e9;

    private AnimatedImage animated = new AnimatedImage();
    public Bullet(double x,
                  double y,
                  String name,
                  int attack,
                  int speed,
                  Side side,
                  int frame
    ) {
        super(x, y);
        this.name = name;
        this.attack = attack;
        this.speed = speed;
        this.side = side;
        this.sizeX = 100;
        this.sizeY = 100;

        for (int i = 0; i < frame; i++)
            animated.getFrames().add(new Image(name + "/bullet_" + i + ".png"));
        animated.setDuration(20.0/this.getSpeed());
    }

    @Override
    public void attack(Damageable e) {
        e.takeDamage(this.attack);
        this.setState(State.DEAD);
    }

    @Override
    public boolean canAttack() {
        return true;
    }

    @Override
    public void move(double dt) {
    	this.setX(this.getX() + speed * side.getVal() * dt);
    }

    private State checkState() {
    	if (this.getState() == State.DEAD) return State.DEAD;
    	Entity nearest = GameController.getFrontMost(this.getSide().getOpposite());
    	if (nearest == null) return State.WALK;
    	if (this.getSide().getVal()*(nearest.getX() - this.getX()) > attackRange) {
    		return State.WALK;
    	} else {
    		return State.ATTACK;
    	}
    }
    
    @Override
    public void update(double dt) {
    	this.setState(checkState());
    }
    
    @Override
    public void draw(GraphicsContext gc, double t) throws NullImageToRenderException {
    	if(this.getState() == State.DEAD) return;
    	Image ig = null;
    	try {
    		ig = animated.getFrame(t + startTime);
    	}catch (IndexOfFrameOutboundException e) {
    		System.out.println(e.getMessage());
    	}
    	if (ig == null) throw new NullImageToRenderException();
    	gc.drawImage(
    			ig,
    			this.getX() - (this.getSide().getVal() * (this.sizeX/2)) + Drawing.getMovePosBgX(),
    			this.getY() - (this.sizeY/2) - 30 + Drawing.getMovePosBgY(),
    			this.getSide().getVal()*ig.getWidth(),
    			ig.getHeight()
    			);
    }
   
    public int getAttack() {
        return this.attack;
    }

    public int getAttackRange() {
        return this.attackRange;
    }

    public int getSpeed() {
        return this.speed;
    }

    public Side getSide() {
        return side;
    }


}
