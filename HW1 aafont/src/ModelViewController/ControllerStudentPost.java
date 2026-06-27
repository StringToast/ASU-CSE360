//ControllerStudentPost.java in ModelViewController
package Model_View_Controller;

public class ControllerStudentPost {
	private static String activeTitle = "";
	private static String activeBody = "";
	protected static Database dataBase = applicationMain.FoundationsMain.database;
	public ControllerStudentPost() {}
	protected static void setActiveTitle() {
		activeTitle = ViewStudentPost.textTitle.getText();
	}
	protected static void setActiveBody() {
		activeBody = ViewStudentPost.textTitle.getText();
	}
	protected static void addToFields(int index) {
		Post chosen = dataBase.getPostByIndex(index); //getPostByIndex from model
		if(chosen != null) {
			ViewStudentPost.textTitle.setText(chosen.getTitle()); //chosen.getTitle() from model
			ViewStudentPost.textBody.setText(chosen.getBody()); //chosen.getBody() from model
		}
	}
	protected static void createPostCheck()
	{
		if (activeTitle.isBlank() || activeBody.isBlank())
		{
			ViewStudentPost.errorMessage.setText("Title and/or body missing");
			return;
		}
		
		dataBase.createPost(activeTitle, activeBody);//createPost from model
		ViewStudentPost.errorMessage.setText("You have posted to the discussion board successfully!");
		clearInputFields();
		refreshListView();
		protected static void updatePostCheck()
		{
			int index = ViewStudentPost.postList.getSelectionModel().getSelectedIndex()
			if(index < 0)
			{
				ViewStudentPost.errorMessage.setText("Please choose a post to update");
				return;
			}
			if(activeTitle.isBlank() || activeBody.isBlank())
			{
				ViewStudentPost.errorMessage.setText("Title and/or body missing");
				return;
			}
			dataBase.updatePost(index, activeTitle, activeBody); //updatePost from model
			ViewStudentPost.errorMessage.setText("You have updated the post successfully!");
			clearInputFields();
			refreshListView();
		}
		protected static void deletePostCheck()
		{
			int index = ViewStudentPost.postList.getSelectionModel().getSelectedIndex();
			if(index < 0)
			{
				ViewStudentPost.errorMessage.setText("Choose which post to delete");
				return;
			}
			dataBase.deletePost(index); //deletePost from model
			ViewStudentPost.errorMessage.setText("The post has been deleted");
			clearInputFields();
			refreshListView();
		}
		
		protected static void goBack(Stage stage, User user)
		{
			guiRole1.ViewRole1Home.displayRole1Home(stage, user);
			
		}
		private static void clearInputFields() 
		{
			ViewStudentPost.textTitle.setText("");
			ViewStudentPost.textBody.setText("");
			activeTitle = "";
			activeBody = "";
		}
		
		private static void refreshListView()
		{
			ViewStudentPost.postList.getItems().clear();
			for(Post post : dataBase.getAllPosts()) { //getAllPosts from model
				ViewStudentPost.postList.getItems().add(post.getTitle() + "-" post.getAuthor());)//post.getTitle() and post.getAuthor from model
			}
		}
		
				
		}
}
