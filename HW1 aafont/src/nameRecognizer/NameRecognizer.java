package nameRecognizer;

/*******
 * <p> Title: NameRecognizer Class. </p>
 * 
 * <p> Description: A validator for first and last name input fields.  A name is required, must
 * not exceed 32 characters, and may contain only letters, spaces, hyphens, and apostrophes.
 * Digits and other symbols are not allowed.  This validator follows the same empty-string-means-
 * valid convention used by the UserNameRecognizer so it integrates the same way.</p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2025 </p>
 * 
 * @author Alfredo Afont
 * 
 * @version 1.00		2025-08-20 Initial version
 * 
 */
public class NameRecognizer {

	/**********
	 * <p> Method: String checkForValidName(String input) </p>
	 * 
	 * <p> Description: Check a name against the name input rules and return a helpful error
	 * message describing the first problem found, or an empty string if the name is valid.</p>
	 * 
	 * @param input		The name to be validated
	 * 
	 * @return			An empty string if the name is valid, otherwise a description of the error
	 * 
	 */
	public static String checkForValidName(String input) {
		// A name is required, so an empty field is an error
		if (input.isEmpty())
			return "*** Error *** A name is required.";

		// A name must not exceed the maximum of 32 characters.  Checking an upper bound on text
		// input also guards against an attacker dropping a huge string into the field.
		if (input.length() > 32)
			return "*** Error *** A name must not exceed 32 characters.";

		// Scan each character; only letters, spaces, hyphens, and apostrophes are allowed
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			boolean isLetter = (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
			boolean isAllowedPunctuation = (c == ' ' || c == '-' || c == '\'');
			if (!isLetter && !isAllowedPunctuation) {
				// A digit gets its own message; anything else is reported as a symbol, since the
				// rules call these two cases out separately
				if (c >= '0' && c <= '9')
					return "*** Error *** A name cannot contain numbers.";
				return "*** Error *** A name cannot contain symbols.";
			}
		}

		// Every character was valid
		return "";
	}
}