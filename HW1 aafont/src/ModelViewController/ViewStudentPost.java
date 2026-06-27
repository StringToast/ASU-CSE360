//ViewStudentPost.java in ModelViewController
package Model_View_Controller;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage; 

public class ViewStudentPost {
	
private static double width = applicationMain.FoundationsMain.WINDOW_WIDTH;
private static double height = applicationMain.FoundationsMain.WINDOW_HEIGHT;
private static Label pageTitle = new Label("View & Manage Student Posts");
private static Label instructions = new Label("Create, update, or delete posts");
private static Label title = new Label("Title:");
private static Label body = new Label("Body:");
private static Label allPosts = new Label("All posts:");
//?
protected static Label errorMessage = new Label();
protected static TextField textTitle = new TextField();
protected static TextArea textBody = new TextArea();

protected static ListView<String> postList = new ListView<>();
private static Button createButton = new Button("Create a post!");
private static Button updateButton = new Button("Update");
private static Button deleteButton = new Button("Delete");
private static Button returnButton = new Button("Return");
private static ViewStudentPost view = null;
protected static Stage stage;
private static Pane rootPane;
private static Scene sceneOfStudentPost = null;
private static User activeUser;

public static void showStudentPost(Stage primaryStage, User user)
{
	stage = primaryStage;
	activeUser = user;
	
	if (view == null) {
		view = new ViewStudentPost();
	}
	
	textTitle.setText("");
	textBody.setText("");
	errorMessage.setText("");
	stage.setTitle("CSE360 Post Manager");
	stage.setScene(sceneOfStudentPost);
	stage.show();
}
private ViewStudentPost() {
	rootPane = new Pane();
	sceneOfStudentPost = new Scene(rootPane, width, height);
	
	setupLabelUI(pageTitle, "Arial", 30, width, Pos.CENTER, 0, 10);
	setupLabelUI(instructions, "Arial", 16, width, Pos.CENTER, 0, 55);
	setupLabelUI(title, "Arial", 14, 200, Pos.BASELINE_LEFT, 30, 100);
	setupLabelUI(textTitle, "Arial", 16, 330, Pos.BASELINE_LEFT, 30, 120, true);
	
	textTitle.setPromptText("Set post title");
	textTitle.textProperty().addListener((_, _, _) -> 
	ControllerStudentPost.setActiveTitle());
	
	setupLabelUI(body, "Arial", 14, 200, Pos.BASELINE_LEFT, 30, 165);
	textBody.setFont(Font.font("Arial", 14));
	textBody.setMinWidth(330);
	textBody.maxWidth(330);
	textBody.setWrapText(true);
	textBody.setLayoutX(30);
	textBody.setLayoutY(185);
	textBody.textProperty().addListener((_, _, _) -> 
	ControllerStudentPost.setActiveBody());
	
	setupButtonUI(createButton, "Chat", 16, 150, Pos.CENTER, 30, 380);
	createButton.setOnAction((_)-> ControllerStudentPost.createPostCheck());
	setUpButtonUI(updateButton, "Update", 16, 150, Pos.CENTER, 200, 380);
	updateButton.setOnAction((_) -> ControllerStudentPost.updatePostCheck());
	setUpButtonUI(deleteButton, "Delete", 16, 150, Pos.CENTER, 200, 380);
	updateButton.setOnAction((_) -> ControllerStudentPost.deletePostCheck());
	setupLabelUI(errorMessage, "Arial", 14, width, Pos.CENTER, 0, 480));
	setupLabelUI(allPosts, "Arial", 14, 300, Pos.BASELINE_LEFT, 430, 100);
	postList.setLayoutX(430);
	postList.setLayoutY(120);
	postList.setPrefWidth(340);
	postList.setPrefHeight(340);
	postList.getSelectionModel().selectedIndexProperty().addListener((_,_,_) -> {
		int index = postList.getSelectionModel().getSelectedIndex();
		if(index >= 0)
		{
			ControllerStudentPost.addToFields(index);
		}});
	setupButtonUI(returnButton, "Return", 16, 120, Pos.CENTER, 30, 530);
	returnButton.setOnAction((_) ->
	    ControllerStudentPost.goBack(stage, activeUser));
	rootPane.getChildren().addAll(pageTitle, instructions, title, textTitle, body, textBody, createButton, updateButton, deleteButton, errorMessage,
			allPosts, postList, returnButton);
	private void setupLabelUI(Label label, String string, double a, double b, Pos pos, double x, double y)
	{
		label.setFont(Font.font(string, a));
		label.setMinWidth(b);
		label.setAlignment(pos);
		label.setLayoutX(x);
		label.setLayoutY(y);
	}
	private void setupButtonUI(Button button, String string, double a, double b, Pos pos, double x, double y)
	{
		button.setFont(Font.font(string, a));
		button.setMinWidth(b);
		button.setAlignment(pos);
		button.setLayoutX(x);
		button.setLayoutY(y);
	}
}