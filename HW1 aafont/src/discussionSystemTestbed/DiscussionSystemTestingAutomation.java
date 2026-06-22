package discussionSystemTestbed;

import java.util.List;
import entityClasses.Post;
import entityClasses.Reply;
import discussionSystem.PostManager;
import discussionSystem.ReplyManager;

/*******
 * <p> Title: DiscussionSystemTestingAutomation Class. </p>
 * 
 * <p> Description: A set of semi-automated test cases for the discussion system's CRUD and input
 * validation operations.  It exercises the PostManager and ReplyManager through create, read,
 * update, delete, search, and the reply-threading query, covering both positive cases (the
 * operation succeeds as intended) and negative cases (invalid input is rejected with a helpful
 * error message, or a missing item is reported).  This class follows the structure of the
 * provided PasswordEvaluationTestingAutomation class, adapted so each test asserts a condition
 * about the outcome of a CRUD operation rather than feeding a single input to one validator.</p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2025 </p>
 * 
 * @author Alfredo Afont
 * 
 * @version 1.00		2025-08-20 Initial version
 * 
 */
public class DiscussionSystemTestingAutomation {

	static int numPassed = 0;	// Counter of the number of passed tests
	static int numFailed = 0;	// Counter of the number of failed tests

	/*
	 * This mainline sets up the managers, runs a sequence of CRUD and validation test cases for
	 * posts and then for replies, and finally displays a summary of the results.
	 */
	public static void main(String[] args) {
		System.out.println("______________________________________");
		System.out.println("\nDiscussion System Testing Automation");

		PostManager pm = new PostManager();
		ReplyManager rm = new ReplyManager();

		/************** Post CRUD and validation tests **************/
		System.out.println("\n========== Post Tests ==========");

		// Create: a valid post should be created and given a positive id
		int p1 = pm.createPost("How do I install the H2 database in Eclipse?", "alfredo",
				"General");
		performTest(1, "Create a valid post returns a positive id", p1 > 0);

		// Create more valid posts so later searches have something to find
		int p2 = pm.createPost("Has anyone gotten JavaFX to run on a Mac?", "joseph", "General");
		int p3 = pm.createPost("Reminder: the exam covers FSMs and input validation.", "molly",
				"Exams");

		// Create (negative): empty content is rejected with a helpful message
		int pEmpty = pm.createPost("", "alfredo", "General");
		performTest(2, "Create a post with empty content is rejected (-1)", pEmpty == -1);
		performTest(3, "Rejected empty post produces a helpful error message",
				!pm.getLastErrorMessage().isEmpty());

		// Create (negative): content over 2000 characters is rejected
		String tooLong = "a".repeat(2001);
		int pLong = pm.createPost(tooLong, "alfredo", "General");
		performTest(4, "Create a post over 2000 characters is rejected (-1)", pLong == -1);

		// Read: an existing post is returned with the expected content
		Post readP1 = pm.readPost(p1);
		performTest(5, "Read an existing post returns the correct post",
				readP1 != null && readP1.getContent().contains("H2 database"));

		// Read (negative): a non-existent id returns null
		performTest(6, "Read a non-existent post returns null", pm.readPost(9999) == null);

		// Update: valid new content replaces the old content
		String upResult = pm.updatePost(p1, "How do I install and configure H2 in Eclipse 2026?");
		performTest(7, "Update an existing post with valid content succeeds", upResult.isEmpty());
		performTest(8, "Updated post shows the new content",
				pm.readPost(p1).getContent().contains("configure H2"));

		// Update (negative): empty content is rejected and the post is unchanged
		String upEmpty = pm.updatePost(p1, "");
		performTest(9, "Update with empty content is rejected with a message", !upEmpty.isEmpty());

		// Update (negative): updating a non-existent post reports it
		String upMissing = pm.updatePost(9999, "anything");
		performTest(10, "Update a non-existent post reports it was not found",
				!upMissing.isEmpty());

		// Search by keyword: matches several
		List<Post> generalMatches = pm.searchByKeyword("a");
		performTest(11, "Search by a common keyword returns several posts",
				generalMatches.size() >= 2);

		// Search by keyword: matches exactly one
		List<Post> examMatches = pm.searchByKeyword("exam");
		performTest(12, "Search by keyword 'exam' returns exactly one post",
				examMatches.size() == 1);

		// Search by keyword: matches none (the empty subset)
		List<Post> noMatches = pm.searchByKeyword("zzzzz");
		performTest(13, "Search with no matches returns an empty list", noMatches.isEmpty());

		// Search by author: returns that author's posts
		List<Post> samPosts = pm.searchByAuthor("molly");
		performTest(14, "Search by author 'molly' returns that author's post",
				samPosts.size() == 1);

		// Delete: an existing post is removed
		boolean delP3 = pm.deletePost(p3);
		performTest(15, "Delete an existing post returns true", delP3);
		performTest(16, "Deleted post can no longer be read", pm.readPost(p3) == null);

		// Delete (negative): deleting a non-existent post returns false
		performTest(17, "Delete a non-existent post returns false", !pm.deletePost(9999));

		/************** Reply CRUD, validation, and threading tests **************/
		System.out.println("\n========== Reply Tests ==========");

		// Create: a valid reply to a post (parentIsPost = true)
		int r1 = rm.createReply("You need to add the H2 jar to your build path.", "molly", p1, true);
		performTest(18, "Create a valid reply to a post returns a positive id", r1 > 0);

		// Create another reply to the same post so the threading query has more than one
		int r2 = rm.createReply("Also make sure H2 is in your root directory.", "joseph", p1, true);

		// Create (negative): empty reply content is rejected
		int rEmpty = rm.createReply("", "kylie", p1, true);
		performTest(19, "Create a reply with empty content is rejected (-1)", rEmpty == -1);

		// Create: a reply to another reply (parentIsPost = false) -- the reply-to-a-reply case
		int r3 = rm.createReply("Where exactly is the root directory on Windows?", "alfredo",
				r1, false);
		performTest(20, "Create a reply to another reply returns a positive id", r3 > 0);

		// Threading: all replies to the post
		List<Reply> repliesToPost = rm.getRepliesTo(p1, true);
		performTest(21, "Get replies to the post returns both post-replies",
				repliesToPost.size() == 2);

		// Threading: replies to the reply (the nested case)
		List<Reply> repliesToReply = rm.getRepliesTo(r1, false);
		performTest(22, "Get replies to a reply returns the nested reply",
				repliesToReply.size() == 1);

		// Threading: a parent with no replies returns the empty subset
		List<Reply> noReplies = rm.getRepliesTo(p2, true);
		performTest(23, "Get replies to a post with none returns an empty list",
				noReplies.isEmpty());

		// Update: a reply's content can be updated
		String upR1 = rm.updateReply(r1, "Add the H2 jar to your project's build path.");
		performTest(24, "Update an existing reply with valid content succeeds", upR1.isEmpty());

		// Search replies by keyword
		List<Reply> h2Replies = rm.searchByKeyword("H2");
		performTest(25, "Search replies by keyword 'H2' returns matching replies",
				h2Replies.size() >= 1);

		// Delete: an existing reply is removed
		performTest(26, "Delete an existing reply returns true", rm.deleteReply(r2));

		/************** Report footer **************/
		System.out.println("____________________________________________________________________________");
		System.out.println();
		System.out.println("Number of tests passed: " + numPassed);
		System.out.println("Number of tests failed: " + numFailed);
	}

	/*
	 * This method displays a single test case's number and description, records whether it passed
	 * or failed based on the asserted condition, and updates the pass/fail counters.  Keeping the
	 * assertion in the caller (rather than running the operation here) lets each CRUD test check
	 * whatever is appropriate for it -- a return value, a list size, or the content of a fetched
	 * item.
	 */
	private static void performTest(int testCase, String description, boolean passed) {
		System.out.println("____________________________________________________________________________\n\nTest case: " + testCase);
		System.out.println("Testing: " + description);
		if (passed) {
			System.out.println("***Success*** This test passed.");
			numPassed++;
		} else {
			System.out.println("***Failure*** This test did not pass.");
			numFailed++;
		}
	}
}