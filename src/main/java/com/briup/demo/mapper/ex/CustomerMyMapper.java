package com.briup.demo.mapper.ex;

import org.springframework.data.jpa.repository.JpaRepository;

import com.briup.demo.bean.Customer;

public interface CustomerMyMapper extends JpaRepository<Customer, Integer>{
	Customer findByUsername(String username);
}
