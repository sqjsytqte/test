package com.briup.demo.service;

import java.util.List;

import com.briup.demo.bean.Category;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.utils.CustomerException;

/**
 * 栏目相关的Service层
 * @author Administrator
 *
 */
public interface ICategoryService {
	/**
	 * 查询所有栏目
	 */
	List<Category> FindAllCategory() throws CustomerException;
	
	/**
	 * 添加/修改栏目信息
	 */
	void SaveOrUpdateCategory(Category cotegory) throws CustomerException;
	
	/**
	 * 根据id删除栏目信息
	 */
	void DeleteCategoryById(int id) throws CustomerException;
	
	/**
	 * 根据栏目id进行查找
	 */
	Category FindCategoryById(int id) throws CustomerException;
	
	/**
	 * 查询栏目信息并级联查询对应文章信息
	 */
	List<CategoryEx> FindAllCategoryEx() throws CustomerException;
	
	/**
	 * 查询一个栏目及其文章信息
	 */
	CategoryEx findCategoryExById(int id);
	
}
