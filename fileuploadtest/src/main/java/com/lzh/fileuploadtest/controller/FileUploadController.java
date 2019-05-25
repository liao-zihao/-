package com.lzh.fileuploadtest.controller;

import java.io.File;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lzh.fileuploadtest.domain.User;

@Controller
public class FileUploadController {
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/registerForm")
	public String registerForm(){
		return "registerForm";
	}
	
	@PostMapping(value="/upload")
	public String upload(HttpServletRequest request,
			@RequestParam("description")String description,
			@RequestParam("file")MultipartFile file)throws Exception{
		System.out.print("description="+description);
		if(!file.isEmpty()) {
			String path = request.getServletContext().getRealPath("/upload");
			System.out.println("path="+path);
			
			String filename=file.getOriginalFilename();
			File filepath=new File(path,filename);
			
			if(!filepath.getParentFile().exists()) {
				filepath.getParentFile().mkdirs();
			}
			file.transferTo(new File(path+File.separator+filename));
			return "success";
		}else return "error";
	}
	@RequestMapping(value="register")
	public String register(HttpServletRequest re,
			@ModelAttribute User user,
			Model model)throws Exception{
		System.out.println("username="+user.getUsername());
		if(!user.getHeadPortrait().isEmpty()) {
			String path = re.getServletContext().getRealPath("/upload");
			System.out.println("path="+path);
			
			String filename=user.getHeadPortrait().getOriginalFilename();
			File filepath=new File(path,filename);
			if(!filepath.getParentFile().exists()) {
				filepath.getParentFile().mkdirs();
			}
			
			user.getHeadPortrait().transferTo(new File(path+File.separator+filename));
			model.addAttribute("user",user);
			return "userinfo";
		}else return "error";
	}
	
	@RequestMapping(value="/download")
	public ResponseEntity<byte[]> download(HttpServletRequest re,
			@RequestParam("filename")String filename,
			@RequestHeader("User-Agent")String userAgent,
			Model model)throws Exception{
		String path=re.getServletContext().getRealPath("/upload/");
		File file=new File(path+File.separator+filename);
		BodyBuilder builder = ResponseEntity.ok();
		builder.contentLength(file.length());
		builder.contentType(MediaType.APPLICATION_OCTET_STREAM);
		filename=URLEncoder.encode(filename, "utf-8");
		
		if(userAgent.indexOf("MSIE")>0) {
			builder.header("Content-Disposition","attachment;filename="+filename );
			
		}else {
			builder.header("Content-Disposition", "attachment;filename*="+filename);
		}
		return builder.body(FileUtils.readFileToByteArray(file));
	}
}
