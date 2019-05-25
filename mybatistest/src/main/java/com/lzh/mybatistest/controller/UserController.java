package com.lzh.mybatistest.controller;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lzh.mybatistest.bean.User;
import com.lzh.mybatistest.service.UserService;
@RestController
@RequestMapping("/user")
public class UserController {
	
	// 注入UserService
	@Resource
	private UserService userService;
	
	@RequestMapping("/insertUser")
	public String insertUser(User user){
		System.out.println("hot boot2");
		return "插入数据222222["+userService.insertUser(user)+"]条";
	}
	
	@RequestMapping("/insertGetKey")
	public User insertGetKey(User user) {
		userService.insertGetKey(user);
		return user ;
	}
	
	@RequestMapping("/selectByUsername")
	public User selectByUsername(String username){
		return userService.selectByUsername(username);
	}
	
	@RequestMapping("/findAll")
	public List<User> findAll(){
		System.out.println("hot boot2");
		return userService.findAll();
	}
	
	@RequestMapping("/update")
	public void update(User user) {
		userService.update(user);
	}
	
	@RequestMapping("/delete")
	public void delete(Integer id) {
		userService.delete(id);
	}
}
