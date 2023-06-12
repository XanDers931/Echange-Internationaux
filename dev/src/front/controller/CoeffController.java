package front.controller;

import front.FXMLScene;
import graphes.AffectationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CoeffController extends SceneController {

    @FXML private Button quitbutton;

    @FXML private TextField coeffAnimal;

    @FXML private TextField coeffDiet;

    @FXML private TextField coeffAge;

    @FXML private TextField coeffSexe;

    @FXML private TextField coeffHistory;

    @FXML private TextField coeffHobbies;
    
    public CoeffController(Stage stage) {
        super(FXMLScene.COEFF_MANAGER.getPath(), FXMLScene.COEFF_MANAGER.getTitle(), stage);
        this.updateStage();
    }

    @Override
    public void initialize() {
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