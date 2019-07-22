package com.zz.cms.user.bean;

public class UserBean  {
	//id 主键
	private int id;
	//登录名
	private String loginname;
	//登录密码
	private String password;
	//真实姓名
	private String realname;
	//性别
	private String sex;
	//生日
	private String birthday;
	//邮箱
	private String email;
	//所在部门
	private int dept;
	//是否可用
	private int enabled;
	//创建人
	private int creatman;
	//部门名称
	private String deptname;
	//是否可用
	private String enabledTxt;
	
	
	
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getEnabledTxt() {
		return enabledTxt;
	}
	public void setEnabledTxt(String enabledTxt) {
		this.enabledTxt = enabledTxt;
	}
	public int getCreatman() {
		return creatman;
	}
	public void setCreatman(int creatman) {
		this.creatman = creatman;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getDept() {
		return dept;
	}
	public void setDept(int dept) {
		this.dept = dept;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	
	
	
}
