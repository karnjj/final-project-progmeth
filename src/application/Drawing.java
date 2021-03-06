package application;


import entity.base.Entity;
import exception.NullImageToRenderException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import gui.*;
import logic.EntityManager;
import logic.GameController;
import logic.GameState;

public class Drawing {
    private static GraphicsContext gc;
    private static final int window_width = 1289;
    private static final int window_height = 595;

    private static final int game_width = 3000;
    private static final int game_height = 715;

    // backGround moving
    private static double movePosBgX;
    private static double movePosBgY;
    private static int inertia;
    private static double speedPosBg;

    private static HomePanel homePanel;
    private static PlayPanel playPanel;
    private static PausePanel pausePanel;
    private static EndgamePanel endgamePanel;
    private static LevelupButton levelupButton;

    private static final WritableImage bg = new WritableImage(new Image(ClassLoader.getSystemResource("roadmap3.png").toString(), game_width, 715, false, false).getPixelReader(), 0, 0, game_width, 715);

    public static void init(GraphicsContext gc) {
        Drawing.gc = gc;
        movePosBgX = 0;
        movePosBgY = 0;
        inertia = 0;
        speedPosBg = 100;
    }


    public static void updatePanel() {
        switch (GameController.getGameState()) {
            case Home -> {
                homePanel.setVisible(true);
                playPanel.setVisible(false);
                pausePanel.setVisible(false);
                endgamePanel.setVisible(false);
            }
            case Play -> {
                homePanel.setVisible(false);
                playPanel.setVisible(true);
                pausePanel.setVisible(false);
                endgamePanel.setVisible(false);
            }
            case Pause -> {
                pausePanel.setVisible(true);
                playPanel.setVisible(false);
                endgamePanel.setVisible(false);
            }
            case End -> {
                homePanel.setVisible(false);
                playPanel.setVisible(false);
                pausePanel.setVisible(false);
                endgamePanel.update();
                endgamePanel.setVisible(true);
            }
        }
    }

    public static void updatePosBg(double dt) {
        if (GameController.getGameState() == GameState.Pause) return;
        if (GameController.getGameState() != GameState.Home)
            setMovePosBgY(getMovePosBgY() - speedPosBg * dt);
        else
            setMovePosBgY(getMovePosBgY() + speedPosBg * dt);
        setMovePosBgX(getMovePosBgX() + getInertia());
    }

    public static void drawEntities(double t) {
        for (Entity e : EntityManager.getAllEntity()) {
            try {
                e.draw(gc, t);
            } catch (NullImageToRenderException nie) {
                System.out.println(nie.getMessage());
            }
        }
    }

    public static void blurBackgroundEffect() {
        gc.setGlobalAlpha(0.9);
        gc.setEffect(new BoxBlur(7, 7, 3));
    }

    public static void resetBackgroundEffect() {
        gc.setGlobalAlpha(1);
        gc.setEffect(null);
    }

    public static void drawEverything(double t) {
        Drawing.drawBackground();
        Drawing.drawEntities(t);
        EnergyPane.draw();
        HeroPane.draw();
        PlayPanel.hpEnemy.draw();
        PlayPanel.hpHero.draw();
    }


    public static void drawBackground() {
        gc.drawImage(bg, 0 + movePosBgX, movePosBgY, bg.getWidth(), bg.getHeight());

    }

    public static int getWindowWidth() {
        return window_width;
    }

    public static int getWindowHeight() {
        return window_height;
    }


    public static HomePanel getHomePanel() {
        return homePanel;
    }

    public static void setHomePanel(HomePanel homePanel) {
        Drawing.homePanel = homePanel;
    }

    public static PlayPanel getPlayPanel() {
        return playPanel;
    }

    public static void setPlayPanel(PlayPanel playPanel) {
        Drawing.playPanel = playPanel;
    }

    public static PausePanel getPausePanel() {
        return pausePanel;
    }

    public static void setPausePanel(PausePanel pausePanel) {
        Drawing.pausePanel = pausePanel;
    }

    public static int getGameWidth() {
        return game_width;
    }

    public static int getGameHeight() {
        return game_height;
    }

    public static void setMovePosBgX(double movePosBgX) {
        Drawing.movePosBgX = movePosBgX;
        if (Drawing.movePosBgX > 0) Drawing.movePosBgX = 0;
        if (Drawing.movePosBgX < (Drawing.window_width - Drawing.game_width))
            Drawing.movePosBgX = (Drawing.window_width - Drawing.game_width);
    }

    public static double getMovePosBgX() {
        return movePosBgX;
    }

    public static double getMovePosBgY() {
        return movePosBgY;
    }

    public static void setMovePosBgY(double movePosBgY) {
        Drawing.movePosBgY = movePosBgY;
        if (Drawing.movePosBgY > 0) Drawing.movePosBgY = 0;
        if (Drawing.movePosBgY < (Drawing.window_height - Drawing.game_height))
            Drawing.movePosBgY = (Drawing.window_height - Drawing.game_height);
    }

    public static EndgamePanel getEndgamePanel() {
        return endgamePanel;
    }

    public static void setEndgamePanel(EndgamePanel endgamePanel) {
        Drawing.endgamePanel = endgamePanel;
    }


    public static double getInertia() {
        return inertia;
    }

    public static void setInertia(int inertia) {
        Drawing.inertia = inertia;
    }


    public static LevelupButton getLevelupButton() {
        return levelupButton;
    }

    public static void setLevelupButton(LevelupButton levelupButton) {
        Drawing.levelupButton = levelupButton;
    }


}
