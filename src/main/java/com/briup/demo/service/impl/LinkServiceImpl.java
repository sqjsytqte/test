package com.briup.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.Link;
import com.briup.demo.bean.LinkExample;
import com.briup.demo.bean.LinkExample.Criteria;
import com.briup.demo.mapper.LinkMapper;
import com.briup.demo.service.ILinkService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.StatusCodeUtil;

/**
 * 操作链接的service功能
 * @author Administrator
 *
 */
@Service
public class LinkServiceImpl implements ILinkService{

	@Autowired
	private LinkMapper linkMapper;
	
	@Override
	public void SaveORUodateLink(Link link) throws CustomerException {
		//判空处理
		if(link == null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "参数为空");
		}
		//判断id是否为空
		if(link.getId() == null) {
			linkMapper.insert(link);
		}else {
			linkMapper.updateByPrimaryKey(link);
		}
		
	}

	@Override
	public List<Link> FindAllLinks() throws CustomerException {
		// TODO Auto-generated method stub
		LinkExample linkExample = new LinkExample();
		List<Link> selectByExample = linkMapper.selectByExample(linkExample);
		return selectByExample;
	}

	@Override
	public void DeleteLinkById(int id) throws CustomerException {
		// TODO Auto-generated method stub
		linkMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public List<Link> FindLinkByName(String name) throws CustomerException {
		// TODO Auto-generated method stub
		LinkExample linkExample = new LinkExample();
		
		name = (name == null?"":name.trim());
		if("".equals(name)) {
			//为空则查询全部
			List<Link> selectByExample = linkMapper.selectByExample(linkExample);
			return selectByExample;
		}else {
			//有条件按条件返回
			Criteria createCriteria = linkExample.createCriteria();
			createCriteria.andNameLike("%"+name+"%");
			List<Link> selectByExample = linkMapper.selectByExample(linkExample);
			return selectByExample;
		}
	}

	

	

}
