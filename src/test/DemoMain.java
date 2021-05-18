package test;

import application.Drawing;
import application.SoundUtils;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.GameController;
import logic.GameLoop;
import logic.GameState;

import java.util.Objects;

public class DemoMain extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception{
		// TODO
		GameController.InitGame();
		StackPane root = new StackPane();
		root.setPadding(new Insets(10,10,10,10));
		
		HomePanel homePanel= new HomePanel();
		PlayPanel playPanel = new PlayPanel();
		PausePanel pausePanel = new  PausePanel();
		Drawing.setHomePanel(homePanel);
		Drawing.setPlayPanel(playPanel);
		Drawing.setPausePanel(pausePanel);
		Drawing.updatePanel(GameState.Home);
		Canvas canvas = new Canvas(Drawing.getWindowWidth(), Drawing.getWindowHeight());
		GraphicsContext gc = canvas.getGraphicsContext2D();


		GameLoop loop = new GameLoop(gc, Drawing.getWindowWidth(), Drawing.getWindowHeight());
		loop.start();
		
		MuteButton muteButton = new MuteButton();
		SoundUtils.setMuteButton(muteButton);
		muteButton.setOnMouseClicked(e ->{
			System.out.println("Log: muteButton(DemoMain)");
			muteButton.update();
		});
		root.setAlignment(muteButton, Pos.BOTTOM_RIGHT);
//		this.getChildren().add(muteButton);


		root.getChildren().addAll(canvas,homePanel,playPanel,pausePanel,muteButton);
//		root.getChildren().add(playPanel);

		Scene scene = new Scene(root,Drawing.getWindowWidth(),Drawing.getWindowHeight());
		//add style
		scene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("style.css")).toExternalForm());
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
