package com.zz.cms.user.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zz.cms.exception.SysException;
import com.zz.cms.user.bean.UserBean;
import com.zz.cms.user.service.UserService;

public class QueryUserListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3972072029746230407L;
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserService us = new UserService();
		

//		int page = Integer.parseInt(req.getParameter("page"));
		//获取查询到的分页数据
		try {
			//设置每页显示的条数
			int size = 5;
			//赋值总页数给pageCount
			int pageCount = us.queryPageCounts(size);
			
			//获取当前页数
			// 获取name的值
			String name=req.getParameter("name");
			// 获取当前的页数
			String p = req.getParameter("currentPage");
			int page = 1;	
			try {
				page = Integer.parseInt(p);
				if(page>pageCount) {
					page=1;
				}
			}catch(Exception e){
				page = 1;
			}
			
			//模糊查询搜索没有值时，默认为空字符串
			if(name==null||name.equals(" ")){
				name="";
			}
			List<UserBean> users = us.queryUserByPage(name,page,size);
			//获取总页数
//			req.setAttribute("page", page);
			req.setAttribute("p", p);
			req.setAttribute("currentPage", page);
			req.setAttribute("pageCount", pageCount);
			req.setAttribute("name", name);	
			
			//将用户集合存储到req作用域中
			req.setAttribute("users", users);
			//转发到用户查询页面，在查询页面显示数据
			req.getRequestDispatcher("user/list.jsp").forward(req,resp);
		} catch (SysException e) {
			// TODO Auto-generated catch block
			resp.sendRedirect("error.html");
		}
		
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}



