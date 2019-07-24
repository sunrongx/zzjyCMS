package com.zz.cms.user.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zz.cms.exception.SysException;
import com.zz.cms.user.bean.UserBean;
import com.zz.cms.util.DBUtil;

public class UserDaoImpl implements UserDao {
	//创建DBUtil对象来连接数据库
	DBUtil db = new DBUtil();
	
	/**
	 * 登陆验证
	 */
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
			//赋值用户名
			userbean.setLoginname(String.valueOf(map.get("loginname")).trim());
			//赋值密码
			userbean.setPassword(String.valueOf(map.get("password")));
			//赋值真实姓名
			userbean.setRealname((String) map.get("realname"));
			//赋值性别
			userbean.setSex((String) map.get("sex"));
			//赋值生日
			userbean.setBirthday((String) map.get("birthday"));
			//赋值邮箱
			userbean.setEmail(String.valueOf(map.get("email")).trim());
			//赋值部门
			userbean.setDept(Integer.parseInt((String) map.get("dept")));
			//赋值是否可用
			userbean.setEnabled(Integer.parseInt((String) map.get("enabled")));
			//赋值创建人
			userbean.setCreatman(Integer.parseInt(String.valueOf( map.get("creatman"))));
			//赋值部门名称
			if(map.get("deptname")!=null){
				userbean.setDeptname((String) map.get("deptname"));
			}
			//赋值是否可用文字
			if(userbean.getEnabled()==1){
				userbean.setEnabledTxt("可用");
			}else{
				userbean.setEnabledTxt("不可用");
			}
			
			// 将userbean对象加入集合中
			users.add(userbean);
		}
		//返回用户集合
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
			//赋值用户名
			userbean.setLoginname((String) map.get("loginname"));
			//赋值密码
			userbean.setPassword((String) map.get("password"));
			//赋值真实姓名
			userbean.setRealname((String) map.get("realname"));
			//赋值性别
			userbean.setSex((String) map.get("sex"));
			//赋值生日
			userbean.setBirthday((String) map.get("birthday"));
			//赋值邮箱
			userbean.setEmail((String) map.get("email"));
			//赋值部门
			userbean.setDept(Integer.parseInt((String) map.get("dept")));
			//赋值是否可用
			userbean.setEnabled(Integer.parseInt((String) map.get("enabled")));
			//赋值创建人
			userbean.setCreatman(Integer.parseInt((String) map.get("creatman")));
			//赋值部门名称
			if(map.get("deptname")!=null){
				userbean.setDeptname((String) map.get("deptname"));
			}
			//赋值是否可用文字
			if(userbean.getEnabled()==1){
				userbean.setEnabledTxt("可用");
			}else{
				userbean.setEnabledTxt("不可用");
			}
			// 将userbean对象加入集合中
			users.add(userbean);
		}
		//返回用户集合
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
		Object [ ] obj = {user.getLoginname(),user.getPassword(),user.getRealname(),user.getSex(),user.getBirthday(),user.getDept(),user.getEmail(),user.getEnabled(),user.getCreatman() };
		//使用新增方法新增用户信息，将新增的结果赋值给int类型的result
		int result = db.execUpdate(sql,obj);
		//返回result
		return result;
	}


	@Override
	//根据用户名查询信息
	public UserBean queryByLoginname(String loginname) {
		// TODO 自动生成的方法存根
		String sql="select * from tuser where loginname=? ";
		//将用户名赋值给数组
		Object [ ] obj = {loginname};
		//根据查询结果返回bean或null
		if(queryByTiaoJian(sql, obj)!=null&&queryByTiaoJian(sql, obj).size()!=0) {
			return this.queryByTiaoJian(sql, obj).get(0);
		}else {
			return null;
		}
		
	}


	@Override
	//根据邮箱查询信息
	public UserBean queryByEmail(String email)  {
		// TODO 自动生成的方法存根
		String sql = "select * from tuser where email=?";
		//将邮箱赋值给数组
		Object [ ]  obj  = {email};
		//根据查询结果返回bean或null
		if(queryByTiaoJian(sql, obj)!=null&&queryByTiaoJian(sql, obj).size()!=0) {
			return this.queryByTiaoJian(sql, obj).get(0);
		}else {
			return null;
		}
		
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

	/**
	 * 分页查询用户所有的信息
	 * */
	@Override
	//public List<UserBean> queryByPage(String name,int start,int size) throws SysException {
	public List<UserBean> queryByPage(String name,String sex,String enabled,int start,int size) throws SysException {
		//查询所有，不需要条件和参数，那么直接给null
		//左外连接
		//String sql="select  u.*,d.deptname from tuser u left join dept d on u.dept=d.id where loginname like ? order by u.id desc limit ?,?";
		String sql = "select  u.*,d.deptname from tuser u left join dept d on u.dept=d.id where loginname like ? and sex like ? and enabled like ? order by u.id desc limit ?,?";
		//Object[] obj={"%"+name+"%",start,size};
		Object [] obj = {"%"+name+"%","%"+sex+"%","%"+enabled+"%",start,size};
		return queryByTiaoJian(sql, obj);
	}
	
/*	@Override
	public List<UserBean> queryByPage(int start, int size) throws SysException {
		// TODO 自动生成的方法存根
		//将所用用户信息降序排序并进行分页
		String sql="select u.*,d.deptname from tuser u left join dept d on u.dept=d.id order by u.id desc limit ?,? ";
		//给参数赋值
		Object [ ] objs = {start,size};
		return queryByTiaoJian(sql,objs);
	}
*/
	/**
	 * 查询总数
	 */
	@Override
	public int queryUserCounts() throws SysException {
		// TODO 自动生成的方法存根
		//通过count方法获取总行数并命名为count
		String sql = "select count(id) count from tuser";
		//将查询结果赋值集合
		List<Map<String, Object>> list = db.execQuery(sql,null);
		//获取count的值
		int userCounts = Integer.parseInt((String) list.get(0).get("count"));
		//返回总数
		return userCounts;
	}

	/**
	 * 根据性别查询用户
	 */
	@Override
	public List<UserBean> queryBySex(String sex,int start,int size) {
		// TODO Auto-generated method stub
		//sql语句
		String sql = "select u.*,d.deptname from tuser u left join dept d on u.dept=d.id order by u.sex desc limit ?,?";
		//加入参数
		Object [] obj = {sex,start,size};
		//返回查询结果
		return queryByTiaoJian(sql, obj);
		
	}

	/**
	 * 根据是否可用查询用户
	 */
	@Override
	public List<UserBean> queryByEnabled(int enabled,int start,int size) {
		// TODO Auto-generated method stub
		String sql = "select u.*,d.deptname from tuser u left join dept d on u.dept=d.id order by u.enabled desc limit ?,?";
		//加入参数
		Object [] obj = {enabled,start,size};
		//返回查询结果
		return queryByTiaoJian(sql, obj);
	}

}
