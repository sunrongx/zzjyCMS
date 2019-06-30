package com.zs.zscms.user.service;

import java.util.List;

import com.zs.zscms.exception.BusinessException;
import com.zs.zscms.exception.SysException;
import com.zs.zscms.user.bean.UserBean;
import com.zs.zscms.user.dao.impl.UserDao;
import com.zs.zscms.user.dao.impl.UserDaoImpl;

public class UserService {
	//采用多态形式创建UserDao对象
	UserDao ud = new UserDaoImpl();
	//判断是否有对应的用户信息，有返回信息，没有返回null
	public UserBean userLogin(UserBean user) throws SysException, BusinessException {
		//将UserDaoImpl中login方法的user集合赋值给users集合
		List<UserBean> users = ud.login(user);
		//判空
		if(users==null || users.size()==0){
			//输入商业异常信息
			throw new BusinessException("该账号信息有误，请重新输入！", 400);
		}
		//返回用户信息集合中的第一个值
		return users.get(0);
	}
	
	//创建查询所有用户信息的方法，否则从servlet直接调用	UserDaoImpl的方法的话，不符合MVC思想
	public List<UserBean> queryAll(){
		return ud.queryAll();
	}
	
	//新增用户
	public int insertUser(UserBean user) throws BusinessException{
		//从新增的用户信息集合中查询用户名并将查询结果赋值给集合
		List<UserBean> users1 = ud.queryByLoginname(user.getLoginname());
		//判断用户名是否重复，如果查询结果不为空且有值，说明用户名重复，抛出商业异常
		if(users1!=null&&users1.size()!=0){
			//将异常信息放到页面上，不重复执行结果，1成功，0失败
			throw new BusinessException("该用户名已存在，请重新输入！", 101);
		}
		//从新增的用户信息集合中查询邮箱并将查询结果赋值给集合
		List<UserBean> users2;
		try {
			users2 = ud.queryByEmail(user.getEmail());
			if(users2!=null&&users2.size()!=0){
				//将异常信息放到页面上，不重复执行结果，1成功，0失败
				throw new BusinessException("该邮箱已存在，请重新输入！", 102);
			}
		} catch (SysException e) {
			
		}
		//判断邮箱是否重复，如果查询结果不为空且有值，说明邮箱重复，抛出商业异常
		
		//都通过时说明全都不重复，可以新增
		return ud.insertUser(user);
	}
	
	//根据ID查询用户信息
	public UserBean queryUserById(int id) throws SysException{
		//将以id为条件的查询结果赋值给集合
		List<UserBean> users = ud.queryUserById(id);
		//返回第一条（唯一一条）信息
		return users.get(0);
	}
	
	//修改用户信息
	public int updateUser(UserBean user) throws SysException{
		//将修改信息的结果赋值给user1
		int user1 = ud.updateUser(user);
		//返回user1
		return user1;
		
	}
	
	//删除用户信息
	public int deleteUser(int id) throws SysException{
		int user= ud.deleteUser(id);
		
		return user;
		
	}
	
	//page表示当前查询页，size表示每页查询数量，返回所有用户信息的集合，并抛出防止数据库问题的异常
	public List<UserBean> queryUserByPage(int page,int size) throws SysException{
		//根据当前页数，判断应该从哪条开始循环
		int start = (page-1)*size;
		return ud.queryByPage(start, size);
		
	}
	
	//查询总页数的方法
	public int queryPageCounts(int size) throws SysException{
		//把用户记录条数转换为总页数
		int userCounts = ud.queryUserCounts();
		if(userCounts%size==0){
			//返回总数能整除每页数量的页数
			return userCounts/size;
		}
		//返回总数无法整除每页数量的页数
		return userCounts/size+1;
		
	}
	
}
