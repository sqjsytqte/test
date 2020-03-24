package com.briup.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.ArticleExample;
import com.briup.demo.bean.Category;
import com.briup.demo.bean.CategoryExample;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.mapper.ArticleMapper;
import com.briup.demo.mapper.CategoryMapper;
import com.briup.demo.mapper.ex.CategoryExMapper;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.StatusCodeUtil;

/**
 * 操作栏目的service功能
 * @author Administrator
 *
 */
@Service
public class CategoryServiceImpl implements ICategoryService{
	
	@Autowired
	private CategoryMapper CategoryMapper;
	
	@Autowired
	private ArticleMapper ArticlMapper;
	
	@Autowired
	private CategoryExMapper CategoryExMapper;

	@Override
	public List<Category> FindAllCategory() throws CustomerException {
		// TODO Auto-generated method stub
		CategoryExample categoryExample = new CategoryExample();
		List<Category> selectByExample = CategoryMapper.selectByExample(categoryExample);
		return selectByExample;
	}

	@Override
	public void SaveOrUpdateCategory(Category cotegory) throws CustomerException {
		// TODO Auto-generated method stub
		//判空处理
		if(cotegory == null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "参数为空");
		}
		//判断id是否为空
		if(cotegory.getId() == null) {
			//判断code是否重复
			CategoryExample categoryExample = new CategoryExample();
			categoryExample.createCriteria().andCodeEqualTo(cotegory.getCode());
			List<Category> selectByExample = CategoryMapper.selectByExample(categoryExample);
			if(selectByExample.size()==0) {
				throw new CustomerException(StatusCodeUtil.ERROR_CODE, "栏目编号已存在");
			}
			//判断name是否重复
			CategoryExample categoryExample2 = new CategoryExample();
			categoryExample2.createCriteria().andNameEqualTo(cotegory.getName());
			List<Category> selectByExample2 = CategoryMapper.selectByExample(categoryExample2);
			if(selectByExample2.size()==0){
				throw new CustomerException(StatusCodeUtil.ERROR_CODE, "栏目名已存在");
			}
			
			CategoryMapper.insert(cotegory);
		}else {
			//除此项的所有项
			//CategoryExample categoryExample = new CategoryExample();
			//List<Integer> id = new ArrayList<Integer>();
			//id.add(cotegory.getId());
			//categoryExample.createCriteria().andIdNotIn(id);
			//List<Category> selectByExample = CategoryMapper.selectByExample(categoryExample);
			
			CategoryMapper.updateByPrimaryKey(cotegory);
		}
	}

	@Override
	public void DeleteCategoryById(int id) throws CustomerException {
		//删除栏目需要删除文章
		ArticleExample articleExample = new ArticleExample();
		articleExample.createCriteria().andCategoryIdEqualTo(id);
		ArticlMapper.deleteByExample(articleExample);
		
		CategoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Category FindCategoryById(int id) throws CustomerException {
		// TODO Auto-generated method stub
		if(id==0) {
			//id为0返回空对象
			return new Category();
		}else {
			Category selectByPrimaryKey = CategoryMapper.selectByPrimaryKey(id);
			return selectByPrimaryKey;
		}
	}

	@Override
	public List<CategoryEx> FindAllCategoryEx() throws CustomerException {
		// TODO Auto-generated method stub
		List<CategoryEx> findAllCategoryExs = CategoryExMapper.findAllCategoryExs();
		return findAllCategoryExs;
	}

	@Override
	public CategoryEx findCategoryExById(int id) {
		// TODO Auto-generated method stub
		CategoryEx findCategoryExById = CategoryExMapper.findCategoryExById(id);
		return findCategoryExById;
	}

}
