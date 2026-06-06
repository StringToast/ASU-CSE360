package guiListUsers;

import java.util.List;
import database.Database;

/*******
 * <p> Title: ControllerListUsers Class. </p>
 * 
 * <p> Description: The Java/FX-based List Users Page.  This class provides the controller
 * actions based on the user's use of the JavaFX GUI widgets defined by the View class.
 * 
 * This page is read-only.  It gathers every user in the system and assembles a readable listing
 * showing each account's username, name, email address, and roles so an admin can review the
 * users of the system.
 * 
 * The class has been written assuming that the View or the Model are the only class methods that
 * can invoke these methods.  This is why each has been declared at "protected".  Do not change any
 * of these methods to public.</p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2025 </p>
 * 
 * @author Alfredo Afont
 * 
 * @version 1.00		2025-08-20 Initial version
 *  
 */

public class ControllerListUsers {
	
	/*-********************************************************************************************

	User Interface Actions for this page
	
	This controller is not a class that gets instantiated.  Rather, it is a collection of protected
	static methods that can be called by the View (which is a singleton instantiated object) and 
	the Model is often just a stub, or will be a singleton instantiated object.
	
	 */

	/**
	 * Default constructor is not used.
	 */
	public ControllerListUsers() {
	}
	
	// Reference for the in-memory database so this package has access
	private static Database theDatabase = applicationMain.FoundationsMain.database;		

	/**********
	 * <p> Method: buildUserListing() </p>
	 * 
	 * <p> Description: This method gathers every user in the system and assembles a single 
	 * string with one block per user.  For each user it shows the username, the full
	 * name, the email address, and the roles played.
	 * </p>
	 * 
	 * @return a String containing the formatted listing of all users
	 * 
	 */
	protected static String buildUserListing() {
		StringBuilder listing = new StringBuilder();
		
		// Fetch the list of all usernames in the system
		List<String> userList = theDatabase.getUserList();
		
		for (String username : userList) {
			
			// The shared user list is headed by a prompt that is not an actual
			// user, so skip it when building the listing
			if (username.compareTo("<Select a User>") == 0)
				continue;
			
			// Load this user's full set of attributes into the database's current values so we
			// can read each field with the getCurrent methods
			theDatabase.getUserAccountDetails(username);
			
			// Assemble the user's name from the parts that are actually present.  Some users
			// may not have provided every name part, so we only include
			// the parts that are non-empty.
			String fullName = "";
			fullName = appendIfPresent(fullName, theDatabase.getCurrentFirstName());
			fullName = appendIfPresent(fullName, theDatabase.getCurrentMiddleName());
			fullName = appendIfPresent(fullName, theDatabase.getCurrentLastName());
			if (fullName.length() == 0)
				fullName = "(no name on file)";
			
			// Assemble the comma separated list of roles this user plays, with Admin first
			String roles = "";
			if (theDatabase.getCurrentAdminRole())
				roles = appendRole(roles, "Admin");
			if (theDatabase.getCurrentNewRole1())
				roles = appendRole(roles, "Role1");
			if (theDatabase.getCurrentNewRole2())
				roles = appendRole(roles, "Role2");
			if (roles.length() == 0)
				roles = "(no roles)";
			
			// Assemble the email; it may also be empty for some users
			String email = theDatabase.getCurrentEmailAddress();
			if (email == null || email.length() == 0)
				email = "(no email on file)";
			// Assemble the preferred name, it may also be empty
			String prefName = theDatabase.getCurrentPreferredFirstName();
				if (prefName == null || prefName.length() == 0)
					prefName = "(No Preferred Name)";
			
			// Build one block of text for this user
			listing.append("Username: ").append(username).append("\n");
			listing.append("   Name:  ").append(fullName).append("\n");
			listing.append("   Email: ").append(email).append("\n");
			listing.append("   Roles: ").append(roles).append("\n");
			listing.append("   Preferred Name:  ").append(prefName).append("\n");
			listing.append("\n");
		}
		
		
		return listing.toString();
	}
	
	
	/**********
	 * <p> Method: appendIfPresent(String soFar, String part) </p>
	 * 
	 * <p> Description: Helper that appends a name part to the name being assembled, inserting a
	 * separating space only when there is already text and the new part is non-empty.  This avoids
	 * stray spaces when some name parts are missing. </p>
	 * 
	 * @param soFar is the name assembled so far
	 * 
	 * @param part is the next name part to consider adding
	 * 
	 * @return the name with the part added if it was present
	 * 
	 */
	private static String appendIfPresent(String soFar, String part) {
		if (part == null || part.length() == 0)
			return soFar;
		if (soFar.length() == 0)
			return part;
		return soFar + " " + part;
	}
	
	
	/**********
	 * <p> Method: appendRole(String soFar, String role) </p>
	 * 
	 * <p> Description: Helper that appends a role to the comma separated list of roles, inserting
	 * a separating comma only when there is already a role in the list.  This mirrors the role
	 * joining done on the Add/Remove Roles page. </p>
	 * 
	 * @param soFar is the list of roles assembled so far
	 * 
	 * @param role is the role to add
	 * 
	 * @return the role list with the new role added
	 * 
	 */
	private static String appendRole(String soFar, String role) {
		if (soFar.length() == 0)
			return role;
		return soFar + ", " + role;
	}
	
	
	/**********
	 * <p> Method: performReturn() </p>
	 * 
	 * <p> Description: This method returns the user (who must be an Admin as only admins are the
	 * only users who have access to this page) to the Admin Home page. </p>
	 * 
	 */
	protected static void performReturn() {
		guiAdminHome.ViewAdminHome.displayAdminHome(ViewListUsers.theStage,
				ViewListUsers.theUser);
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
		guiUserLogin.ViewUserLogin.displayUserLogin(ViewListUsers.theStage);
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