package com.lzh.mybatistest.service;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.lzh.mybatistest.bean.User;
import com.lzh.mybatistest.repository.UserRepository;
@Service
public class UserService {
	
	// 注入UserRepository
	@Resource
	private UserRepository userRepository;
	
	public int insertUser(User user){
		return userRepository.insertUser(user);
	}
	
	public User selectByUsername(String username){
		return userRepository.selectByUsername(username);
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public void insertGetKey(User user) {
		// 数据插入成功以后，Mybatis框架会将插入成功的数据主键存入到user对象中去
		userRepository.insertGetKey(user);
	}
	
	public void update(User user) {
		userRepository.update(user);
	}
	
	public void delete(Integer id) {
		userRepository.delete(id);
	}
}