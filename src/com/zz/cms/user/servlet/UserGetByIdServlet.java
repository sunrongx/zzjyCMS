package com.zz.cms.user.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zz.cms.exception.SysException;
import com.zz.cms.user.bean.DeptBean;
import com.zz.cms.user.bean.UserBean;
import com.zz.cms.user.service.DeptService;
import com.zz.cms.user.service.UserService;

public class UserGetByIdServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5275101720493905304L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取要修改的用户的ID
		int id = Integer.parseInt(req.getParameter("id"));
		//创建逻辑层对象
		UserService us = new UserService();
		DeptService ds = new DeptService();
		try {
			//使用ID查询并将结果赋值给user
			UserBean user = us.queryUserById(id);
			//获取全部部门
			List<DeptBean> depts = ds.queryAll();
			//将对象存储到作用域
			req.setAttribute("user", user);
			req.setAttribute("depts", depts);
			//转发到修改页面
			req.getRequestDispatcher("user/update.jsp").forward(req, resp);
			
		} catch (SysException e) {
			//e.getErrMsg();
			//重定向到错误页面
			resp.sendRedirect("error.jsp");
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
