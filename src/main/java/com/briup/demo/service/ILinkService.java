package com.briup.demo.service;

import java.util.List;

import com.briup.demo.bean.Link;
import com.briup.demo.utils.CustomerException;

/**
 * 关于链接的相关操作
 * @author Administrator
 *
 */
public interface ILinkService {
	/**
	 * 保存/修改链接信息
	 */
	void SaveORUodateLink(Link link) throws CustomerException;
	
	/**
	 * 查询所有链接信息
	 */
	List<Link> FindAllLinks() throws CustomerException;
	
	/**
	 * 根据id删除链接信息
	 */
	void DeleteLinkById(int id) throws CustomerException;
	
	/**
	 * 根据链接名查询链接信息
	 */
	List<Link> FindLinkByName(String name) throws CustomerException;
	
}
