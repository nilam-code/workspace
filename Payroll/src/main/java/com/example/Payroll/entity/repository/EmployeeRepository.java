package com.example.Payroll.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Payroll.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
