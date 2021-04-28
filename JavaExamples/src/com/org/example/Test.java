package com.org.example;

public class Test {
	public static void main(String[] args) {
		int a[] = {1,5,2,3};
		int b[] = {1,5,2,4,3};
		
		for(int i=0; i<= a.length;i++)
		{
			for(int j=0;j<=b.length;j++)
			{
				if(a[i] == b[j])
				{
					System.out.println("Element found");
				}
				else
				{
					System.out.println("Element Not found");
				}
			}
		}
	}
	
	
	

}
