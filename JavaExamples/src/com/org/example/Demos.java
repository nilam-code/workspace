package com.org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Demos {
	public static void main(String[] args) {
		double a = 295.04;
		int  b =300;
		byte c = (byte)a;
		byte d = (byte)b;
		System.out.println(c + "" +d);
		int  sum = 0;
		for (int i = 0, j =0; i < 10 & j <10 ; ++i, j= i+1) {
			sum+= i;
		System.out.println(sum);
		
		int num = 17;
		System.out.println(Integer.toBinaryString(num));
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("one");
		list.add("Two");
		Collections.unmodifiableList(list);
		list.add("Three");
		for (String string : list) {
			System.out.println(string);
		}
		}
	}
}
