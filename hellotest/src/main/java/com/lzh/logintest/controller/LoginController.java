package com.lzh.logintest.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	@PostMapping("login")
	public ModelAndView login(
			@RequestParam("loginName") String loginName,
			@RequestParam("password")String password,
			ModelAndView mv){
		System.out.println("登录名:"+loginName+"密码："+password);
		mv.setViewName("redirect:/main");
		return mv;
	}
}
