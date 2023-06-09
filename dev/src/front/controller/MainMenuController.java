package front.controller;

import java.io.File;
import java.io.FileInputStream;

import front.FXMLScene;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.ObjectInputStream;

/** This {@code SceneController} is used to control the main menu scene.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 */
public class MainMenuController extends SceneController {
	
	public MainMenuController(Stage stage) {
		super(FXMLScene.MAIN_MENU.getPath(), FXMLScene.MAIN_MENU.getTitle(), stage);
		this.updateStage();
	}

	/**
	 * A {@code Button} used to create a new session;
	 */
	@FXML private Button newSessionButton;
	
	/**
	 * A {@code Button} used to create a new session;
	 */
	@FXML private Button loadSessionButton;
	
	/**
	 * A {@code Button} used to exit the app.
	 */
	@FXML private Button quitButton;
	
	/**
	 * Add all the event handler needed
	 */
	public void initialize() {
		//Go to importation scene
		this.newSessionButton.addEventHandler(ActionEvent.ACTION, a -> {
			SceneController controller = new FileSelectorController(this.stage);
			controller.getStage().show();
		});
		//load existing session
		if (new File(voyages.Platform.SAVE_PATH).exists()) {
			this.loadSessionButton.setDisable(false);
			this.loadSessionButton.addEventHandler(ActionEvent.ACTION, a -> {
				voyages.Platform platform = null;
				try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(voyages.Platform.SAVE_PATH))) {
					platform = (voyages.Platform)ois.readObject();
				} catch (Exception e) {
					Alert alert = new Alert(AlertType.ERROR, "Une erreur est survenue, impossible de charger la session précédente.\n\nDétail : " + e.getMessage());
					alert.showAndWait();
					e.printStackTrace();
					return;
				}
				SceneController controller = new AffectationController(this.stage, platform);
				controller.getStage().show();
			});
		}
		//Exit the app
		this.quitButton.addEventHandler(ActionEvent.ACTION, a -> {
			Platform.exit();
			System.exit(0);
		});
	}
}
