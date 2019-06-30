package com.zs.zscms.tarticle.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zs.zscms.tarticle.bean.TarticleBean;
import com.zs.zscms.tarticle.service.TarticleService;
import com.zs.zscms.user.bean.UserBean;
import com.zs.zscms.user.service.UserService;

public class QueryTarticleServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//创建逻辑层对象
		TarticleService tas = new TarticleService();
		List<TarticleBean> tarts = tas.queryAll();
		//将文章集合存储到req作用域中
		req.setAttribute("tarts", tarts);
		//转发到文章查询页面，在查询页面显示数据
		req.getRequestDispatcher("tart/list.jsp").forward(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
