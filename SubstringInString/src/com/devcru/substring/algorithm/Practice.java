package com.devcru.substring.algorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Practice {
	
	public static void main(String[] args) {
		
		long startTime = System.nanoTime();
		try {
			search("div");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		Practice.BM bm = new Practice().new BM();
//		bm.construct2DTable(9);
		
		long endTime = System.nanoTime();
		
		System.out.println("runtime(nano): " + (endTime - startTime));
		System.out.println("runtime(ms): " + (endTime - startTime)/1000000);
		
	}
	
	public static void search(String pattern) throws IOException {
		
		FileReader fr = new FileReader("stuff.txt");
		BufferedReader br = new BufferedReader(fr);
		
		boolean inHead = true;
		boolean startRecord = false;
		String line = "";
		
		String headContent = "";
		
		/* 
		 * Remember that this loop only happens if inHead is true
		 * which means that this only happens from the beginning to
		 * when </head> is reached, the rest of stuff.txt is ignored
		 */
		while((line = br.readLine()) != null && inHead) {
			
			if(line.toLowerCase().contains("<head>")) {
				inHead = true;
				startRecord = true;
			}
			if(line.toLowerCase().contains("</head>")) {
				inHead = false;
				startRecord = false;
			}
			if(startRecord) {
				headContent += line;
			}
			
		}
		
		br.close();
		
		/*
		 * Now, we could search the post-processed headContent string...
		 * but this will depend on the use-case.  Seems that the more efficient
		 * way to go about it is to doSomething() only once with headContent
		 * as opposed to doSomething() every single time while inHead = true
		 * (such a use-case may include showing the line where it occurs)
		 */
		
		if(headContent.toLowerCase().contains(pattern)) {
			/*
			 * This just finds all occurrences of the pattern in the post-processed
			 * headContent (outside of the initial while loop), once again reducing
			 * need for loop-overhead.
			 * 
			 * Note that it finds the indexOf()... which means it iterates through
			 * the search string naively.  This is most likely where we would implement
			 * the Boyer-Moore algorithm.
			 */
			int mostRecentIndex = 0;
			int count = 0;
			ArrayList<Integer> foundIndices = new ArrayList<Integer>();
			
			while(mostRecentIndex != -1) {
//				System.out.println("1mostRecentIndex: " + mostRecentIndex);
				// Remember: indexOf(String substring, int fromIndex)
				// Remember: When indexOf() can't find it, it returns -1
				mostRecentIndex = headContent.indexOf(pattern, mostRecentIndex);
				if(mostRecentIndex != -1 ) foundIndices.add(mostRecentIndex);
//				System.out.println("2indexOf(): " + mostRecentIndex);
				if(mostRecentIndex != -1){
					count++;
					mostRecentIndex += pattern.length();
					//System.out.println("pattern.length(): " + pattern.length());
//					System.out.println("3concat(): " + mostRecentIndex);
				}
			}
			System.out.println("foundIndices: " + foundIndices);
			System.out.println("Found " + count + " instances of " + pattern + "!");
			
		} else {
			System.out.println(pattern + " not found!");
		}
		
//		System.out.println("headContent:" + headContent);
//		System.out.println("headContent.length(): " + headContent.length());
		
	}
	
	public void boyerMooreStuff() {
		
		String source = "wubbalubbadubdub";
		String pattern = "bad";
		
		// Bad Character Rule
		/*
		 * 
		 */
		//int[][] patternIndices;
		for(int i = 0; i < source.length(); i++) {
			
		}
		
		
		// Good Suffix Rule
		/*
		 * 
		 */
		
		// Search stuff
		/*
		 * 
		 */
		
	}

	protected char[] pattern;
	protected char[] text;

	private class BM {

		private final int radix = 256; // max expected indices
		private int[][] table; // 2D table

		private void construct2DTable(int patternLength) {

			table = new int[radix][patternLength];
			// table = [256][9]
			
			// For all enumerations in radix, fill table with each as indices
			for (int i = 0; i < radix; i++) {
				Arrays.fill(table[i], -1);
				System.out.println(table[i]);
			}
			
			// For all iterations less than the length of pattern
			for (int i = 0; i < patternLength; i++) {
				
				// For all times where j is equal to i;
				// for as long as j is greater than or equal to 0;
				// decrement j by one.
				for (int j = i - 1; j >= 0; j--) {
					// Each iteration of the loop,
					// create cIndex and assign it the int value of pattern[j]
					int cIndex = (int) pattern[j];
					// if j is less than table[cIndex][i]
					if (j > table[cIndex][i]) {
						table[cIndex][i] = j;
					}
				}
			}
			// if not found, still -1
			return;
		}

	}

}
