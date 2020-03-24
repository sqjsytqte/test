package com.briup.demo.service;

import java.util.List;

import com.briup.demo.bean.Article;
import com.briup.demo.utils.CustomerException;

/**
 * 文章相关内容的service接口
 * @author Administrator
 *
 */
public interface IArticleService {

	/**
	 * 新增/修改文章
	 */
	void SaveOrUpdateArticle(Article article) throws CustomerException;
	
	/**
	 * 删除文章
	 */
	void DeleteArticleById(int id) throws CustomerException;
	
	/**
	 * 查询文章
	 * @param KeyStr 搜索框
	 * @param condition 栏目框
	 * @return
	 * @throws CustomerException
	 */
	List<Article> FindArticleByCondition(String KeyStr,String condition) throws CustomerException;
	
	/**
	 * 根据id查询文章
	 */
	Article FindArticleById(int id) throws CustomerException;
	
}
