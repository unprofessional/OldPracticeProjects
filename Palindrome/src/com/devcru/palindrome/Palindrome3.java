package com.devcru.palindrome;

import java.util.Scanner;

public class Palindrome3 {

	// Making use of StringBuilder and regex.
	
	private static String pattern = "\\s|\\.|\\,";
	private static String pattern2 = "[\\s\\.\\,]";

	private static String palindrome;

	private static StringBuilder reverse = new StringBuilder();

	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		System.out
				.println("Please enter a string.  All spaces will be parsed out.");

		palindrome = scan.nextLine();
		palindrome = palindrome.replaceAll(pattern2, "");

		System.out.println("You have entered: " + palindrome);

		System.out.println("Before assignment, value of reverse: " + reverse);

		System.out.println("Is it a palindrome?  Survey says... "
				+ palindromeTF(returnPalindrome()).toString().toUpperCase()
				+ "!");

		System.out.println("After assignment, value of reverse: " + reverse);

	}

	public static String returnPalindrome() {

		for (int i = palindrome.length() - 1; i >= 0; i--) {

			reverse.append(palindrome.charAt(i));

		}

		return reverse.toString();

	}

	/*
	 * StringBuilder only returns true when passed the same object as an
	 * argument. So in returnPalindrome, we returned the value as a String via
	 * the toString() method. This value is then passed in as a String parameter
	 * to palindromeTF().
	 */

	public static Boolean palindromeTF(String x) {

		if (palindrome.equals(x.toString())) {
			return true;
		}
		return false;

	}
}