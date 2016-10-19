package com.devcru.substring.algorithm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SubstringInString {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		long startTime = System.nanoTime();
		processFileHead("div");
		long endTime = System.nanoTime();
		
		System.out.println("runtime(nano): " + (endTime - startTime));
		System.out.println("runtime(ms): " + ((endTime - startTime)/1000000)); // milliseconds
	}
	
	public static String processFileHead(String str) throws FileNotFoundException, IOException {
		
		String head = null;
		int count = 0;
		
		BufferedReader br = null;		
		try {
			br = new BufferedReader(new FileReader("stuff.txt"));
			String line;
			boolean inHead = true;
			while((line = br.readLine()) != null && inHead) {
				if(line.toLowerCase().contains("<head>")) {
					inHead = true;
				}
				if(line.toLowerCase().contains("</head>")) {
					inHead = false;
				}
				if(line.toLowerCase().contains(str)) {
					System.out.println(str + " found at: " + line);
				}
				
				int lastIndex = 0;
				
				while(lastIndex != -1) {
					lastIndex = line.indexOf(str, lastIndex);
					if(lastIndex != -1){
						count++;
						lastIndex += str.length();
					}
				}
			}
		} finally {
			br.close();
		}
		
		System.out.println(count + " instances of " + str + " found!");
		return head;
	}

}
