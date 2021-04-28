package com.org.example.interfacetest;

public class MainTest1 {
	public static void main(String[] args) {
		Aa a1 = new Aa();
		a1.printMe(new Aa());
		
		Aa a2 = new Bb();
		a2.printMe(new Aa());
		
		Aa a3 = new Bb();
		a3.printMe(new Bb());
		
		
		
		Bb a5 = new Bb();
		a5.printMe(new Aa());
		
		Bb a6 = new Bb();
		a6.printMe(new Bb());
	}
	
}
