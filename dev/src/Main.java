import front.SceneWrapper;
import front.MainMenuController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	public void start(Stage stage) throws Exception {
		MainMenuController controller = new MainMenuController();
		SceneWrapper main = new SceneWrapper("./ihm/main/menu.fxml", "Menu principal", controller, stage);
		controller.setSceneWrapperParent(main);
        main.getStage().show();
	}

	public static void main(String[] args) {
	        Application.launch(args);
	}
}
