package com.zz.cms.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zz.cms.exception.SysException;
import com.zz.cms.user.bean.UserBean;
import com.zz.cms.user.service.UserService;

public class UserUpdateServlet extends HttpServlet {
	/**
	 * 串行ID
	 */
	private static final long serialVersionUID = 4840656634448342529L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//创建userbean对象
		UserBean user = new UserBean();
		//从页面获取id并赋值给int类型的id
		int id = Integer.parseInt(req.getParameter("id"));
		//赋值给user
		user.setId(id);
		//赋值给user用户名
		user.setLoginname(req.getParameter("loginname").trim());
		//赋值给user密码
		user.setPassword(req.getParameter("password"));
		//赋值给user真实姓名
		user.setRealname(req.getParameter("realname"));
		//赋值给user性别
		if (req.getParameter("sex").equals("1")) {
			user.setSex("男");
		}else{
			user.setSex("女");
		}
		//赋值给user生日
		user.setBirthday(req.getParameter("birthday"));
		//赋值给user部门
		user.setDept(Integer.parseInt(req.getParameter("dept")));
		//赋值给user邮箱
		user.setEmail(req.getParameter("email").trim());
		//赋值给user是否可用
		user.setEnabled(Integer.parseInt(req.getParameter("enabled")));
		//创建逻辑层对象
		UserService us = new UserService();
		try {
			//修改信息
			us.updateUser(user);
			//转发至显示页面
			req.getRequestDispatcher("userlist.do").forward(req, resp);
			
		} catch (SysException e) {
			// 修改失败时回带数据和异常信息
			req.setAttribute("id", id);
			req.setAttribute("msg", e.getErrMsg());
			//转发至原页面
			req.getRequestDispatcher("userget.do").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
