package com.zz.cms.user.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zz.cms.exception.AppException;
import com.zz.cms.exception.BusinessException;
import com.zz.cms.exception.SysException;
import com.zz.cms.user.bean.UserBean;
import com.zz.cms.user.service.UserService;

public class UserAddServlet extends HttpServlet {
	/**
	 * 串行ID
	 */
	private static final long serialVersionUID = 3019328063624516373L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//新增用户，获取新增用户页面中表单的数据，将数据封装到bean包里
		//创建UserBean对象
		UserBean user = new UserBean();
		UserService us = new UserService();
		//获取页面信息给user赋值
		user.setLoginname(req.getParameter("loginname").trim());
		//获取页面信息给user赋值
		user.setPassword(req.getParameter("password"));
		//获取页面信息给user赋值
		user.setRealname(req.getParameter("realname"));
		//获取页面信息给user赋值
		if (req.getParameter("sex").equals("1")) {
			user.setSex("男");
		}else{
			user.setSex("女");
		}
		
		//获取页面信息给user赋值
		user.setBirthday(req.getParameter("birthday"));
		//获取页面信息给user赋值
		user.setEmail(req.getParameter("email").trim());
		//获取页面信息给user赋值
		user.setDept(Integer.parseInt(req.getParameter("dept")));
		//新增用户默认可用，所以为1
		user.setEnabled(1);
		//创建session
		HttpSession session = req.getSession();
		//从session中获取登录的用户信息
		UserBean users = (UserBean) session.getAttribute("userBean");
		//将登录用户的ID从users赋值进来
		List<UserBean> list = us.queryAll();
		//初始化用户ID
		int uid = 0;
		//遍历查询结果
		for (UserBean user1 : list) {
			//判断用户名是否存在
			if(users.getLoginname().equals(user1.getLoginname())) {
				//存在时赋值给uid
				uid=user1.getId();
			}
		}
		//当uid没有值时
		if(uid==0) {
			try {
				//抛异常
				throw new AppException(1005, "未获得用户信息！请检查用户设置！");
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			//否则将该id赋值给创建人
			user.setCreatman(uid);
		}
		
		//确认密码的数据
		String repwd = req.getParameter("repwd");
		//判断两次输入的密码是否一致
		if(!user.getPassword().equals(repwd)){
			//不一致时提示错误信息
			req.setAttribute("pwdMsg","两次输入的密码不一致，请重新输入！");
			//重定向至dept.do页面，如果重定向至当前页，之前添加的信息将被清空
			req.getRequestDispatcher("dept.do").forward(req,resp);
			//跳出
			return;
		}
		try {
			//添加用户后转发到用户查询页面
			if(us.insertUser(user)>0){
				req.getRequestDispatcher("userlist.do").forward(req, resp);
			}
		} catch (BusinessException e) {
			//将异常信息作为参数传至作用域
			req.setAttribute("msg", e.getErrMsg());
			//转发到部门显示servlet
			req.getRequestDispatcher("dept.do").forward(req,resp);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
