package com.lzh.myoa.identity.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity @Table(name="oa_id_user",
		indexes= {@Index(columnList="name",name="idx_user_name")})
public class User implements Serializable{
	@Id @Column(name="user_id",length=50)
	private String userId;
	@Id @Column(name="pass_word",length=50)
	private String password;
	@Column(name="name",length=50)
	private String name;
	@Column(name="sex")
	private Short sex=1;
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Dept.class)
	@JoinColumn(name="dept_id",referencedColumnName="id",
			foreignKey=@ForeignKey(name="fk_user_dept"))
	private Dept dept;
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Job.class)
	@JoinColumn(name="job_code",referencedColumnName="code",
			foreignKey=@ForeignKey(name="fk_user_job"))
	private Job job;
	@Column(name="email",length=50)
	private String email;
	@Column(name="tel",length=50)
	private String tel;
	@Column(name="phone",length=50)
	private String phone;
	@Column(name="qq_num",length=50)
	private String qqNum;
	@Column(name="question")
	private Short question;
	@Column(name="answer",length=200)
	private String answer;
	@Column(name="status")
	private Short status=0;
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=User.class)
	@JoinColumn(name="creater",referencedColumnName="user_id",
			foreignKey=@ForeignKey(name="fk_user_creater"))
	private User creater;
	@Column(name="create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=User.class)
	@JoinColumn(name="modifier",referencedColumnName="user_id",
			foreignKey=@ForeignKey(name="fk_user_modifier"))
	private User modifier;
	@Column(name="modifier_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate;
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=User.class)
	@JoinColumn(name="checker",referencedColumnName="user_id",
			foreignKey=@ForeignKey(name="fk_user_checker"))
	private User checker;
	@Column(name="check_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date checkDate;
	@ManyToMany(fetch=FetchType.LAZY,targetEntity=Role.class,mappedBy="users")
	private Set<Role> roles=new HashSet<>();
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Short getSex() {
		return sex;
	}
	public void setSex(Short sex) {
		this.sex = sex;
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQqNum() {
		return qqNum;
	}
	public void setQqNum(String qqNum) {
		this.qqNum = qqNum;
	}
	public Short getQuestion() {
		return question;
	}
	public void setQuestion(Short question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public User getCreater() {
		return creater;
	}
	public void setCreater(User creater) {
		this.creater = creater;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public User getModifier() {
		return modifier;
	}
	public void setModifier(User modifier) {
		this.modifier = modifier;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public User getChecker() {
		return checker;
	}
	public void setChecker(User checker) {
		this.checker = checker;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
}
