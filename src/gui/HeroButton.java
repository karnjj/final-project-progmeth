package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.GameController;

import java.util.concurrent.atomic.AtomicInteger;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;


public class HeroButton extends StackPane {
    private Hero hero;
    private Button btn;
    private VBox processBox;
    private ProgressBar pb;
    private ImageView imageView;


    public HeroButton(String name) {
        this.btn = new Button();
        btn.setPadding(new Insets(5, 5, 5, 5));

        pb = new ProgressBar();
        processBox = new VBox(pb);
        processBox.setAlignment(Pos.CENTER);

        this.hero = new Hero(name);
        imageView = new ImageView(this.hero.getRanger().getUrl());
        imageView.setFitWidth(70);
        imageView.setFitHeight(70);

        btn.setGraphic(imageView);
        this.setTooltip();

        btn.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        mouseClickHandler();
                        System.out.println("Log: Click summon ranger(HeroPane)");

                    }
                });

        this.getChildren().addAll(btn, processBox);

    }


    private void mouseClickHandler() {
        if (this.hero.canBuy()) {
            this.getHero().Buy();
        }
    }

    private void setBackGround() {
        if (this.hero.getBuyCountdown() != 0) {
            processBox.setDisable(false);
            processBox.setVisible(true);
        } else {
            processBox.setDisable(true);
            processBox.setVisible(false);
        }
        if (this.hero.canBuy()) {
            btn.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            btn.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        btn.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }

    public void update(double dt) {
        this.hero.update(dt);
        this.pb.setProgress(1 - this.hero.getBuyCountdown() / this.hero.getRanger().getBuyDelay());
    }

    public void draw() {
        this.setBackGround();
    }

    private void setTooltip() {
        Tooltip tooltip = new Tooltip();
        tooltip.setFont(new Font(12));
        tooltip.setText(hero.tooltipMassage());
        this.btn.setOnMouseMoved((MouseEvent e) -> {
            if (hero != null)
                tooltip.show(this, e.getScreenX(), e.getScreenY() + 10);
        });
        this.btn.setOnMouseExited((MouseEvent e) -> {
            tooltip.hide();
        });
    }

    public Hero getHero() {
        return this.hero;
    }
}
