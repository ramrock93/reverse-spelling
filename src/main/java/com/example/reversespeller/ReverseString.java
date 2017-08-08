package com.example.reversespeller;

public class ReverseString {

	public static String reverseString(String string) {
		StringBuilder sb = new StringBuilder();
		sb.append(string);

		return sb.reverse().toString();
	}
}
