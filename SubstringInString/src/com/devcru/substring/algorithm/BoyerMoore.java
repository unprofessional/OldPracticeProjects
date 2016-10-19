package com.devcru.substring.algorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BoyerMoore {
	private final int R; // the radix
	private int[] right; // the bad-character skip array
	private String pattern; // or as a string
	
	private static int count = 0;
	
	// test client
	public static void main(String[] args) throws IOException {
		String pattern = "unprofessional";
		String source = "";
		
		// Preprocess file into string
		ArrayList<String> lines = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader("stuff.txt"));
		String line;
		while((line = br.readLine()) != null) {
			lines.add(line);
		}
		for (int i = 0; i < lines.size(); i++) {
			source += lines.get(i);
		}
		br.close();

		// Execute and time it
		long startTime = System.nanoTime();
		BoyerMoore boyermoore1 = new BoyerMoore(pattern);
		ArrayList<Integer> offset = boyermoore1.search(source);
		long endTime = System.nanoTime();

		// Print results
		//System.out.println("source: " + source);
		System.out.println("Offset/Index: " + offset);
		System.out.println(count + " instances of " + pattern + " found!");
		System.out.println("runtime(nano): " + (endTime - startTime));
		System.out.println("runtime(ms): " + ((endTime - startTime)/1000000)); // milliseconds
	}

	// Constructor for supplied pattern to pre-process
	public BoyerMoore(String pat) {
		this.R = 15000;
		this.pattern = pat;

		// position of rightmost occurrence of c in the pattern
		right = new int[R];
		for (int c = 0; c < R; c++)
			right[c] = -1;
		for (int j = 0; j < pat.length(); j++)
			right[pat.charAt(j)] = j;
	}

	// return offset of first match; N if no match
	public ArrayList<Integer> search(String source) {
		int M = pattern.length();
		int N = source.length();
		ArrayList<Integer> newArrayInt = new ArrayList<Integer>();
		int skip;
		for (int i = 0; i <= N - M; i += skip) {
			skip = 0;
			for (int j = M - 1; j >= 0; j--) {
				if (pattern.charAt(j) != source.charAt(i + j)) {
					skip = Math.max(1, j - right[source.charAt(i + j)]);
					break;
				}
			}
			if (skip == 0) {
				newArrayInt.add(i); // found
				count++;
				skip++;
			}
		}
		return newArrayInt; // not found
	}
}