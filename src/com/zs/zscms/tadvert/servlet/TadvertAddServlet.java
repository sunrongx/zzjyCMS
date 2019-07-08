package com.zs.zscms.tadvert.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zs.zscms.tadvert.bean.TadvertBean;
import com.zs.zscms.tadvert.service.TadvertService;
import com.zs.zscms.user.bean.UserBean;
import com.zs.zscms.user.service.UserService;
import com.zz.cms.exception.BusinessException;

public class TadvertAddServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//新增广告，获取新增广告页面中表单的数据，将数据封装到bean包里
		//创建TadvertBean对象
		TadvertBean tadv = new TadvertBean();
		//获取页面信息给tadv赋值
		tadv.setTitle(req.getParameter("title"));
		//获取页面信息给tadv赋值
		tadv.setContent(req.getParameter("content"));
		//获取页面信息给tadv赋值
		tadv.setCrtime(req.getParameter("crtime"));
		//获取广告的创建人
		tadv.setCrman(req.getParameter("crman"));
/*		//创建session
		HttpSession session = req.getSession();
		
		//从session中获取广告信息
		TadvertBean tadvs = (TadvertBean) session.getAttribute("tadvbean");*/
		//创建service层对象
		TadvertService ts = new TadvertService();
		try {
			//添加广告后转发到广告查询页面
			if(ts.insertTadvert(tadv)>0){
				req.getRequestDispatcher("tadv.do").forward(req, resp);
			}
		} catch (BusinessException e) {
			//提示错误信息
			req.setAttribute("msg", e.getErrMsg());
			//转发至原页面
			req.getRequestDispatcher("tadv.do").forward(req,resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
