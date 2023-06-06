package front.controller;

import java.io.IOException;
import java.net.MalformedURLException;

import front.FXMLScene;
import front.SceneWrapper;
import javafx.stage.Modality;

/** 
 * This abstract class set the minimum requirement to be a FXML Stage controller
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @version 0.0.1, 06/06/23
 */
public abstract class Controller {
	
	/**
	 * That static method return a Yes/No popup SceneWrapper
	 * @param question, a {@code String} representing the question to ask.
	 * @param hint, a {@code String} representing the hint to answer the question.
	 * @return a new {@code SceneWrapper}
	 * @throws MalformedURLException
	 * @throws IOException
	 * @see YesNoController
	 */
	public static YesNoController getYesNoPopUp(String question, String hint) throws MalformedURLException, IOException {
		YesNoController result = new YesNoController(question, hint);
		SceneWrapper scene = new SceneWrapper();
		scene.updateScene(FXMLScene.YES_NO_POPUP.getPath(), question, result);
		scene.getStage().initModality(Modality.APPLICATION_MODAL);
		return result;
	}
	
	/** 
	 * The {@code SceneWrapper} that is controlled by this controller.
	 * @see SceneWrapper
	 */
	protected SceneWrapper parentSceneWrapper;
	
	/** 
	 * The setter of parentSceneWrapper
	 * @param parentScene, the {@code SceneWrapper} that is controlled by this controller.
	 */
	public final void setSceneWrapperParent(SceneWrapper parentScene) {
		this.parentSceneWrapper = parentScene;
	}
	
	/** 
	 * The getter of parentSceneWrapper
	 * @return the {@code SceneWrapper} that is controlled by this controller.
	 */
	public final SceneWrapper getSceneWrapperParent() {
		return this.parentSceneWrapper;
	}
	
	/** 
	 * That method is called by the {@code FXMLLoader}, it generally used to add all the event handler.
	 */
	public abstract void initialize();
}
