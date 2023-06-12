package front;

/** This {@code Enum} is used to store all information about that app's FXML Scenes.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @version 0.0.1, 06/06/23
 */
public enum FXMLScene {
	
	/**
	 * The main menu scene.
	 */
	MAIN_MENU("./dev/res/fxml/main/menu.fxml", "Menu principal"),
	
	
	/**
	 * The import csv scene.
	 */
	IMPORT_CSV("./dev/res/fxml/importation/log-importation.fxml", "Importation des données"),
	
	
	/**
	 * The files selector scene.
	 */
	SELECT_FILES("./dev/res/fxml/main/popup-csv-import.fxml", "Sélection de fichiers"),
	
	/**
	 * The affectation manager scene
	 */
	AFFECTATION_MANAGER("./dev/res/fxml/affectation.fxml", "Gestion des échanges"),
	
	/**
	 * The affectation element row
	 */
	AFFECTATION_ROW("./dev/res/fxml/element/affectation-row.fxml", ""),
	
	/**
	 * The coefficient affectation manager scene
	 */
	COEFF_MANAGER("./dev/res/fxml/coefWindow.fxml", "Gestion des coefficients");
	
	
	/**
	 * The FXML scene's path 
	 */
	private final String path;
	
	/**
	 * The Stage's title 
	 */
	private final String title;
	
	/**
	 * 
	 * @param path, a {@code String} representing the FXML scene's path
	 * @param title, a {@code String} representing the FXML scene's default title
	 */
	private FXMLScene(String path, String title) {
		this.path = path;
		this.title = title;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	
}
