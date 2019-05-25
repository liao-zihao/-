package com.lzh.myoa.identity.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.lzh.myoa.common.CommonContants;
import com.lzh.myoa.common.paper.PageModel;
import com.lzh.myoa.identity.domain.Dept;
import com.lzh.myoa.identity.domain.Module;
import com.lzh.myoa.identity.domain.Popedom;
import com.lzh.myoa.identity.domain.Role;
import com.lzh.myoa.identity.domain.User;
import com.lzh.myoa.identity.dto.UserModule;
import com.lzh.myoa.identity.repository.DeptRepository;
import com.lzh.myoa.identity.repository.JobRepository;
import com.lzh.myoa.identity.repository.ModuleRepository;
import com.lzh.myoa.identity.repository.PopedomRepository;
import com.lzh.myoa.identity.repository.RoleRepository;
import com.lzh.myoa.identity.repository.UserRepository;
import com.lzh.myoa.identity.service.IdentityService;
import com.lzh.myoa.identity.utils.OaContants;
import com.lzh.myoa.identity.utils.OaException;
import com.lzh.myoa.identity.utils.UserHolder;
import com.lzh.myoa.identity.vo.TreeData;

@Service("identityService")
@Transactional(readOnly=true)
public class IdentityServiceImpl implements IdentityService{
	@Autowired
	@Qualifier("deptRepository")
	private DeptRepository deptRepository;
	
	@Autowired
	@Qualifier("jobRepository")
	private JobRepository jobRepository;
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	@Autowired
	@Qualifier("moduleRepository")
	private ModuleRepository moduleRepository;
	
	@Autowired
	@Qualifier("roleRepository")
	private RoleRepository roleRepository;
	
	@Autowired
	@Qualifier("popedomRepository")
	private PopedomRepository popedomRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<Dept> getAllDepts() {
		try {
			List<Dept> depts= deptRepository.findAll();
			for(Dept dept : depts) {
				if(dept.getCreater()!=null) dept.getCreater().getName();
				if(dept.getModifier()!=null) dept.getModifier().getName();
			}
			return depts;
 		}catch(Exception e) {
			throw new OaException("查询部门失败了",e);
		}
	}

	@Override
	public Map<String, Object> login(Map<String, Object> params) {
		Map<String,Object> result = new HashMap<>();
		try {
			String userId = (String)params.get("userId");
			String passWord = (String)params.get("passWord");
			String vcode = (String)params.get("vcode");
			HttpSession session=(HttpSession) params.get("session");
			
			if(StringUtils.isEmpty(userId)||StringUtils.isEmpty(passWord)||StringUtils.isEmpty(vcode)) {
				result.put("status",0);
				result.put("tip", "参数有为空的");
			}else {
				String sysCode=(String) session.getAttribute(CommonContants.VERIFY_SESSION);
				if(vcode.equalsIgnoreCase(sysCode)) {
					User user = getUserById(userId);
					if(user!=null) {
						if(user.getPassword().equals(passWord)) {
							if(user.getStatus()==1) {
								session.setAttribute(OaContants.USER_SESSION, user);
								result.put("status", 1);
								result.put("tip", "登录成功");
								UserHolder.addCurrentUser(user);
								Map<String,List<String>> userAllOperasPopedomUrls=getUserAllOperasPopedomUrls();
								session.setAttribute(OaContants.USER_ALL_OPERAS_POPEDOM_URLS, userAllOperasPopedomUrls);
							}else {
								result.put("status", 5);
								result.put("tip", "您的账号未被激活");
							}
						}else {
							result.put("status", 2);
							result.put("tip", "密码错误");
						}
					}else {
						result.put("status",3);
						result.put("tip", "没有该账户");
					}
				}else {
					result.put("status", 4);
					result.put("tip", "验证码不正确");
				}
			}
			return result;
		}catch(Exception e) {
			throw new OaException("异步登录业务抛出异常",e);
		}
	}

	private Map<String, List<String>> getUserAllOperasPopedomUrls() {
		try {
			List<String> userAllPopedomOperasCodes=popedomRepository.getUserPopedomOperasCodes(UserHolder.getCurrentUser().getUserId());
			if(userAllPopedomOperasCodes!=null&&userAllPopedomOperasCodes.size()>0) {
				Map<String,List<String>> userAllOperasPopedomUrls=new HashMap<>();
				String moduleUrl="";
				List<String> moduleOperaUrls=null;
				for(String operaCode:userAllPopedomOperasCodes) {
					String parentModuleCode = operaCode.substring(0,operaCode.length()-OaContants.CODE_LEN);
					moduleUrl=getModuleByCode(parentModuleCode).getUrl();
					if(!userAllOperasPopedomUrls.containsKey(moduleUrl)) {
						moduleOperaUrls=new ArrayList<String>();
						userAllOperasPopedomUrls.put(moduleUrl, moduleOperaUrls);
					}
					moduleOperaUrls.add(getModuleByCode(operaCode).getUrl());
					//一个父权限一个list，分类处理
				}
				return userAllOperasPopedomUrls;
			}
			return null;
		}catch(Exception e) {
			throw new OaException("登录查询用户操作权限异常",e);
		}
	}

	@Override
	public User getUserById(String userId) {
		try {
			User user = userRepository.findById(userId).get();
			if(user!=null) {
				if(user.getDept()!=null) user.getDept().getName();
				if(user.getJob()!=null) user.getJob().getName();
				if(user.getCreater()!=null) user.getCreater().getName();
				if(user.getModifier()!=null) user.getModifier().getName();
				if(user.getChecker()!=null) user.getChecker().getName();
				return user;
			}
			return null;
		}catch(OaException e) {
			throw new OaException("查询用户失败",e);
		}
	}

	@Transactional
	@Override
	public void updateSelf(User user, HttpSession session) {
		try {
			User sessionUser = userRepository.findById(user.getUserId()).get();
			sessionUser.setModifyDate(new Date());
			sessionUser.setModifier(user);
			sessionUser.setName(user.getName());
			sessionUser.setEmail(user.getEmail());
			sessionUser.setTel(user.getTel());
			sessionUser.setPhone(user.getPhone());
			sessionUser.setQuestion(user.getQuestion());
			sessionUser.setAnswer(user.getAnswer());
			sessionUser.setQqNum(user.getQqNum());
			
			if(sessionUser.getDept()!=null) sessionUser.getDept().getName();
			if(sessionUser.getJob()!=null) sessionUser.getJob().getName();
			session.setAttribute(OaContants.USER_SESSION,sessionUser);
		}catch(Exception e) {
			throw new OaException("修改用户失败了",e);
		}
	}

	@Override
	public Map<String, Object> getAllDeptsAndJobsAjax() {
		try {
			Map<String,Object> deptJobDatas = new HashMap<>();
			
			List<Map<String,Object>> deptsList = deptRepository.findDepts();
			
			List<Map<String,Object>> jobLists = jobRepository.findJobs();
			
			deptJobDatas.put("depts", deptsList);
			deptJobDatas.put("jobs", jobLists);
			
			return deptJobDatas;
		}catch(Exception e) {
			throw new OaException("查询部门与职位信息异常",e);
		}
	}

	//看不懂~~~
	@SuppressWarnings("serial")
	@Override
	public List<User> getUserByPager(User user, PageModel pageModel) {
		try {
			Page<User> usersPager = userRepository.findAll(new Specification<User>() {
				@Override
				public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query,
						CriteriaBuilder cb) {
					// 本集合用于封装查询条件
					List<Predicate> predicates = new ArrayList<Predicate>();  
					if(user!=null){
						/** 是否传入了姓名来查询  */
						if(!StringUtils.isEmpty(user.getName())){
							predicates.add(cb.like(root.<String> get("name"),"%" + user.getName() + "%"));
						}
						/** 是否传入手机号码了来查询  */
						if(!StringUtils.isEmpty(user.getPhone())){
							predicates.add(cb.like(root.<String> get("phone"),"%" + user.getPhone() + "%"));
						}
						/** 是否传入部门来查询  */
						if(user.getDept()!=null && user.getDept().getId()!=null && user.getDept().getId()!= 0 ){
							root.join("dept", JoinType.INNER);
							Path<Long> d_id = root.get("dept").get("id");
							predicates.add(cb.equal(d_id, user.getDept().getId()));

						}
						if(user.getJob()!=null && !StringUtils.isEmpty(user.getJob().getCode()) 
								&& !user.getJob().getCode().equals("0")){
							root.join("job", JoinType.INNER);
							Path<String> j_id = root.get("job").get("code");
							predicates.add(cb.equal(j_id, user.getJob().getCode()));
						}
					}
					return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
				}
			}, PageRequest.of(pageModel.getPageIndex() - 1, pageModel.getPageSize()));
			pageModel.setRecordCount(usersPager.getTotalElements());
			/** 取每个用户的延迟加载属性 */
			List<User> users = usersPager.getContent();
			for(User u : users){
				if(u.getDept()!=null)u.getDept().getName();
				if(u.getJob()!=null)u.getJob().getName();
				if(u.getChecker()!=null)u.getChecker().getName();
			}
			return users;
		} catch (Exception e) {
			throw new OaException("查询用户信息异常了", e);
		}
	}

	@Override
	public void deleteUserByUserIds(String ids) {
		try {
			List<User> users = new ArrayList<User>();
			for(String id : ids.split(",")) {
				User user=new User();
				user.setUserId(id);
				users.add(user);
			}
			userRepository.deleteInBatch(users);
		}catch(Exception e) {
			throw new OaException("删除用户信息失败",e);
		}
	}

	@Override
	public String isUserValidAjax(String userId) {
		try {
			User user = userRepository.findById(userId).get();
			return user==null? "success":"error";
		}catch(Exception e) {
			throw new OaException("校验登录名异常",e);
		}
	}

	@Transactional
	@Override
	public void addUser(User user) {
		try {
			user.setCreateDate(new Date());
			user.setCreater(UserHolder.getCurrentUser());;
			userRepository.save(user);
		}catch(Exception e) {
			throw new OaException("添加用户信息异常",e);
		}
	}

	@Transactional
	@Override
	public void updateUser(User user) {
		try {
			User sessionUser = userRepository.findById(user.getUserId()).get();
			sessionUser.setModifyDate(new Date());
			sessionUser.setModifier(UserHolder.getCurrentUser());
			sessionUser.setPassword(user.getPassword());
			sessionUser.setName(user.getName());
			sessionUser.setDept(user.getDept());
			sessionUser.setJob(user.getJob());
			sessionUser.setEmail(user.getEmail());
			sessionUser.setSex(user.getSex());
			sessionUser.setTel(user.getTel());
			sessionUser.setPhone(user.getPhone());
			sessionUser.setQuestion(user.getQuestion());
			sessionUser.setAnswer(user.getAnswer());
			sessionUser.setQqNum(user.getQqNum());
		}catch(Exception e) {
			throw new OaException("修改用户失败",e);
		}
	}

	@Transactional
	@Override
	public void activeUser(User user) {
		try {
			User sessionUser = userRepository.findById(user.getUserId()).get();
			sessionUser.setCheckDate(new Date());
			sessionUser.setChecker(UserHolder.getCurrentUser());
			sessionUser.setStatus(user.getStatus());
		}catch(Exception e) {
			throw new OaException("激活用户失败",e);
		}
	}

	@Override
	public List<TreeData> loadAllModuleTrees() {
		try {
			/** 查询所有的模块信息   */
			List<Module> modules = moduleRepository.findAll();
			/** 拼装成dtree需要的树节点  */
			List<TreeData> treeDatas = new ArrayList<>();
			for(Module m : modules){
				TreeData data = new TreeData();
				data.setId(m.getCode());
				data.setName(m.getName());
				// 长度为4的编号的父节点是0
				// 其余节点的父节点是从开始位置一直截取到总长度减去步长的位置。 00010001的父节点是0001
				String pid = m.getCode().length()==OaContants.CODE_LEN?"0":m.getCode().substring(0, m.getCode().length()-OaContants.CODE_LEN);
				data.setPid(pid);
				treeDatas.add(data);
			}
			return treeDatas;
		} catch (Exception e) {
			throw new OaException("加载模块树异常", e);
		}
	}

	@SuppressWarnings("serial")
	@Override
	public List<Module> getModulesByParent(String parentCode,PageModel pageModel) {
		try {
			parentCode = parentCode==null?"":parentCode;   
			List<Object> values = new ArrayList<>();
			values.add(parentCode+"%");
			values.add(parentCode.length()+OaContants.CODE_LEN);
//			// 子节点的编号的长度是父节点编号长度+步长
//		    // 子节点前几位的编号必须与父节点编码一致
			Page<Module> modulesPager = moduleRepository.findAll(new Specification<Module>() {

				@Override
				public Predicate toPredicate(Root<Module> root, CriteriaQuery<?> query,
						CriteriaBuilder cb) {
					// 本集合用于封装查询条件
					List<Predicate> predicates = new ArrayList<Predicate>();  
					predicates.add(cb.like(root.<String> get("code"),values.get(0)+""));
					predicates.add(cb.equal(cb.length(root.<String> get("code")),values.get(1)));
					return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
				}
			}, PageRequest.of(pageModel.getPageIndex() - 1, pageModel.getPageSize()));
			pageModel.setRecordCount(modulesPager.getTotalElements());
			/** 取每个用户的延迟加载属性 */
			List<Module> sonModules = modulesPager.getContent();
			for(Module m : sonModules){
				if(m.getCreater()!=null)m.getCreater().getName();
				if(m.getModifier()!=null)m.getModifier().getName();
			}
			return sonModules;
		} catch (Exception e) {
			throw new OaException("查询子模块异常", e);
			
		}
	}

	@Override
	public List<Module> getModelesByParent(String parentCode) {
		
		try {
			parentCode = parentCode==null?"":parentCode;   
			List<Module> sonModules = moduleRepository.findModules(parentCode+"%" , parentCode.length()+OaContants.CODE_LEN);
			for(Module m : sonModules){
				if(m.getCreater()!=null)m.getCreater().getName();
				if(m.getModifier()!=null)m.getModifier().getName();
			}
			return sonModules;
		} catch (Exception e) {
			throw new OaException("查询子模块异常", e);
		}
		
	}

	@Transactional
	@Override
	public void deleteModules(String ids) {
		try {
			for(String code  : ids.split(",")){
				moduleRepository.setCode(code);
			}
		} catch (Exception e) {
			throw new OaException("批量删除菜单异常", e);
		}
		
	}
	@Transactional
	@Override
	public void addModules(String parentCode, Module module) {
		try {
			
			/** 维护编号:通用工具类(给你一个父节点,给你一张表,给你那个字段,
			 *  找出该字段该父节点下的下一个儿子节点的编号 ) */
			module.setCode(getNextSonCode(parentCode, OaContants.CODE_LEN));
			module.setCreateDate(new Date());
			module.setCreater(UserHolder.getCurrentUser());
			moduleRepository.save(module);
		} catch (Exception e) {
			throw new OaException("添加子菜单异常", e);
		}
		
	}
	
	public String getNextSonCode(String parentCode,int codeLen) throws Exception {
		/** 判断父节点是否为null */
		parentCode =  parentCode==null?"":parentCode;
		/** 1.查询出当前父节点下的最大儿子节点编号 */
		String maxSonCode = moduleRepository.findUniqueEntity(parentCode+"%" , parentCode.length()+codeLen);
		String nextSonCode = ""; // 保存最终的下一个儿子节点编号 
		/** 4.判断最大儿子节点编号是否存在 ,因为极有可能父节点此时一个子节点都没有 */
		if(StringUtils.isEmpty(maxSonCode)){
			/** 儿子节点编号不存在 */
			String preSuffix = "" ; // 0 需要拼接多少个0
			for(int i = 0 ; i < codeLen - 1; i++ ){
				preSuffix+="0";
			}
			nextSonCode = parentCode+preSuffix+1;
		}else{
			/** 儿子节点编号存在  */
			/** 截取出当前儿子节点编号的步长出来  */
			String currentMaxSonCode = maxSonCode.substring(parentCode.length());
			/** 得到儿子节点步长编号的整形形式   */
			int maxSonCodeInt = Integer.valueOf(currentMaxSonCode);
			maxSonCodeInt++; 
			/** 判断编号是否越界了 */
			if((maxSonCodeInt+"").length() > codeLen){
				throw new OaException("编号越界了！");
			}else{
				/** 没有越界 */
				String preSuffix = "" ; // 0 需要拼接多少个0
				for(int i = 0 ; i< codeLen-(maxSonCodeInt+"").length() ; i++){
					preSuffix+="0";
				}
				
				nextSonCode = parentCode+preSuffix+maxSonCodeInt;
			}
		}
		return nextSonCode;
	}

	@Override
	public Module getModuleByCode(String code) {
		try {
			return moduleRepository.findById(code).get();
		} catch (Exception e) {
			throw new OaException("查询模块异常", e);
		}
	}

	@Transactional
	@Override
	public void updateModule(Module module) {
		try {
			Module sessionModule = moduleRepository.findById(module.getCode()).get();
			sessionModule.setModifier(UserHolder.getCurrentUser());
			sessionModule.setModifyDate(new Date());
			sessionModule.setName(module.getName());
			sessionModule.setRemark(module.getRemark());
			sessionModule.setUrl(module.getUrl());
		} catch (Exception e) {
			throw new OaException("修改模块异常", e);
		}
		
	}

	@Override
	public List<Role> getRoleByPager(PageModel pageModel) {
		try {
			// 指定排序参数对象：根据id，进行降序查询
			Sort sort = new Sort(Sort.Direction.ASC, "id");
		   /**
			* 封装分页实体 
			* 参数一：pageIndex表示当前查询的第几页(默认从0开始，0表示第一页) 
			* 参数二：表示每页展示多少数据，现在设置每页展示2条数据
			* 参数三：封装排序对象，根据该对象的参数指定根据id降序查询
			*/
			Pageable page = PageRequest.of(pageModel.getPageIndex() - 1, pageModel.getPageSize(), sort);
			Page<Role> rolePager  = roleRepository.findAll(page);
			pageModel.setRecordCount(rolePager.getTotalElements());
			/** 取每个用户的延迟加载属性 */
			List<Role> roles = rolePager.getContent();
			for(Role r : roles){
				if(r.getModifier()!=null)r.getModifier().getName();
				if(r.getCreater()!=null)r.getCreater().getName();
			}
			return roles;
		} catch (Exception e) {
			throw new OaException("查询角色异常", e);
		}
	}

	@Transactional
	@Override
	public void addRole(Role role) {
		try {
			role.setCreateDate(new Date());
			role.setCreater(UserHolder.getCurrentUser());
			roleRepository.save(role);
		} catch (Exception e) {
			throw new OaException("添加角色异常", e);
		}
		
	}

	@Transactional
	@Override
	public void deleteRole(String ids) {
		try {
			List<Role> roles = new ArrayList<Role>();
			for(String id : ids.split(",")){
				Role role = new Role() ;
				role.setId(Long.valueOf(id));
			}
			roleRepository.deleteInBatch(roles);
			
		} catch (Exception e) {
			throw new OaException("批量删除角色异常", e);
		}
	}

	@Override
	public Role getRoleById(Long id) {
		try {
			return roleRepository.findById(id).get();
		} catch (Exception e) {
			throw new OaException("根据id查询角色异常", e);
		}
	}

	@Transactional
	@Override
	public void updateRole(Role role) {
		try {
			Role r = roleRepository.findById(role.getId()).get();
			r.setName(role.getName());
			r.setRemark(role.getRemark());
			r.setModifier(UserHolder.getCurrentUser());
			r.setModifyDate(new Date());
		} catch (Exception e) {
			throw new OaException("根据id修改角色异常", e);
		}
		
	}

	@SuppressWarnings("serial")
	@Override
	public List<User> selectRoleUser(Role role, PageModel pageModel) {
		try {
			Page<User> usersPager = userRepository.findAll(new Specification<User>() {
				@Override
				public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query,
						CriteriaBuilder cb) {
					// 本集合用于封装查询条件
					List<Predicate> predicates = new ArrayList<>();  
					List<String> userIds = userRepository.findRoleUsers(role.getId());  
                    predicates.add(root.<String>get("userId").in(userIds));
					return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
				}
			}, PageRequest.of(pageModel.getPageIndex() - 1, pageModel.getPageSize()));
			pageModel.setRecordCount(usersPager.getTotalElements());
			List<User> users =  usersPager.getContent();
			for(User u : users){
				if(u.getDept()!=null)u.getDept().getName();
				if(u.getJob()!=null)u.getJob().getName();
				if(u.getChecker()!=null)u.getChecker().getName();
			}
			return users;
		} catch (Exception e) {
			throw new OaException("查询属于角色下的用户信息异常", e);
		}
		
	}

	@SuppressWarnings("serial")
	@Override
	public List<User> selectNotRoleUser(Role role, PageModel pageModel) {
		try {
			
			Page<User> usersPager = userRepository.findAll(new Specification<User>() {
				@Override
				public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query,
						CriteriaBuilder cb) {
					// 本集合用于封装查询条件
					List<Predicate> predicates = new ArrayList<Predicate>();  
					// 先查询出不属于这个角色下的用户
					List<String> userId = userRepository.getRolesUsers(role.getId());
					predicates.add(root.<String>get("userId").in(userId));  
					return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
				}
			}, PageRequest.of(pageModel.getPageIndex() - 1, pageModel.getPageSize()));
			pageModel.setRecordCount(usersPager.getTotalElements());
			List<User> users =  usersPager.getContent();
			
			for(User u : users){
				if(u.getDept()!=null)u.getDept().getName();
				if(u.getJob()!=null)u.getJob().getName();
				if(u.getChecker()!=null)u.getChecker().getName();
			}
			return users;
		} catch (Exception e) {
			throw new OaException("查询不属于角色下的用户信息异常", e);
		}
	}

	@Transactional
	@Override
	public void bindUser(Role role, String ids) {
		try {
			/** 给角色绑定一批用户 */
			/** 1.先查询出该角色 */
			Role session = roleRepository.findById(role.getId()).get();
			/** 2.给角色的users添加需要绑定的用户 */
			for(String userId : ids.split(",")){
				User user = userRepository.findById(userId).get();
				session.getUsers().add(user);
			}
			
		} catch (Exception e) {
			throw new OaException("绑定角色下的用户异常", e);
		}
		
	}

	@Transactional
	@Override
	public void unBindUser(Role role, String ids) {
		try {
			/** 给角色绑定一批用户 */
			/** 1.先查询出该角色 */
			Role session = roleRepository.findById(role.getId()).get();
			/** 2.给角色的users添加需要绑定的用户 */
			for(String userId : ids.split(",")){
				User user = userRepository.findById(userId).get();
				session.getUsers().remove(user);
			}
			
		} catch (Exception e) {
			throw new OaException("绑定角色下的用户异常", e);
		}
		
	}

	@Override
	public List<String> getRoleModuleOperasCodes(Role role, String parentCode) {
		try {
			List<String> roleModuleOperasCodes = popedomRepository.findByIdAndParentCode(role.getId(),parentCode);
			return roleModuleOperasCodes;
		} catch (Exception e) {
			throw new OaException("查询当前角色在当前模块下拥有的操作权限编号异常", e);
		}
	}

	@Transactional
	@Override
	public void bindPopedom(String codes, Role role, String parentCode) {
		try {
		    /** 1.先清空此角色在此模块下的所有操作权限 */
			popedomRepository.setByIdAndParentCode(role.getId(),parentCode);
			/** 2.更新新的角色模块权限 */
			if(!StringUtils.isEmpty(codes)){
				Module parent = getModuleByCode(parentCode);
				/** 添加一些更新的权限 */
				for(String code : codes.split(",")){
					/** 创建一个权限对象 */
					Popedom popedom = new Popedom();
					popedom.setRole(role);
					popedom.setModule(parent);
					popedom.setOpera(getModuleByCode(code));
					popedom.setCreateDate(new Date());
					popedom.setCreater(UserHolder.getCurrentUser());
					popedomRepository.save(popedom);
				}
			}
		} catch (Exception e) {
			throw new OaException("给角色绑定某个模块的操作权限异常", e);
		}
		
	}

	@Override
	public List<UserModule> getUserPopedomModules() {
		try {
			/**查询当前用户的权限模块 ：先查用户所有的角色,再查这些角色拥有的所有权限模块  */
			List<String> popedomModuleCodes = popedomRepository.getUserPopedomModuleCodes(UserHolder.getCurrentUser().getUserId());
			if(popedomModuleCodes!=null && popedomModuleCodes.size()>0){
				
				/** 定义一个Map集合用于保存用户的权限模块 
				 *  Map<Module,List<Module>> 
				 *  {系统管理=[用户管理,角色管理] , 假期模块=[查询信息,用户请假]}
				 *  */
			    Map<Module,List<Module>> userModulesMap = new LinkedHashMap<>();
			    Module fistModule = null ;
			    List<Module> secondModules = null ;
				for(String moduleCode : popedomModuleCodes){
					/** 截取当前模块的一级模块编号 */
					String fistCode = moduleCode.substring(0, OaContants.CODE_LEN);
					/** 查询出一级模块对象 */
					fistModule = getModuleByCode(fistCode);
					fistModule.setName(fistModule.getName().replaceAll("-", ""));
					/**如果map集合中没有包含当前一级模块的key,说明是第一次添加一级模块 */
					if(!userModulesMap.containsKey(fistModule)){
						secondModules = new ArrayList<Module>();
						userModulesMap.put(fistModule, secondModules);
					}
					Module secondModule = getModuleByCode(moduleCode);
					secondModule.setName(secondModule.getName().replaceAll("-", ""));
					secondModules.add(secondModule);
				}
				
				List<UserModule> userModules = new ArrayList<>();
				for(Entry<Module, List<Module>> entry : userModulesMap.entrySet()){
					Module key = entry.getKey();
					List<Module> value = entry.getValue();
					UserModule userModule = new UserModule();
					userModule.setFirstModule(key);
					userModule.setSecondModules(value);
					userModules.add(userModule);
				}
				return userModules;
				
			}
			return null;
			
		} catch (Exception e) {
			throw new OaException("查询当前用户的权限模块", e);
		}
	}
	
}
