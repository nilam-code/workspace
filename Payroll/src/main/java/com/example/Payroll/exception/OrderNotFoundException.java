package com.example.Payroll.exception;

public class OrderNotFoundException extends RuntimeException{

	public OrderNotFoundException(Long id)
	{
		super("COuld not find Order"+id);
	}
}
