package testClasses;

import entityClasses.Post;

/**
 * This class tests the Post class.
 * 
 * These are semi-automated tests.
 * That means we run this file, and it prints PASS or FAIL.
 */
public class StudentPostSemiAutomatedTests {

    // These numbers keep track of how many tests pass or fail.
    private static int testsPassed = 0;
    private static int testsFailed = 0;

    /**
     * This is where the program starts.
     * 
     * It runs each test one by one.
     */
    public static void main(String[] args) {

        System.out.println("Running Student Post tests...\n");

        testCreatePost();
        testEmptyContent();
        testLongContent();
        testValidPostType();
        testInvalidPostType();
        testDeletePost();
        testMarkPostAsRead();
        testChangeContent();

        System.out.println("\nTesting finished.");
        System.out.println("Tests passed: " + testsPassed);
        System.out.println("Tests failed: " + testsFailed);
    }

    /**
     * This test checks if a post can be created.
     */
    private static void testCreatePost() {

        // Create a new post.
        Post post = new Post(
                1,
                "This is a test post.",
                "student01",
                "General",
                "Question"
        );

        // Check if the post saved the correct information.
        boolean passed =
                post.getId() == 1
                && post.getContent().equals("This is a test post.")
                && post.getAuthor().equals("student01")
                && post.getThread().equals("General")
                && post.getPostType().equals("Question")
                && post.isDeleted() == false;

        printResult(passed, "testCreatePost");
    }

    /**
     * This test checks that empty content is not allowed.
     */
    private static void testEmptyContent() {

        // Ask the Post class if empty content is valid.
        String result = Post.checkForValidContent("");

        // If the result is not empty, that means there was an error message.
        // That is good because empty content should be rejected.
        boolean passed = !result.equals("");

        printResult(passed, "testEmptyContent");
    }

    /**
     * This test checks that content over 2000 characters is not allowed.
     */
    private static void testLongContent() {

        // Build a very long post with 2001 characters.
        String longContent = "";

        for (int i = 0; i < 2001; i++) {
            longContent = longContent + "a";
        }

        // Ask the Post class if this long content is valid.
        String result = Post.checkForValidContent(longContent);

        // If the result is not empty, the long content was rejected.
        boolean passed = !result.equals("");

        printResult(passed, "testLongContent");
    }

    /**
     * This test checks that "Question" is a valid post type.
     */
    private static void testValidPostType() {

        // Ask the Post class if "Question" is valid.
        String result = Post.checkForValidType("Question");

        // An empty result means there is no error.
        boolean passed = result.equals("");

        printResult(passed, "testValidPostType");
    }

    /**
     * This test checks that a bad post type is not allowed.
     */
    private static void testInvalidPostType() {

        // "RandomType" should not be allowed.
        String result = Post.checkForValidType("RandomType");

        // If the result is not empty, there was an error message.
        boolean passed = !result.equals("");

        printResult(passed, "testInvalidPostType");
    }

    /**
     * This test checks if a post can be marked as deleted.
     */
    private static void testDeletePost() {

        // Create a post.
        Post post = new Post(
                2,
                "This post will be deleted.",
                "student01",
                "General",
                "Statement"
        );

        // Mark the post as deleted.
        post.markDeleted();

        // Check if the post is now deleted.
        boolean passed = post.isDeleted();

        printResult(passed, "testDeletePost");
    }

    /**
     * This test checks if a user can be marked as having read a post.
     */
    private static void testMarkPostAsRead() {

        // Create a post.
        Post post = new Post(
                3,
                "This post will be marked as read.",
                "student01",
                "General",
                "Question"
        );

        // Mark student02 as having read this post.
        post.markRead("student02");

        // Check if student02 is listed as having read the post.
        boolean passed = post.isReadBy("student02");

        printResult(passed, "testMarkPostAsRead");
    }

    /**
     * This test checks if the content of a post can be changed.
     */
    private static void testChangeContent() {

        // Create a post with old content.
        Post post = new Post(
                4,
                "Old content",
                "student01",
                "General",
                "Statement"
        );

        // Change the content.
        post.setContent("New content");

        // Check if the content changed.
        boolean passed = post.getContent().equals("New content");

        printResult(passed, "testChangeContent");
    }

    /**
     * This helper method prints PASS or FAIL.
     */
    private static void printResult(boolean passed, String testName) {

        if (passed) {
            testsPassed++;
            System.out.println("PASS: " + testName);
        } else {
            testsFailed++;
            System.out.println("FAIL: " + testName);
        }
    }
}
