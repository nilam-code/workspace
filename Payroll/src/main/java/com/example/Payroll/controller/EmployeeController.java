package com.example.Payroll.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Payroll.entity.Employee;
import com.example.Payroll.entity.repository.EmployeeRepository;
import com.example.Payroll.exception.EmployeeNotFoundException;

@RestController
public class EmployeeController {

		private final EmployeeRepository employeeRepo;
		
		public EmployeeController(EmployeeRepository repository)
		{
			this.employeeRepo = repository;
		}
		
		@GetMapping("/employees")
		public List<Employee> all()
		{
			return employeeRepo.findAll();
		}
		
		@PostMapping("/employees")
		public Employee newEmployee(@RequestBody Employee newEmployee)
		{
			return employeeRepo.save(newEmployee);
		}
		
		@GetMapping("/employee/{id}")
		public Employee one(@PathVariable Long id)
		{
			return employeeRepo.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
		}
		
		@PutMapping("/employee/{id}")
		public Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id)
		{
			return employeeRepo.findById(id).map(employee -> {
				employee.setName(newEmployee.getName());
				employee.setRole(newEmployee.getRole());
				return employeeRepo.save(newEmployee);
			})
			.orElseGet(() -> {
				newEmployee.setId(id);
				return employeeRepo.save(newEmployee);
			});
		}
		
		@DeleteMapping("/employees/{id}")
		public  void deleteEmployee(@PathVariable Long id) {
		    employeeRepo.deleteById(id);
		  }
}
