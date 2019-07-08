package com.zs.zscms.user.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zs.zscms.user.bean.UserBean;
import com.zs.zscms.user.service.UserService;
import com.zz.cms.exception.SysException;

public class QueryUserListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3972072029746230407L;
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//定义每页查询数量
		int size=1;
		//获取当前页数
		int page=Integer.parseInt(req.getParameter("currentPage"));
		
		
		//创建逻辑层对象
		UserService us = new UserService();
		//分页查询
		try {
			List<UserBean> users1 = us.queryUserByPage(page,size);
		} catch (SysException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<UserBean> users = us.queryAll();
		//将用户集合存储到req作用域中
		req.setAttribute("users", users);
		//转发到用户查询页面，在查询页面显示数据
		req.getRequestDispatcher("user/list.jsp").forward(req,resp);
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
