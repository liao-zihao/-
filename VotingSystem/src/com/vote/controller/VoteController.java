package com.vote.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vote.bean.Vote_item;
import com.vote.bean.Vote_option;
import com.vote.bean.Vote_user;
import com.vote.service.Vote_itemService;
import com.vote.service.Vote_optionService;
import com.vote.service.Vote_subjectService;
import com.vote.service.Vote_userService;
import com.vote.service.impl.Vote_itemServiceImpl;
import com.vote.service.impl.Vote_optionServiceImpl;
import com.vote.service.impl.Vote_subjectServiceImpl;
import com.vote.service.impl.Vote_userServiceImpl;
import com.vote.vo.Page;
import com.vote.vo.ShowVo;

@Controller
public class VoteController {

	Vote_userService vus=new Vote_userServiceImpl();
	Vote_subjectService vss=new Vote_subjectServiceImpl();
	Vote_optionService vos=new Vote_optionServiceImpl();
	Vote_itemService vis=new Vote_itemServiceImpl();
	ModelAndView mav =null;
	
	@RequestMapping("/dologin.action")
	public ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Vote_user vu=new Vote_user();
		vu.setVu_user_name(request.getParameter("uname"));
		String upwd=request.getParameter("upwd");
		
		int result=vus.searchUname(vu);
		if(result>0){
			vu.setVu_password(upwd);
			int result1=vus.searchUname(vu);
			
			if(result1>0){
				request.getSession().setAttribute("user", vu);
				mav=new ModelAndView("doindex.action");
			}else{
				mav=new ModelAndView("login.jsp");
				mav.addObject("mess", "密码已存在");
			}
		}else{
			mav=new ModelAndView("login.jsp");
			mav.addObject("mess", "用户名不存在");
		}
		return mav;
	}
	
	@RequestMapping("/doregister.action")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String uname=request.getParameter("uname");
		String pwd=request.getParameter("pwd");
		int id=Integer.valueOf(request.getParameter("uid"));
		Vote_user vu=new Vote_user();
		vu.setVu_password(pwd);
		vu.setVu_user_id(id);
		vu.setVu_user_name(uname);
		
		int result=vus.doregister(vu);
		if(result>0){
			request.getSession().setAttribute("user", vu);
			mav=new ModelAndView("doindex.action");
		}else{
			mav=new ModelAndView("login.jsp");
			mav.addObject("mess", "注册失败");
		}
		return mav;
	}
	
	@RequestMapping("/doindex.action")
	public ModelAndView doindex(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		Page p = new Page();
    	p.setRownum(3);
    	p.setAllrownum(vss.searchAllCount());
    	
    	if(p.getAllrownum()%p.getRownum()==0){
    		p.setPagenum(p.getAllrownum()/p.getRownum());
    	}else{
    		p.setPagenum(p.getAllrownum()/p.getRownum()+1);
    	}
    	
    	if(request.getParameter("yema")==null){
    		p.setCurrpage(1);
    		p.setStart((p.getCurrpage()-1)*p.getRownum());
    		List<ShowVo> showList = vss.showall(p);
       		mav = new ModelAndView("showVote.jsp");
			mav.addObject("showList", showList);
			mav.addObject("page", p);
    	}else{
    		p.setCurrpage(Integer.valueOf(request.getParameter("yema")));
    		p.setStart((p.getCurrpage()-1)*p.getRownum());
    		List<ShowVo> showList = vss.showall(p);
       		mav = new ModelAndView("showVoteTemplate.jsp");
       		mav.addObject("showList", showList);
       		mav.addObject("page", p);
    	}
		return mav;
	}
	
	@RequestMapping("/dovote.action")
	public ModelAndView dovote(HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		int sid=Integer.valueOf(request.getParameter("sid"));
		String title=request.getParameter("title");
		String numoptions=request.getParameter("numoptions");
		String votenum=request.getParameter("votenum");
		
		ShowVo vo=new ShowVo();
		vo.setNumoptions(Integer.valueOf(numoptions));
		vo.setSid(sid);
		vo.setTitle(title);
		vo.setVotenum(Integer.valueOf(votenum));
		
		List<Vote_option> olist=vos.searchoption(sid);
		
		mav=new ModelAndView("dovote.jsp");
		mav.addObject("vo", vo);
		mav.addObject("olist", olist);
		return mav;
	}
	
	@RequestMapping("/addvote.action")
	public ModelAndView addvote(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Vote_item vi=new Vote_item();
		vi.setVo_id(Integer.valueOf(request.getParameter("vo_option")));
		Vote_user user=(Vote_user) request.getSession().getAttribute("user");
		vi.setVs_user_id(user.getVu_user_id());
		vi.setVs_id(Integer.valueOf(request.getParameter("vs_id")));
		
		int result=vis.addvote(vi);
		if(result>0){
			mav=new ModelAndView("go.jsp");
		}
		return mav;
	}
	
}
