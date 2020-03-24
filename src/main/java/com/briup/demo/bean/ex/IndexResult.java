package com.briup.demo.bean.ex;

import java.io.Serializable;
import java.util.List;

import com.briup.demo.bean.Link;

/**
 * 保存首页的所有数据
 * 
 * @author Administrator
 *
 */
public class IndexResult implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<Link> links;

	private List<CategoryEx> categoryex;

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public List<CategoryEx> getCategoryex() {
		return categoryex;
	}

	public void setCategoryex(List<CategoryEx> categoryex) {
		this.categoryex = categoryex;
	}

	public IndexResult(List<Link> links, List<CategoryEx> categoryex) {
		this.links = links;
		this.categoryex = categoryex;
	}

	public IndexResult() {
		// TODO Auto-generated constructor stub
	}

	
}
