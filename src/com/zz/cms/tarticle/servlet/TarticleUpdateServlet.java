package com.zz.cms.tarticle.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zz.cms.exception.SysException;
import com.zz.cms.tarticle.bean.TarticleBean;
import com.zz.cms.tarticle.service.TarticleService;
import com.zz.cms.user.bean.UserBean;
import com.zz.cms.user.service.UserService;

public class TarticleUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//创建Tarticlebean对象
		TarticleBean tart = new TarticleBean();
		//从页面获取id并赋值给int类型的id
		int id = Integer.parseInt(req.getParameter("id"));
		//赋值给tart
		tart.setId(id);
		//赋值给tart
		tart.setTitle(req.getParameter("title"));
		//赋值给tart
		tart.setContent(req.getParameter("content"));
		//赋值给tart
		tart.setAuther(req.getParameter("auther"));
		//赋值给tart
		tart.setCtime(req.getParameter("ctime"));
		//赋值给tart
		tart.setChannel(Integer.parseInt(req.getParameter("channel").trim()));
		//赋值给tart
		tart.setIsremod(Integer.parseInt(req.getParameter("isremod")));
		//赋值给tart
		tart.setIshot(Integer.parseInt(req.getParameter("ishot")));
		//创建逻辑层对象
		TarticleService tas = new TarticleService();
		try {
			//修改信息
			tas.updateTart(tart);
			//转发
			req.getRequestDispatcher("tart.do").forward(req, resp);
			
		} catch (SysException e) {
			// 修改失败时
			req.setAttribute("id", id);
			req.setAttribute("msg", e.getErrMsg());
			//转发至原页面
			req.getRequestDispatcher("tartupdate.do").forward(req, resp);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
