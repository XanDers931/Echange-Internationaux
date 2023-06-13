package front.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import front.FXMLScene;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * This {@code SceneController} is used to control the file selection scene.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 */
public class FileSelectorController extends SceneController {
	
	/**
	 * the {@code SceneController} who called that controller.
	 */
	private SceneController previousController;

	/**
	 * A {@code Button} used to go back to main menu.
	 */
	@FXML private Button closePopUpButton;
	
	/**
	 * A {@code Button} used to chose some files.
	 */
	@FXML private Button chooseFileButton;
	
	/**
	 * A {@code Button} used to perform the importation.
	 */
	@FXML private Button importButton;
	
	/**
	 * A {@code Button} used to remove selected files.
	 */
	@FXML private Button removeFileButton;
	
	/**
	 * A {@code ListView<File>} used to list all selected files.
	 */
	@FXML private ListView<File> filesListView;
	
	/**
	 * FileSelectorController constructor.
	 * @param stage, a {@code Stage} used to show the current Scene.
	 * @param previousController, the {@code SceneController} who called that controller.
	 */
	public FileSelectorController(Stage stage, SceneController previousController) {
		super(FXMLScene.SELECT_FILES.getPath(), FXMLScene.SELECT_FILES.getTitle(), stage);
		this.previousController = previousController;
		this.updateStage();
	}

	/**
	 * Add all the event handler needed
	 */
	public void initialize() {
		//Close the scene and go back to main menu
		this.closePopUpButton.addEventHandler(ActionEvent.ACTION, a -> {
			this.previousController.updateStage();
			this.previousController.getStage().show();
		});
		
		//Listen files list changes and perform some changes on button
		this.filesListView.getItems().addListener((ListChangeListener<? super File>)(c -> {
			if (this.filesListView.getItems().isEmpty()) {
				//The list is empty
				this.importButton.setDefaultButton(false);
				this.chooseFileButton.setDefaultButton(true);
				this.importButton.setDisable(true);
				this.removeFileButton.setDisable(true);
				
			} else {
				//The list is not empty
				this.importButton.setDisable(false);
				this.chooseFileButton.setDefaultButton(false);
				this.importButton.setDefaultButton(true);
				this.removeFileButton.setDisable(false);
			}
		}));
		//Perform file chooser operations
		this.chooseFileButton.addEventHandler(ActionEvent.ACTION, e -> {
			FileChooser chooser = new FileChooser();
			List<File> choosenFiles = chooser.showOpenMultipleDialog(this.stage);
			if (choosenFiles != null) {
				for (File file : choosenFiles) {
					this.filesListView.getItems().add(file);
				}
			}
		});
		//Remove file event
		this.removeFileButton.addEventHandler(ActionEvent.ACTION, e -> {
			MultipleSelectionModel<File> selectedItems = this.filesListView.getSelectionModel();
			List<File> toRemove = new ArrayList<File>();
			for (File currentFile : selectedItems.getSelectedItems()) {
				toRemove.add(currentFile);
			}
			this.filesListView.getItems().removeAll(toRemove);
		});
		//perform the importation
		this.importButton.addEventHandler(ActionEvent.ACTION, a -> {
			//ask if user want to generate log file
			Alert alert = new Alert(AlertType.CONFIRMATION, "En cas d'anomalie sur un ou plusieurs fichier(s), souhaitez vous générer un fichier csv contenant les lignes en erreur ?\n\n", ButtonType.YES, ButtonType.NO);
			alert.showAndWait();
			boolean generateLogFile = alert.getResult() == ButtonType.YES;
			CsvImportatorController controller = new CsvImportatorController(this.stage, filesListView.getItems(), generateLogFile);
			controller.getStage().show();
		});
	}
}