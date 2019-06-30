package com.zs.zscms.tadvert.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zs.zscms.exception.SysException;
import com.zs.zscms.tadvert.bean.TadvertBean;
import com.zs.zscms.tadvert.service.TadvertService;
import com.zs.zscms.user.bean.UserBean;
import com.zs.zscms.user.service.UserService;

public class TadvertUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//创建tadvbean对象
		TadvertBean tadv = new TadvertBean();
		//从页面获取id并赋值给int类型的id
		int id = Integer.parseInt(req.getParameter("id"));
		//赋值给tadv
		tadv.setId(id);
		//赋值给tadv
		tadv.setTitle(req.getParameter("title"));
		//赋值给tadv
		tadv.setContent(req.getParameter("content"));
		//赋值给tadv
		tadv.setCrtime(req.getParameter("crtime"));
		//赋值给tadv
		tadv.setCrman(req.getParameter("crman"));
		
		//创建逻辑层对象
		TadvertService ts = new TadvertService();
		try {
			//修改信息
			ts.updateTadv(tadv);
			//转发
			req.getRequestDispatcher("tadv.do").forward(req, resp);
			
		} catch (SysException e) {
			// 修改失败时
			req.setAttribute("id", id);
			
			req.setAttribute("msg", e.getErrMsg());
			//转发至原页面
			req.getRequestDispatcher("tadvget.do").forward(req, resp);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
