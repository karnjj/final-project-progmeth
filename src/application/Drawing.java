package application;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class Drawing {
	private static final int window_width = 1289;
	private static final int window_height = 595;
	
	private static WritableImage bg = new WritableImage(new Image(ClassLoader.getSystemResource("roadmap.png").toString(),window_width,434,false,false).getPixelReader(),0,0,window_width,434);
//	private static WritableImage bg = new WritableImage(new Image(ClassLoader.getSystemResource("roadmap.png").toString(),window_width,434,false,false).getPixelReader(),0,0,window_width,434);
	
	public static void drawBackground(GraphicsContext gc) {
		gc.drawImage(bg, 0, 0);
	}
	
	public static int getWindowWidth() {
		return window_width;
	}
	public static int getWindowHeight() {
		return window_height;
	}
}
