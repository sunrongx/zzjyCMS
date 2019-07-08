package com.zs.zscms.user.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zs.zscms.user.bean.UserBean;
import com.zs.zscms.util.DBUtil;
import com.zz.cms.exception.SysException;

public class UserDaoImpl implements UserDao {
	//创建DBUtil对象来连接数据库
	DBUtil db = new DBUtil();
	@Override
	public List<UserBean> login(UserBean user) throws SysException {
		// TODO 自动生成的方法存根
		//登录查询的SQL语句
		String sql="select * from tuser where loginname=? and password=? order by id";
		//给SQL语句的参数赋值
		Object [] obj = {user.getLoginname(),user.getPassword()};
		//执行查询，返回结果
		List<Map<String, Object>> list = db.execQuery(sql,obj);
		//创建list集合来存储user对象
		List <UserBean> users = new ArrayList<UserBean> ();
		//遍历集合，将map集合中的数据取出，然后直接封装到userbean中
		for(Map<String, Object> map:list){
			//创建UserBean对象
			UserBean userbean = new UserBean();
			//根据key来取map集合的值
			userbean.setId(Integer.parseInt((String) map.get("id")));
			userbean.setLoginname(String.valueOf(map.get("loginname")));
			userbean.setPassword(String.valueOf(map.get("password")));
			userbean.setRealname((String) map.get("realname"));
			userbean.setSex((String) map.get("sex"));
			userbean.setBirthday((String) map.get("birthday"));
			userbean.setEmail((String) map.get("email"));
			userbean.setDept(Integer.parseInt((String) map.get("dept")));
			userbean.setEnabled(Integer.parseInt((String) map.get("enabled")));
			userbean.setCreatman(Integer.parseInt(String.valueOf( map.get("creatman"))));
			if(map.get("deptname")!=null){
				userbean.setDeptname((String) map.get("deptname"));
			}
			if(userbean.getEnabled()==1){
				userbean.setEnabledTxt("可用");
			}else{
				userbean.setEnabledTxt("不可用");
			}
			
			// 将userbean对象加入集合中
			users.add(userbean);
		}
		return users;
	}
	
	
	@Override
	//根据条件查询用户信息
	public List<UserBean> queryByTiaoJian(String sql, Object[] obj) {
		// TODO 自动生成的方法存根
		//用三元运算符与sql语句拼串，结果相当于：select * from tuser where 1=1 and id=?
		//String sql="select * from tuser where 1=1"+(TiaoJian==null?"":TiaoJian);
		//创建listmap集合存储信息
		List<Map<String, Object>> list =db.execQuery(sql,obj);
		//创建集合来存储对象
		List <UserBean> users = new ArrayList<UserBean>();
		//遍历集合，将数据从map集合中取出然后直接封装到userbean中
		for(Map<String, Object> map:list){
			//创建UserBean对象
			UserBean userbean = new UserBean();
			//根据key来取map集合的值
			userbean.setId(Integer.parseInt((String) map.get("id")));
			userbean.setLoginname((String) map.get("loginname"));
			userbean.setPassword((String) map.get("password"));
			userbean.setRealname((String) map.get("realname"));
			userbean.setSex((String) map.get("sex"));
			userbean.setBirthday((String) map.get("birthday"));
			userbean.setEmail((String) map.get("email"));
			userbean.setDept(Integer.parseInt((String) map.get("dept")));
			userbean.setEnabled(Integer.parseInt((String) map.get("enabled")));
			userbean.setCreatman(Integer.parseInt((String) map.get("creatman")));
			if(map.get("deptname")!=null){
				userbean.setDeptname((String) map.get("deptname"));
			}
			if(userbean.getEnabled()==1){
				userbean.setEnabledTxt("可用");
			}else{
				userbean.setEnabledTxt("不可用");
			}
			// 将userbean对象加入集合中
			users.add(userbean);
		}
		return users;
		
	}


	@Override
	public List<UserBean> queryAll() {
		// TODO 自动生成的方法存根
		
		//查询所有信息，不需要条件和参数，所以直接给null
		String sql="select u.*,d.deptname from tuser u left join dept d on u.dept=d.id ;";
		//返回根据sql语句查询的结果
		return queryByTiaoJian(sql,null);
	}


	@Override
	public int insertUser(UserBean user) {
		// TODO 自动生成的方法存根
		//添加用户信息的sql语句
		String sql="insert into tuser values(null,?,?,?,?,?,?,?,?,?)";
		//创建获得用户信息的Object数组
		Object [ ] obj = {user.getLoginname(),user.getPassword(),user.getRealname(),user.getSex(),user.getBirthday(),user.getEmail(), user.getDept(),user.getEnabled(),user.getCreatman() };
		//使用新增方法新增用户信息，将新增的结果赋值给int类型的result
		int result = db.execUpdate(sql,obj);
		//返回result
		return result;
	}


	@Override
	//根据用户名查询信息
	public List<UserBean> queryByLoginname(String loginname) {
		// TODO 自动生成的方法存根
		String sql="select * from tuser where loginname=? ";
		//将用户名赋值给数组
		Object [ ] obj = {loginname};
		//返回根据数组信息为条件查询到的结果
		return this.queryByTiaoJian(sql, obj);
	}


	@Override
	//根据邮箱查询信息
	public List<UserBean> queryByEmail(String email)  {
		// TODO 自动生成的方法存根
		String sql = "select * from tuser where email=?";
		//将邮箱赋值给数组
		Object [ ]  obj  = {email};
		//返回根据数组信息为条件查询到的结果
		return this.queryByTiaoJian(sql, obj);
	}


	@Override
	//根据ID查询信息
	public List<UserBean> queryUserById(int id) throws SysException {
		// TODO 自动生成的方法存根
		String sql= "select * from tuser where id=? ";
		//创建数组
		Object  [ ] obj = {id};
		//返回查询结果
		return this.queryByTiaoJian(sql, obj);
	}


	@Override
	//修改用户信息
	public int updateUser(UserBean user) throws SysException {
		// TODO 自动生成的方法存根
		String sql = "update tuser set loginname=?,realname=?,sex=?,birthday=?,email=?,dept=?,enabled=?  where id=?";
		//创建数组
		Object [ ]  obj = { user.getLoginname(),user.getRealname(),user.getSex(),user.getBirthday(),user.getEmail(),user.getDept(),user.getEnabled(),user.getId() };
		//将修改结果赋值给int类型的值
		int result = db.execUpdate(sql,obj);
		//返回该结果值
		return result;
	}


	@Override
	public int deleteUser(int id) throws SysException {
		// TODO 自动生成的方法存根
		//删除用户信息
		String sql="delete from tuser where id=?";		
		Object []  obj={id};
		//创建int类型变量接收删除结果
		int result = db.execUpdate(sql,obj);
		//返回变量
		return result;
	}


	@Override
	public List<UserBean> queryByPage(int start, int size) throws SysException {
		// TODO 自动生成的方法存根
		//将所用用户信息降序排序并进行分页
		String sql="select u.*,d.dname from tuser u left join tdept d on u.dept=d.id order by u.id desc limit ?,? ";
		//给参数赋值
		Object [ ] objs = {start,size};
		return queryByTiaoJian(sql,objs);
	}


	@Override
	public int queryUserCounts() throws SysException {
		// TODO 自动生成的方法存根
		//通过count方法获取总行数并命名为count
		String sql = "select count(id) count from tuser";
		List<Map<String, Object>> list = db.execQuery(sql,null);
		//获取count的值
		int userCounts = Integer.parseInt((String) list.get(0).get("count"));
		
		return userCounts;
	}

}
