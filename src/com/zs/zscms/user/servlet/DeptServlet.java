package com.zs.zscms.user.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zs.zscms.user.bean.DeptBean;
import com.zs.zscms.user.service.DeptService;

public class DeptServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//创建逻辑层对象
		DeptService ds = new DeptService();
		//将查询结果赋值给部门信息集合
		List<DeptBean> depts = ds.queryAll();
		//将信息放入req对象中，转发到add.jsp中
		req.setAttribute("depts",depts);
		//转发至网页
		req.getRequestDispatcher("user/add.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
