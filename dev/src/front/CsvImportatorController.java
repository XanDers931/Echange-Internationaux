package front;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import voyages.Platform;

public class CsvImportatorController implements Controller {
	private SceneWrapper parentSceneWrapper;
	private List<File> filesToImport;
	private Platform currenPlatform;
	@FXML TextArea logArea;
	@FXML Button backButton;
	
	public CsvImportatorController(Collection<? extends File> filesToImport) {
		this.filesToImport = new ArrayList<File>();
		this.filesToImport.addAll(filesToImport);
		this.currenPlatform = new Platform();
	}

	@Override
	public void initialize() throws Exception {
		this.backButton.addEventHandler(ActionEvent.ACTION, a -> {
			MainMenuController controller = new MainMenuController();
			try {
				SceneWrapper main = new SceneWrapper("./ihm/main/menu.fxml", "Menu principal", controller, parentSceneWrapper.getStage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		for (File file : filesToImport) {
			logArea.setText(logArea.getText() + "\n" + file.getName());
		}
	}

	@Override
	public void setSceneWrapperParent(SceneWrapper s) {
		this.parentSceneWrapper = s;
	}

	@Override
	public SceneWrapper getSceneWrapperParent() {
		return this.parentSceneWrapper;
	}

}
