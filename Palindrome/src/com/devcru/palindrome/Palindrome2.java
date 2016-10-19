package com.devcru.palindrome;

import java.util.Scanner;

public class Palindrome2 {

	private static String original = "", reverse = "";

	public static void main(String[] args) {

		// String original = "", reverse = "";

		System.out.println("Enter a string!  Spaces will be parsed out.");

		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);

		original = in.nextLine();

		original = original.replaceAll("\\s", "");

		System.out.println("You have entered: " + original);

		/*
		 * We subtract 1 from length because length begins at 1, whereas arrays
		 * and objects start at 0. In the case of "hello" the length is 5 chars.
		 * However, the index of 'h' begins at index 0. So o is index 4.
		 * 
		 * So for the for loop with original equal to "hello", we initialize i
		 * at 5 - 1 = 4 characters. And we say that for as long as i is greater
		 * than or equal to 0, we decrement 1.
		 * 
		 * We subtract i because we assign reverse the character at i (in this
		 * case, i represents an index for a character in original)
		 */

		System.out.println("original's character-length is: "
				+ original.length());

		for (int i = original.length() - 1; i >= 0; i--) {
			reverse = reverse + original.charAt(i);
		}

		/*
		 * So right now, reverse is a mirror-image of original. Which means if
		 * it were a palindrome, a content comparison where original is equal to
		 * reverse will evaluate to true.
		 */

		System.out.println(original);
		System.out.println(reverse);

		System.out.println("Is it a palindrome?  SURVEY SAYS... "
				+ isAPalindrome() + "!");

	}

	public static boolean isAPalindrome() {
		if (original.equals(reverse)) {
			return true;
		} else {
			return false;
		}
	}
}