package com.briup.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Article;
import com.briup.demo.service.IArticleService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 文章相关信息的controller
 * @author Administrator
 *
 */
@RestController
@Api(description="文章相关接口")
public class ArticleController {
	
	@Autowired
	private IArticleService articleservice;
	
	@PostMapping("/addarticle")
	@ApiOperation("添加文章信息")
	public Message<String> saveArticle(Article article){
		try {
			articleservice.SaveOrUpdateArticle(article);
			return MessageUtil.success();
		}catch (CustomerException e) {
			// TODO: handle exception
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误"+e.getMessage());
		}
	}
	
	@PostMapping("/updatearticle")
	@ApiOperation("修改文章信息")
	public Message<String> updateArticle(Article article){
		try {
			articleservice.SaveOrUpdateArticle(article);
			return MessageUtil.success();
		}catch (CustomerException e) {
			// TODO: handle exception
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误"+e.getMessage());
		}
	}
	
	@GetMapping("/deleteArticleById")
	@ApiOperation("根据id删除文章信息")
	public Message<String> deleteArticleById(int id){
		articleservice.DeleteArticleById(id);
		return MessageUtil.success();
	}
	
	@GetMapping("/findArticleByCondition")
	@ApiOperation("根据条件查询文章")
	public Message<List<Article>> getArticleByCondition(String KeyStr,String condition){
		try {
			List<Article> findArticleByCondition = articleservice.FindArticleByCondition(KeyStr, condition);
			return MessageUtil.success(findArticleByCondition);
		}catch (CustomerException e) {
			// TODO: handle exception
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误"+e.getMessage());
		}
	}
	
	@GetMapping("/findArticleById")
	@ApiOperation("根据条件查询文章")
	public Message<Article> getArticleById(int id){
		Article findArticleById = articleservice.FindArticleById(id);
		return MessageUtil.success(findArticleById);
	}
}
