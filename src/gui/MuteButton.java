package gui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.GameController;

public class MuteButton extends Button {
		private static ImageView onImage;
		private static ImageView offImage;
	
		public MuteButton() {
			this.setHeight(100);
			this.setHeight(100);
			this.onImage = new ImageView(new Image("Button/MuteButton"));
			this.offImage = new ImageView(new Image("Button/MuteButton"));
			update();	
		}
		
		public void update() {
			if(!GameController.isMuteSound()) {
				this.setGraphic(onImage);
				GameController.setMuteSound(true);
			}
			else {
				this.setGraphic(offImage);
				GameController.setMuteSound(false);
			}
		}
}
