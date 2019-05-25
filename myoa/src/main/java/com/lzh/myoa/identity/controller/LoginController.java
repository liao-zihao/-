package com.lzh.myoa.identity.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lzh.myoa.identity.service.IdentityService;

@Controller
public class LoginController {
	@Resource
	private IdentityService identityService;
	
	@ResponseBody
	@RequestMapping(value="/loginAjax",produces="application/json;charset=utf-8")
	public Map<String,Object> login(@RequestParam("userId")String userId,@RequestParam("passWord")String passWord,
			@RequestParam("vcode")String vcode,HttpSession session){
		try {
			Map<String,Object> params = new HashMap<>();
			params.put("userId",userId);
			params.put("passWord", passWord);
			params.put("vcode", vcode);
			params.put("session", session);
			
			Map<String,Object> result = identityService.login(params);
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
