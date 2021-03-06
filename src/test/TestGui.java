package test;

import gui.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.GameLoop;
import logic.Side;

public class TestGui extends Application {

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        EndgamePanel itemButton = new EndgamePanel();

        root.getChildren().add(itemButton);
        Scene scene = new Scene(root);
        primaryStage.setTitle("MyJavaFX"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


