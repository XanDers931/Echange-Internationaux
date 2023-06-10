package front.controller;

import front.FXMLScene;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import voyages.Affectation;
import voyages.CountryName;
import voyages.Exchange;
import voyages.Platform;
import voyages.SameCountryException;
import voyages.SameTeenagerException;

/** 
 * This {@code SceneController} is used to control the affectation scene.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 */
public class AffectationController extends SceneController {
	/**
	 * Current {@code Platform}.
	 * @see Platform
	 */
	private Platform currentPlatform;
	
	/**
	 * Current selected {@code Exchange}.
	 * @see Exchange
	 */
	private Exchange currentExchange;
	
	/**
	 * A {@code VBox} container used to store all affectation rows.
	 */
	@FXML VBox affectationContainer;
	
	/**
	 * A {@code ChoiceBox} used to select host country.
	 */
	@FXML ChoiceBox<CountryName> hostCountryChoiceBox;
	
	/**
	 * A {@code ChoiceBox} used to select guest country.
	 */
	@FXML ChoiceBox<CountryName> guestCountryChoiceBox;
	
	/**
	 * A {@code Button} used to save generate optimal affectations.
	 */
	@FXML Button optimalAffectationsButton;
	
	/**
	 * A {@code Button} used to save current platform.
	 */
	@FXML Button saveButton;
	
	
	/**
	 * A {@code MenuItem} used to go back to main menu.
	 */
	@FXML MenuItem backToMainMenu;
	
	
	/**
	 * A {@code MenuItem} used to import csv file.
	 */
	@FXML MenuItem importCsvFile;
	
	
	/**
	 * AffectationController constructor.
	 * @param stage, a {@Code Stage} used to show current Scene.
	 * @param p, the {@Code Platform} to use.
	 */
	public AffectationController(Stage stage, Platform p) {
		super(FXMLScene.AFFECTATION_MANAGER.getPath(), FXMLScene.AFFECTATION_MANAGER.getTitle(), stage);
		this.currentPlatform = p;
		this.updateStage();
	}
	
	/** 
	 * That method is called by the {@code FXMLLoader}, it is used to add all event handler.
	 */
	@Override
	public void initialize() {
		//Back to main menu
		this.backToMainMenu.addEventHandler(ActionEvent.ACTION, e -> {
			Alert alert = new Alert(AlertType.INFORMATION, "Souhaitez vous sauvegarder avant de retourner au menu principal ?", ButtonType.YES, ButtonType.NO);
			alert.showAndWait();
			if (alert.getResult().equals(ButtonType.YES)) {
				this.saveButton.fire();
			}
			SceneController controller = new MainMenuController(this.stage);
			controller.getStage().show();
		});
		//import csv file
		this.importCsvFile.addEventHandler(ActionEvent.ACTION, e -> {
			SceneController controller = new FileSelectorController(this.stage, this);
			controller.getStage().show();
		});
		List<CountryName> representedCountries = this.currentPlatform.getAllRepresentedCountry();
		this.hostCountryChoiceBox.getItems().addAll(representedCountries);
		this.guestCountryChoiceBox.getItems().addAll(representedCountries);
		//Host country value change listener
		this.hostCountryChoiceBox.valueProperty().addListener((arg, oldVal, newVal)-> {
			if (this.hostCountryChoiceBox.getValue() == null) {
				this.optimalAffectationsButton.setDisable(true);
				this.saveButton.setDisable(true);
			}
			//Delete in guest choiceBox
			if (this.guestCountryChoiceBox.getValue() == newVal) {
				this.guestCountryChoiceBox.setValue(null);
			}
			this.guestCountryChoiceBox.getItems().remove(newVal);
			//Add old value in guest choiceBox if necessary
			if (!this.guestCountryChoiceBox.getItems().contains(oldVal) && oldVal != null) {
				this.guestCountryChoiceBox.getItems().add(oldVal);
			}
			loadExchange();
		});
		//Guest country value change listener
		this.guestCountryChoiceBox.valueProperty().addListener(l -> {
			if (this.guestCountryChoiceBox.getValue() == null) {
				this.optimalAffectationsButton.setDisable(true);
				this.saveButton.setDisable(true);
			}
			loadExchange();
		});
		//Optimal affectations listener
		this.optimalAffectationsButton.addEventHandler(ActionEvent.ACTION, a -> {
			if (this.currentExchange != null) {
				try {
					this.currentExchange.setOptimalAffectation(this.currentPlatform);
				} catch (SameTeenagerException e) {
					Alert alert = new Alert(AlertType.ERROR, e.getMessage());
					alert.showAndWait();
					e.printStackTrace();
					return;
				}
				this.updateAffectations();
			}
		});
		//Save button listener
		this.saveButton.addEventHandler(ActionEvent.ACTION, a -> {
			//try to save current platform.
			try {
				this.currentPlatform.save();
				Alert alert = new Alert(AlertType.INFORMATION, "La session a bien été enregistrée");
				alert.showAndWait();
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.ERROR, e.getMessage());
				alert.showAndWait();
				e.printStackTrace();
			}
		});
	}
	
	/**
	 * That method load current exchange, based on host and guest selecteds countries.
	 */
	private void loadExchange() {
		if (this.hostCountryChoiceBox.getValue() == null || this.guestCountryChoiceBox.getValue() == null) {
			this.currentExchange = null;
		} else {
			this.optimalAffectationsButton.setDisable(false);
			this.saveButton.setDisable(false);
			//try to add exchange or get it if already exists
			try {
				this.currentExchange = this.currentPlatform.addExchange(this.hostCountryChoiceBox.getValue(), this.guestCountryChoiceBox.getValue());
			} catch (SameCountryException e) {
				Alert alert = new Alert(AlertType.ERROR, e.getMessage());
				alert.showAndWait();
				e.printStackTrace();
				return;
			} catch (SameTeenagerException e) {
				Alert alert = new Alert(AlertType.ERROR, e.getMessage());
				alert.showAndWait();
				e.printStackTrace();
				return;
			}	
		}
		this.updateAffectations();
	}
	
	/**
	 * That method update the screen to display all affectations
	 */
	private void updateAffectations() {
		this.affectationContainer.getChildren().clear();
		if (this.currentExchange == null) {
			return;
		}
		for (Affectation currentCouple : this.currentExchange.getAffectations()) {
			AffectationRowController currentElementController = new AffectationRowController(currentCouple);
			currentElementController.getGuestChoiceList().getItems().add(null);
			currentElementController.getGuestChoiceList().getItems().addAll(this.currentPlatform.getTeenagersByCountry(this.currentExchange.getGuestCountry()));
			this.affectationContainer.getChildren().add(currentElementController.getRoot());
		}
	}
}
