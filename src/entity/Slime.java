package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import logic.AnimatedImage;
import logic.Side;
import logic.State;

public class Slime extends Ranger {
    private static final String name;
    private static final int mxHP;
    private static final int attack;
    private static final int attackRange;
    private static final double attackDelay;
    private static final double buyDelay;
    private static final int energyUsage;
    private static final int speed;
    private static final int sizeX;

    AnimatedImage walkAnimated = new AnimatedImage();
    Image[] walkImages = new Image[4];

    AnimatedImage atkAnimated = new AnimatedImage();
    Image[] atkImages = new Image[5];


    AnimatedImage idleAnimated = new AnimatedImage();
    Image[] idleImages = new Image[4];


    static {
        name = "Slime";
        mxHP = 200;
        attack = 1;
        attackRange = 0;
        attackDelay = 2;
        buyDelay = 4;
        energyUsage = 60;
        speed = 100;
        sizeX = 200;
    }

    public Slime(int x, int y, Side side) {
        super(name,
                mxHP,
                attack,
                attackRange,
                attackDelay,
                buyDelay,
                energyUsage,
                speed,
                x,
                y,
                side
        );
        for (int i = 0; i < walkImages.length; i++)
            walkImages[i] = new Image( name + "/walk_" + i + ".png" );
        walkAnimated.frames = walkImages;
        walkAnimated.duration = 20.0/this.getSpeed();

        for (int i = 0; i < atkImages.length; i++)
            atkImages[i] = new Image( name + "/attack_" + i + ".png" );
        atkAnimated.frames = atkImages;
        atkAnimated.duration = this.getAttackDelay()*0.1/atkImages.length;

        for (int i = 0; i < idleImages.length; i++)
            idleImages[i] = new Image( name + "/idle_" + i + ".png" );
        idleAnimated.frames = idleImages;
        idleAnimated.duration = 20.0/this.getSpeed();
        ;
    }

    public static String getName() {
        return name;
    }

    @Override
    public void draw(GraphicsContext gc, double t) {
        Image ig = null;
        if (this.getState() == State.WALK) {
            ig = walkAnimated.getFrame(t);
        }else if(this.getState() == State.ATTACK) {
            if (this.getAttackDelay()*0.1 <= this.getAttackCountdown()) {
                ig = idleAnimated.getFrame(t);
            }else {
                ig = atkAnimated.getFrame((this.getAttackDelay()-this.getAttackCountdown()));
            }
        }
        if(ig == null) return;
        gc.drawImage(ig,this.getX() - this.getSide().getVal()*sizeX/2,this.getY(), getSide().getVal()*ig.getWidth(), ig.getHeight());

    }
}