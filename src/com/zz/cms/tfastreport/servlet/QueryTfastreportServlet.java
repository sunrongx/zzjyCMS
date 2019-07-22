package com.zz.cms.tfastreport.servlet;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zz.cms.tarticle.bean.TarticleBean;
import com.zz.cms.tfastreport.bean.TfastreportBean;
import com.zz.cms.tfastreport.service.TfastreportService;


public class QueryTfastreportServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//创建逻辑层对象
		TfastreportService ts = new TfastreportService();
		int size = 5;
		// 获取name的值
		String name = req.getParameter("name");
		// 赋值总页数给pageCount
		int pageCount = ts.queryPageCounts(size);
		// 获取当前的页数
		String p = req.getParameter("currentPage");
		int page = 1;
		try {
			page = Integer.parseInt(p);
			if (page > pageCount) {
				page = 1;
			}
		} catch (Exception e) {
			page = 1;
		}
		
		// 模糊查询搜索没有值时，默认为空字符串
		if (name == null || name.equals(" ")) {
			name = "";
		}
		List<TfastreportBean> tfas = ts.queryByPage(name, page, size);
		// 获取总页数
		// req.setAttribute("page", page);
		req.setAttribute("p", p);
		req.setAttribute("currentPage", page);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("name", name);
		
		//List<TfastreportBean> tfass = ts.queryAll();
		//将广告集合存储到req作用域中
		req.setAttribute("tfass", tfas);
		//转发到广告查询页面，在查询页面显示数据
		req.getRequestDispatcher("tfas/list.jsp").forward(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
