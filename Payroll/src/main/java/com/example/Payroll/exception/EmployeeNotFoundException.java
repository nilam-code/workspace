package com.example.Payroll.exception;

public class EmployeeNotFoundException extends RuntimeException{

		public EmployeeNotFoundException(Long id)
		{
			super("COuld not find employee"+id);
		}
}
