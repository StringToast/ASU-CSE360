package guiSetOneTimePassword;

import database.Database;
import entityClasses.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//Test Git Changes

/*******
 * <p> Title: ViewSetOnetimePassword Class. </p>
 * 
 * <p> Description: The JavaFX-based Set One-Time Password page.  This page allows
 * an administrator to select a user and assign a temporary one-time password.</p>
 */
public class ViewSetOneTimePassword {

	private static Database theDatabase = applicationMain.FoundationsMain.database;

	private static ComboBox<String> combobox_UserName = new ComboBox<String>();
	private static PasswordField text_OneTimePassword = new PasswordField();

	private static Button button_SetPassword = new Button("Set One-Time Password");
	private static Button button_Return = new Button("Return to Admin Home");

	private static Alert alertError = new Alert(Alert.AlertType.ERROR);
	private static Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);

	public static void displaySetOnetimePassword(Stage theStage, User theUser) {

		Label label_Title = new Label("Set One-Time Password");
		label_Title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

		combobox_UserName.getItems().clear();
		combobox_UserName.getItems().addAll(theDatabase.getUserList());
		combobox_UserName.getSelectionModel().selectFirst();

		text_OneTimePassword.setPromptText("Enter temporary password");

		button_SetPassword.setOnAction(event -> setOneTimePassword());
		button_Return.setOnAction(event -> guiAdminHome.ViewAdminHome.displayAdminHome(theStage, theUser));

		VBox root = new VBox(15);
		root.setAlignment(Pos.CENTER);
		root.setPrefWidth(500);
		root.setPrefHeight(350);

		root.getChildren().addAll(
				label_Title,
				new Label("Select User:"),
				combobox_UserName,
				new Label("One-Time Password:"),
				text_OneTimePassword,
				button_SetPassword,
				button_Return
		);

		Scene scene = new Scene(root, 500, 350);
		theStage.setScene(scene);
		theStage.setTitle("Set One-Time Password");
		theStage.show();
	}

	private static void setOneTimePassword() {
		String userName = combobox_UserName.getValue();
		String password = text_OneTimePassword.getText();

		if (userName == null || userName.equals("<Select a User>")) {
			showError("User Selection Error", "Please select a valid user.");
			return;
		}

		if (password == null || password.length() == 0) {
			showError("Password Error", "The one-time password cannot be empty.");
			return;
		}

		if (password.length() > 64) {
			showError("Password Error", "The one-time password is too long.");
			return;
		}

		boolean result = theDatabase.setOneTimePassword(userName, password);

		if (result) {
			alertInfo.setTitle("One-Time Password Set");
			alertInfo.setHeaderText("Success");
			alertInfo.setContentText("A one-time password has been set for " + userName + ".");
			alertInfo.showAndWait();

			text_OneTimePassword.setText("");
			combobox_UserName.getSelectionModel().selectFirst();
		} else {
			showError("Database Error", "The one-time password could not be set.");
		}
	}

	private static void showError(String header, String content) {
		alertError.setTitle("Error");
		alertError.setHeaderText(header);
		alertError.setContentText(content);
		alertError.showAndWait();
	}
}
