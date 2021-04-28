package com.org.example;

public class AccTest {
	public static int  differenceofSum(int n, int m)
	{
	    int i, sum1 = 0, sum2 = 0;
	    for(i=1; i<=m; i++)
	    {
	        if(i%n==0)
	        {
	            sum1 = sum1 + i;
	        }
	        else
	        {
	            sum2 = sum2 + i;
	        }   
	    }
	    return sum2 - sum1;
	}

	public static void main(String[] args) {
		int n=4, m= 20;
	    int result;
	   
	    result = differenceofSum(n, m);
	    System.out.println("%d"+result);
	   
Integer i =42;
        
        String s=(i<40)?"life":(i>50)?"Univers":"everything";
        System.out.println(s);
        
        final String userName;
        userName = "nab";
        System.out.println(userName);
        
	}
}
