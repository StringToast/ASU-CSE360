package applicationMain;

import usernameRecognizer.UserNameRecognizer;	
import passwordPopUpWindow.Model;				

/*******
 * <p> Title: FoundationsMainTestingAutomation Class. </p>
 * 
 * <p> Description: A Java set of semi-automated test cases for the input validation used by the
 * Foundations application.  This class exercises the two validators that the application uses to
 * check user input before it is accepted: the username recognizer and the password evaluator.
 * Each validator is a pure function (input string in, error string out), which makes it possible
 * to test it here without involving the GUI.  This class follows the same structure as the
 * provided PasswordEvaluationTestingAutomation class.</p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2025 </p>
 * 
 * @author Alfredo Afont
 * 
 * @version 1.00		2025-08-20 Initial version
 * 
 */
public class FoundationsMainTestingAutomation {
	
	static int numPassed = 0;	// Counter of the number of passed tests
	static int numFailed = 0;	// Counter of the number of failed tests

	/*
	 * This mainline displays a header, runs the username test cases, runs the password test
	 * cases, and then displays a footer summarizing the results.
	 */
	public static void main(String[] args) {
		System.out.println("______________________________________");
		System.out.println("\nFoundations Input Validation Testing Automation");

		// Username Recognizer Test Cases
		System.out.println("\n========== Username Recognizer Tests ==========");
		
		// Positive tests,  these usernames should be accepted
		performUsernameTestCase(1, "abcd", true);      // simple valid name, minimum length
		performUsernameTestCase(2, "john123", true);   // letters followed by digits
		performUsernameTestCase(3, "one.two", true);   // valid period separator
		performUsernameTestCase(4, "one&two", true);   // valid ampersand separator (new rule)
		performUsernameTestCase(5, "a1b2c3d4", true);  // mix of letters and digits

		// Negative tests these usernames should be rejected
		performUsernameTestCase(6, "1abc", false);     // starts with a digit (new rule)
		performUsernameTestCase(7, "ab", false);       // too short (fewer than 4)
		performUsernameTestCase(8, "abcdefghijklmnopq", false); // too long (more than 16)
		performUsernameTestCase(9, "one&&two", false); // two separators in a row
		performUsernameTestCase(10, "one&", false);    // ends with a separator
		performUsernameTestCase(11, "", false);        // empty input

		//Password evaluator test cases
		System.out.println("\n========== Password Evaluator Tests ==========");
		
		// Positive tests, these passwords should be accepted
		performPasswordTestCase(12, "Aa!15678", true); // upper, lower, special, digit, 8 chars
		performPasswordTestCase(13, "Aaaaaa1!", true); // all requirements, exactly 8 chars

		// Negative tests, these passwords should be rejected
		performPasswordTestCase(14, "A!", false);       // too short, missing requirements
		performPasswordTestCase(15, "", false);         // empty input
		performPasswordTestCase(16, "aaaaaaaa", false); // no upper, no digit, no special
		performPasswordTestCase(17, "Aaaaaaaa", false); // no digit, no special
		performPasswordTestCase(18, "Aaaaaaa1", false); // no special character
		performPasswordTestCase(19, "Aaaaa1aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", false); // password too long

		// Report footer
		System.out.println("____________________________________________________________________________");
		System.out.println();
		System.out.println("Number of tests passed: " + numPassed);
		System.out.println("Number of tests failed: " + numFailed);
	}
	
	/*
	 * This method runs a single username test case.  It feeds the input to the same recognizer the
	 * application uses, interprets the returned value (empty means valid, non-empty means an error
	 * message), and compares the result against what the test case expected.
	 */
	private static void performUsernameTestCase(int testCase, String inputText,
			boolean expectedPass) {
		System.out.println("____________________________________________________________________________\n\nTest case: " + testCase);
		System.out.println("Type: Username");
		System.out.println("Input: \"" + inputText + "\"");
		System.out.println("______________");

		// Call the same recognizer the application uses to validate usernames
		String resultText = UserNameRecognizer.checkForValidUserName(inputText);
		System.out.println();

		// A non-empty result means the recognizer rejected the input
		if (resultText != "") {
			if (expectedPass) {
				System.out.println("***Failure*** The username <" + inputText + "> is invalid," +
						"\nbut it was supposed to be valid, so this is a failure!\n");
				System.out.println("Error message: " + resultText);
				numFailed++;
			} else {
				System.out.println("***Success*** The username <" + inputText + "> is invalid," +
						"\nand it was supposed to be invalid, so this is a pass!\n");
				System.out.println("Error message: " + resultText);
				numPassed++;
			}
		}
		// An empty result means the recognizer accepted the input
		else {
			if (expectedPass) {
				System.out.println("***Success*** The username <" + inputText +
						"> is valid, so this is a pass!");
				numPassed++;
			} else {
				System.out.println("***Failure*** The username <" + inputText +
						"> was judged valid," +
						"\nbut it was supposed to be invalid, so this is a failure!");
				numFailed++;
			}
		}
	}
	
	/*
	 * This method runs a single password test case.  It feeds the input to the same evaluator the
	 * application uses, interprets the returned value (empty means valid, non-empty means an error
	 * message), and compares the result against what the test case expected.
	 */
	private static void performPasswordTestCase(int testCase, String inputText,
			boolean expectedPass) {
		System.out.println("____________________________________________________________________________\n\nTest case: " + testCase);
		System.out.println("Type: Password");
		System.out.println("Input: \"" + inputText + "\"");
		System.out.println("______________");

		// Call the same evaluator the application uses to validate passwords
		String resultText = Model.evaluatePassword(inputText);
		System.out.println();

		// A non-empty result means the evaluator rejected the input
		if (resultText != "") {
			if (expectedPass) {
				System.out.println("***Failure*** The password <" + inputText + "> is invalid," +
						"\nbut it was supposed to be valid, so this is a failure!\n");
				System.out.println("Error message: " + resultText);
				numFailed++;
			} else {
				System.out.println("***Success*** The password <" + inputText + "> is invalid," +
						"\nand it was supposed to be invalid, so this is a pass!\n");
				System.out.println("Error message: " + resultText);
				numPassed++;
			}
		}
		// An empty result means the evaluator accepted the input
		else {
			if (expectedPass) {
				System.out.println("***Success*** The password <" + inputText +
						"> is valid, so this is a pass!");
				numPassed++;
			} else {
				System.out.println("***Failure*** The password <" + inputText +
						"> was judged valid," +
						"\nbut it was supposed to be invalid, so this is a failure!");
				numFailed++;
			}
		}
	}
}