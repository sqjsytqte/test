package com.briup.demo.bean.ex;

import java.io.Serializable;
import java.util.List;

import com.briup.demo.bean.Article;

import io.swagger.annotations.ApiParam;

/**
 * 栏目扩展类
 * @author Administrator
 *
 */
public class CategoryEx implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@ApiParam(value="栏目编号",required=true)
	private Integer code;
	@ApiParam(value="栏目名",required=true)
	private String name;
	
	//添加级联的所有文章
	private List<Article> articles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public CategoryEx(Integer id, Integer code, String name, List<Article> articles) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.articles = articles;
	}

	public CategoryEx() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
