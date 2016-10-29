package com.devcru.findmissingnum;

import com.devcru.findmissingnum.utils.NumArrayGenerator;

public class FindMissingNum {
	
	static int ARRAY_LENGTH = 100001; // offset by 1 to account for index/value removed from array on generation
	static int missingNum[] = new int[ARRAY_LENGTH];
	
	public static void main(String[] args) {
		
		System.out.println("missingNum.length: " + missingNum.length);
		
		long startTime = 0;
		long endTime = 0;
		
		startTime = System.nanoTime();
		missingNum = NumArrayGenerator.generateArray(missingNum);
		endTime = System.nanoTime();
		System.out.println("runtime(nano): " + (endTime - startTime));
		System.out.println("runtime(ms): " + (endTime - startTime)/1000000);
		
		System.out.println();
		
		startTime = System.nanoTime();
		System.out.println("(NAIVE 1) FINAL RESULT: " + FindMissingNumNaive.bruteForceMissingNum(missingNum));
		endTime = System.nanoTime();
		System.out.println("runtime(nano): " + (endTime - startTime));
		System.out.println("runtime(ms): " + (endTime - startTime)/1000000);
		
		System.out.println();
		
		startTime = System.nanoTime();
		System.out.println("(NAIVE 2) FINAL RESULT: " + FindMissingNumNaive.calculateMissingNum(missingNum));
		endTime = System.nanoTime();
		System.out.println("runtime(nano): " + (endTime - startTime));
		System.out.println("runtime(ms): " + (endTime - startTime)/1000000);
		
		System.out.println();
		
		startTime = System.nanoTime();
		System.out.println("(BEST SOLUTION) FINAL RESULT: " + recursiveSolution(0, missingNum.length));
		endTime = System.nanoTime();
		System.out.println("runtime(nano): " + (endTime - startTime));
		System.out.println("runtime(ms): " + (endTime - startTime)/1000000);
		
	}
	
	// Recursive range testing
	public static int recursiveSolution(int indexA, int indexB) {
		
		int midIndex = (indexA + indexB)/2;
		int midValue = missingNum[midIndex];
		
		// Currently set up for evens, not odds
		int answer = 9998;
		
		if(missingNum[indexB - 1] - missingNum[indexA] == 2) {
			answer = missingNum[indexA] + 1;
			//System.out.println("ANSWER FOUND! " + answer);
			return answer;
		} else {
			
			boolean inFirstHalf = midIndex != midValue;
			
//			System.out.println("Does " + midIndex + " != " + midValue + "? " + inFirstHalf);
			
			if(inFirstHalf) {
//				System.out.println("Problem is in first half");
//				System.out.println("Checking range(" + indexA + ", " + midIndex + ")");
//				System.out.println();
				return recursiveSolution(indexA, midIndex);
			} else {
//				System.out.println("Problem is in second half");
//				System.out.println("Checking range(" + midIndex + ", " + indexB + ")");
//				System.out.println();
				return recursiveSolution(midIndex, indexB);
			}
		}
	}
		

}
