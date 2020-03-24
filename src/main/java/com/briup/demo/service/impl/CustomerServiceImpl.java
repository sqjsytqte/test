package com.briup.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.Customer;
import com.briup.demo.bean.CustomerExample;
import com.briup.demo.mapper.CustomerMapper;
import com.briup.demo.service.ICustomerService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.StatusCodeUtil;

/**
 * 实现用户管理相关的逻辑类
 * @author Administrator
 *
 */
@Service
public class CustomerServiceImpl implements ICustomerService{

	@Autowired
	private CustomerMapper customermapper;
	
	@Override
	public void SaveOrUpdateCustomer(Customer customer) throws CustomerException {
		// TODO Auto-generated method stub
		if(customer == null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "参数为空");
		}
		if(customer.getId() == null) {
			customermapper.insert(customer);
		}else {
			customermapper.updateByPrimaryKey(customer);
		}
	}

	@Override
	public void DeleteCustomerById(int id) throws CustomerException {
		// TODO Auto-generated method stub
		customermapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Customer> FindAllCustomer() throws CustomerException {
		// TODO Auto-generated method stub
		CustomerExample customerExample = new CustomerExample();
		List<Customer> selectByExample = customermapper.selectByExample(customerExample);
		return selectByExample;
	}

	@Override
	public Customer FindCustomerById(int id) throws CustomerException {
		// TODO Auto-generated method stub
		Customer selectByPrimaryKey = customermapper.selectByPrimaryKey(id);
		return selectByPrimaryKey;
	}

	
}
