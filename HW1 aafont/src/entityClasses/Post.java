package entityClasses;

import java.time.LocalDateTime;

/*******
 * <p> Title: Post Class. </p>
 * 
 * <p> Description: This Post class represents a single post in the discussion system.  It holds
 * the post's content along with the metadata needed to identify it, attribute it to an author,
 * place it in a thread, and record when it was created.  The id is assigned by the posts manager
 * (which hands out auto-incrementing ids) so that every post can be uniquely found, updated, and
 * deleted, and so that replies can refer back to the post they answer.</p>
 * 
 * <p> The content validation is provided as a static method so the manager can check input before
 * a Post is created or updated, following the same empty-string-means-valid convention used by
 * the other validators in this project.</p>
 * 
 * 
 * @author Alfredo Afont
 * 
 * @version 1.00		2025-08-20 Initial version
 * 
 */
public class Post {

	/*
	 * These are the private attributes for this entity object
	 */
	private int id;	// The unique id assigned by the posts manager
	private String content; // The body text of the post
	private String author;// The username of the user who created the post
	private String thread;// The thread this post belongs to
	private LocalDateTime timestamp;// When the post was created

	
	/*****
	 * <p> Method: Post(int id, String content, String author, String thread) </p>
	 * 
	 * <p> Description: This constructor establishes a Post.  The timestamp is set to the moment of
	 * creation rather than being passed in, since a post's creation time is determined by when it
	 * is actually made, not by the caller.</p>
	 * 
	 * @param id specifies the unique id assigned to this post by the manager
	 * 
	 * @param content specifies the body text of the post
	 * 
	 * @param author specifies the username of the user creating the post
	 * 
	 * @param thread specifies the thread this post belongs to
	 * 
	 */
	public Post(int id, String content, String author, String thread) {
		this.id = id;
		this.content = content;
		this.author = author;
		this.thread = thread;
		this.timestamp = LocalDateTime.now();
	}

	
	/*****
	 * <p> Method: String checkForValidContent(String content) </p>
	 * 
	 * <p> Description: Validate the body of a post.  The content is required (it cannot be empty)
	 * and must not exceed 2000 characters.  A character limit (rather than a word limit) is used
	 * because a word limit would not stop an attacker from dropping a single enormous run of
	 * characters into the field to probe for a crash.  Special characters are intentionally
	 * allowed, since real discussion posts legitimately contain them.</p>
	 * 
	 * @param content is the body text to be validated
	 * 
	 * @return an empty string if the content is valid, otherwise a helpful error message
	 * 
	 */
	public static String checkForValidContent(String content) {
		// The content is required, so a null or empty body is an error
		if (content == null || content.isEmpty())
			return "*** Error *** A post must have content; it cannot be empty.";

		// The content must not exceed the maximum of 2000 characters
		if (content.length() > 2000)
			return "*** Error *** A post must not exceed 2000 characters.";

		// The content is valid
		return "";
	}

	
	/*
	 * The getters for this entity object
	 */
	
	/*****
	 * <p> Method: int getId() </p>
	 * <p> Description: Return the unique id of this post. </p>
	 * @return the id of the post
	 */
	public int getId() { 
		return id; 
		}

	/*****
	 * <p> Method: String getContent() </p>
	 * <p> Description: Return the body text of this post. </p>
	 * @return the content of the post
	 */
	public String getContent() {
		return content; 
		}

	/*****
	 * <p> Method: String getAuthor() </p>
	 * <p> Description: Return the username of the author of this post. </p>
	 * @return the author of the post
	 */
	public String getAuthor() { 
		return author; 
		}

	/*****
	 * <p> Method: String getThread() </p>
	 * <p> Description: Return the thread this post belongs to. </p>
	 * @return the thread of the post
	 */
	public String getThread() {
		return thread; 
		}

	/*****
	 * <p> Method: LocalDateTime getTimestamp() </p>
	 * <p> Description: Return the time this post was created. </p>
	 * @return the timestamp of the post
	 */
	public LocalDateTime getTimestamp() {
		return timestamp; 
		}

	
	/*
	 * The setters for the fields that may change after creation.  The id, author, and timestamp
	 * are not given setters because they identify the post and when/by whom it was made; allowing
	 * them to change would break the integrity of the stored posts and the replies that point to
	 * them.
	 */
	
	/*****
	 * <p> Method: void setContent(String content) </p>
	 * <p> Description: Update the body text of this post (used by the Update operation). </p>
	 * @param content is the new body text for the post
	 */
	public void setContent(String content) {
		this.content = content; 
		}

	/*****
	 * <p> Method: void setThread(String thread) </p>
	 * <p> Description: Update the thread this post belongs to. </p>
	 * @param thread is the new thread for the post
	 */
	public void setThread(String thread) {
		this.thread = thread; 
		}
}