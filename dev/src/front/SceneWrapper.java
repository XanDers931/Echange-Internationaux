package front;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import front.controller.Controller;
import front.controller.YesNoController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/** 
 * This class is a JavaFX Scene and Stage wrapped. It's used to make the creation of Stage easier.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @version 0.0.1, 06/06/23
 */
public class SceneWrapper {
	protected Stage stage;
	protected Parent root;
	protected Scene scene;
	protected FXMLLoader loader;
	protected Controller controller;
	
	
	/** 
	 * The full construtctor for the wrapper, only a {@code Stage} is needed. It's usefull when the purpose is to update an existing {@code Stage}.
	 * You will need to call {@code updateScene(...)} later.
	 * @param stage, representing the {@Stage} you want to use with this wrapper.
	 * @see updateScene
	 */
	public SceneWrapper(Stage stage) {
		this.stage = stage;
	}
	
	/** 
	 * The default constructor. It create a new {@code Stage} for you.
	 * You will need to call {@code updateScene(...)} later.
	 * @see updateScene
	 */
	public SceneWrapper() {
		this(new Stage());
	}
	
	
	/** 
	 * That method used to update the scene, using a fxml file. You can call it right after created the object, or call it whenever you want, no matter how many.
	 * @param fxmlFilePath a {@String} representing the path to the fxml file.
	 * @param title a {@String} representing
	 * @param controller, a {@code Controller} who will manage all the event of that Stage.
	 * @throws MalformedURLException when the path to file is wrong.
	 * @throws IOException when the {@code FXMLLoader} failed.
	 */
	public void updateScene(String fxmlFilePath, String title, Controller controller) throws MalformedURLException, IOException {
		FXMLLoader loader = new FXMLLoader(new File(fxmlFilePath).toURI().toURL());
		this.controller = controller;
		this.controller.setSceneWrapperParent(this);
		loader.setController(this.controller);
		this.root = loader.load();
		this.scene = new Scene(this.root);
		this.stage.setScene(this.scene);
        this.stage.setTitle(title);
	}
	
	/** 
	 * That method used to update the scene, using a fxml file. You can call it right after created the object, or call it whenever you want, no matter how many.
	 * @param fxmlScene a {@FXMLScene} representing the FXML element to create
	 * @param controller, a {@code Controller} who will manage all the event of that Stage.
	 * @throws MalformedURLException when the path to file is wrong.
	 * @throws IOException when the {@code FXMLLoader} failed.
	 * @see FXMLScene
	 */
	public void updateScene(FXMLScene fxmlScene, Controller controller) throws MalformedURLException, IOException {
		this.updateScene(fxmlScene.getPath(), fxmlScene.getTitle(), controller);
	}
	
	/**
	 * Getter for current {@code Stage}.
	 * @return the current {@code Stage}.
	 */
	public Stage getStage() {
		return this.stage;
	}
	
	/**
	 * Getter for current {@code Scene}.
	 * @return the current {@code Scene}.
	 */
	public Scene getScene() {
		return this.scene;
	}
}
