package guiRemoveUser;

import java.util.Optional;
import javafx.scene.control.ButtonType;
import database.Database;

/*******
 * <p> Title: ControllerDeleteUser Class. </p>
 * 
 * <p> Description: The Java/FX-based Delete User Page.  This class provides the controller
 * actions based on the user's use of the JavaFX GUI widgets defined by the View class.
 * 
 * An admin selects a user from the ComboBox and presses the Delete button.  Before a user is
 * removed, two safety checks are performed: an admin may not delete their own account, and the
 * system must always retain at least one admin.  When neither check blocks the action, the admin
 * is asked to confirm the delete before it takes place.
 * 
 * The class has been written assuming that the View or the Model are the only class methods that
 * can invoke these methods.  This is why each has been declared at "protected".  Do not change any
 * of these methods to public.</p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2025 </p>
 * 
 * @author Lynn Robert Carter
 * 
 * @version 1.00		2025-08-20 Initial version
 *  
 */

public class ControllerRemoveUser {
	
	/*-********************************************************************************************

	User Interface Actions for this page
	
	This controller is not a class that gets instantiated.  Rather, it is a collection of protected
	static methods that can be called by the View (which is a singleton instantiated object) and 
	the Model is often just a stub, or will be a singleton instantiated object.
	
	 */

	/**
	 * Default constructor is not used.
	 */
	public ControllerRemoveUser() {
	}
	
	// Reference for the in-memory database so this package has access
	private static Database theDatabase = applicationMain.FoundationsMain.database;		

	/**********
	 * <p> Method: doSelectUser() </p>
	 * 
	 * <p> Description: This method uses the ComboBox widget and fetches which item in the ComboBox
	 * was selected (a user in this case), recording it as the user that will be deleted when the
	 * admin presses the Delete button.  Unlike the Add/Remove Roles page, no account details are
	 * fetched here because the only detail needed (whether the user is an admin) is not required
	 * until the moment the delete is actually requested. </p>
	 * 
	 */
	protected static void doSelectUser() {
		viewRemoveUser.theSelectedUser =
				(String) viewRemoveUser.combobox_SelectUser.getValue();
	}
	
	
	/**********
	 * <p> Method: performDeleteUser() </p>
	 * 
	 * <p> Description: This method removes the selected user from the system.  Before doing so it
	 * performs two safety checks and then asks the admin to confirm.  The two checks exist because
	 * removing the wrong account could lock everyone out of admin functions: an admin may not
	 * delete their own account (some other admin must do it), and the last remaining admin may not
	 * be deleted so that admin work can always be performed. </p>
	 * 
	 */
	protected static void performDeleteUser() {
		
		// Determine which user was selected.  The first entry in the list is the
		// "<Select a User>" prompt, not an actual user.
		viewRemoveUser.theSelectedUser =
				(String) viewRemoveUser.combobox_SelectUser.getValue();
		
		// If the prompt is still selected, there is no user to delete, so do nothing
		if (viewRemoveUser.theSelectedUser == null ||
				viewRemoveUser.theSelectedUser.compareTo("<Select a User>") == 0)
			return;
		
		// Safety check 1: An admin may not delete their own account.  Requiring some other admin
		// to do it guarantees an admin remains available to perform admin work.
		if (viewRemoveUser.theSelectedUser.compareTo(
				viewRemoveUser.theUser.getUserName()) == 0) {
			viewRemoveUser.alertDeleteError.setContentText(
					"You cannot delete your own account. Another admin must do this.");
			viewRemoveUser.alertDeleteError.showAndWait();
			return;
		}
		
		// Safety check 2: The system must always retain at least one admin.  Fetch the selected
		// user's details so we can see if they are an admin, and if so, that they are not the last
		// one.
		theDatabase.getUserAccountDetails(viewRemoveUser.theSelectedUser);
		if (theDatabase.getCurrentAdminRole() && theDatabase.getNumberOfAdmins() <= 1) {
			viewRemoveUser.alertDeleteError.setContentText(
					"This is the only admin. The system must always have at least one admin.");
			viewRemoveUser.alertDeleteError.showAndWait();
			return;
		}
		
		// Confirm the delete with the admin before it takes place (the "Are you sure?")
		viewRemoveUser.alertConfirmDelete.setContentText(
				"Delete the account for \"" + viewRemoveUser.theSelectedUser + "\"?");
		Optional<ButtonType> result = viewRemoveUser.alertConfirmDelete.showAndWait();
		
		// Only proceed if the admin explicitly acknowledged the confirmation
		if (result.isPresent() && result.get() == ButtonType.OK) {
			
			// If the database successfully removed the user, refresh the page so the deleted user
			// no longer appears in the list
			if (theDatabase.deleteUser(viewRemoveUser.theSelectedUser)) {
				viewRemoveUser.displayDeleteUser(viewRemoveUser.theStage,
						viewRemoveUser.theUser);
			}
		}
	}
	
	
	/**********
	 * <p> Method: performReturn() </p>
	 * 
	 * <p> Description: This method returns the user (who must be an Admin as only admins are the
	 * only users who have access to this page) to the Admin Home page. </p>
	 * 
	 */
	protected static void performReturn() {
		guiAdminHome.ViewAdminHome.displayAdminHome(viewRemoveUser.theStage,
				viewRemoveUser.theUser);
	}
	
	
	/**********
	 * <p> Method: performLogout() </p>
	 * 
	 * <p> Description: This method logs out the current user and proceeds to the normal login
	 * page where existing users can log in or potential new users with a invitation code can
	 * start the process of setting up an account. </p>
	 * 
	 */
	protected static void performLogout() {
		guiUserLogin.ViewUserLogin.displayUserLogin(viewRemoveUser.theStage);
	}
	
	
	/**********
	 * <p> Method: performQuit() </p>
	 * 
	 * <p> Description: This method terminates the execution of the program.  It leaves the
	 * database in a state where the normal login page will be displayed when the application is
	 * restarted.</p>
	 * 
	 */
	protected static void performQuit() {
		System.exit(0);
	}
}