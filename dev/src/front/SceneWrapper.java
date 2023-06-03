package front;

import java.io.File;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneWrapper {
	protected Stage stage;
	protected Parent root;
	protected Scene scene;
	protected FXMLLoader loader;
	protected Controller controller;
	
	public SceneWrapper(String fxmlFilePath, String title, Controller controller, Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(new File(fxmlFilePath).toURI().toURL());
		this.controller = controller;
		loader.setController(controller);
		this.root = loader.load();
		this.scene = new Scene(this.root);
		this.stage = stage;
		this.stage.setScene(this.scene);
        this.stage.setTitle(title);
	}
	
	public void updateScene(String fxmlFilePath, String title) throws Exception {
		FXMLLoader loader = new FXMLLoader(new File(fxmlFilePath).toURI().toURL());
		loader.setController(this.controller);
		this.root = loader.load();
		this.scene = new Scene(this.root);
		this.stage.setScene(this.scene);
        this.stage.setTitle(title);
	}
	
	public SceneWrapper(String fxmlFilePath, String title, Controller controller) throws Exception {
		this(fxmlFilePath, title, controller, new Stage());
	}
	
	public Stage getStage() {
		return this.stage;
	}
	
	public Scene getScene() {
		return this.scene;
	}
}
