package com.example.reversespeller;

/**
 * The ReverseString class contains one class method that takes in a string
 * parameter and returns the revered string.
 * 
 * @author Ramin Esfandiari </br>
 *         8. aug. 2017 </br>
 *
 */
public class ReverseString {

	/**
	 * Takes in a string parameter, and returns the reversed string.
	 * 
	 * @param string
	 *            The string to reverse.
	 * @return The reversed string.
	 */
	public static String reverseString(String string) {
		StringBuilder sb = new StringBuilder(); // Instantiate a new StringBuilder object.
		sb.append(string); // Append the string to reverse to the StringBuilder.

		return sb.reverse().toString(); // Reverse the string and return it.
	}
}
