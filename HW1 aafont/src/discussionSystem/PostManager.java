package discussionSystem;

import java.util.ArrayList;
import java.util.List;
import entityClasses.Post;

/*******
 * <p> Title: PostManager Class. </p>
 * 
 * <p> Description: This PostManager class stores all of the posts in the discussion system and
 * provides the CRUD operations over them, along with searches
 * that return subsets of the posts.  The posts are held in an ArrayList, 
 * satisfying the requirement that the collection be able to grow
 * arbitrarily large.</p>
 * 
 * <p> The manager owns an auto-incrementing counter that assigns a unique id to each new post.
 * Posts are always located by their id rather than by their position in the list, because a
 * delete shifts positions but ids remain stable, and because replies refer to the post they
 * answer by id.</p>
 * 
 * <p> The search methods return a new list containing the matching subset of posts.  That subset
 * may be empty, may contain one element, or may be arbitrarily large.</p>
 * 
 * 
 * @author Alfredo Afont
 * 
 * @version 1.00		2025-08-20 Initial version
 * 
 */
public class PostManager {

	private List<Post> posts = new ArrayList<Post>();	//all posts currently stored
	private int nextId = 1;								//the next id to assign to a new post
	private String lastErrorMessage = "";				//the message from the most recent failure

	
	/*****
	 * <p> Method: int createPost(String content, String author, String thread) </p>
	 * 
	 * <p> Description: Create a new post.  The content is validated first. if it
	 * is not valid, no post is created, the helpful error message is stored where the caller can
	 * read it with getLastErrorMessage, and -1 is returned to signal the failure.  If the content
	 * is valid, the post is given the next available id, added to the collection, and its new id
	 * is returned so the caller can immediately read, update, delete, or reply to it.</p>
	 * 
	 * @param content specifies the body text of the new post
	 * 
	 * @param author specifies the username of the user creating the post
	 * 
	 * @param thread specifies the thread the post belongs to
	 * 
	 * @return the new post's id on success, or -1 if the content was not valid
	 * 
	 */
	public int createPost(String content, String author, String thread) {
		//validate the content before anything is stored
		String error = Post.checkForValidContent(content);
		if (!error.isEmpty()) {
			lastErrorMessage = error;
			return -1;
		}
		
		//The content is valid, so assign the next id, create the post, and store it
		lastErrorMessage = "";
		Post post = new Post(nextId, content, author, thread);
		posts.add(post);
		nextId++;					//Advance the counter so the next post gets a fresh id
		return post.getId();
	}

	
	/*****
	 * <p> Method: Post readPost(int id) </p>
	 * 
	 * <p> Description: Read (retrieve) the post with the given id (the "R" in CRUD).  Returns the
	 * matching post, or null if no post has that id.</p>
	 * 
	 * @param id specifies the id of the post to retrieve
	 * 
	 * @return the matching Post, or null if none exists
	 * 
	 */
	public Post readPost(int id) {
		return findById(id);
	}

	
	/*****
	 * <p> Method: String updatePost(int id, String newContent) </p>
	 * 
	 * <p> Description: Update the content of the post with the given id (the "U" in CRUD).  The
	 * update can fail for two distinct reasons, so a specific message is returned for each: there
	 * is no post with that id, or the new content is not valid.  An empty string is returned when
	 * the update succeeds.</p>
	 * 
	 * @param id specifies the id of the post to update
	 * 
	 * @param newContent specifies the replacement body text
	 * 
	 * @return an empty string on success, otherwise a helpful error message
	 * 
	 */
	public String updatePost(int id, String newContent) {
		// Find the post; if it is not there, report that specifically
		Post post = findById(id);
		if (post == null)
			return "*** Error *** No post exists with id " + id + ".";
		
		// Validate the replacement content before changing anything
		String error = Post.checkForValidContent(newContent);
		if (!error.isEmpty())
			return error;
		
		// Both checks passed, so apply the update
		post.setContent(newContent);
		return "";
	}

	
	/*****
	 * <p> Method: boolean deletePost(int id) </p>
	 * 
	 * <p> Description: Delete the post with the given id (the "D" in CRUD).  Returns true if a
	 * post was found and removed, or false if no post had that id.</p>
	 * 
	 * @param id specifies the id of the post to delete
	 * 
	 * @return true if a post was removed, false if no post had that id
	 * 
	 */
	public boolean deletePost(int id) {
		Post post = findById(id);
		if (post == null)
			return false;
		posts.remove(post);
		return true;
	}

	
	/*****
	 * <p> Method: searchByKeyword(String keyword) </p>
	 * 
	 * <p> Description: Return the subset of posts whose content contains the given keyword.  The
	 * match is case-insensitive.  The returned list is a new list, so the caller cannot disturb
	 * the manager's stored collection, and it may be empty if nothing matches.</p>
	 * 
	 * @param keyword specifies the text to search for within each post's content
	 * 
	 * @return a list of the matching posts (possibly empty)
	 * 
	 */
	public List<Post> searchByKeyword(String keyword) {
		List<Post> matches = new ArrayList<Post>();
		String lowerKeyword = keyword.toLowerCase();
		for (Post post : posts) {
			if (post.getContent().toLowerCase().contains(lowerKeyword))
				matches.add(post);
		}
		return matches;
	}

	
	/*****
	 * <p> Method:searchByAuthor(String author) </p>
	 * 
	 * <p> Description: Return the subset of posts written by the given author.  The match is
	 * case-insensitive.The returned list is a new list and may be empty if the author has no
	 * posts.</p>
	 * 
	 * @param author specifies the username whose posts should be returned
	 * 
	 * @return a list of the matching posts (possibly empty)
	 * 
	 */
	public List<Post> searchByAuthor(String author) {
		List<Post> matches = new ArrayList<Post>();
		for (Post post : posts) {
			if (post.getAuthor().equalsIgnoreCase(author))
				matches.add(post);
		}
		return matches;
	}

	
	/*****
	 * <p> Method: getAllPosts() </p>
	 * 
	 * <p> Description: Return all of the stored posts as a new list.  A copy is returned rather
	 * than the internal list so that a caller iterating over the result cannot accidentally modify
	 * the manager's collection.</p>
	 * 
	 * @return a list containing all stored posts
	 * 
	 */
	public List<Post> getAllPosts() {
		return new ArrayList<Post>(posts);
	}

	
	/*****
	 * <p> Method: String getLastErrorMessage() </p>
	 * 
	 * <p> Description: Return the error message produced by the most recent failed operation (for
	 * example, after createPost returns -1).</p>
	 * 
	 * @return the most recent error message
	 * 
	 */
	public String getLastErrorMessage() { return lastErrorMessage; }

	
	/*****
	 * <p> Method: Post findById(int id) </p>
	 * 
	 * <p> Description: A private helper that walks the stored posts and returns the one whose id
	 * matches, or null if none does.  Read, update, and delete all need to locate a post by id, so
	 * that logic is kept in one place here rather than repeated in each of them.</p>
	 * 
	 * @param id specifies the id to search for
	 * 
	 * @return the matching Post, or null if none exists
	 * 
	 */
	private Post findById(int id) {
		for (Post post : posts) {
			if (post.getId() == id)
				return post;
		}
		return null;
	}
}