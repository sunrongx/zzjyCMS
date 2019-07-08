package com.zs.zscms.user.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zs.zscms.user.bean.UserBean;
import com.zs.zscms.user.service.UserService;
import com.zs.zscms.util.DBUtil;
import com.zz.cms.exception.BusinessException;
import com.zz.cms.exception.SysException;

public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6791644530059615253L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//创建逻辑层对象
		UserService us = new UserService();
		//实现页面登录功能，获取账号密码
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		//创建userbean对象
		UserBean user = new UserBean();
		user.setLoginname(username);
		user.setPassword(password);
		
		//获取是否记住密码的属性值
		String rmbPwd=req.getParameter("rmbPwd");
		//判断是否记住密码
		if (rmbPwd!=null&&rmbPwd.equals("1")) {
			//记住密码，利用cookie
			//创建cookie，保存用户名和密码
			Cookie cookie1 = new Cookie("username",username);
			Cookie cookie2 = new Cookie("password",password);
			//设置cookie时长（秒）
			cookie1.setMaxAge(600);
			cookie2.setMaxAge(600);
			
			//响应给客户端
			resp.addCookie(cookie1);
			resp.addCookie(cookie2);
		} 
		UserBean userBean = new UserBean();
		try {
			userBean = us.userLogin(user);
			//排空，当userBean不为空时：
			if(userBean!=null){
				//创建session
				HttpSession session = req.getSession();
				//将用户信息中的真实姓名取出并赋值给session
				session.setAttribute("userBean", userBean);
				
			}
			
			//转发主页面
			req.getRequestDispatcher("main.jsp").forward(req, resp);
			return;
		} catch (SysException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (BusinessException e) {
			req.setAttribute("msg", e.getErrMsg());
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			return;
		}
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
