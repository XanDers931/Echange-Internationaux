package Main;
import front.controller.SceneController;
import front.controller.MainMenuController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	public void start(Stage stage) {		
		SceneController controller = new MainMenuController(stage);
		controller.getStage().show();
	}

	public static void main(String[] args) {
	        Application.launch(args);
	}
}
