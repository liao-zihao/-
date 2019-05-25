package java.com.lzh.pojo;
/*
 * @author LIAO ZIHAO
 * @date 2018/12/19
 */
public class Employee {
	private Integer empId;
	private String empName;
	private String empMail;
	private String empGender;
	private Integer empDepartmentId;
	private Department dept;
	
	public Department getDept() {
		return dept;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpMail() {
		return empMail;
	}
	public void setEmpMail(String empMail) {
		this.empMail = empMail;
	}
	public String getEmpGender() {
		return empGender;
	}
	public void setEmpGender(String empGender) {
		this.empGender = empGender;
	}
	public Integer getEmpDepartmentId() {
		return empDepartmentId;
	}
	public void setEmpDepartmentId(Integer empDepartmentId) {
		this.empDepartmentId = empDepartmentId;
	}
	
}
