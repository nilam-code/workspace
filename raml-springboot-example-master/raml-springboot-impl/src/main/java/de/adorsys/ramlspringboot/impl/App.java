package de.adorsys.ramlspringboot.impl;

/**
 * @author Florian Hirsch
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        Integer i =42;
        
        String s=(i<40)?"life":(i>50)?"Univers":"everything";
        System.out.println(s);
        
        
    }

    
}