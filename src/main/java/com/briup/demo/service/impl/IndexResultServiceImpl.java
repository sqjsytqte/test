package com.briup.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.Link;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.bean.ex.IndexResult;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.service.IIndexResultService;
import com.briup.demo.service.ILinkService;
import com.briup.demo.utils.CustomerException;

/**
 * 返回首页数据的service功能
 * @author Administrator
 *
 */
@Service
public class IndexResultServiceImpl implements IIndexResultService{

	//关联超链接的service层接口
	@Autowired
	private ILinkService linkservice;
	
	@Autowired
	private ICategoryService categoryservice;
	
	@Override
	public IndexResult FindIndexAllResult() throws CustomerException {
		// TODO Auto-generated method stub
		IndexResult indexResult = new IndexResult();
		//设置所有超链接信息
		List<Link> findAllLinks = linkservice.FindAllLinks();
		indexResult.setLinks(findAllLinks);
		//设置所有栏目及其文章信息
		List<CategoryEx> findAllCategoryEx = categoryservice.FindAllCategoryEx();
		indexResult.setCategoryex(findAllCategoryEx);
		
		return indexResult;
	}

	

}
