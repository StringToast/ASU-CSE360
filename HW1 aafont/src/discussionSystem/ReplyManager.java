package discussionSystem;

import java.util.ArrayList;
import java.util.List;
import entityClasses.Reply;

/*******
 * <p> Title: ReplyManager Class. </p>
 * 
 * <p> Description: This ReplyManager class stores all of the replies in the discussion system and
 * provides the CRUD operations over them, along with searches that return subsets of the replies.  
 * The replies are held in an ArrayList.</p>
 * 
 * <p> This manager parallels the PostManager, but adds the ability to find every reply that
 * answers a particular parent.  Because a reply may answer either a post or another reply, a
 * parent is identified by both its id and a flag indicating whether it is a post or a reply; the
 * threading query uses both so that, for example, replies to post 3 are not confused with replies
 * to reply 3.</p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2025 </p>
 * 
 * @author Alfredo Afont
 * 
 * @version 1.00		2025-08-20 Initial version
 * 
 */
public class ReplyManager {

	/*
	 * These are the private attributes for this manager
	 */
	private List<Reply> replies = new ArrayList<Reply>();	// All replies currently stored
	private int nextId = 1;								// The next id to assign to a new reply
	private String lastErrorMessage = "";				// The message from the most recent failure

	
	/*****
	 * <p> Method: int createReply(String content, String author, int parentId,
	 * 		boolean parentIsPost) </p>
	 * 
	 * <p> Description: Create a new reply. The content is validated first; if it
	 * is not valid, no reply is created, the helpful error message is stored where the caller can
	 * read it with getLastErrorMessage, and -1 is returned.  If the content is valid, the reply is
	 * given the next available id, recorded as answering the given parent, added to the
	 * collection, and its new id is returned.</p>
	 * 
	 * @param content specifies the body text of the new reply
	 * 
	 * @param author specifies the username of the user creating the reply
	 * 
	 * @param parentId specifies the id of the post or reply being answered
	 * 
	 * @param parentIsPost specifies whether the parent is a post (true) or a reply (false)
	 * 
	 * @return the new reply's id on success, or -1 if the content was not valid
	 * 
	 */
	public int createReply(String content, String author, int parentId, boolean parentIsPost) {
		// Validate the content before anything is stored
		String error = Reply.checkForValidContent(content);
		if (!error.isEmpty()) {
			lastErrorMessage = error;
			return -1;
		}
		
		// The content is valid, so assign the next id, create the reply, and store it
		lastErrorMessage = "";
		Reply reply = new Reply(nextId, content, author, parentId, parentIsPost);
		replies.add(reply);
		nextId++;					// Advance the counter so the next reply gets a fresh id
		return reply.getId();
	}

	
	/*****
	 * <p> Method: Reply readReply(int id) </p>
	 * 
	 * <p> Description: Read (retrieve) the reply with the given id (the "R" in CRUD).  Returns the
	 * matching reply, or null if no reply has that id.</p>
	 * 
	 * @param id specifies the id of the reply to retrieve
	 * 
	 * @return the matching Reply, or null if none exists
	 * 
	 */
	public Reply readReply(int id) {
		return findById(id);
	}

	
	/*****
	 * <p> Method: String updateReply(int id, String newContent) </p>
	 * 
	 * <p> Description: Update the content of the reply with the given id (the "U" in CRUD).  As
	 * with updating a post, a specific message is returned for each way the update can fail: there
	 * is no reply with that id, or the new content is not valid.  An empty string is returned when
	 * the update succeeds.</p>
	 * 
	 * @param id specifies the id of the reply to update
	 * 
	 * @param newContent specifies the replacement body text
	 * 
	 * @return an empty string on success, otherwise a helpful error message
	 * 
	 */
	public String updateReply(int id, String newContent) {
		// Find the reply; if it is not there, report that specifically
		Reply reply = findById(id);
		if (reply == null)
			return "*** Error *** No reply exists with id " + id + ".";
		
		// Validate the replacement content before changing anything
		String error = Reply.checkForValidContent(newContent);
		if (!error.isEmpty())
			return error;
		
		// Both checks passed, so apply the update
		reply.setContent(newContent);
		return "";
	}
	public boolean markReplyRead(int id, String username) {
		Reply reply = findById(id);
		if (reply == null)
			return false;
		reply.markRead(username);
		return true;
	}
	public List<Reply> getUnreadRepliesTo(int parentId, boolean parentIsPost, String username) {
		List<Reply> matches = new ArrayList<Reply>();
		for (Reply reply : replies) {
			if (reply.getParentId() == parentId
					&& reply.getParentIsPost() == parentIsPost
					&& !reply.isReadBy(username))
				matches.add(reply);
		}
		return matches;
	}
	
	/*****
	 * <p> Method: boolean deleteReply(int id) </p>
	 * 
	 * <p> Description: Delete the reply with the given id (the "D" in CRUD).  Returns true if a
	 * reply was found and removed, or false if no reply had that id.</p>
	 * 
	 * @param id specifies the id of the reply to delete
	 * 
	 * @return true if a reply was removed, false if no reply had that id
	 * 
	 */
	public boolean deleteReply(int id) {
		Reply reply = findById(id);
		if (reply == null)
			return false;
		replies.remove(reply);
		return true;
	}

	
	/*****
	 * <p> Method: getRepliesTo(int parentId, boolean parentIsPost) </p>
	 * 
	 * <p> Description: Return the subset of replies that answer the given parent.  A reply matches
	 * only when both its parent id and its parent type agree with the arguments, so that replies
	 * to a post are not confused with replies to a reply that happens to share the same id number.
	 * The returned list is a new list and may be empty if the parent has no replies.</p>
	 * 
	 * @param parentId specifies the id of the post or reply whose replies are wanted
	 * 
	 * @param parentIsPost specifies whether the parent is a post (true) or a reply (false)
	 * 
	 * @return a list of the replies answering that parent (possibly empty)
	 * 
	 */
	public List<Reply> getRepliesTo(int parentId, boolean parentIsPost) {
		List<Reply> matches = new ArrayList<Reply>();
		for (Reply reply : replies) {
			if (reply.getParentId() == parentId && reply.getParentIsPost() == parentIsPost)
				matches.add(reply);
		}
		return matches;
	}

	
	/*****
	 * <p> Method: searchByKeyword(String keyword) </p>
	 * 
	 * <p> Description: Return the subset of replies whose content contains the given keyword.  The
	 * match is case-insensitive.  The returned list is a new list and may be empty.</p>
	 * 
	 * @param keyword specifies the text to search for within each reply's content
	 * 
	 * @return a list of the matching replies (possibly empty)
	 * 
	 */
	public List<Reply> searchByKeyword(String keyword) {
		List<Reply> matches = new ArrayList<Reply>();
		String lowerKeyword = keyword.toLowerCase();
		for (Reply reply : replies) {
			if (reply.getContent().toLowerCase().contains(lowerKeyword))
				matches.add(reply);
		}
		return matches;
	}

	
	/*****
	 * <p> Method:searchByAuthor(String author) </p>
	 * 
	 * <p> Description: Return the subset of replies written by the given author.  The match is
	 * case-insensitive.  The returned list is a new list and may be empty.</p>
	 * 
	 * @param author specifies the username whose replies should be returned
	 * 
	 * @return a list of the matching replies (possibly empty)
	 * 
	 */
	public List<Reply> searchByAuthor(String author) {
		List<Reply> matches = new ArrayList<Reply>();
		for (Reply reply : replies) {
			if (reply.getAuthor().equalsIgnoreCase(author))
				matches.add(reply);
		}
		return matches;
	}

	
	/*****
	 * <p> Method: getAllReplies() </p>
	 * 
	 * <p> Description: Return all of the stored replies as a new list, so that a caller iterating
	 * the result cannot modify the manager's collection.</p>
	 * 
	 * @return a list containing all stored replies
	 * 
	 */
	public List<Reply> getAllReplies() {
		return new ArrayList<Reply>(replies);
	}

	
	/*****
	 * <p> Method: String getLastErrorMessage() </p>
	 * 
	 * <p> Description: Return the error message produced by the most recent failed operation (for
	 * example, after createReply returns -1).</p>
	 * 
	 * @return the most recent error message
	 * 
	 */
	public String getLastErrorMessage() { return lastErrorMessage; }

	
	/*****
	 * <p> Method: Reply findById(int id) </p>
	 * 
	 * <p> Description: A private helper that walks the stored replies and returns the one whose id
	 * matches, or null if none does.  Read, update, and delete all locate a reply by id, so that
	 * logic is kept here in one place.</p>
	 * 
	 * @param id specifies the id to search for
	 * 
	 * @return the matching Reply, or null if none exists
	 * 
	 */
	private Reply findById(int id) {
		for (Reply reply : replies) {
			if (reply.getId() == id)
				return reply;
		}
		return null;
	}
}