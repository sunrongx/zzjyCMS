package com.zz.cms.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zz.cms.exception.SysException;
import com.zz.cms.user.bean.UserBean;
import com.zz.cms.user.service.UserService;

public class ChkServlet extends HttpServlet {
	/**
	 * 串行ID
	 */
	private static final long serialVersionUID = -5930173266732764892L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取type参数
		String type = req.getParameter("type");
		//逻辑层对象
		UserService us = new UserService();
		//bean对象
		UserBean user = new UserBean();
		switch (type) {
		case "1":
			//获取loginname参数
			String loginname = req.getParameter("loginname");
			//通过loginname查询并赋值结果
			user = us.queryByLoginname(loginname);
			//将信息响应给ajax
			if(user==null) {
				//没查到时，回应页面结果
				PrintWriter pw = resp.getWriter();
				pw.write("true");
				pw.flush();
				pw.close();
			}else {
				//查到即重复，回应页面结果
				PrintWriter pw = resp.getWriter();
				pw.write("false");
				pw.flush();
				pw.close();
			}
			break;
		case "2":
			
			try {
				//获取loginname参数
				String email = req.getParameter("email");
				//通过loginname查询并赋值结果
				user = us.queryByEmail(email);
				//将信息响应给ajax
				if(user==null) {
					//没查到时，回应页面结果
					PrintWriter pw = resp.getWriter();
					pw.write("true");
					pw.flush();
					pw.close();
				}else {
					//查到即重复，回应页面结果
					PrintWriter pw = resp.getWriter();
					pw.write("false");
					pw.flush();
					pw.close();
				}
			} catch (SysException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		default:
			
			break;
		}
		
		
	}
}
