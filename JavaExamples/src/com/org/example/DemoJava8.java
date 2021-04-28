package com.org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class DemoJava8 {
	
	public static void main(String[] args) {
		Map<String, Integer> prices = new HashMap<>();
		prices.put("Apple", 50);
		prices.put("Orange", 20);
		prices.put("Banana", 10);
		prices.put("Grapes", 40);
		prices.put("Papaya", 50);
		
				
		prices.forEach((k,v)->System.out.println("Fruit: " + k + ", Price: " + v));

		prices.entrySet().stream()
	      .forEach(e -> System.out.println(e.getKey() + ":" + e.getValue()));
		

		prices.entrySet().stream().filter(e -> e.getValue() >= 40)
        .forEach(e -> {
            System.out.println("key: " + e.getKey() + " value: " + e.getValue());
         });
		
	}
	
}
