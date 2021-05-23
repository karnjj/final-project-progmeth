package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import logic.GameController;
import logic.Side;

import java.util.ArrayList;

public class HeroPane extends HBox {
    private static ArrayList<HeroButton> HeroButtonList = new ArrayList<HeroButton>();

    public HeroPane() {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setSpacing(10);
        this.setMaxHeight(20);
        this.setMaxWidth(20);
        this.setAlignment(Pos.BOTTOM_CENTER);
        HeroButtonList.add(new HeroButton("Slime"));
        HeroButtonList.add(new HeroButton("Alien"));
        HeroButtonList.add(new HeroButton("Minotaur"));
        HeroButtonList.add(new HeroButton("Inkblue"));
        HeroButtonList.add(new HeroButton("Inkred"));

        this.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        for (HeroButton heroButton : HeroButtonList) {
            this.getChildren().add(heroButton);
        }

    }


    public static void draw() {
        for (HeroButton x : HeroButtonList) {
            x.draw();
        }
    }

    public static void update(double dt) {
        for (HeroButton x : HeroButtonList) {
            x.update(dt);
        }
    }
}
