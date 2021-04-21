package logic;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class AnimatedImage{
    public ArrayList<Image> frames = new ArrayList<Image>();
    public double duration;

    public Image getFrame(double time)
    {
        int index = (int)((time % (frames.size() * duration)) / duration);
        return frames.get(index);
    }
}