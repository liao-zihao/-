package com.lzh.myoa.identity.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.lzh.myoa.common.paper.PageModel;
import com.lzh.myoa.identity.domain.Dept;
import com.lzh.myoa.identity.domain.Module;
import com.lzh.myoa.identity.domain.Role;
import com.lzh.myoa.identity.domain.User;
import com.lzh.myoa.identity.dto.UserModule;
import com.lzh.myoa.identity.vo.TreeData;

public interface IdentityService {
	List<Dept> getAllDepts();
	Map<String,Object> login(Map<String,Object> params);
	User getUserById(String userId);
	void updateSelf(User user,HttpSession session);
	Map<String,Object> getAllDeptsAndJobsAjax();
	
	List<User> getUserByPager(User user,PageModel pageModel);
	void deleteUserByUserIds(String ids);
	String isUserValidAjax(String userId);
	void addUser(User user);
	void updateUser(User user);
	void activeUser(User user);
	
	List<TreeData> loadAllModuleTrees();
	List<Module> getModulesByParent(String parentCode,PageModel pageModel);
	List<Module> getModelesByParent(String parentCode);
	void deleteModules(String ids);
	void addModules(String parentCode,Module module);
	Module getModuleByCode(String code);
	void updateModule(Module module);
	
	List<Role> getRoleByPager(PageModel pageModel);
	void addRole(Role role);
	void deleteRole(String ids);
	Role getRoleById(Long id);
	void updateRole(Role role);
	List<User> selectRoleUser(Role role,PageModel pageModel);
	List<User> selectNotRoleUser(Role role,PageModel pageModel);
	
	void bindUser(Role role,String ids);
	void unBindUser(Role role,String ids);
	List<String> getRoleModuleOperasCodes(Role role,String parentCode);
	void bindPopedom(String codes,Role role,String parentCode);
	List<UserModule> getUserPopedomModules();
}
