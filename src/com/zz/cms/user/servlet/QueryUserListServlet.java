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
	 * 串行ID
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
			//获取sex的值
			String gender = req.getParameter("gender");
			//获取enabled的值
			
			String enabled = req.getParameter("enabled");
			
			// 获取当前的页数
			String p = req.getParameter("currentPage");
			//初始化页数
			int page = 1;	
			try {
				//获取的页数赋值给页数
				page = Integer.parseInt(p);
				//判断页数大于总页数时
				if(page>pageCount) {
					//重赋值1
					page=1;
				}
			}catch(Exception e){
				//输入乱七八糟的东西时重赋值1
				page = 1;
			}
			
			//模糊查询搜索没有值时，默认为空字符串
			if(name==null||name.equals(" ")){
				//没输入默认查询所有
				name="";
			}
			if(gender==null||gender.equals(" ")){
				//没输入默认查询所有
				gender="";
			}
			if(enabled==null||enabled.equals(" ")){
				//没输入默认查询所有
				enabled="";
			}
			//分页查询并赋值集合
			List<UserBean> users = us.queryUserByPage(name,gender,enabled,page,size);
//			req.setAttribute("page", page);
			//将参数塞进作用域
			req.setAttribute("p", p);
			req.setAttribute("currentPage", page);
			req.setAttribute("pageCount", pageCount);
			req.setAttribute("name", name);	
			req.setAttribute("gender", gender);	
			req.setAttribute("enabled", enabled);	
			
			//将用户集合存储到req作用域中
			req.setAttribute("users", users);
			//转发到用户查询页面，在查询页面显示数据
			req.getRequestDispatcher("user/list.jsp").forward(req,resp);
		} catch (SysException e) {
			// TODO Auto-generated catch block
			//重定向到错误页面
			resp.sendRedirect("error.html");
		}
		
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}



