package com.devcru.findmissingnum.utils;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class NumArrayGenerator {
	
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
		
		System.out.println("Generation complete!");
		
		return intArray;
	}

}
