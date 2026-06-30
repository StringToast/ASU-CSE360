package testClasses;

/**
 * This class lists the manual tests for the Student Post GUI.
 *
 * <p>
 * Manual tests are tests that a person performs by clicking through the program.
 * These tests check whether a student can use the post screen correctly.
 * </p>
 *
 * <p>
 * This class is documented with Javadoc so the generated HTML page can be placed
 * in the {@code StudentPostTests_Javadocs} folder for TP2 Task 6. The methods do
 * not contain automated code because each test is performed manually through the
 * graphical user interface.
 * </p>
 *
 * @author Joseph Banda
 * @version 1.0
 */
public class StudentPostManualTests {

    /**
     * Manual Test MT-01: Create a post.
     *
     * <p><b>Requirement Tested:</b>
     * A student shall be able to create a new discussion post.</p>
     *
     * <p><b>Purpose:</b>
     * This test verifies that the Student Post GUI accepts valid post information
     * and creates a new post that appears on the screen.</p>
     *
     * <p><b>Steps:</b></p>
     * <ol>
     *   <li>Open the program.</li>
     *   <li>Log in as a student.</li>
     *   <li>Go to the student post page.</li>
     *   <li>Enter a valid post title.</li>
     *   <li>Enter valid post content.</li>
     *   <li>Click the button to create the post.</li>
     * </ol>
     *
     * <p><b>Expected Result:</b>
     * The post is created and shown on the screen.</p>
     *
     * <p><b>Pass Criteria:</b>
     * The new post appears in the list of posts and no error message is shown.</p>
     */
    public void manualTestCreatePost() {
        // This is a manual test, so there is no automated code here.
    }

    /**
     * Manual Test MT-02: View existing posts.
     *
     * <p><b>Requirement Tested:</b>
     * A student shall be able to view discussion posts that have already been created.</p>
     *
     * <p><b>Purpose:</b>
     * This test verifies that the Student Post GUI displays existing posts so the
     * student can read and participate in discussions.</p>
     *
     * <p><b>Steps:</b></p>
     * <ol>
     *   <li>Open the program.</li>
     *   <li>Log in as a student.</li>
     *   <li>Go to the student post page.</li>
     *   <li>Observe the list of available posts.</li>
     *   <li>Select or open a post.</li>
     * </ol>
     *
     * <p><b>Expected Result:</b>
     * Existing posts are displayed, and the selected post can be viewed.</p>
     *
     * <p><b>Pass Criteria:</b>
     * The post title and content are readable on the screen.</p>
     */
    public void manualTestViewPosts() {
        // This is a manual test, so there is no automated code here.
    }

    /**
     * Manual Test MT-03: Update a post.
     *
     * <p><b>Requirement Tested:</b>
     * A student shall be able to edit or update a post when allowed by the system.</p>
     *
     * <p><b>Purpose:</b>
     * This test verifies that a valid post update is accepted and that the changed
     * information is shown after saving.</p>
     *
     * <p><b>Steps:</b></p>
     * <ol>
     *   <li>Open the student post page.</li>
     *   <li>Select an existing post.</li>
     *   <li>Click the edit or update option.</li>
     *   <li>Change the title and/or content.</li>
     *   <li>Save the changes.</li>
     * </ol>
     *
     * <p><b>Expected Result:</b>
     * The updated post information is saved and displayed.</p>
     *
     * <p><b>Pass Criteria:</b>
     * The post displays the updated title or content after the save operation.</p>
     */
    public void manualTestUpdatePost() {
        // This is a manual test, so there is no automated code here.
    }

    /**
     * Manual Test MT-04: Delete a post.
     *
     * <p><b>Requirement Tested:</b>
     * A student shall be able to delete or remove a post when allowed by the system.</p>
     *
     * <p><b>Purpose:</b>
     * This test verifies that the delete function removes the selected post from
     * the visible post list or marks it as deleted according to the design.</p>
     *
     * <p><b>Steps:</b></p>
     * <ol>
     *   <li>Open the student post page.</li>
     *   <li>Select a post.</li>
     *   <li>Click the delete button.</li>
     *   <li>Confirm the delete action if the program asks for confirmation.</li>
     * </ol>
     *
     * <p><b>Expected Result:</b>
     * The post is marked as deleted or removed from the list.</p>
     *
     * <p><b>Pass Criteria:</b>
     * The deleted post no longer appears as an active post.</p>
     */
    public void manualTestDeletePost() {
        // This is a manual test, so there is no automated code here.
    }

    /**
     * Manual Test MT-05: Create a reply.
     *
     * <p><b>Requirement Tested:</b>
     * A student shall be able to reply to an existing discussion post.</p>
     *
     * <p><b>Purpose:</b>
     * This test verifies that valid reply content can be added to a selected post.</p>
     *
     * <p><b>Steps:</b></p>
     * <ol>
     *   <li>Open the student post page.</li>
     *   <li>Select an existing post.</li>
     *   <li>Enter valid reply content.</li>
     *   <li>Click the button to submit the reply.</li>
     * </ol>
     *
     * <p><b>Expected Result:</b>
     * The reply is created and displayed under the selected post.</p>
     *
     * <p><b>Pass Criteria:</b>
     * The reply appears with the correct content and is connected to the correct post.</p>
     */
    public void manualTestCreateReply() {
        // This is a manual test, so there is no automated code here.
    }

    /**
     * Manual Test MT-06: View replies.
     *
     * <p><b>Requirement Tested:</b>
     * A student shall be able to view replies in a discussion thread.</p>
     *
     * <p><b>Purpose:</b>
     * This test verifies that replies connected to a post are displayed for the user.</p>
     *
     * <p><b>Steps:</b></p>
     * <ol>
     *   <li>Open the student post page.</li>
     *   <li>Select a post that has one or more replies.</li>
     *   <li>Review the replies displayed under the post.</li>
     * </ol>
     *
     * <p><b>Expected Result:</b>
     * Replies for the selected post are shown on the screen.</p>
     *
     * <p><b>Pass Criteria:</b>
     * The expected reply content appears below the correct post.</p>
     */
    public void manualTestViewReplies() {
        // This is a manual test, so there is no automated code here.
    }

    /**
     * Manual Test MT-07: Update a reply.
     *
     * <p><b>Requirement Tested:</b>
     * A student shall be able to edit or update a reply when allowed by the system.</p>
     *
     * <p><b>Purpose:</b>
     * This test verifies that reply edits are saved and displayed correctly.</p>
     *
     * <p><b>Steps:</b></p>
     * <ol>
     *   <li>Open a post that contains a reply.</li>
     *   <li>Select the reply to update.</li>
     *   <li>Change the reply content.</li>
     *   <li>Save the updated reply.</li>
     * </ol>
     *
     * <p><b>Expected Result:</b>
     * The reply is updated and the new content is displayed.</p>
     *
     * <p><b>Pass Criteria:</b>
     * The reply shows the updated content after saving.</p>
     */
    public void manualTestUpdateReply() {
        // This is a manual test, so there is no automated code here.
    }

    /**
     * Manual Test MT-08: Delete a reply.
     *
     * <p><b>Requirement Tested:</b>
     * A student shall be able to delete or remove a reply when allowed by the system.</p>
     *
     * <p><b>Purpose:</b>
     * This test verifies that the reply delete function removes the selected reply
     * or marks it as deleted according to the system design.</p>
     *
     * <p><b>Steps:</b></p>
     * <ol>
     *   <li>Open a post that contains a reply.</li>
     *   <li>Select the reply to delete.</li>
     *   <li>Click the delete reply button.</li>
     *   <li>Confirm the delete action if prompted.</li>
     * </ol>
     *
     * <p><b>Expected Result:</b>
     * The reply is removed or marked as deleted.</p>
     *
     * <p><b>Pass Criteria:</b>
     * The reply no longer appears as an active reply under the post.</p>
     */
    public void manualTestDeleteReply() {
        // This is a manual test, so there is no automated code here.
    }

    /**
     * Manual Test MT-09: Try to create a post with no title.
     *
     * <p><b>Requirement Tested:</b>
     * The system shall validate that a post title is not empty.</p>
     *
     * <p><b>Purpose:</b>
     * This negative test verifies that the system prevents invalid posts from
     * being created when the title field is blank.</p>
     *
     * <p><b>Steps:</b></p>
     * <ol>
     *   <li>Open the student post page.</li>
     *   <li>Leave the post title blank.</li>
     *   <li>Enter valid post content.</li>
     *   <li>Click the button to create the post.</li>
     * </ol>
     *
     * <p><b>Expected Result:</b>
     * The program shows an error message and does not create the post.</p>
     *
     * <p><b>Pass Criteria:</b>
     * A message such as "Title cannot be empty" appears and no blank-title post is saved.</p>
     */
    public void manualTestEmptyPostTitle() {
        // This is a manual test, so there is no automated code here.
    }

    /**
     * Manual Test MT-10: Try to create a post with no content.
     *
     * <p><b>Requirement Tested:</b>
     * The system shall validate that post content is not empty.</p>
     *
     * <p><b>Purpose:</b>
     * This negative test verifies that the system rejects a post when the content
     * field is blank.</p>
     *
     * <p><b>Steps:</b></p>
     * <ol>
     *   <li>Open the student post page.</li>
     *   <li>Enter a valid post title.</li>
     *   <li>Leave the post content blank.</li>
     *   <li>Click the button to create the post.</li>
     * </ol>
     *
     * <p><b>Expected Result:</b>
     * The program shows an error message and the empty post is not created.</p>
     *
     * <p><b>Pass Criteria:</b>
     * A message such as "Post content cannot be empty" appears and no empty post is saved.</p>
     */
    public void manualTestEmptyPostContent() {
        // This is a manual test, so there is no automated code here.
    }

    /**
     * Manual Test MT-11: Try to create an empty reply.
     *
     * <p><b>Requirement Tested:</b>
     * The system shall validate that reply content is not empty.</p>
     *
     * <p><b>Purpose:</b>
     * This negative test verifies that the system rejects blank replies.</p>
     *
     * <p><b>Steps:</b></p>
     * <ol>
     *   <li>Open the student post page.</li>
     *   <li>Select a valid post.</li>
     *   <li>Leave the reply content blank.</li>
     *   <li>Click the button to submit the reply.</li>
     * </ol>
     *
     * <p><b>Expected Result:</b>
     * The program shows an error message and the empty reply is not created.</p>
     *
     * <p><b>Pass Criteria:</b>
     * A message such as "Reply cannot be empty" appears and no blank reply is saved.</p>
     */
    public void manualTestEmptyReplyContent() {
        // This is a manual test, so there is no automated code here.
    }

    /**
     * Manual Test MT-12: Attempt to update a post with invalid input.
     *
     * <p><b>Requirement Tested:</b>
     * The system shall reject invalid post updates and protect existing valid data.</p>
     *
     * <p><b>Purpose:</b>
     * This negative test verifies that the user cannot update a post to invalid
     * values such as a blank title or blank content.</p>
     *
     * <p><b>Steps:</b></p>
     * <ol>
     *   <li>Open the student post page.</li>
     *   <li>Select an existing post.</li>
     *   <li>Click edit or update.</li>
     *   <li>Clear the title or content field.</li>
     *   <li>Try to save the update.</li>
     * </ol>
     *
     * <p><b>Expected Result:</b>
     * The system rejects the update and shows a validation message.</p>
     *
     * <p><b>Pass Criteria:</b>
     * The original valid post remains unchanged after the failed update.</p>
     */
    public void manualTestInvalidPostUpdateInput() {
        // This is a manual test, so there is no automated code here.
    }

    /**
     * Manual Test MT-13: Multiple replies on one post.
     *
     * <p><b>Requirement Tested:</b>
     * The system shall support a discussion thread where one post can have multiple replies.</p>
     *
     * <p><b>Purpose:</b>
     * This test verifies that the system can display more than one reply under
     * the same post and keep them associated with the correct post.</p>
     *
     * <p><b>Steps:</b></p>
     * <ol>
     *   <li>Open the student post page.</li>
     *   <li>Select one existing post.</li>
     *   <li>Create two or more valid replies for that post.</li>
     *   <li>Refresh or reopen the post if necessary.</li>
     * </ol>
     *
     * <p><b>Expected Result:</b>
     * All replies are shown under the selected post.</p>
     *
     * <p><b>Pass Criteria:</b>
     * Each reply is visible and no reply appears under the wrong post.</p>
     */
    public void manualTestMultipleRepliesOnPost() {
        // This is a manual test, so there is no automated code here.
    }

    /**
     * Manual Test MT-14: Mark a post as read.
     *
     * <p><b>Requirement Tested:</b>
     * The system shall allow a student to identify a post as read after opening it.</p>
     *
     * <p><b>Purpose:</b>
     * This test verifies that opening or selecting a post changes its read status
     * for the current student if that feature is implemented in the GUI.</p>
     *
     * <p><b>Steps:</b></p>
     * <ol>
     *   <li>Open the student post page.</li>
     *   <li>Select or open an unread post.</li>
     *   <li>Return to the post list.</li>
     * </ol>
     *
     * <p><b>Expected Result:</b>
     * The program marks the post as read for that user.</p>
     *
     * <p><b>Pass Criteria:</b>
     * The post no longer appears as unread for the same student.</p>
     */
    public void manualTestMarkPostAsRead() {
        // This is a manual test, so there is no automated code here.
    }

    /**
     * Manual Test MT-15: Confirm that deleted content does not break the post list.
     *
     * <p><b>Requirement Tested:</b>
     * The system shall handle deleted posts or replies safely without crashing
     * the discussion interface.</p>
     *
     * <p><b>Purpose:</b>
     * This test verifies that after a post or reply is deleted, the GUI still
     * loads correctly and the remaining discussion content is still usable.</p>
     *
     * <p><b>Steps:</b></p>
     * <ol>
     *   <li>Create a valid post and at least one valid reply.</li>
     *   <li>Delete the reply or delete the post.</li>
     *   <li>Refresh or reopen the student post page.</li>
     *   <li>Try to view remaining posts and replies.</li>
     * </ol>
     *
     * <p><b>Expected Result:</b>
     * The GUI continues to display remaining valid discussion content.</p>
     *
     * <p><b>Pass Criteria:</b>
     * The program does not crash, and remaining posts or replies are still readable.</p>
     */
    public void manualTestDeletedContentDoesNotBreakList() {
        // This is a manual test, so there is no automated code here.
    }
}
