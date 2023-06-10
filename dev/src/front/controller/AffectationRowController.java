package front.controller;

import java.io.File;
import java.net.MalformedURLException;

import front.FXMLScene;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Cursor;
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
	 * Locker img.
	 */
	private Image lockerImg;
	
	/**
	 * Unlocker img.
	 */
	private Image unLockerImg;
	
	/**
	 * A boolean to know we have to keep locker visible
	 */
	private boolean keepLockerVisible;
	
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
	 * A {@code Canvas} container used to display lock img.
	 */
	@FXML Canvas lockCanvas;
	
	/**
	 * The {@code VBox} root container.
	 */
	@FXML VBox rootContainer;
	
	/**
	 * AffectationRowController constructor.
	 * @param affectation, the {@code Affectation} to manage.
	 * @see Affectation
	 */
	public AffectationRowController(Affectation affectation) {
		super(FXMLScene.AFFECTATION_ROW.getPath());
		this.currentAffectation = affectation;
		try {
			this.lockerImg = new Image(new File("./dev/res/img/lock-svgrepo-com.png").toURI().toURL().toString());
			this.unLockerImg = new Image(new File("./dev/res/img/lock-unlocked-svgrepo-com.png").toURI().toURL().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.keepLockerVisible = true;
		this.loadElement();
	}
	
	
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
		//locker
		if (this.currentAffectation.isLocked()) {
			this.lockCanvas.setVisible(true);
			this.lock();
		} else {
			this.lockCanvas.setVisible(false);
			this.unlock();
		}
		this.rootContainer.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
			this.lockCanvas.setVisible(true);
		});
		this.rootContainer.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
			if (!keepLockerVisible) {
				this.lockCanvas.setVisible(false);
			}
		});
		this.lockCanvas.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
			this.lockCanvas.getScene().setCursor(Cursor.HAND);
		});
		this.lockCanvas.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
			this.lockCanvas.getScene().setCursor(Cursor.DEFAULT);
		});
		this.lockCanvas.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			if (this.currentAffectation.isLocked()) {
				this.unlock();
			} else {
				this.lock();
			}
		});
	}
	
	/**
	 * This method update criterions labels.
	 */
	private void setCrierions() {
		this.goodCriterionsLabel.setText(String.join(", ", this.currentAffectation.getGoodRequirements()));
		this.badCriterionsLabel.setText(String.join(", ", this.currentAffectation.getBadRequirements()));
	}
	
	/**
	 * This method lock the current affectation
	 */
	private void lock() {
		this.currentAffectation.setLocked(true);
		this.keepLockerVisible = true;
		this.guestChoiceList.setDisable(true);
		this.lockCanvas.getGraphicsContext2D().clearRect(0, 0, this.lockCanvas.getWidth(), this.lockCanvas.getHeight());
		this.lockCanvas.getGraphicsContext2D().drawImage(lockerImg, 0, 0, this.lockCanvas.getWidth(), this.lockCanvas.getHeight());
	}
	
	/**
	 * This method unlock the current affectation
	 */
	private void unlock() {
		this.currentAffectation.setLocked(false);
		this.keepLockerVisible = false;
		this.guestChoiceList.setDisable(false);
		this.lockCanvas.getGraphicsContext2D().clearRect(0, 0, this.lockCanvas.getWidth(), this.lockCanvas.getHeight());
		this.lockCanvas.getGraphicsContext2D().drawImage(unLockerImg, 0, 0, this.lockCanvas.getWidth(), this.lockCanvas.getHeight());
	}

	/**
	 * @return the guestChoiceList
	 */
	public ChoiceBox<Teenager> getGuestChoiceList() {
		return guestChoiceList;
	}
}
