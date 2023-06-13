package front;

import java.io.File;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/** This {@code Enum} is used to store all information about that app's FXML Scenes.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @version 0.0.5, 06/13/23
 */
public enum FXMLScene {
	

	/**
	 * The main menu scene.
	 */
	MAIN_MENU("fxml/main/menu.fxml", "Menu principal"),
	
	
	/**
	 * The import csv scene.
	 */
	IMPORT_CSV("fxml/importation/log-importation.fxml", "Importation des données"),
	
	
	/**
	 * The files selector scene.
	 */
	SELECT_FILES("fxml/main/popup-csv-import.fxml", "Sélection de fichiers"),
	

	/**
	 * The affectation manager scene
	 */
	AFFECTATION_MANAGER("fxml/affectation.fxml", "Gestion des échanges"),
	

	/**
	 * The affectation element row
	 */
	AFFECTATION_ROW("fxml/element/affectation-row.fxml", ""),
	

	/**
	 * The coefficient affectation manager scene
	 */
	COEFF_MANAGER("fxml/coefWindow.fxml", "Gestion des coefficients");
	
	
	/**
	 * The FXML scene's path 
	 */
	private final String path;
	
	/**
	 * The Stage's title 
	 */
	private final String title;
	
	/**
	 * A {@code FXMLScene} constructor.
	 * @param path, a {@code String} representing the FXML scene's path
	 * @param title, a {@code String} representing the FXML scene's default title
	 */
	private FXMLScene(String path, String title) {
		this.path = path;
		this.title = title;
	}

	/** The {@code FXMLScene} path getter.
	 * @return the path
	 */
	public String getPath() {
		return Main.Main.RES_PATH + this.path;
	}

	/** The {@code FXMLScene} title getter.
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
}