package Main;
import front.controller.SceneController;

import java.io.File;

import front.controller.MainMenuController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	public static final String RES_PATH = System.getProperty("user.dir") + File.separator + "res" + File.separator;

	
	public void start(Stage stage) {
		SceneController controller = new MainMenuController(stage);
		controller.getStage().show();
	}

	public static void main(String[] args) {
	        Application.launch(args);
	}
}
