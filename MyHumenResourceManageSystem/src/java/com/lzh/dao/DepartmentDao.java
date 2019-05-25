package java.com.lzh.dao;

import java.com.lzh.pojo.Department;
import java.util.List;

public interface DepartmentDao {
	
	int deleteDeptById(Integer deptId);
	
	int updateDeptById(Integer deptId);
	
	int insertDept(Department dept);
	
	Department selectById(Integer deptId);
	
	Department selectByLeader(String leader);
	
	Department selectByName(String name);
	
	List<Department> selectAllDept();
	
	List<Department> selectDeptByPage(Integer offset,Integer limit);
	
	int checkDeptByNameAndLeader(String name,String leader);
	
	int totalCount();
}
