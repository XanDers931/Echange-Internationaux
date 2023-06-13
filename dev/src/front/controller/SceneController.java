package front.controller;

import java.io.File;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/** 
 * This abstract class set the minimum requirement to be a FXML Scene controller
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 */
public abstract class SceneController extends Controller {

	/**
	 * The stage's title.
	 */
	protected String title;
	
	/**
	 * The {@Stage} where display current scene.
	 */
	protected Stage stage;
	
	/**
	 * A {@Code Scene} container to contain {@code Parent} root attribute.
	 */
	protected Scene scene;
	
	/**
	 * Scene controller constructor
	 * @param fxmlPath, a {@code String} representing the fxml file path.
	 * @param title, a {@code String} representing the stage's title.
	 * @param stage, a {@Code Stage} used to show current Scene.
	 */
	public SceneController(String fxmlPath, String title, Stage stage) {
		super(fxmlPath);
		this.title = title;
		this.stage = stage;
	}
	
	/**
	 * This method load the FXML element into the {@code Stage} attribute.
	 */
	public final void updateStage() {
		try {
			FXMLLoader loader = new FXMLLoader(new File(this.fxmlPath).toURI().toURL());
			loader.setController(this);
			this.root = loader.load();
			this.scene = new Scene(this.root);
			this.stage.setScene(this.scene);
	        this.stage.setTitle(this.title);
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, "Une erreur innatendue est survenue.\n\nDétail : " + e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
		}
	}

	/**
	 * @return the stage
	 */
	public Stage getStage() {
		return stage;
	}

	/**
	 * @return the scene
	 */
	public Scene getScene() {
		return scene;
	}
}