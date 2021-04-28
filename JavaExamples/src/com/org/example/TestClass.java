package com.org.example;

public class TestClass {
	String str1 = "Java";
	String str2 = "Java";
	
	String str3 = new String(str1);
	String str4 = new String(str1);
	
	@Test
	public void test1()
	{
		Assert.assertEqual(str1, str2);
	}
}
