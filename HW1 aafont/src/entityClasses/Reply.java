package entityClasses;

import java.time.LocalDateTime;

/*******
 * <p> Title: Reply Class. </p>
 * 
 * <p> Description: This Reply class represents a single reply in the discussion system.  Like a
 * Post, it holds body content and the metadata to identify and attribute it.  What makes a Reply
 * different is that it points back to what it is replying to: it stores the id of its parent and
 * a flag indicating whether that parent is a post or another reply.  Storing the parent type as
 * well as the parent id is what allows a reply to answer either a post or another reply, so the
 * replies can form a thread of conversation rather than a single flat list under each post.</p>
 * 
 * <p> The body validation rules for a reply are identical to those for a post, so this class
 * reuses Post.checkForValidContent rather than duplicating the rule, keeping a single source of
 * truth for what valid content means.</p>
 * 
 * 
 * @author Alfredo Afont
 * 
 * @version 1.00		2025-08-20 Initial version
 * 
 */
public class Reply {

	/*
	 * These are the private attributes for this entity object
	 */
	private int id;					// The unique id assigned by the replies manager
	private String content;			// The body text of the reply
	private String author;			// The username of the user who created the reply
	private int parentId;			// The id of the post or reply this reply answers
	private boolean parentIsPost;	// true if the parent is a post, false if it is a reply
	private LocalDateTime timestamp;	// When the reply was created

	
	/*****
	 * <p> Method: Reply(int id, String content, String author, int parentId,
	 * 		boolean parentIsPost) </p>
	 * 
	 * <p> Description: This constructor establishes a Reply.  As with a Post, the timestamp is set
	 * at the moment of creation rather than being passed in.  The parentId together with the
	 * parentIsPost flag records exactly what this reply is answering.</p>
	 * 
	 * @param id specifies the unique id assigned to this reply by the manager
	 * 
	 * @param content specifies the body text of the reply
	 * 
	 * @param author specifies the username of the user creating the reply
	 * 
	 * @param parentId specifies the id of the post or reply being answered
	 * 
	 * @param parentIsPost specifies whether the parent is a post (true) or a reply (false)
	 * 
	 */
	public Reply(int id, String content, String author, int parentId, boolean parentIsPost) {
		this.id = id;
		this.content = content;
		this.author = author;
		this.parentId = parentId;
		this.parentIsPost = parentIsPost;
		this.timestamp = LocalDateTime.now();
	}

	
	/*****
	 * <p> Method: String checkForValidContent(String content) </p>
	 * 
	 * <p> Description: Validate the body of a reply.  A reply's content has the same requirements
	 * as a post's (required and no more than 2000 characters), so this method delegates to
	 * Post.checkForValidContent to avoid duplicating the rule.  If the rule for posts and replies
	 * ever needs to diverge, this is the single place that would change.</p>
	 * 
	 * @param content is the body text to be validated
	 * 
	 * @return an empty string if the content is valid, otherwise a helpful error message
	 * 
	 */
	public static String checkForValidContent(String content) {
		return Post.checkForValidContent(content);
	}

	
	/*
	 * The getters for this entity object
	 */
	
	/*****
	 * <p> Method: int getId() </p>
	 * <p> Description: Return the unique id of this reply. </p>
	 * @return the id of the reply
	 */
	public int getId() { 
		return id; 
		}

	/*****
	 * <p> Method: String getContent() </p>
	 * <p> Description: Return the body text of this reply. </p>
	 * @return the content of the reply
	 */
	public String getContent() { 
		return content; 
		}

	/*****
	 * <p> Method: String getAuthor() </p>
	 * <p> Description: Return the username of the author of this reply. </p>
	 * @return the author of the reply
	 */
	public String getAuthor() { 
		return author; 
		}

	/*****
	 * <p> Method: int getParentId() </p>
	 * <p> Description: Return the id of the post or reply this reply answers. </p>
	 * @return the parent id of the reply
	 */
	public int getParentId() { 
		return parentId; 
		}

	/*****
	 * <p> Method: boolean getParentIsPost() </p>
	 * <p> Description: Return whether the parent is a post (true) or another reply (false). </p>
	 * @return true if the parent is a post, false if it is a reply
	 */
	public boolean getParentIsPost() { 
		return parentIsPost; 
		}

	/*****
	 * <p> Method: LocalDateTime getTimestamp() </p>
	 * <p> Description: Return the time this reply was created. </p>
	 * @return the timestamp of the reply
	 */
	public LocalDateTime getTimestamp() { 
		return timestamp; 
		}

	
	/*
	 * The setter for the field that may change after creation.  As with Post, the id, author,
	 * timestamp, and the parent references are not given setters because they identify the reply
	 * and what it answers; changing them would break the structure of the conversation.
	 */
	
	/*****
	 * <p> Method: void setContent(String content) </p>
	 * <p> Description: Update the body text of this reply (used by the Update operation). </p>
	 * @param content is the new body text for the reply
	 */
	public void setContent(String content) { 
		this.content = content; 
		}
}