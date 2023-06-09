package front.controller;

import back.task.ImportCsvTask;
import front.FXMLScene;

import java.io.File;
import java.util.Collection;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import voyages.Platform;

/**
 * This {@code SceneController} is used to control the csv importation scene.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 */
public class CsvImportatorController extends SceneController {
	
	/** 
	 * The platform used to perform the import.
	 */
	private Platform currenPlatform;
	
	/** 
	 * A {@Thread} used to perform back operations, such as import teenagers from csv
	 * @see Thread
	 */
	private Thread secondaryThread;
	
	/** 
	 * A {@Task} used to exchanges datas between back operation and JavaFX thread.
	 * @see Task
	 */
	private ImportCsvTask task;
	
	/** 
	 * A {@code SimpleStringProperty} used to store back operation log, and to display on Visual window.
	 */
	private SimpleStringProperty logText;
	
	/** 
	 * Title of the scene
	 */
	@FXML Label title;
	
	/**
	 * A {@code TextArea} used to display some log informations
	 */
	@FXML TextArea logArea;
	
	/**
	 * A {@Button} to go back to main Menu
	 */
	@FXML Button backButton;
	
	/**
	 * A {@Button} to go to the next step
	 */
	@FXML Button nextButton;
	
	/** 
	 * @param filesToImport a {@code Collection<? extends File>} with all files used to import teenagers.
	 * @param generateLogFile a {@code boolean} to know if the controller has to generate log files.
	 */
	public CsvImportatorController(Stage stage, Collection<? extends File> filesToImport, boolean generateLogFile) {
		super(FXMLScene.IMPORT_CSV.getPath(), FXMLScene.IMPORT_CSV.getTitle(), stage);
		this.currenPlatform = new Platform();
		this.logText = new SimpleStringProperty();
		this.task = new ImportCsvTask(this.currenPlatform, filesToImport, generateLogFile);
		this.updateStage();
	}
	
	/**
	 * Add all the event handler needed
	 */
	public void initialize() {
		//Go back to menu
		this.backButton.addEventHandler(ActionEvent.ACTION, a -> {
			//Stop secondary thread
			this.secondaryThread.interrupt();
			//Update the Stage
			SceneController controller = new MainMenuController(this.stage);
			controller.getStage().show();
		});
		
		//Next step button
		this.nextButton.addEventHandler(ActionEvent.ACTION, e -> {
			SceneController controller = new AffectationController(this.stage, this.currenPlatform);
			controller.getStage().show();
		});
		
		//Add a listener to update the log TextArea
		this.logText.addListener(c -> {
			if (this.logText.getValue() != null && !this.logText.getValue().isEmpty()) {
				if (!this.logArea.getText().isEmpty()) {
					this.logArea.appendText("\n");
				}
				this.logArea.appendText(this.logText.getValue());
			}
		});
		
		//When the task succeed
		this.task.setOnSucceeded(e -> {
			this.title.setText("Success");
			this.nextButton.setDisable(false);
			this.nextButton.setDefaultButton(true);
		});
		
		//Bind logTextValue
		this.logText.bind(this.task.messageProperty());
		this.secondaryThread = new Thread(this.task);
		//start the import file task
		this.secondaryThread.start();
	}
}
