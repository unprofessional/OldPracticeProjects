package com.devcru.palindrome;

import java.util.Scanner;

public class Palindrome1 {

	static String palindrome = "Hello, world!";
	static String start;
	static String end;

	public static void main(String[] args) {

		System.out.println(palindrome + "\n");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out
				.println("Please enter a string!  Spaces will be parsed out.");

		palindrome = sc.nextLine();
		palindrome = palindrome.replaceAll("\\s+", "");

		System.out.println("\nYou have entered: " + "\n" + palindrome + "\n");

		System.out.println("Is it a Palindrome? Survey says... "
				+ isAPalindrome().toString().toUpperCase() + "!");

		// System.out.println("DEBUG:\n" + "START: " + start + "\n" + "END: "
		// + end);

		// System.out.println(palindrome.substring(1, palindrome.length() - 1));
		prune();
		System.out.println(palindrome);
	}

	public static Boolean isAPalindrome() {

		start = palindrome.substring(0, 1);
		end = palindrome.substring(palindrome.length() - 1);

		// prune();

		if (start.equals(end)) {
			return true;
		}
		return false;

	}

	// Bad code.  Redo logic.
	
	public static void prune() {

		for (int i = 0; i < palindrome.length(); i++) {

			if (palindrome.length() == 1) {
				palindrome = palindrome.substring(0, 1);
			} else if (palindrome.length() > 0) {
				start = palindrome.substring(0, 1);
				end = palindrome.substring(palindrome.length() - 1);

				palindrome = palindrome.substring(1, palindrome.length() - 1);
			}
			/*
			 * else { palindrome = palindrome; }
			 */
		}
	}
}