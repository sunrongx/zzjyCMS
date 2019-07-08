package com.zs.zscms.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zs.zscms.user.bean.UserBean;
import com.zs.zscms.user.service.UserService;
import com.zz.cms.exception.BusinessException;
import com.zz.cms.exception.SysException;

public class UserAddServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3019328063624516373L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//新增用户，获取新增用户页面中表单的数据，将数据封装到bean包里
		//创建UserBean对象
		UserBean user = new UserBean();
		//获取页面信息给user赋值
		user.setLoginname(req.getParameter("loginname"));
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
		user.setEmail(req.getParameter("email"));
		//获取页面信息给user赋值
		user.setDept(Integer.parseInt(req.getParameter("dept")));
		//新增用户默认可用，所以为1
		user.setEnabled(1);
		//创建session
		HttpSession session = req.getSession();
		//从session中获取登录的用户信息
		UserBean users = (UserBean) session.getAttribute("userBean");
		//将登录用户的ID从users赋值进来
		user.setCreatman(users.getId());
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
		//创建service层对象
		UserService us = new UserService();
		try {
			//添加用户后转发到用户查询页面
			if(us.insertUser(user)>0){
				req.getRequestDispatcher("userlist.do").forward(req, resp);
			}
		} catch (BusinessException e) {
			req.setAttribute("msg", e.getErrMsg());
			req.getRequestDispatcher("dept.do").forward(req,resp);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
