package com.zz.cms.user.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.code.kaptcha.Constants;
import com.zz.cms.exception.BusinessException;
import com.zz.cms.exception.SysException;
import com.zz.cms.user.bean.UserBean;
import com.zz.cms.user.service.UserService;

public class LoginServlet extends HttpServlet {
	/**
	 * 串行ID
	 */
	private static final long serialVersionUID = -6791644530059615253L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		String code = req.getParameter("code");
		 String ocode =  (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
         if (ocode!=null&&ocode!=""&&!ocode.equalsIgnoreCase(code)) {
			req.setAttribute("MSG","验证码输入有误");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			return;	
         }

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
		String rmb=req.getParameter("choose");
		//判断是否记住密码
		if (rmb!=null&&rmb.equals("1")) {
			//记住密码，利用cookie
			//创建cookie，保存用户名和密码
			Cookie cookie1 = new Cookie("username",username);
			Cookie cookie2 = new Cookie("password",password);
			//设置cookie时长（秒）
			cookie1.setMaxAge(60*60);
			cookie2.setMaxAge(60*60);
			
			//响应给客户端
			resp.addCookie(cookie1);
			resp.addCookie(cookie2);
		} 
		//创建UserBean对象
		UserBean userBean = new UserBean();
		try {
			//验证登陆的结果赋值UserBean
			userBean = us.userLogin(user);
			//排空，当userBean不为空时：
			/*if(userBean!=null){
				//创建session
				
				//将用户信息中的真实姓名取出并赋值给session
				session.setAttribute("userBean", userBean);
				session.setMaxInactiveInterval(60*60);
			}*/
			if(userBean.getEnabled()==1){
				//创建session
				
				//将用户信息中的真实姓名取出并赋值给session
				session.setAttribute("userBean", userBean);
				//将session过期设置为一小时后
				session.setMaxInactiveInterval(60*60);
			}else if(userBean.getEnabled()==2){
				req.setAttribute("msg1","该账号已冻结，无法登陆，请联系管理员");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
				return;
			}else {
				req.setAttribute("msg2","账号或密码错误，请重新输入");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
				return;
			}
			
			//转发主页面
			req.getRequestDispatcher("main.jsp").forward(req, resp);
			return;
		} catch (SysException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (BusinessException e) {
			//req.setAttribute("msg", e.getErrMsg());
			req.setAttribute("msg","账号或密码错误，请重新输入" );
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
