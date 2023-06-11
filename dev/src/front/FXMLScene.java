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
	MAIN_MENU("./ihm/main/menu.fxml", "Menu principal"),
	
	
	/**
	 * The import csv scene.
	 */
	IMPORT_CSV("./ihm/importation/log-importation.fxml", "Importation des données"),
	
	
	/**
	 * The files selector scene.
	 */
	SELECT_FILES("./ihm/main/popup-csv-import.fxml", "Sélection de fichiers"),
	
	/**
	 * The affectation manager scene
	 */
	AFFECTATION_MANAGER("./ihm/affectation.fxml", "Gestion des échanges"),
	
	/**
	 * The affectation element row
	 */
	AFFECTATION_ROW("./ihm/element/affectation-row.fxml", ""),

	COEFF_MANAGER("./ihm/coefWindow.fxml", "Gestion des coefficients");
	
	
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
