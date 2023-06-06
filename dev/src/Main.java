import front.FXMLScene;
import front.SceneWrapper;
import front.controller.MainMenuController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	public void start(Stage stage) throws Exception {
		MainMenuController controller = new MainMenuController();
		SceneWrapper main = new SceneWrapper(stage);
		main.updateScene(FXMLScene.MAIN_MENU, controller);
        main.getStage().show();
	}

	public static void main(String[] args) {
	        Application.launch(args);
	}
}
