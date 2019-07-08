package com.zs.zscms.tarticle.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zs.zscms.tarticle.bean.TarticleBean;
import com.zs.zscms.tarticle.service.TarticleService;
import com.zs.zscms.user.bean.UserBean;
import com.zs.zscms.user.service.UserService;
import com.zz.cms.exception.BusinessException;

public class TarticleAddServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 新增文章，获取新增文章页面中表单的数据，将数据封装到bean包里
		// 创建TarticleBean对象
		TarticleBean tart = new TarticleBean();
		// 获取页面信息给tart赋值
		tart.setTitle(req.getParameter("title"));
		// 获取页面信息给tart赋值
		tart.setContent(req.getParameter("content"));
		// 获取页面信息给tart赋值
		tart.setAuther(req.getParameter("auther"));
		// 获取页面信息给tart赋值
		tart.setCrtime(req.getParameter("crtime"));
		// 获取页面信息给tart赋值
		tart.setChannel(Integer.parseInt(req.getParameter("channel")));
		// 获取页面信息给tart赋值
		tart.setIsremod(Integer.parseInt(req.getParameter("isremod")));
		// 获取页面信息给tart赋值
		tart.setIshot(Integer.parseInt(req.getParameter("ishot")));
		

		/*
		 * //创建session HttpSession session = req.getSession(); //从session中获取文章信息
		 * TarticleBean tarts = (TarticleBean)
		 * session.getAttribute("tarticleBean");
		 */

		// 创建service层对象
		TarticleService ts = new TarticleService();
		try {
			// 添加文章后转发到文章查询页面
			int i = ts.insertTart(tart);
			if (i > 0) {
				req.getRequestDispatcher("tart.do").forward(req, resp);
			}
		} catch (BusinessException e) {
			req.setAttribute("msg", e.getErrMsg());
			req.getRequestDispatcher("tartadd.do").forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
