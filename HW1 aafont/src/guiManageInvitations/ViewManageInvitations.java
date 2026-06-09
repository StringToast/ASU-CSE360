package guiManageInvitations;

import database.Database;
import entityClasses.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Test Git Change
/*******
 * <p> Title: ViewManageInvitations Class. </p>
 * 
 * <p> Description: The JavaFX-based Manage Invitations page.  This page allows
 * an administrator to view outstanding invitation codes, delete a selected
 * invitation, and remove expired invitations.</p>
 */
public class ViewManageInvitations {

	private static Database theDatabase = applicationMain.FoundationsMain.database;

	private static ListView<String> invitationList = new ListView<String>();

	private static Button button_DeleteInvitation = new Button("Delete Selected Invitation");
	private static Button button_RemoveExpired = new Button("Remove Expired Invitations");
	private static Button button_Return = new Button("Return to Admin Home");

	private static Alert alertError = new Alert(Alert.AlertType.ERROR);
	private static Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);

	public static void displayManageInvitations(Stage theStage, User theUser) {

		Label label_Title = new Label("Manage Invitations");
		label_Title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

		refreshInvitationList();

		button_DeleteInvitation.setOnAction(event -> deleteSelectedInvitation());
		button_RemoveExpired.setOnAction(event -> removeExpiredInvitations());
		button_Return.setOnAction(event -> guiAdminHome.ViewAdminHome.displayAdminHome(theStage, theUser));

		VBox root = new VBox(15);
		root.setAlignment(Pos.CENTER);
		root.setPrefWidth(700);
		root.setPrefHeight(450);

		root.getChildren().addAll(
				label_Title,
				new Label("Outstanding Invitations:"),
				invitationList,
				button_DeleteInvitation,
				button_RemoveExpired,
				button_Return
		);

		Scene scene = new Scene(root, 700, 450);
		theStage.setScene(scene);
		theStage.setTitle("Manage Invitations");
		theStage.show();
	}

	private static void refreshInvitationList() {
		invitationList.getItems().clear();

		for (String invitation : theDatabase.getInvitationList()) {
			invitationList.getItems().add(invitation);
		}
	}

	private static void deleteSelectedInvitation() {
		String selectedInvitation = invitationList.getSelectionModel().getSelectedItem();

		if (selectedInvitation == null) {
			alertError.setTitle("Selection Error");
			alertError.setHeaderText("No Invitation Selected");
			alertError.setContentText("Please select an invitation to delete.");
			alertError.showAndWait();
			return;
		}

		boolean result = theDatabase.deleteInvitation(selectedInvitation);

		if (result) {
			alertInfo.setTitle("Invitation Deleted");
			alertInfo.setHeaderText("Success");
			alertInfo.setContentText("The invitation was deleted.");
			alertInfo.showAndWait();
			refreshInvitationList();
		} else {
			alertError.setTitle("Delete Error");
			alertError.setHeaderText("Unable to Delete Invitation");
			alertError.setContentText("The selected invitation could not be deleted.");
			alertError.showAndWait();
		}
	}

	private static void removeExpiredInvitations() {
		int numberRemoved = theDatabase.removeExpiredInvitations();

		alertInfo.setTitle("Expired Invitations Removed");
		alertInfo.setHeaderText("Completed");
		alertInfo.setContentText(numberRemoved + " expired invitation(s) removed.");
		alertInfo.showAndWait();

		refreshInvitationList();
	}
}
