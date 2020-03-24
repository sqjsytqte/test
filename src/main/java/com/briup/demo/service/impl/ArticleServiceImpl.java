package com.briup.demo.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.Article;
import com.briup.demo.bean.ArticleExample;
import com.briup.demo.bean.Category;
import com.briup.demo.bean.CategoryExample;
import com.briup.demo.mapper.ArticleMapper;
import com.briup.demo.mapper.CategoryMapper;
import com.briup.demo.service.IArticleService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.StatusCodeUtil;

/**
 * 实现文章管理相关的逻辑类
 * @author Administrator
 *
 */
@Service
public class ArticleServiceImpl implements IArticleService{
	
	@Autowired
	private ArticleMapper articlemapple;
	
	@Autowired
	private CategoryMapper categorymapper;

	@Override
	public void SaveOrUpdateArticle(Article article) throws CustomerException {
		// TODO Auto-generated method stub
		if(article == null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "参数为空");
		}
		if(article.getId() == null) {
			//需要额外数据
			article.setPublishdate(new Date());
			article.setClicktimes(0);
			articlemapple.insert(article);
		}else {
			article.setPublishdate(new Date());
			articlemapple.updateByPrimaryKey(article);
		}
	}

	@Override
	public void DeleteArticleById(int id) throws CustomerException {
		// TODO Auto-generated method stub
		articlemapple.deleteByPrimaryKey(id);
	}

	@Override
	public List<Article> FindArticleByCondition(String KeyStr, String condition) throws CustomerException {
		// TODO Auto-generated method stub
		ArticleExample articleExample = new ArticleExample();
		KeyStr = (KeyStr==null?"":KeyStr.trim());
		condition = (condition==null?"":condition.trim());
		//分三种情况
		if("".equals(KeyStr) && "".equals(condition)) {
			//1.没有条件，查询所有
			List<Article> selectByExample = articlemapple.selectByExample(articleExample);
			return selectByExample;
		}else if(!"".equals(KeyStr) && "".equals(condition)) {
			//2.没有栏目，根据关键字查询所有
			//按标题搜索
			articleExample.createCriteria().andTitleLike("%"+KeyStr+"%");
			List<Article> selectByExample = articlemapple.selectByExample(articleExample);
			return selectByExample;
		}else if(!"".equals(condition) && "".equals(KeyStr)) {
			//3.指定栏目，没有关键字，根据栏目查询
			//获取栏目
			CategoryExample categoryExample = new CategoryExample();
			categoryExample.createCriteria().andNameLike(condition);
			List<Category> selectByExample2 = categorymapper.selectByExample(categoryExample);
			if(selectByExample2.size()>0) {
				//通过栏目id查询
				articleExample.createCriteria().andCategoryIdEqualTo(selectByExample2.get(0).getId());
				List<Article> selectByExample = articlemapple.selectByExample(articleExample);
				return selectByExample;
			}else {
				throw new CustomerException(StatusCodeUtil.ERROR_CODE, "没有此栏目");
			}
		}else {
			//4.指定栏目，同时指定关键字，则根据栏目和关键字查询
			//获取栏目
			CategoryExample categoryExample = new CategoryExample();
			categoryExample.createCriteria().andNameLike("%"+condition+"%");
			List<Category> selectByExample2 = categorymapper.selectByExample(categoryExample);
			//此栏目下再查询标题
			if(selectByExample2.size()>0) {
				//and方式拼接
				articleExample.createCriteria().andCategoryIdEqualTo(selectByExample2.get(0).getId())
											   .andTitleLike("%"+KeyStr+"%");
				//or的方式拼接
				//articleExample.or()
				
				List<Article> selectByExample = articlemapple.selectByExample(articleExample);
				return selectByExample;
			}else {
				throw new CustomerException(StatusCodeUtil.ERROR_CODE, "没有此栏目");
			}
		}
		
	}

	@Override
	public Article FindArticleById(int id) throws CustomerException {
		// TODO Auto-generated method stub
		Article selectByPrimaryKey = null;
		selectByPrimaryKey = articlemapple.selectByPrimaryKey(id);
		if(selectByPrimaryKey == null)
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "没有此栏目"); 
		int Clicktimes = selectByPrimaryKey.getClicktimes() == null?0:selectByPrimaryKey.getClicktimes();
		selectByPrimaryKey.setClicktimes(Clicktimes+1);
		this.SaveOrUpdateArticle(selectByPrimaryKey);
		return selectByPrimaryKey;
	}
	
}
