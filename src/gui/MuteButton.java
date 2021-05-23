package gui;

import application.SoundUtils;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.GameController;

public class MuteButton extends Button {
    private static ImageView onImage;
    private static ImageView offImage;
    private final int size = 50;

    public MuteButton() {
        this.onImage = new ImageView(new Image("Button/soundButtonOn.png"));
        this.offImage = new ImageView(new Image("Button/soundButtonOff.png"));
        onImage.setFitHeight(size);
        onImage.setFitWidth(size);

        offImage.setFitWidth(size);
        offImage.setFitHeight(size);
        this.getStyleClass().add("muteButton");
        this.setGraphic(onImage);
    }

    public void update() {
        System.out.println(SoundUtils.isSoundOn());
        if (SoundUtils.isSoundOn()) {
            this.setGraphic(offImage);
            SoundUtils.setSoundOn(false);
        } else {
            this.setGraphic(onImage);
            SoundUtils.setSoundOn(true);
        }
    }

}
