package front.controller;

import javafx.scene.Parent;

/** 
 * This abstract class set the minimum requirement to be a FXML controller
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @version 0.0.1, 06/06/23
 */
public abstract class Controller {
	
	/**
	 * A {@code String} representing the fxml file path.
	 */
	protected String fxmlPath;
	
	/**
	 * A {@code Parent} used to store root element of fxml file.
	 */
	protected Parent root;
	
	/**
	 * Controller constructor.
	 * @param fxmlPath a {@code String} representing the fxml file path.
	 */
	public Controller(String fxmlPath) {
		this.fxmlPath = fxmlPath;
	}
	
	/**
	 * @return the root element
	 */
	public Parent getRoot() {
		return root;
	}

	/** 
	 * That method is called by the {@code FXMLLoader}, it generally used to add all the event handler.
	 */
	public abstract void initialize();
}