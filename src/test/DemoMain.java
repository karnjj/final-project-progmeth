package test;

import gui.ControlPane;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.GameController;
import logic.GameLoop;

public class DemoMain extends Application{
	private final int window_width = 1289;
	private final int window_height = 595;
	@Override
	public void start(Stage primaryStage) throws Exception{
		// TODO
		GameController.InitGame();
		VBox root = new VBox();
		root.setSpacing(10);
		root.setPadding(new Insets(5,5,5,5));
		
		// Marker size
//		Button bn= new Button();
//		bn.setPrefHeight(434);
//		bn.setPrefWidth(1289);
		
		Canvas canvas = new Canvas(window_width, 434);
		ControlPane controlpane = new ControlPane();
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		GameLoop loop = new GameLoop(gc, window_width, 434);
		loop.start();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				Platform.exit();
				System.exit(0);
			}
		});
		
		root.getChildren().add(canvas);
		root.getChildren().add(controlpane);
		Scene scene = new Scene(root,window_width,window_height);
		primaryStage.setTitle("Zaa");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
