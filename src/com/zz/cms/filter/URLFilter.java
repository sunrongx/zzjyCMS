package com.zz.cms.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zz.cms.user.bean.UserBean;

public class URLFilter implements javax.servlet.Filter {

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 拦截URL策略：
	 * 		1、对servlet进行拦截，对自己编写的servlet加标识，所以编写url-pattern:xxx.do，除了login.do
	 * 		2、对JSP进行拦截，除了login.jsp外，其余JSP都需要拦截
	 * 		3、对HTML进行拦截，除了login.html外，其余html都需要拦截
	 * 拦截策略：
	 * 		对需要拦截的URL判断是否登陆，如果登陆则放行，没有登陆则拦截
	 * 		session中如果有数据，则判断已经登陆了，负责进行拦截
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		//获取URL的方法，只会获取服务器端口号之后的内容
		String url = req.getServletPath();
		//判断是否包含.do
		if(url.contains(".do")&&!url.contains("login.do")) {
			//获取session
			HttpSession session = req.getSession();
			//获取session中在登录时塞进去的用户信息
			UserBean user = (UserBean) session.getAttribute("userBean");
			//判断userbean是否有值，如果没有则没登录
			if(user==null) {
				//进行拦截
				resp.sendRedirect("login.jsp");
				return;
			}
			
		}
		
		if(url.contains(".jsp")&&!url.contains("login.jsp")) {
			//获取session
			HttpSession session = req.getSession();
			//获取session中在登录时塞进去的用户信息
			UserBean user = (UserBean) session.getAttribute("userBean");
			//判断userbean是否有值，如果没有则没登录
			if(user==null) {
				//进行拦截
				resp.sendRedirect(req.getContextPath()+"/login.jsp");
				return;
			}
			
		}
		
		if(url.contains(".html")&&!url.contains("login.html")) {
			//获取session
			HttpSession session = req.getSession();
			//获取session中在登录时塞进去的用户信息
			UserBean user = (UserBean) session.getAttribute("userBean");
			//判断userbean是否有值，如果没有则没登录
			if(user==null) {
				//进行拦截
				resp.sendRedirect(req.getContextPath()+"/login.jsp");
				return;
			}
			
		}
		
		//放行
		chain.doFilter(req, resp);
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
