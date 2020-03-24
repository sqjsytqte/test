package com.briup.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Article;
import com.briup.demo.bean.Category;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.bean.ex.IndexResult;
import com.briup.demo.service.IArticleService;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.service.IIndexResultService;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description="首页需要的所有数据")
public class IndexResultController {
	
	@Autowired
	private IIndexResultService indexresultservice;
	
	@Autowired
	private IArticleService articleservice;
	
	@Autowired
	private ICategoryService categoryservice;
	
	@GetMapping("/getIndexResult")
	@ApiOperation("获取首页需要的所有数据")
	public Message<IndexResult> getIndexResult(){
		IndexResult findIndexAllResult = indexresultservice.FindIndexAllResult();
		return MessageUtil.success(findIndexAllResult);
	}
	
	/*@GetMapping("/getCategoryExById")
	@ApiOperation("根据栏目id获取栏目及其文章")
	public Message<CategoryEx> getCategoryExById(int id){
		CategoryEx categoryEx = new CategoryEx();
		Category findCategoryById = categoryservice.FindCategoryById(id);
		List<Article> findArticleByCondition = 
				articleservice.FindArticleByCondition("", findCategoryById.getName());
		categoryEx.setId(findCategoryById.getId());
		categoryEx.setCode(findCategoryById.getCode());
		categoryEx.setName(findCategoryById.getName());
		categoryEx.setArticles(findArticleByCondition);
		
		return MessageUtil.success(categoryEx);
	}
	
	@GetMapping("/getArticleById")
	@ApiOperation("获取一篇文章")
	public Message<Article> getArticleById(int id){
		Article findArticleById = articleservice.FindArticleById(id);
		//增加点击数
		if(findArticleById.getClicktimes() == null) {
			findArticleById.setCategoryId(1);
		}else {	
			findArticleById.setClicktimes(findArticleById.getClicktimes() + 1);
		}
		articleservice.SaveOrUpdateArticle(findArticleById);
		return MessageUtil.success(findArticleById);
	}*/
	
}
