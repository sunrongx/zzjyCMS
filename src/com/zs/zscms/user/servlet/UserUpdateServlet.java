package com.zs.zscms.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zs.zscms.exception.SysException;
import com.zs.zscms.user.bean.UserBean;
import com.zs.zscms.user.service.UserService;

public class UserUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//创建userbean对象
		UserBean user = new UserBean();
		//从页面获取id并赋值给int类型的id
		int id = Integer.parseInt(req.getParameter("id"));
		//赋值给user
		user.setId(id);
		//赋值给user
		user.setLoginname(req.getParameter("loginname"));
		//赋值给user
		user.setRealname(req.getParameter("realname"));
		//赋值给user
		if (req.getParameter("sex").equals("1")) {
			user.setSex("男");
		}else{
			user.setSex("女");
		}
		//赋值给user
		user.setBirthday(req.getParameter("birthday"));
		//赋值给user
		user.setDept(Integer.parseInt(req.getParameter("dept")));
		//赋值给user
		user.setEmail(req.getParameter("email"));
		//赋值给user
		user.setEnabled(Integer.parseInt(req.getParameter("enabled")));
		//创建逻辑层对象
		UserService us = new UserService();
		try {
			//修改信息
			us.updateUser(user);
			//转发
			req.getRequestDispatcher("userlist.do").forward(req, resp);
			
		} catch (SysException e) {
			// 修改失败时
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
