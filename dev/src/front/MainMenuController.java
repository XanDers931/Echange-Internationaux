package front;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;

public class MainMenuController implements Controller {
	private SceneWrapper parentSceneWrapper;
	private SceneWrapper csvFileSelectorPopUp;
	private PopupController popupController;
	@FXML private Button importMenuButton;
	@FXML private Button quitButton;
	
	public void initialize() throws Exception {
		this.importMenuButton.addEventHandler(ActionEvent.ACTION, a -> {
			this.popupController = new PopupController();
			try {
				this.csvFileSelectorPopUp = new SceneWrapper("./ihm/main/popup-csv-import.fxml", "Importer des données", this.popupController);
				this.popupController.setSceneWrapperParent(this.csvFileSelectorPopUp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.csvFileSelectorPopUp.getStage().initModality(Modality.APPLICATION_MODAL);
			this.csvFileSelectorPopUp.getStage().setResizable(false);
			this.csvFileSelectorPopUp.getStage().show();
		});
		this.quitButton.addEventHandler(ActionEvent.ACTION, e -> {
			Platform.exit();
		});
	}
	
	private void importFiles() {
		
	}
	
	@Override
	public void setSceneWrapperParent(SceneWrapper s) {
		this.parentSceneWrapper = s;
		
	}

	@Override
	public SceneWrapper getSceneWrapperParent() {
		return this.parentSceneWrapper;
	}
	
	public class PopupController implements Controller {
		@FXML private Button closePopUpButton;
		@FXML private Button chooseFileButton;
		@FXML private Button importButton;
		@FXML private Button removeFileButton;
		@FXML private ListView<File> filesListView;
		
		public void initialize() {
			this.closePopUpButton.addEventHandler(ActionEvent.ACTION, e -> {
				csvFileSelectorPopUp.getStage().close();
			});
			
			this.filesListView.getItems().addListener((ListChangeListener<? super File>)(c -> {
				if (!this.filesListView.getItems().isEmpty()) {
					//si la liste n'est pas vide
					this.importButton.setDisable(false);
					this.chooseFileButton.setDefaultButton(false);
					this.importButton.setDefaultButton(true);
					this.removeFileButton.setDisable(false);
				} else {
					//la liste est vide
					this.importButton.setDefaultButton(false);
					this.chooseFileButton.setDefaultButton(true);
					this.importButton.setDisable(true);
					this.removeFileButton.setDisable(true);
				}
			}));
			
			this.chooseFileButton.addEventHandler(ActionEvent.ACTION, e -> {
				FileChooser chooser = new FileChooser();
				List<File> choosenFiles = chooser.showOpenMultipleDialog(csvFileSelectorPopUp.getStage());
				for (File file : choosenFiles) {
					this.filesListView.getItems().add(file);
				}
			});
			
			this.removeFileButton.addEventHandler(ActionEvent.ACTION, e -> {
				MultipleSelectionModel<File> selectedItems = this.filesListView.getSelectionModel();
				List<File> toRemove = new ArrayList<File>();
				for (File currentFile : selectedItems.getSelectedItems()) {
					toRemove.add(currentFile);
				}
				this.filesListView.getItems().removeAll(toRemove);
			});
			
			this.importButton.addEventHandler(ActionEvent.ACTION, a -> {
				CsvImportatorController controller = new CsvImportatorController(filesListView.getItems());
				try {
					parentSceneWrapper = new SceneWrapper("./ihm/importation/log-importation.fxml", "Importation des données", controller, parentSceneWrapper.getStage());
				} catch (Exception e) {
					e.printStackTrace();
				}
				controller.setSceneWrapperParent(parentSceneWrapper);
				parentSceneWrapper.getStage().show();
				csvFileSelectorPopUp.getStage().close();
			});
		}
		
		@Override
		public void setSceneWrapperParent(SceneWrapper s) {
			return;
			
		}

		@Override
		public SceneWrapper getSceneWrapperParent() {
			return null;
		}
	}
}
