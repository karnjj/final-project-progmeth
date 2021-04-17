package application;

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

public class Main extends Application {
	
	private final int window_width = 1289;
	private final int window_height = 595;
	
    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        
        Scene scene = new Scene(root, window_width, window_height);

        Canvas canvas = new Canvas(window_width, window_height);
        root.getChildren().add( canvas );
        GraphicsContext gc = canvas.getGraphicsContext2D();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
        GameLoop loop = new GameLoop(gc, window_width, window_height);
        loop.start();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
