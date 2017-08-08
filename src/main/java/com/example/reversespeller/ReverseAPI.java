package com.example.reversespeller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The ReverseAPI class is the reverse spelling applications' REST service. It
 * contains one method that is mapped to an URI. When this URI is requested, the
 * method is mapped to that URI.</br>
 * <strong>Example:</strong></br>
 * Calling {@link https://reverse-speller.herokuapp.com/api/reverse/text},
 * returns txet.
 * 
 * @author Ramin Esfandiari </br>
 *         8. aug. 2017 </br>
 *
 */
@RestController
public class ReverseAPI {

	/**
	 * Takes in a string parameter, and returns the reverse of the string.
	 * 
	 * @param reverse
	 *            The string to reverse.
	 * @return The reversed string to return.
	 */
	@RequestMapping(value = "/api/reverse/{reverse}")
	public String reverseString(@PathVariable String reverse) {
		return ReverseString.reverseString(reverse);
	}
}
