package front.controller;

import front.FXMLScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CoeffController extends SceneController {

    @FXML private Button quitbutton;
    
    public CoeffController(Stage stage) {
        super(FXMLScene.COEFF_MANAGER.getPath(), FXMLScene.COEFF_MANAGER.getTitle(), stage);
        this.updateStage();
    }

    @Override
    public void initialize() {
        this.quitbutton.addEventHandler(ActionEvent.ACTION, a -> {
            this.stage.close();
        });
    }
}