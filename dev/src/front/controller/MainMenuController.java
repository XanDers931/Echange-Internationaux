package front.controller;

import front.FXMLScene;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/** This {@code Enum} is used to store all information about that app's FXML Scenes.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @version 0.0.1, 06/06/23
 */
public class MainMenuController extends Controller {
	
	/**
	 * A {@code Button} used to go the importation scene.
	 */
	@FXML private Button importMenuButton;
	
	/**
	 * A {@code Button} used to exit the app.
	 */
	@FXML private Button quitButton;
	
	/**
	 * Add all the event handler needed
	 */
	public void initialize() {
		//Go to importation scene
		this.importMenuButton.addEventHandler(ActionEvent.ACTION, a -> {
			try {
				this.parentSceneWrapper.updateScene(FXMLScene.SELECT_FILES, new FileSelectorController());
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.parentSceneWrapper.getStage().show();
		});
		//Exit the app
		this.quitButton.addEventHandler(ActionEvent.ACTION, a -> {
			Platform.exit();
			System.exit(0);
		});
	}
}
