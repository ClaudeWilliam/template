package com.gbros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.gbros.modle.User;
import com.gbros.service.UserService;
import com.mysql.fabric.Response;

@RestController//配置controller类型，把controller放到spring容器中
@RequestMapping(value = "/user")//配置URL
public class UserController {

	@Autowired//自动填充service对象
	private UserService userService;
	//设置请求方式
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<User> list() {

		return userService.getUserList();
	}
	//配置url和请求方式
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public User view(@PathVariable("id") String userId) {
		return userService.getUserById(userId);
	}
	//配置url和请求方式
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	@ResponseBody
	public List<User> delete(@PathVariable("id") String userId){
		System.out.println("User Id" + userId);
		userService.deleteUser(userId);
		return userService.getUserList();	
	} 
	//设置请求方式
	@RequestMapping(method=RequestMethod.POST)
	public List<User> insert(@RequestBody String text){
		//userService.saveUser(userId, name);
		System.out.println(text);
		User user=JSON.parseObject(text, User.class);
		
		userService.saveUser(user.getId(), user.getName());
		return userService.getUserList();
	}
	//设置请求方式
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	public List<User>  update(@RequestBody String text) {
		//userService.updateUser(userId,name);
		System.out.println(text);
		User user=JSON.parseObject(text, User.class);
		userService.updateUser(user.getId(), user.getName());
		return userService.getUserList();
	}
}
