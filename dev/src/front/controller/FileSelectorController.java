package front.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import front.FXMLScene;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.stage.FileChooser;


/** This {@code Controller} is used to manage the file selector scene.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @version 0.0.1, 06/06/23
 */
public class FileSelectorController extends Controller {
	
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
	 * Add all the event handler needed
	 */
	public void initialize() {
		//Close the scene and go back to main menu
		this.closePopUpButton.addEventHandler(ActionEvent.ACTION, a -> {
			MainMenuController controller = new MainMenuController();
			try {
				this.parentSceneWrapper.updateScene(FXMLScene.MAIN_MENU, controller);
			} catch (Exception e) {
				e.printStackTrace();
			}
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
			List<File> choosenFiles = chooser.showOpenMultipleDialog(this.parentSceneWrapper.getStage());
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
			YesNoController yesNoPopUpController;
			boolean generateLogFile = false;
			try {
				yesNoPopUpController = Controller.getYesNoPopUp("Souhaitez vous récupérer le fichier de synthèse ?", "Le fichier de synthèse sera enregistré au même endroit que celui d'origine");
				yesNoPopUpController.getSceneWrapperParent().getStage().showAndWait();
				generateLogFile = yesNoPopUpController.getResult();
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			CsvImportatorController controller = new CsvImportatorController(filesListView.getItems(), generateLogFile);
			try {
				this.parentSceneWrapper.updateScene(FXMLScene.IMPORT_CSV, controller);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
