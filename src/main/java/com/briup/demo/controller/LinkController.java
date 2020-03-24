package com.briup.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Link;
import com.briup.demo.service.ILinkService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 与链接相关的  和前端交互的web层
 * @author Administrator
 *
 */
@RestController
@Api(description="链接相关接口")
public class LinkController {
	
	@Autowired
	private ILinkService linkService;
	
	@PostMapping("/addlink")
	@ApiOperation("新增链接")
	public Message<String> add(Link link){
		try {
			linkService.SaveORUodateLink(link);
			return MessageUtil.success();
		}catch (CustomerException e) {
			// TODO: handle exception
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "参数错误"+e.getMessage());
		}
		 
	}
	
	@PostMapping("/updatelink")
	@ApiOperation("修改链接")
	public Message<String> update(Link link){
		try {
			linkService.SaveORUodateLink(link);
			return MessageUtil.success();
		}catch (CustomerException e) {
			// TODO: handle exception
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "参数错误"+e.getMessage());
		}
		 
	}
	
	@GetMapping("/selsetlinks")
	@ApiOperation("查询所有链接")
	public Message<List<Link>> selsetLinks(){
		List<Link> findAllLinks = linkService.FindAllLinks();
		return MessageUtil.success(findAllLinks);
		 
	}
	
	@GetMapping("/deletelinkbyid")
	@ApiOperation("删除链接")
	public Message<String> deletebyid(int id){
		linkService.DeleteLinkById(id);
		return MessageUtil.success();
		 
	}
	
	@GetMapping("/selsetlinkbyname")
	@ApiOperation("按条件查询链接")
	public Message<List<Link>> selsetLinkbyname(String name){
		List<Link> findLinkByName = linkService.FindLinkByName(name);
		return MessageUtil.success(findLinkByName);
		 
	}
}
