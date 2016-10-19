package com.devcru.findmissingnum;

public class FindMissingNumNaive {
	
	public static int bruteForceMissingNum(int[] arr) {
		int answer = 0;
		int temp = 0;
		for (int i = 0; i < arr.length; i++) {

			if (temp != arr[i] - 1 && arr[i] != 0) {
//				System.out.println("Missing number found: " + (arr[i] - 1));
				answer = (arr[i] - 1);
				break;
			}
			temp = arr[i];
		}
		
		return answer;
	}

	public static int calculateMissingNum(int[] arr) {
		int answer = 0;
		int actualSum = 0;
		int shouldBeSum = arr.length * (arr.length + 1) / 2;
		for (int i = 0; i < arr.length; i++) {
			actualSum += arr[i];
		}
//		System.out.println("shouldBeSum: " + shouldBeSum + ", actualSum:  "
//				+ actualSum);
//		System.out.println("Missing number (formula): "
//				+ (shouldBeSum - actualSum));
		answer = shouldBeSum - actualSum;
		return answer;
	}

}
