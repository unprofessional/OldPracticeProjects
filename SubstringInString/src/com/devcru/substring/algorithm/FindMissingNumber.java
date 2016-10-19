package com.devcru.substring.algorithm;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class FindMissingNumber {
	
	static int ARRAY_LENGTH = 1001;

	public static void main(String[] args) {
		int missingNum[] = new int[ARRAY_LENGTH];
		long startTime = 0;
		long endTime = 0;
		
		startTime = System.nanoTime();
		missingNum = generateArray(missingNum);
		endTime = System.nanoTime();
		System.out.println("runtime(nano): " + (endTime - startTime));
		System.out.println("runtime(ms): " + (endTime - startTime)/1000000);
		
		System.out.println("missingNum.length: " + missingNum.length);
		System.out.println();
		
		startTime = System.nanoTime();
		bruteForceMissingNum(missingNum);
		endTime = System.nanoTime();
		System.out.println("runtime(nano): " + (endTime - startTime));
		System.out.println("runtime(ms): " + (endTime - startTime)/1000000);
		
		System.out.println();
		
		startTime = System.nanoTime();
		calculateMissingNum(missingNum);
		endTime = System.nanoTime();
		System.out.println("runtime(nano): " + (endTime - startTime));
		System.out.println("runtime(ms): " + (endTime - startTime)/1000000);
		
		System.out.println();
		
		startTime = System.nanoTime();
		splitArrayAndCalcMissingNum(missingNum);
		endTime = System.nanoTime();
		System.out.println("runtime(nano): " + (endTime - startTime));
		System.out.println("runtime(ms): " + (endTime - startTime)/1000000);
	}
	
	public static int[] generateArray(int[] arr) {		
		ArrayList<Integer> arrList = new ArrayList<Integer>();
		// Java 1.7+ implementation
		int random = ThreadLocalRandom.current().nextInt(1, arr.length + 1);
		
		for (int i = 0; i < arr.length; i++) {
			arrList.add(i);
		}
		
		arrList.remove(random);
		
		int[] intArray = new int[arrList.size()];
		for(int i = 0; i < intArray.length; i++) {
			intArray[i] = arrList.get(i).intValue();
		}
		
//		for (int i = 0; i < arr.length; i++) {
//			arr[i] = i + 1;
//		}
//		
//		arr[random] = 0;
		
		System.out.println("Generation complete!");
		
		return intArray;
	}

	public static void bruteForceMissingNum(int[] arr) {
		int temp = 0;

		for (int i = 0; i < arr.length; i++) {

			if (temp != arr[i] - 1 && arr[i] != 0) {
				System.out.println("Missing number found: " + (arr[i] - 1));
			}
			temp = arr[i];

		}
	}

	public static void calculateMissingNum(int[] arr) {
		int actualSum = 0;
		int shouldBeSum = arr.length * (arr.length + 1) / 2;
		for (int i = 0; i < arr.length; i++) {
			actualSum += arr[i];
		}
		System.out.println("shouldBeSum: " + shouldBeSum + ", actualSum:  "
				+ actualSum);
		System.out.println("Missing number (formula): "
				+ (shouldBeSum - actualSum));
	}
	
	public static void splitArrayAndCalcMissingNum(int[] arr) {
		
		int midInd = arr.length/2;
		int midVal = arr[midInd];
		int firstVal = arr[0];
		
		// middle index value should always be predictable no matter the start number
		// provided we use a formula to calculate it based on the start number
		// formula: midVal - firstVal should ALWAYS equal midInd
		// if not, something is amiss in first half of array
		
		System.out.println("middle index: " + midInd);
		System.out.println("mid minus first: " + (midVal - firstVal));
		
		int tempMidInd = midInd;
		int tempMidVal = arr[tempMidInd];
		int tempFirstVal = arr[0];
		
		int tempLastVal = arr[arr.length - 1]; // should be 1000
		
		while(tempMidInd > 100) {
			
			if(tempMidVal - tempFirstVal != tempMidInd) {
				System.out.println("tempMidVal: " + tempMidVal);
				System.out.println("tempMidInd: " + tempMidInd);
				System.out.println("mid minus first: " + (tempMidVal - firstVal));
				System.out.println("Array is out of sequence in first half!");
				int sum = 0;
				int shouldBeSum = tempMidInd * (tempMidInd + 1) / 2;
				for(int i= 0; i < tempMidInd; i++) {
					sum += arr[i];
				}
				System.out.println("sum: " + sum);
				System.out.println("shouldBeSum: " + shouldBeSum);
				System.out.println("diff: " + (shouldBeSum - sum));
			} else {
				System.out.println("tempMidVal: " + tempMidVal);
				System.out.println("tempMidInd: " + tempMidInd);
				System.out.println("mid minus first: " + (tempMidVal - firstVal));
				System.out.println("Array is out of sequence in second half!");
				// TODO: take midInd through lastInd?
				// FIXME: Find formula for calculating second half totals!
				int sum = 0;
				int shouldBeSum = tempMidInd * (tempMidInd + 1) / 2;
				for(int i= 0; i < tempMidInd; i++) {
					sum += arr[i];
				}
				System.out.println("sum: " + sum);
				System.out.println("shouldBeSum: " + shouldBeSum);
				System.out.println("diff: " + (shouldBeSum - sum));
			}
			
//			for(int i = 0; i < arr[tempMidInd]; i++) {
//				System.out.println(arr[i]);
//			}
			tempMidInd = tempMidInd/2;
			tempMidVal = arr[tempMidInd];
			
//			tempLastVal = arr[arr.length - tempMidInd];
//			System.out.println("tempLastVal: " + tempLastVal);
			
		}
		
//		// OK so let's try to "split" an array based on range
//		int firstHalfDiff= arr[arr.length/2] - arr[0];
//		int secondHalfDiff = arr[arr.length - 1] - arr[arr.length/2];
//		
//		
//		System.out.println("firstHalfDiff: " + firstHalfDiff);
//		System.out.println("secondHalfDiff: " + secondHalfDiff);
//		
//		if(firstHalfDiff != secondHalfDiff) {
//			System.out.println("Indeed, problem in first half");
//		} else {
//			System.out.println("Hmm, problem in second half");
//		}
		
//		if(tempMidInd <=125) {
//			int actualSum = 0;
//			int shouldBeSum = tempMidInd * (tempMidInd + 1) / 2;
//			for (int i = 0; i < tempMidInd; i++) {
//				actualSum += arr[i];
//			}
//			System.out.println("shouldBeSum: " + shouldBeSum + ", actualSum:  "
//					+ actualSum);
//			System.out.println("!!! Missing number (formula): "
//					+ (shouldBeSum - actualSum));
//		}
		
		// Thoughts: We may need to add a "second half" series of vars, as well
		
	}

}
