package front.controller;

import java.io.File;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/** 
 * This abstract class set the minimum requirement to be a FXML Element controller
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 */
public abstract class ElementController extends Controller {
	
	/**
	 * Element controller constructor.
	 * @param fxmlPath a {@code String} representing the fxml file path.
	 */
	public ElementController(String fxmlPath) {
		super(fxmlPath);
	}
	
	/**
	 * This method load the FXML element into the {@code Parent} root attribute.
	 */
	protected final void loadElement() {
		try {
			FXMLLoader loader = new FXMLLoader(new File(this.fxmlPath).toURI().toURL());
			loader.setController(this);
			this.root = loader.load();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, "Une erreur innatendue est survenue.\n\nDétail : " + e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
		}
	}
}
