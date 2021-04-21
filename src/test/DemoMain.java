package test;

import application.Drawing;
import gui.ControlPane;
import gui.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.GameController;
import logic.GameLoop;
import logic.GameState;

public class DemoMain extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception{
		// TODO
		GameController.InitGame();
		StackPane root = new StackPane();
		root.setPadding(new Insets(2,2,2,2));
		
		HomePanel homePanel= new HomePanel();
		PlayPanel playPanel = new PlayPanel();
		PausePanel pausePanel = new  PausePanel();
		Drawing.setHomePanel(homePanel);
		Drawing.setPlayPanel(playPanel);
		Drawing.setPausePanel(pausePanel);
		Drawing.updatePanel();
		
		Canvas canvas = new Canvas(Drawing.getWindowWidth(), Drawing.getWindowHeight());
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		
		GameLoop loop = new GameLoop(gc, Drawing.getWindowWidth(), Drawing.getWindowHeight());
		loop.start();
		
		
		root.getChildren().addAll(canvas,homePanel,playPanel,pausePanel);
//		root.getChildren().add(playPanel);
	
		
		Scene scene = new Scene(root,Drawing.getWindowWidth(),Drawing.getWindowHeight());
		primaryStage.setTitle("Zaa");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
		
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
