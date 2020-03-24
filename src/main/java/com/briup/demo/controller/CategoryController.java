package com.briup.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Category;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 与栏目相关的  和前端交互的web层
 * @author Administrator
 *
 */
@RestController
@Api(description="栏目相关接口")
public class CategoryController {
	
	@Autowired
	private ICategoryService categoryService;
	
	@PostMapping("/addcategory")
	@ApiOperation("新增栏目")
	public Message<String> add(Category category){
		try {
			categoryService.SaveOrUpdateCategory(category);
			return MessageUtil.success();
		}catch (CustomerException e) {
			// TODO: handle exception
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "参数错误"+e.getMessage());
		}
		 
	}
	
	@PostMapping("/updatecategory")
	@ApiOperation("修改栏目")
	public Message<String> update(Category category){
		try {
			categoryService.SaveOrUpdateCategory(category);
			return MessageUtil.success();
		}catch (CustomerException e) {
			// TODO: handle exception
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "参数错误"+e.getMessage());
		}
		 
	}
	
	@GetMapping("/deletecategorybyid")
	@ApiOperation("删除栏目")
	public Message<String> deletebyid(int id){
		categoryService.DeleteCategoryById(id);
		return MessageUtil.success();
		 
	}
	
	@GetMapping("/selsetcategorys")
	@ApiOperation("查询所有栏目")
	public Message<List<Category>> selsetLinks(){
		List<Category> findAllCategory = categoryService.FindAllCategory();
		return MessageUtil.success(findAllCategory);
		 
	}
	
	@GetMapping("/selsetcategorybyid")
	@ApiOperation("按id查询栏目")
	public Message<Category> selsetLinkbyname(int id){
		Category findCategoryById = categoryService.FindCategoryById(id);
		return MessageUtil.success(findCategoryById);
		 
	}
	
	@GetMapping("/findCategoryExById")
	@ApiOperation("按id查询栏目及其文章")
	public Message<CategoryEx> findCategoryById(int id){
		CategoryEx findCategoryExById = categoryService.findCategoryExById(id);
		return MessageUtil.success(findCategoryExById);
		 
	}

}
