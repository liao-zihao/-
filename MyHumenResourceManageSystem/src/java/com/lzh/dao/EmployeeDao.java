package java.com.lzh.dao;

import java.com.lzh.pojo.Employee;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface EmployeeDao {
	
	int deleteById(Integer empId);
	
	int updateById(@Param("empId")Integer empId,@Param("emp")Employee emp);
	
	int insertOne(@Param("emp")Employee emp);
	
	Employee selectById(Integer empId);
	
	Employee selectByName(String name);
	
	//Employee selectWithDeptById(Integer empId);
	
	//List<Employee> selectByPage(Integer offset,Integer limit);
	
	int totalCount();
}
