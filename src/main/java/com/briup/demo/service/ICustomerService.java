package com.briup.demo.service;

import java.util.List;

import com.briup.demo.bean.Customer;
import com.briup.demo.utils.CustomerException;

public interface ICustomerService {

	/**
	 * 新增/修改用户
	 */
	void SaveOrUpdateCustomer(Customer customer) throws CustomerException;
	
	/**
	 * 删除用户
	 */
	void DeleteCustomerById(int id) throws CustomerException;
	
	/**
	 * 查询所有用户
	 */
	List<Customer> FindAllCustomer() throws CustomerException;
	
	/**
	 * 按id查询用户
	 */
	Customer FindCustomerById(int id) throws CustomerException;
	
}
