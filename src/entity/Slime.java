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

    AnimatedImage walk = new AnimatedImage();
    Image[] walkImages = new Image[4];


    static {
        name = "Slime";
        mxHP = 200;
        attack = 5;
        attackRange = 30;
        attackDelay = 2;
        buyDelay = 4;
        energyUsage = 60;
        speed = 100;
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
        for (int i = 0; i < 4; i++)
            walkImages[i] = new Image( "slime/walk_" + i + ".png" );
        walk.frames = walkImages;
        walk.duration = 20.0/this.getSpeed();
        ;
    }

    public static String getName() {
        return name;
    }

    @Override
    public void draw(GraphicsContext gc, double t) {
        Image ig = walk.getFrame(t);
        int flip = 1;
        if (this.getSide() == Side.HERO) flip = -1;
        gc.drawImage(ig,this.getX(),this.getY(), flip*ig.getWidth(), ig.getHeight());
    }
}