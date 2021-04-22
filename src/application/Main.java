package application;

import application.Drawing;
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

public class Main extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception{
		// TODO
		GameController.InitGame();
		VBox root = new VBox();
		root.setSpacing(10);
		root.setPadding(new Insets(2,2,2,2));
		
		// Marker size
//		Button bn= new Button();
//		bn.setPrefHeight(434);
//		bn.setPrefWidth(1289);
		
		Canvas canvas = new Canvas(Drawing.getWindowWidth(), 434);
		ControlPane controlpane = new ControlPane();
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		GameLoop loop = new GameLoop(gc, Drawing.getWindowWidth(), 434);
		loop.start();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				Platform.exit();
				System.exit(0);
			}
		});
		root.getChildren().add(canvas);
//		root.getChildren().add(controlpane);
		Scene scene = new Scene(root,Drawing.getWindowWidth(),Drawing.getWindowHeight());
		primaryStage.setTitle("Zaa");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
