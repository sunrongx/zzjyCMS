package com.zz.cms.user.dao.impl;


import java.util.List;

import com.zz.cms.exception.SysException;
import com.zz.cms.user.bean.UserBean;

public interface UserDao {
	//一个map集合对应一个userbean
	public List<UserBean> login(UserBean user) throws SysException ;
	//根据条件查询，参数为查询条件，返回的是所有用户的集合
	public List<UserBean> queryByTiaoJian(String TiaoJian,Object [ ] obj);
	//查询所有用户信息的方法
	public List<UserBean> queryAll();
	//用户新增的判断方法
	public int insertUser(UserBean user);
	//根据用户名查询用户信息
	public UserBean queryByLoginname(String loginname);
	//根据邮箱查询用户信息
	public UserBean queryByEmail(String email ) throws SysException;
	//根据用户ID查询用户信息
	public List<UserBean> queryUserById(int id) throws SysException;
	//修改用户的方法
	public int updateUser(UserBean user) throws SysException;
	//删除用户的方法
	public int deleteUser(int id) throws SysException;
	//通过页数查询用户
//	public List<UserBean> queryByPage(int start,int size) throws SysException;
	//通过总数查询用户
	public int queryUserCounts() throws SysException;
	//分页查询所有的方法
	//public List<UserBean> queryByPage(String name, int start, int size) throws SysException;
	public List<UserBean> queryByPage(String name,String sex,String enabled, int start, int size) throws SysException;
	//根据性别查询用户
	public List<UserBean> queryBySex(String sex,int start,int size);
	//根据是否可用查询用户
	public List<UserBean> queryByEnabled(int enabled,int start,int size);
	
}
