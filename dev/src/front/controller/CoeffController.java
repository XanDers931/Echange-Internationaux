package front.controller;

import front.FXMLScene;
import graphes.AffectationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Tooltip;

/**The {@code CoeffController} class is used to control the coefficient manager scene.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @version 0.0.3, 06/11/23
 */
public class CoeffController extends SceneController {

    /**A {@code Button} used to quit the scene.
     */
    @FXML private Button quitbutton;

    /**A {@code TextField} used to set the coefficient of the animal allergy.
     */
    @FXML private TextField coeffAnimal;

    /**A {@code TextField} used to set the coefficient of the diet.
     */
    @FXML private TextField coeffDiet;

    /**A {@code TextField} used to set the coefficient of the age.
     */
    @FXML private TextField coeffAge;

    /**A {@code TextField} used to set the coefficient of the gender.
     */
    @FXML private TextField coeffSexe;

    /**A {@code TextField} used to set the coefficient of the history.
     */
    @FXML private TextField coeffHistory;

    /**A {@code TextField} used to set the coefficient of the hobbies.
     */
    @FXML private TextField coeffHobbies;
    
    /**CoeffController constructor.
     * @param stage a {@code Stage} used to show the current Scene.
     */
    public CoeffController(Stage stage) {
        super(FXMLScene.COEFF_MANAGER, stage);
        this.updateStage();
        this.coeffAnimal.setText(Integer.toString(AffectationUtil.getCoefficientAnimalAllergy()));
        this.coeffDiet.setText(Integer.toString(AffectationUtil.getCoefficientDiet()));
        this.coeffAge.setText(Integer.toString(AffectationUtil.getCoefficientAge()));
        this.coeffSexe.setText(Integer.toString(AffectationUtil.getCoefficientGender()));
        this.coeffHistory.setText(Integer.toString(AffectationUtil.getCoefficientHistory()));
        this.coeffHobbies.setText(Integer.toString(AffectationUtil.getCoefficientHobbies()));
    }

    /**Initialize the scene and add all the event handler needed.
     */
    @Override
    public void initialize() {
    	Tooltip tip = new Tooltip("Veuillez saisir une valeur entre 0 et 10 non compris");
    	this.coeffAnimal.setTooltip(tip);
    	this.coeffDiet.setTooltip(tip);
    	this.coeffAge.setTooltip(tip);
    	this.coeffSexe.setTooltip(tip);
    	this.coeffHistory.setTooltip(tip);
    	this.coeffHobbies.setTooltip(tip);
        this.quitbutton.addEventHandler(ActionEvent.ACTION, a -> {
            if (!this.coeffAnimal.getText().equals("")) {
                AffectationUtil.setCoefficientAnimalAllergy(Integer.parseInt(this.coeffAnimal.getText()));
            }
            if (!this.coeffDiet.getText().equals("")) {
                AffectationUtil.setCoefficientDiet(Integer.parseInt(this.coeffDiet.getText()));
            }
            if (!this.coeffAge.getText().equals("")) {
                AffectationUtil.setCoefficientAge(Integer.parseInt(this.coeffAge.getText()));
            }
            if (!this.coeffSexe.getText().equals("")) {
                AffectationUtil.setCoefficientGender(Integer.parseInt(this.coeffSexe.getText()));
            }
            if (!this.coeffHistory.getText().equals("")) {
                AffectationUtil.setCoefficientHistory(Integer.parseInt(this.coeffHistory.getText()));
            }
            if (!this.coeffHobbies.getText().equals("")) {
                AffectationUtil.setCoefficientHobbies(Integer.parseInt(this.coeffHobbies.getText()));
            }
            this.stage.close();
        });
    }

    /**Check if the text in the textfield is a number between 1 and 10.
     */
    public void numberTest() {
        if (!this.coeffAnimal.getText().matches("^[1-9]{0,1}+$|^$")) {
            this.coeffAnimal.setText("");
        }
        if (!this.coeffDiet.getText().matches("^[1-9]{0,1}+$|^$")) {
            this.coeffDiet.setText("");
        }
        if (!this.coeffAge.getText().matches("^[1-9]{0,1}+$|^$")) {
            this.coeffAge.setText("");
        }
        if (!this.coeffSexe.getText().matches("^[1-9]{0,1}+$|^$")) {
            this.coeffSexe.setText("");
        }
        if (!this.coeffHistory.getText().matches("^[1-9]{0,1}+$|^$")) {
            this.coeffHistory.setText("");
        }
        if (!this.coeffHobbies.getText().matches("^[1-9]{0,1}+$|^$")) {
            this.coeffHobbies.setText("");
        }
    }
}