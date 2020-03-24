package com.briup.demo.mapper.ex;

import java.util.List;

import com.briup.demo.bean.ex.CategoryEx;

/**
 * 处理查询栏目及其包含文章信息
 * @author Administrator
 *
 */
public interface CategoryExMapper {

	/**
	 * 查询栏目信息并级联查询对应文章信息
	 * @return
	 */
	List<CategoryEx> findAllCategoryExs();
	
	CategoryEx findCategoryExById(int id);
	
}
