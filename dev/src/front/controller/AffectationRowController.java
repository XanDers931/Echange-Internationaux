package front.controller;


import front.FXMLScene;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import voyages.Affectation;
import voyages.SameTeenagerException;
import voyages.Teenager;

/**
 * This {@code ElementController} is used to control an affectation row.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 */
public class AffectationRowController extends ElementController {
	
	/**
	 * Current {@code Affectation} to manage.
	 * @see Affectation
	 */
	private Affectation currentAffectation;
	
	/**
	 * AffectationRowController constructor.
	 * @param affectation, the {@code Affectation} to manage.
	 * @see Affectation
	 */
	public AffectationRowController(Affectation affectation) {
		super(FXMLScene.AFFECTATION_ROW.getPath());
		this.currentAffectation = affectation;
		this.loadElement();
	}
	
	
	/**
	 * A {@code TextField} used to store host.
	 */
	@FXML TextField hostField;
	
	/**
	 * A {@code ChoiceBox} used to store all guests.
	 */
	@FXML ChoiceBox<Teenager> guestChoiceList;
	
	/**
	 * A {@code Label} container used to display compatible criterions.
	 */
	@FXML Label goodCriterionsLabel;
	
	/**
	 * A {@code Label} container used to display incompatible criterions.
	 */
	@FXML Label badCriterionsLabel;
	
	
	/** 
	 * That method is called by the {@code FXMLLoader}, it is used to add all event handler.
	 */
	@Override
	public void initialize() {
		this.hostField.setText(this.currentAffectation.getHost().toString());
		this.guestChoiceList.setValue(this.currentAffectation.getGuest());
		this.setCrierions();
		//guest choiceList listener
		this.guestChoiceList.valueProperty().addListener((arg, oldVal, newVal)-> {
			try {
				this.currentAffectation.setGuest(newVal);
				this.setCrierions();
			} catch (SameTeenagerException e) {
				Alert alert = new Alert(AlertType.ERROR, e.getMessage());
				alert.showAndWait();
				e.printStackTrace();
				return;
			}
		});
	}
	
	/**
	 * That method update criterions labels.
	 */
	private void setCrierions() {
		this.goodCriterionsLabel.setText(String.join(", ", this.currentAffectation.getGoodRequirements()));
		this.badCriterionsLabel.setText(String.join(", ", this.currentAffectation.getBadRequirements()));
	}

	/**
	 * @return the guestChoiceList
	 */
	public ChoiceBox<Teenager> getGuestChoiceList() {
		return guestChoiceList;
	}
}
