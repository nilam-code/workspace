package com.example.Payroll.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Payroll.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
