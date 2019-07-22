package com.zz.cms.user.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zz.cms.exception.SysException;
import com.zz.cms.util.DBUtil;


public class LoginServlet2 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String loginname = req.getParameter("loginname");
		String password = req.getParameter("password");
		DBUtil db = new DBUtil();
		String sql = "select * from tuser where loginname=? and password=?;";
		Object [] obj = {loginname,password};
		List<Map<String , Object>> list = db.execQuery(sql, obj);
		if(list!=null&&list.size()!=0) {
			req.getRequestDispatcher("main.jsp").forward(req, resp);
		}else {
			resp.sendRedirect("login.do");
		}
	}
	
}
