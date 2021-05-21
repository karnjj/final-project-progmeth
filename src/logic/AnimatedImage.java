package logic;

import exception.IndexOfFrameOutboundException;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class AnimatedImage{
    public ArrayList<Image> frames = new ArrayList<Image>();
    public double duration;

    public Image getFrame(double time) throws IndexOfFrameOutboundException {
        int index = (int)((time % (frames.size() * duration)) / duration);
        if (index >= frames.size()) throw new IndexOfFrameOutboundException();
        return frames.get(index);
    }
}