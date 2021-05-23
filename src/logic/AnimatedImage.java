package logic;

import exception.IndexOfFrameOutboundException;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class AnimatedImage {
    private ArrayList<Image> frames;
    private double duration;

    public AnimatedImage() {
        frames = new ArrayList<Image>();
    }

    public Image getFrame(double time) throws IndexOfFrameOutboundException {
        int index = (int) ((time % (frames.size() * duration)) / duration);
        if (index >= frames.size()) throw new IndexOfFrameOutboundException();
        return frames.get(index);
    }

    public ArrayList<Image> getFrames() {
        return frames;
    }

    public void setFrames(ArrayList<Image> frames) {
        this.frames = frames;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }


}