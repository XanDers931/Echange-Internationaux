/**
 * 
 */
package front.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/** This {@code Controller} is used to display a yes no popup and get the result.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @version 0.0.1, 06/06/23
 */
public class YesNoController extends Controller {
	
	/**
	 * The question to ask.
	 */
	private String question;
	
	/**
	 * Hint to help answer the question.
	 */
	private String hint;
	
	/**
	 * A {@code boolean} representing the result.
	 */
	private boolean result;
	
	/**
	 * A {@code Label} representing the question label.
	 */
	@FXML Label questionLabel;
	
	/**
	 * A {@code Label} representing the hint label.
	 */
	@FXML Label hintLabel;
	
	/**
	 * A {@code Button} representing the yes button.
	 */
	@FXML Button yesButton;
	
	/**
	 * A {@code Button} representing the no button.
	 */
	@FXML Button noButton;
	
	/**
	 * The constructor of {@code YesNoController}.
	 * @param question, a {@code String} representing the question to ask.
	 * @param hint, a {@code String} representing the hint to answer the question.
	 */
	public YesNoController(String question, String hint) {
		this.question = question;
		this.hint = hint;
	}
	
	/**
	 * Set up all event handler.
	 */
	@Override
	public void initialize() {
		this.questionLabel.setText(this.question);
		this.hintLabel.setText(this.hint);
		this.yesButton.addEventHandler(ActionEvent.ACTION, e -> {
			this.result = true;
			this.parentSceneWrapper.getStage().close();
		});
		this.noButton.addEventHandler(ActionEvent.ACTION, e -> {
			this.result = false;
			this.parentSceneWrapper.getStage().close();
		});
	}
	
	/**
	 * @return the user's choice, true or false.
	 */
	public boolean getResult() {
		return this.result;
	}

}
