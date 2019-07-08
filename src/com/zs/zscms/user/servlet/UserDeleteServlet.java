package com.zs.zscms.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zs.zscms.user.service.UserService;
import com.zz.cms.exception.SysException;

public class UserDeleteServlet extends HttpServlet {
	//删除用户信息
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取页面的ID
		String id=req.getParameter("id");
		//创建逻辑层对象
		UserService us = new UserService();
		try {
			//调用删除的方法
			us.deleteUser(Integer.parseInt(id));
			//重定向
			resp.sendRedirect("userlist.do");
			
			
		} catch (NumberFormatException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SysException e) {
			// TODO 自动生成的 catch 块
			e.getErrMsg();
			//重定向
			resp.sendRedirect("userlist.do");
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
