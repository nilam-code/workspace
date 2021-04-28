package com.example.Payroll.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.Payroll.entity.Employee;
import com.example.Payroll.entity.Order;
import com.example.Payroll.entity.Status;
import com.example.Payroll.entity.repository.EmployeeRepository;
import com.example.Payroll.entity.repository.OrderRepository;

@Configuration
public class LoadDatabase {
	
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	  @Bean
	  public CommandLineRunner initDatabase(EmployeeRepository employeeRepository, OrderRepository orderRepository) {

		    return args -> {
		      employeeRepository.save(new Employee("Bilbo", "Baggins", "burglar"));
		      employeeRepository.save(new Employee("Frodo", "Baggins", "thief"));

		      employeeRepository.findAll().forEach(employee -> log.info("Preloaded " + employee));

		      
		      orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
		      orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));

		      orderRepository.findAll().forEach(order -> {
		        log.info("Preloaded " + order);
		      });
		      
		    };
		  }
}
