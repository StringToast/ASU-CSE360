package guiListUsers;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import database.Database;
import entityClasses.User;

/*******
 * <p> Title: GUIListUsersPage Class. </p>
 * 
 * <p> Description: The Java/FX-based page for listing all users of the system.  This page is
 * read-only: it displays each account's username, name, email address, and roles in a scrollable
 * text area so an admin can review the users.  The layout largely borrows from the Add/Remove
 * Roles and Remove User pages.</p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2025 </p>
 * 
 * @author Alfredo Afont
 * 
 * @version 1.00		2025-08-20 Initial version
 *  
 */

public class ViewListUsers {
	
	/*-*******************************************************************************************

	Attributes
	
	*/
	
	// These are the application values required by the user interface
	
	private static double width = applicationMain.FoundationsMain.WINDOW_WIDTH;
	private static double height = applicationMain.FoundationsMain.WINDOW_HEIGHT;

	
	// These are the widget attributes for the GUI. There are 3 areas for this GUI.
	
	// GUI Area 1: It informs the user about the purpose of this page, whose account is being used,
	// and a button to allow this user to update the account settings.
	protected static Label label_PageTitle = new Label();
	protected static Label label_UserDetails = new Label();
	protected static Button button_UpdateThisUser = new Button("Account Update");
	
	// This is a separator and it is used to partition the GUI for various tasks
	protected static Line line_Separator1 = new Line(20, 95, width-20, 95);
	
	// GUI Area 2: This area displays the list of all users.  It is read-only, so a non-editable,
	// scrollable text area is used to show the assembled listing.
	protected static Label label_ListTitle = new Label("All users of the system:");
	protected static TextArea textArea_UserList = new TextArea();
	
	// This is a separator and it is used to partition the GUI for various tasks
	protected static Line line_Separator4 = new Line(20, 525, width-20,525);
	
	// GUI Area 3: This is last of the GUI areas.  It is used for quitting the application, logging
	// out, and returning to the Admin Home page when the actions on this page are complete.
	protected static Button button_Return = new Button("Return");
	protected static Button button_Logout = new Button("Logout");
	protected static Button button_Quit = new Button("Quit");

	// This is the end of the GUI objects for the page.
	
	// These attributes are used to configure the page and populate it with this user's information
	private static ViewListUsers theView;		// Used to determine if instantiation is needed

	// Reference for the in-memory database so this package has access
	@SuppressWarnings("unused")
	private static Database theDatabase = applicationMain.FoundationsMain.database;		

	protected static Stage theStage;			// The Stage that JavaFX has established for us
	protected static Pane theRootPane;			// The Pane that holds all the GUI widgets 
	protected static User theUser;				// The current user of the application
	
	public static Scene theListUsersScene = null;	// The Scene each invocation populates



	/*-*******************************************************************************************

	Constructors
	
	*/

	/**********
	 * <p> Method: displayListUsers(Stage ps, User user) </p>
	 * 
	 * <p> Description: This method is the single entry point from outside this package to cause
	 * the ListUsers page to be displayed.
	 * 
	 * It first sets up the shared attributes so we don't have to pass parameters.
	 * 
	 * It then checks to see if the page has been setup.  If not, it instantiates the class and
	 * initializes all the static aspects of the GUI widgets.
	 * 
	 * After the instantiation, the code refreshes the listing (since users may have been added or
	 * removed since the last time this page was shown), sets the Scene onto the stage, and makes
	 * it visible to the user.</p>
	 * 
	 * @param ps specifies the JavaFX Stage to be used for this GUI and it's methods
	 * 
	 * @param user specifies the current admin User
	 *
	 */
	public static void displayListUsers(Stage ps, User user) {
		
		// Establish the references to the GUI and the current user
		theStage = ps;
		theUser = user;
		
		// If not yet established, populate the static aspects of the GUI by creating the
		// singleton instance of this class
		if (theView == null) theView = new ViewListUsers();
		
		// Refresh the listing each time the page is shown because the set of users may have
		// changed since the last time it was displayed
		textArea_UserList.setText(ControllerListUsers.buildUserListing());
		
		// Set the title for the window, display the page, and wait for the admin to do something
		theStage.setTitle("CSE 360 Foundation Code: List Users Page");
		theStage.setScene(theListUsersScene);
		theStage.show();
	}

	
	/**********
	 * <p> Method: GUIListUsersPage() </p>
	 * 
	 * <p> Description: This method initializes all the elements of the graphical user interface.
	 * This method determines the location, size, font, color, and change and event handlers for
	 * each GUI object.
	 * 
	 * This is a singleton, so this is performed just once.  Subsequent uses fill in the changeable
	 * fields using the displayListUsers method.</p>
	 * 
	 */
	public ViewListUsers() {
		
		// This page is used only by the admin role, so we do not specify the role being used
			
		// Create the Pane for the list of widgets and the Scene for the window
		theRootPane = new Pane();
		theListUsersScene = new Scene(theRootPane, width, height);
		
		// Populate the window with the title and other common widgets and set their static state
		
		// GUI Area 1
		label_PageTitle.setText("List Users Page");
		setupLabelUI(label_PageTitle, "Arial", 28, width, Pos.CENTER, 0, 5);

		label_UserDetails.setText("User: " + theUser.getUserName());
		setupLabelUI(label_UserDetails, "Arial", 20, width, Pos.BASELINE_LEFT, 20, 55);
		
		setupButtonUI(button_UpdateThisUser, "Dialog", 18, 170, Pos.CENTER, 610, 45);
		button_UpdateThisUser.setOnAction((_) -> 
			{guiUserUpdate.ViewUserUpdate.displayUserUpdate(theStage, theUser); });
		
		// GUI Area 2
		setupLabelUI(label_ListTitle, "Arial", 20, 300, Pos.BASELINE_LEFT, 20, 110);
		setupTextAreaUI(textArea_UserList, "Monospaced", 14, width-40, 360, 20, 145);
		
		// GUI Area 3
		setupButtonUI(button_Return, "Dialog", 18, 210, Pos.CENTER, 20, 540);
		button_Return.setOnAction((_) -> {ControllerListUsers.performReturn(); });

		setupButtonUI(button_Logout, "Dialog", 18, 210, Pos.CENTER, 300, 540);
		button_Logout.setOnAction((_) -> {ControllerListUsers.performLogout(); });
    
		setupButtonUI(button_Quit, "Dialog", 18, 210, Pos.CENTER, 570, 540);
		button_Quit.setOnAction((_) -> {ControllerListUsers.performQuit(); });
		
		// This is the end of the GUI Widgets for the page
		
		// This page has a single, fixed layout, so the widgets are placed into the Root Pane here
		// in the constructor (as is done in ViewAdminHome) rather than being delegated to a
		// controller repaint method.
		theRootPane.getChildren().addAll(
			label_PageTitle, label_UserDetails, button_UpdateThisUser, line_Separator1,
			label_ListTitle, textArea_UserList,
			line_Separator4, button_Return, button_Logout, button_Quit);
	}	

	/*-*******************************************************************************************

	Helper methods used to minimizes the number of lines of code needed above
	
	*/

	/**********
	 * Private local method to initialize the standard fields for a label
	 * 
	 * @param l		The Label object to be initialized
	 * @param ff	The font to be used
	 * @param f		The size of the font to be used
	 * @param w		The width of the Button
	 * @param p		The alignment (e.g. left, centered, or right)
	 * @param x		The location from the left edge (x axis)
	 * @param y		The location from the top (y axis)
	 */
	private static void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x,
			double y){
		l.setFont(Font.font(ff, f));
		l.setMinWidth(w);
		l.setAlignment(p);
		l.setLayoutX(x);
		l.setLayoutY(y);		
	}
	
	
	/**********
	 * Private local method to initialize the standard fields for a button
	 * 
	 * @param b		The Button object to be initialized
	 * @param ff	The font to be used
	 * @param f		The size of the font to be used
	 * @param w		The width of the Button
	 * @param p		The alignment (e.g. left, centered, or right)
	 * @param x		The location from the left edge (x axis)
	 * @param y		The location from the top (y axis)
	 */
	protected static void setupButtonUI(Button b, String ff, double f, double w, Pos p, double x,
			double y){
		b.setFont(Font.font(ff, f));
		b.setMinWidth(w);
		b.setAlignment(p);
		b.setLayoutX(x);
		b.setLayoutY(y);		
	}

	/**********
	 * Private local method to initialize the standard fields for a read-only text area
	 * 
	 * @param t		The TextArea object to be initialized
	 * @param ff	The font to be used
	 * @param f		The size of the font to be used
	 * @param w		The width of the TextArea
	 * @param h		The height of the TextArea
	 * @param x		The location from the left edge (x axis)
	 * @param y		The location from the top (y axis)
	 */
	private static void setupTextAreaUI(TextArea t, String ff, double f, double w, double h,
			double x, double y){
		t.setFont(Font.font(ff, f));
		t.setMinWidth(w);
		t.setMaxWidth(w);
		t.setMinHeight(h);
		t.setMaxHeight(h);
		t.setLayoutX(x);
		t.setLayoutY(y);
		t.setEditable(false);		// The list is read-only, so the admin cannot type into it
		t.setWrapText(true);		// Wrap long lines so nothing runs off the right edge
	}
}