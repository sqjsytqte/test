package com.briup.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Customer;
import com.briup.demo.mapper.ex.CustomerMyMapper;
import com.briup.demo.service.ICustomerService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户相关信息的controller
 * @author Administrator
 *
 */
@RestController
@Api(description="用户相关接口")
public class CustomerController {

	/*@Autowired
	private ICustomerService customerservice;
	
	@PostMapping("/addcustomer")
	@ApiOperation("新增用户")
	public Message<String> add(Customer customer){
		try {
			customerservice.SaveOrUpdateCustomer(customer);
			return MessageUtil.success();
		}catch (CustomerException e) {
			// TODO: handle exception
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "参数错误"+e.getMessage());
		}
		 
	}
	
	@PostMapping("/updatecustomer")
	@ApiOperation("修改用户")
	public Message<String> update(Customer customer){
		try {
			customerservice.SaveOrUpdateCustomer(customer);
			return MessageUtil.success();
		}catch (CustomerException e) {
			// TODO: handle exception
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "参数错误"+e.getMessage());
		}
		 
	}
	
	@GetMapping("/deletecustomerbyid")
	@ApiOperation("删除用户")
	public Message<String> deletebyid(int id){
		customerservice.DeleteCustomerById(id);
		return MessageUtil.success();
		 
	}
	
	@GetMapping("/selsetcustomers")
	@ApiOperation("查询所有用户")
	public Message<List<Customer>> selsetLinks(){
		List<Customer> findAllCustomer = customerservice.FindAllCustomer();
		return MessageUtil.success(findAllCustomer);
		 
	}
	
	@GetMapping("/selsetcustomerbyid")
	@ApiOperation("按id查询用户")
	public Message<Customer> selsetLinkbyname(int id){
		Customer findCustomerById = customerservice.FindCustomerById(id);
		return MessageUtil.success(findCustomerById);
		 
	}*/
	
	@Autowired
	private CustomerMyMapper customermymapper;
	
	@GetMapping("/login")
	@ApiOperation("登陆")
	public Message<Boolean> login(Customer customer){
		List<Customer> findAll = customermymapper.findAll();
		Customer findByUsername = null;
		for(int i=0;i<findAll.size();i++) {
			if(findAll.get(i).getUsername().equals(customer.getUsername()))
				findByUsername = customermymapper.findByUsername(customer.getUsername());
		}
		if(findByUsername==null) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "用户不存在");
		}
		if(findByUsername.getPassword().equals(customer.getPassword())) {
			return MessageUtil.success(true);
		}else {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "密码错误");
		}
		 
	}
	
	@GetMapping("/register")
	@ApiOperation("注册")
	public Message<Boolean> register(Customer customer){
		List<Customer> findAll = customermymapper.findAll();
		for(int i=0;i<findAll.size();i++) {
			if(findAll.get(i).getUsername().equals(customer.getUsername()))
				return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "用户名已存在");
		}
		customermymapper.save(customer);
		return MessageUtil.success(true);
		
		 
	}

}
