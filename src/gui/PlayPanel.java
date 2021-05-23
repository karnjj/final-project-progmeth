package gui;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import logic.GameController;
import logic.GameState;
import logic.Side;
import application.Drawing;
import application.SoundUtils;
import javafx.geometry.Insets;

public class PlayPanel extends StackPane {

    public static HpBar hpHero = new HpBar(Side.HERO);
    public static HpBar hpEnemy = new HpBar(Side.ENEMY);

    public PlayPanel() {

        StackPane.setMargin(hpHero, new Insets(10, 10, 0, 0));
        StackPane.setAlignment(hpHero, Pos.TOP_LEFT);

        StackPane.setMargin(hpEnemy, new Insets(10, 0, 0, 10));
        StackPane.setAlignment(hpEnemy, Pos.TOP_RIGHT);

        Button pauseButton = new Button();
        pauseButton.getStyleClass().add("pauseButton");
        pauseButton.setOnMouseClicked(e -> {
            System.out.println("Log: pauseButton(PlayPanel)");
            SoundUtils.clickedSound();
            Drawing.blurBackgroundEffect();
            Drawing.drawBackground();
            GameController.updateGameState(GameState.Pause);
        });
        StackPane.setMargin(pauseButton, new Insets(10, 0, 0, 0));
        StackPane.setAlignment(pauseButton, Pos.TOP_CENTER);

        EnergyPane energyPane = new EnergyPane();
        StackPane.setMargin(energyPane, new Insets(0, 0, 10, 100));
        StackPane.setAlignment(energyPane, Pos.BOTTOM_LEFT);

        LevelupButton levelupButton = new LevelupButton();
        StackPane.setMargin(levelupButton, new Insets(0, 100, 10, 0));
        StackPane.setAlignment(levelupButton, Pos.BOTTOM_RIGHT);
        Drawing.setLevelupButton(levelupButton);

        HeroPane heroPane = new HeroPane();
        StackPane.setMargin(heroPane, new Insets(0, 0, 10, 0));
        StackPane.setAlignment(heroPane, Pos.BOTTOM_CENTER);

        VBox detectMouse = new VBox();
//		

        detectMouse.addEventFilter(MouseEvent.ANY,
                new EventHandler<MouseEvent>() {
                    double startDraw = 0;
                    double startPos = 0;
                    double lastTime = 0;
                    double lastPos = 0;

                    public void handle(MouseEvent e) {
                        double now = System.currentTimeMillis();
                        if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
                            Drawing.setInertia(0);
                            startDraw = Drawing.getMovePosBgX();
                            lastTime = System.currentTimeMillis();
                            startPos = e.getX();
                            lastPos = e.getX();
                        } else if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                            Drawing.setMovePosBgX(startDraw - (startPos - e.getX()));
                            if (now - lastTime > 50) {
                                lastPos = e.getX();
                                lastTime = System.currentTimeMillis();
                            }
                        } else if (e.getEventType() == MouseEvent.MOUSE_RELEASED) {
                            int inertia = (int) ((e.getX() - lastPos) / (now - lastTime) * 8);
                            Drawing.setInertia(inertia);
                        }
                    }
                });
        this.getChildren().addAll(detectMouse, hpHero, hpEnemy, pauseButton, heroPane, energyPane, levelupButton);
    }

}
