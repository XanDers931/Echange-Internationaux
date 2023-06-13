package front.controller;

import java.io.File;
import java.util.List;

import front.FXMLScene;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
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
	 * A {@code Tooltip} for alreadyAffectedLabel.
	 */
	private Tooltip alreadyAffectedTip;
	
	/**
	 * A boolean to know we have to keep locker visible.
	 */
	private boolean keepLockerVisible;
	
	/**
	 * A boolean to know if details are currently visible.
	 */
	private boolean detailVisible;
	
	/**
	 * A {@code Tooltip} for locker.
	 */
	private Tooltip lockerTooltip;
	
	/**
	 * A {@code Tooltip} for showDetailSVG.
	 */
	private Tooltip showDetailTooltip;
	
	/**
	 * A {@code ObservableListBase<Teenager>} of non affected guests.
	 */
	private ObservableList<Teenager> nonAffectedGuests;
	
	/**
	 * A {@code ObservableListBase<Teenager>} of non affected hosts.
	 */
	private ObservableList<Teenager> nonAffectedHosts;
	
	/**
	 * the {@code AffectationController} who called that constructor.
	 */
	private AffectationController rootController;
	
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
	 * A {@code SVGPath} to show more detail about teens.
	 */
	@FXML SVGPath showDetailSVG;
	
	/**
	 * A {@code Label} container used to display host details.
	 */
	@FXML Label hostDetailLabel;
	
	/**
	 * A {@code Label} container used to display guest details.
	 */
	@FXML Label guestDetailLabel;
	
	/**
	 * A {@code HBox} container used to display host and guest details.
	 */
	@FXML HBox detailContainer;
	
	/**
	 * A {@code Label} container used to know if guest is already affected.
	 */
	@FXML Label alreadyAffectedLabel;
	
	/**
	 * A {@code Label} container used to display clickable questionMark.
	 */
	@FXML Label questionMarkLabel;
	
	
	
	/**
	 * AffectationRowController constructor.
	 * @param rootController, the {@code AffectationController} who called that constructor.
	 * @param affectation, the {@code Affectation} to manage.
	 * @param nonAffectedHosts, an {@code ObservableList} of non affected hosts.
	 * @param nonAffectedHosts, an {@code ObservableList} of non affected guests.
	 * @see Affectation
	 */
	public AffectationRowController(AffectationController rootController, Affectation affectation, ObservableList<Teenager> nonAffectedHosts, ObservableList<Teenager> nonAffectedGuests) {
		super(FXMLScene.AFFECTATION_ROW.getPath());
		this.currentAffectation = affectation;
		try {
			this.lockerImg = new Image(new File("./dev/res/img/lock-svgrepo-com.png").toURI().toURL().toString());
			this.unLockerImg = new Image(new File("./dev/res/img/lock-unlocked-svgrepo-com.png").toURI().toURL().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.keepLockerVisible = true;
		this.detailVisible = false;
		this.lockerTooltip = new Tooltip();
		this.alreadyAffectedTip = new Tooltip();
		this.alreadyAffectedTip.setFont(new Font(12));
		this.showDetailTooltip = new Tooltip("Afficher les détails");
		this.nonAffectedHosts = nonAffectedHosts;
		this.nonAffectedGuests = nonAffectedGuests;
		this.rootController = rootController;
		this.loadElement();
	}
	
	
	/** 
	 * That method is called by the {@code FXMLLoader}, it is used to add all event handler.
	 */
	@Override
	public void initialize() {
		this.guestChoiceList.setValue(this.currentAffectation.getGuest());
		this.setCrierions();
		this.hostField.setText(this.currentAffectation.getHost().toString());
		//guest choiceList listener
		this.guestChoiceList.valueProperty().addListener((arg, oldVal, newVal)-> {
			try {
				this.currentAffectation.setGuest(newVal);
				this.setCrierions();
				this.guestDetailLabel.setText(this.currentAffectation.getGuestDetail());
			} catch (SameTeenagerException e) {
				Alert alert = new Alert(AlertType.ERROR, e.getMessage());
				alert.showAndWait();
				e.printStackTrace();
				return;
			}
			//update non affected teens			
			if (oldVal == null && newVal != null) {
				//L'hôte et l'invité sont forcément affectés
				this.nonAffectedHosts.remove(this.currentAffectation.getHost());
				this.nonAffectedGuests.remove(newVal);
			} else if (oldVal != null && newVal == null) {
				//Vérif ancien guest, faut il le rajouter ?
				if (!this.currentAffectation.getCurrentExchange().isAffected(oldVal)) {
					this.nonAffectedGuests.add(oldVal);
				}
				//Hôte plus affecté
				this.nonAffectedHosts.add(this.currentAffectation.getHost());
			} else if (oldVal != null && newVal != null) {
				//Hôte toujours affecté
				//vérif ancien guest, faut il le rajouter ?
				if (!this.currentAffectation.getCurrentExchange().isAffected(oldVal)) {
					this.nonAffectedGuests.add(oldVal);
				}
				//nouveau guest forcément affecté
				this.nonAffectedGuests.remove(newVal);
			}
			//update already affected label for all
			this.updateAlreadyAffectedLabel();
			for (AffectationRowController rowController : this.rootController.getRowsControllers()) {
				if (rowController != this) {
					if (newVal != null) {
						if (newVal.equals(rowController.getCurrentAffectation().getGuest())) {
							rowController.updateAlreadyAffectedLabel();
						}
					}
					if (oldVal != null) {
						if (oldVal.equals(rowController.getCurrentAffectation().getGuest())) {
							rowController.updateAlreadyAffectedLabel();
						}
					}
				}
			}
		});
		//locker
		Tooltip.install(this.lockCanvas, this.lockerTooltip);
		if (this.currentAffectation.isLocked()) {
			this.lock();
		} else {
			this.unlock(false);
		}
		this.rootContainer.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
			this.lockCanvas.setVisible(true);
			this.showDetailSVG.setVisible(true);
		});
		this.rootContainer.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
			if (!keepLockerVisible) {
				this.lockCanvas.setVisible(false);
			}
			this.showDetailSVG.setVisible(false);
		});
		this.lockCanvas.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
			this.lockCanvas.getScene().setCursor(Cursor.HAND);
		});
		this.lockCanvas.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
			this.lockCanvas.getScene().setCursor(Cursor.DEFAULT);
		});
		this.lockCanvas.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			if (this.currentAffectation.isLocked()) {
				this.unlock(true);
			} else {
				this.lock();
			}
		});
		//show detail svg
		Tooltip.install(this.showDetailSVG, this.showDetailTooltip);
		this.showDetailSVG.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
			this.showDetailSVG.getScene().setCursor(Cursor.HAND);
		});
		this.showDetailSVG.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
			this.showDetailSVG.getScene().setCursor(Cursor.DEFAULT);
		});
		this.showDetailSVG.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			if (!this.detailVisible) {
				//show detail
				this.detailVisible = true;
				this.showDetailTooltip.setText("Masquer les détails");
				this.showDetailSVG.setRotate(90);
				this.detailContainer.setVisible(true);
				this.detailContainer.setMaxHeight(Control.USE_COMPUTED_SIZE);
			} else {
				//hide detail
				this.detailVisible = false;
				this.showDetailTooltip.setText("Afficher les détails");
				this.showDetailSVG.setRotate(0);
				this.detailContainer.setMaxHeight(0);
				this.detailContainer.setVisible(false);
			}
		});
		//details
		this.hostDetailLabel.setText(this.currentAffectation.getHostDetail());
		this.guestDetailLabel.setText(this.currentAffectation.getGuestDetail());
		//already affected img
		Tooltip.install(this.questionMarkLabel, alreadyAffectedTip);
		this.updateAlreadyAffectedLabel();
	}

	/**
	 * This method update criterions labels.
	 */
	private void setCrierions() {
		this.goodCriterionsLabel.setText(String.join(", ", this.currentAffectation.getGoodRequirements()) + "\n");
		this.badCriterionsLabel.setText(String.join(", ", this.currentAffectation.getBadRequirements()) + "\n");
	}
	
	/**
	 * This method lock the current affectation
	 */
	public void lock() {
		this.currentAffectation.setLocked(true);
		this.keepLockerVisible = true;
		this.lockCanvas.setVisible(true);
		this.guestChoiceList.setDisable(true);
		this.lockerTooltip.setText("Dévérouiller affectation");
		this.lockCanvas.getGraphicsContext2D().clearRect(0, 0, this.lockCanvas.getWidth(), this.lockCanvas.getHeight());
		this.lockCanvas.getGraphicsContext2D().drawImage(lockerImg, 0, 0, this.lockCanvas.getWidth(), this.lockCanvas.getHeight());
		this.rootContainer.setStyle("-fx-background-color:rgb(245,245,245)");
	}
	
	/**
	 * This method unlock the current affectation
	 */
	public void unlock(boolean setLockerVisible) {
		this.currentAffectation.setLocked(false);
		this.keepLockerVisible = false;
		this.lockCanvas.setVisible(setLockerVisible);
		this.guestChoiceList.setDisable(false);
		this.lockerTooltip.setText("Vérouiller affectation");
		this.lockCanvas.getGraphicsContext2D().clearRect(0, 0, this.lockCanvas.getWidth(), this.lockCanvas.getHeight());
		this.lockCanvas.getGraphicsContext2D().drawImage(unLockerImg, 0, 0, this.lockCanvas.getWidth(), this.lockCanvas.getHeight());
		this.rootContainer.setStyle("-fx-background-color:white");
	}
	
	/**
	 * This method update the already affected label
	 */
	public void updateAlreadyAffectedLabel() {
		//update already affected label
		if (this.currentAffectation.getGuest() != null) {
			List<Teenager> allAffectedWith = this.currentAffectation.getCurrentExchange().alreadyAffectedWith(this.currentAffectation.getGuest());
			if (allAffectedWith.size() > 1) {
				this.alreadyAffectedLabel.setText("Déjà affecté avec " + (allAffectedWith.size() - 1) + " autre(s) hôte(s)");
				String tip = "Affecté avec : ";
				for (Teenager teenager : allAffectedWith) {
					if (!teenager.equals(this.currentAffectation.getHost())) {
						tip += "\n" + teenager.getFirstName() + " " + teenager.getLastName();
					}
				}
				this.alreadyAffectedTip.setText(tip);
				this.alreadyAffectedLabel.setVisible(true);
				this.questionMarkLabel.setVisible(true);
			} else {
				//remove already affected label
				this.alreadyAffectedLabel.setVisible(false);
				this.questionMarkLabel.setVisible(false);
			}
		} else {
			//remove already affected label
			this.alreadyAffectedLabel.setVisible(false);
			this.questionMarkLabel.setVisible(false);
		}
	}

	/**
	 * @return the guestChoiceList
	 */
	public ChoiceBox<Teenager> getGuestChoiceList() {
		return guestChoiceList;
	}


	/**
	 * @return the currentAffectation
	 */
	public Affectation getCurrentAffectation() {
		return currentAffectation;
	}
	
	
}