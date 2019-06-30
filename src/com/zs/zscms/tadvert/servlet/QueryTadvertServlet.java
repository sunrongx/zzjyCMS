package com.zs.zscms.tadvert.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zs.zscms.tadvert.bean.TadvertBean;
import com.zs.zscms.tadvert.service.TadvertService;
import com.zs.zscms.user.bean.UserBean;
import com.zs.zscms.user.service.UserService;

public class QueryTadvertServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//创建逻辑层对象
		TadvertService ts = new TadvertService();
		List<TadvertBean> tadvs = ts.queryAll();
		//将广告集合存储到req作用域中
		req.setAttribute("tadvs", tadvs);
		//转发到广告查询页面，在查询页面显示数据
		req.getRequestDispatcher("tadv/list.jsp").forward(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
